#!/usr/bin/perl

=head1 NAME

osv-bms-data-driver.pl - This script is used to get the data from the OSV's BMS,
through serial port, and write it to temporary files, as a driver would do.

=head1 COPYRIGHT

Copyright 2016 Institut Mines-Télécom

osv-bms-data-driver.pl is free software: you can redistribute it and/or modify it under
the terms of the GNU General Public License as published by the Free
Software Foundation, either version 3 of the License, or (at your option)
any later version.

osv-bms-data-driver.pl is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with osv-bms-data-driver.pl. If not, see <http://www.gnu.org/licenses/>.

=head1 VERSIONS

Version 0.1.

Initial version, all paths and file names are hardcoded.

=head1 AUTHORS

Guillaume Le Gall <guillaume.legall@telecom-bretagne.eu>

=head1 SEE ALSO

BMS protocol documentation - <https://github.com/telecombretagne/osv/blob/master/bms_reverse/bms_reverse.html>

=cut

use strict;
use warnings;

use Device::SerialPort qw( :PARAM :STAT 0.07 );
use File::Path qw(make_path);
use Switch;

my $pathToBmsFiles           = "/tmp/osv-bms";
my $currentMultiplyingFactor = 3;

# Open port
my $port = Device::SerialPort->new( "/dev/ttyACM0", 0 )
  or die "Could not open serial port: " . $!;

# Create path to temp files
if ( !-d $pathToBmsFiles ) {
	make_path($pathToBmsFiles) or die "Could not create directory \"" . $pathToBmsFiles . "\".";
}

# Configuration
$port->parity("none");
$port->databits(8);
$port->stopbits(1);
$port->baudrate(9600);
$port->read_char_time(10);

sleep 1;

# Catch sig int and close port before exiting.
$SIG{INT} = sub {
	$port->write("00\n");
	$port->close() or warn "Failed to close port: " . $!;
	undef $port;    # Frees object memory.
};

$port->write("01") or die "Initialization sequence: write failed.";

my ( $count_in, $string_in );
my ( $soc, @cellVoltage, @temperature, $current, $pwmToCharger,
	$pwmToEngineController );

my ( $soc_old, @cellVoltage_old, @temperature_old, $current_old, $pwmToCharger_old,
	$pwmToEngineController_old );

# Global variables initialization
@cellVoltage = ();
@temperature = ();
@cellVoltage_old = ();
@temperature_old = ();

$soc = 0;
$soc_old = 0;
$current = 0;
$current_old = 0;
$pwmToCharger = 0;
$pwmToCharger_old = 0;
$pwmToEngineController = 0;
$pwmToEngineController_old = 0;

for(my $i = 0 ; $i < 4 ; $i++) {
	$temperature[$i] = -1;
	$temperature_old[$i] = -1;
}

for(my $i = 0 ; $i < 24 ; $i++) {
	$cellVoltage[$i] = 0;
	$cellVoltage_old[$i] = 0;
}

my $str = "";

my ( $key, $value );

# Main loop
while (1) {
	( $count_in, $string_in ) = $port->read(1);

	# TODO probaby not the optimal way to wait for new char.
	next if ( !$count_in );
	warn "Could not read" unless $count_in;

	if ( $string_in ne "\n" ) {
		$str .= $string_in;
	}
	else {
		if ( $str =~ /^(\d\d)(\d{3})/ ) {
			$key   = $1 + 0;
			$value = $2 + 0;
			switch ($key) {
				case 94 {
					if ( $value < 200 ) {
						$soc = $value;
					}
					elsif ( $value >= 300 ) {
						$pwmToCharger = $value - 300;
					}

					#TODO deal with boolean values: 2xx
				}
				case [ 1 .. 24 ] {
					$cellVoltage[ $key - 1 ] = $value / 200;
				}
				case 74 {
					if ( $value =~ /(\d)(\d\d)/ ) {
						$temperature[$1] = $2 + 0;
					} elsif( $value =~ /(\d\d)/ ) {
						$temperature[0] = $1 + 0;
					}
				}
				case 84 {
					$current = ( $value - 400 ) / $currentMultiplyingFactor;
				}
				case 99 {
					$pwmToEngineController = $value;
				}
			}
		}

		$str = "";
		
		&updateValues();
	}
}


sub updateValues {
	
	# Soc
	if($soc != $soc_old) {
		&writeToFile("soc", $soc);
		$soc_old = $soc;
	}
	
	# @cellVoltage
	for(my $i = 0 ; $i < 24 ; $i++ ) {
		if($cellVoltage[$i] != $cellVoltage_old[$i]) {
			&writeToFile("cell_" . ($i < 10 ? "0" : "") . $i . "_voltage", $cellVoltage[$i]);
			$cellVoltage_old[$i] = $cellVoltage[$i];
		}
	}
	
	# @temperature
	for(my $i = 0 ; $i < 4 ; $i++ ) {
		if($temperature[$i] != $temperature_old[$i]) {
			&writeToFile("temperature_" . $i , $temperature[$i]);
			$temperature_old[$i] = $temperature[$i];
		}
	}
	
	# $current
	if($current != $current_old) {
		&writeToFile("current", $current);
		$current_old = $current;
	}
	
	# $pwmToCharger
	if($pwmToCharger != $pwmToCharger_old) {
		&writeToFile("pwm_to_charger", $pwmToCharger);
		$pwmToCharger_old = $pwmToCharger;
	}
	
	# $pwmToEngineController
	if($pwmToEngineController != $pwmToEngineController_old) {
		&writeToFile("pwm_to_engine_controller", $pwmToEngineController);
		$pwmToEngineController_old = $pwmToEngineController;
	}
}

# @param File name.
# @param Content to be written.
sub writeToFile {
	open(FILE, ">" . $pathToBmsFiles . "/" . $_[0]) or warn "Could not open " . $_[0] . ": " . $!;
	print FILE $_[1] . "\n";
	close(FILE);
}

# Note: this is never used.
sub readPortStatus {
	my ( $blockingflags, $inbytes, $outbytes, $errflags ) = $port->status
	  or warn "could not get port status\n";

	print "blockingflags= " . $blockingflags . "\n";
	print "inbytes= " . $inbytes . "\n";
	print "outbytes= " . $outbytes . "\n";
	print "errflags= " . $errflags . "\n";

	die "Too many inbytes, wrong mode." if ( $inbytes > 1000 );
}
