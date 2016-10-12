/*
 * Main.cpp
 *
 *  Created on: 11 oct. 2016
 *      Author: guillaumelg
 *
 * Copyright 2016 Institut Mines-Télécom
 *
 * This file is part of osv_engine_controller_data_reader.
 *
 * osv_engine_controller_data_reader is free software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * osv_engine_controller_data_reader is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General
 * Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * osv_engine_controller_data_reader. If not, see <http://www.gnu.org/licenses/>.
 */

#include <signal.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

#include <iostream>

#include "ECClient.h"

ECClient * ecClient;

void my_handler(int s) {
	printf("Caught signal %d\n", s);
	delete ecClient;
	exit(EXIT_SUCCESS);
}

int main(int argc, char * argv[]) {
	std::cout << "Test!" << std::endl;

	// Signal handling
	struct sigaction sigIntHandler;
	sigIntHandler.sa_handler = my_handler;
	sigemptyset(&sigIntHandler.sa_mask);
	sigIntHandler.sa_flags = 0;
	sigaction(SIGINT, &sigIntHandler, NULL);
	std::cout << "Sigint handling initialized." << std::endl;

	ecClient = new ECClient();

	std::cout << "Client created." << std::endl;

	std::cout << "Will get speed." << std::endl;

	while (true) {
		std::cout << std::to_string(ecClient->getEVSpeed()) << " km/h" << std::endl;
		usleep(250000);
	}

	std::cout << "Client delete." << std::endl;
	delete ecClient;

	return EXIT_SUCCESS;
}
