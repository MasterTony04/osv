/*
 * ECError.cpp
 *
 *  Created on: 11 oct. 2016
 *      Author: guillaumelg
 */

#include "ECError.h"

ECError::ECError(const std::basic_string<char> desc) :
				description(desc) {
}

ECError::~ECError() {
}

const char * ECError::what() const throw () {
	return this->description.c_str();
}
