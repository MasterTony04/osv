/*
 * DashboardClient.h
 *
 *  Created on: 12 oct. 2016
 *      Author: guillaumelg
 */

#ifndef SRC_DASHBOARDCLIENT_H_
#define SRC_DASHBOARDCLIENT_H_

#include <curlpp/cURLpp.hpp>
#include <curlpp/Easy.hpp>
#include <curlpp/Exception.hpp>
#include <curlpp/Infos.hpp>
#include <curlpp/Options.hpp>
#include <iostream>
#include <sstream>
#include <string>

/*
 *
 */
class DashboardClient {
public:

	static const std::string ADDRESS;
	static const std::string PORT;
	static const std::string SPEED;

	static const std::string CONNECTION;
	static const std::string CONNECTION_CLOSE;
	static const std::string CONTENT_TYPE;
	static const std::string SENT_CONTENT_TYPE;
	static const std::string NO_EXPECT;

	DashboardClient();
	virtual ~DashboardClient();

	void postSpeed(float speed);
};

#endif /* SRC_DASHBOARDCLIENT_H_ */
