/*
 * DashboardClient.cpp
 *
 *  Created on: 12 oct. 2016
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

#include "DashboardClient.h"

//const std::string DashboardClient::ADDRESS = "192.168.1.2";
const std::string DashboardClient::ADDRESS = "127.0.0.1";
const std::string DashboardClient::PORT = "80";
const std::string DashboardClient::SPEED = "/speed";

const std::string DashboardClient::CONNECTION = "Connection: ";
const std::string DashboardClient::CONNECTION_CLOSE = "Close";
const std::string DashboardClient::CONTENT_TYPE = "Content-Type: ";
const std::string DashboardClient::SENT_CONTENT_TYPE = "application/json";
const std::string DashboardClient::NO_EXPECT = "Expect: ";

DashboardClient::DashboardClient() {
}

DashboardClient::~DashboardClient() {
}

void DashboardClient::postSpeed(float speed) {

	uint16_t responseCode = 0;

	std::string toReturn = "";

	std::cout << ("Client, will POST resource: /speed, with content: " + std::to_string(speed)) << std::endl;

	try {
		curlpp::Cleanup cleaner;
		curlpp::Easy request;
		std::ostringstream os;


		request.setOpt(new curlpp::options::Url("http://" + DashboardClient::ADDRESS + ":" + DashboardClient::PORT + DashboardClient::SPEED));

		// DEBUG Next line is useful for debugging.
		request.setOpt(new curlpp::options::Verbose(true));

		std::list<std::string> header;
		header.push_back(DashboardClient::CONNECTION + DashboardClient::CONNECTION_CLOSE);
		header.push_back(DashboardClient::CONTENT_TYPE + DashboardClient::SENT_CONTENT_TYPE);
		header.push_back(DashboardClient::NO_EXPECT);
		request.setOpt(new curlpp::options::HttpHeader(header));

		std::istringstream is("{\"speed\": " + std::to_string(speed) + "}");
		request.setOpt(new curlpp::options::ReadStream(&is));
//		request.setOpt(new curlpp::options::PostFields(is.str()));
//		request.setOpt(new curlpp::options::PostFieldSize(is.str().length()));
		request.setOpt(new curlpp::options::Upload(true));
		request.setOpt(new curlpp::options::InfileSize(is.str().size()));
		request.setOpt(new curlpp::options::WriteStream(&os));
		request.setOpt(new curlpp::options::Timeout(1L));

		request.perform();

		responseCode = curlpp::infos::ResponseCode::get(request);
		std::cout << "Received response (response code: " << std::to_string(responseCode) << ")." << std::endl;

//		toReturn = os.str();
//		printOut("Received:");
//		printJson(toReturn);

	} catch (curlpp::LogicError & e) {
		std::cerr <<
				"Error requesting ressource <" + SPEED + ">: " + e.what() << std::endl;
	} catch (curlpp::RuntimeError & e) {
		std::cerr <<
				"Error requesting ressource <" + SPEED + ">: " + e.what() << std::endl;
	}
}
