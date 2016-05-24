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

print "\n\n";

opendir(DIR, "/home/guillaumelg/osv-bms");
my @files = readdir(DIR);
closedir(DIR);

@files = sort { lc($a) cmp lc($b) } @files;

for (my $i = 0 ; $i < @files ; $i++) {
	print "public static final String " . uc($files[$i]) . " = \"" . $files[$i] . "\";\n";
}

foreach (@files) {
	print "files.add(" . uc($_) . ");\n";
}

print "\n\n";

foreach (@files) {
	print "if(s.equals(" . uc($_) . ")) {\n\tmw.CENTER_OR_EASTPanel.PANEL_NAME.setData(DataPanel." . uc($_) . ", Float.parseFloat(readFirstLineInFile(path)));\n}\n";
}

