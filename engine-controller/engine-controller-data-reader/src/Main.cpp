/*
 * Main.cpp
 *
 *  Created on: 11 oct. 2016
 *      Author: guillaumelg
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
