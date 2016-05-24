#!/usr/bin/perl

use strict;
use warnings;

for (my $i = 1 ; $i < 25 ; $i++) {
	my $s = ($i < 10 ? "0" : "") . $i;
	print 'public static final String CELL_' . $s . '_VOLTAGE = "Cell ' . $s . ' voltage (V): ";' . "\n";
}


print "\n\n";

my $k = 0;

for (my $j = 1 ; $j < 9 ; $j++) {
	for (my $i = 0 ; ; $i = $i + 8) {
		$k = $i + $j;
		last if($k > 24);
		my $s = ($k < 10 ? "0" : "") . $k;
		print "cellVoltagePanel.setData(DataPanel.CELL_" . $s . "_VOLTAGE, 0);\n";
	}
}

