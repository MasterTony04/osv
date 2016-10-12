/*
 * ECError.cpp
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

#include "ECError.h"

ECError::ECError(const std::basic_string<char> desc) :
				description(desc) {
}

ECError::~ECError() {
}

const char * ECError::what() const throw () {
	return this->description.c_str();
}
