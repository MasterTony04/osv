/*
 * Main.cpp
 *
 *  Created on: 11 oct. 2016
 *      Author: guillaumelg
 */

#include <iostream>

#include "ECClient.h"

int main(int argc, char * argv[]) {
	std::cout << "Test!" << std::endl;

	ECClient * ecClient = new ECClient();

	std::cout << "Client created." << std::endl;

	std::cout << "Will get speed." << std::endl;
	std::cout << std::to_string(ecClient->getEVSpeed()) << "km/h" << std::endl;

	std::cout << "Client delete." << std::endl;
	delete ecClient;

	return EXIT_SUCCESS;
}
