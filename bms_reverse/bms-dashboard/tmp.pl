#!/usr/bin/perl

use strict;
use warnings;

for (my $i = 1 ; $i < 24 ; $i++) {
	my $s = ($i < 10 ? "0" : "") . $i;
	print 'public static final String CELL_' . $s . '_VOLTAGE = "Cell ' . $s . ' voltage (V): ";' . "\n";
}

