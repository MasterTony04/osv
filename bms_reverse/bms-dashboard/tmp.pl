#!/usr/bin/perl

for(my $i = 1 ; $i < 25 ; $i++) {
	print "bv += mw.centerPanel.cellVoltagePanel.getData(DataPanel.CELL_" . ($i < 10 ? "0" : "" ) . $i .  "_VOLTAGE);\n";
}

