/*
 * ECClient.h
 *
 *  Created on: 11 oct. 2016
 *      Author: guillaumelg
 */

#ifndef SRC_ECCLIENT_H_
#define SRC_ECCLIENT_H_

#include <fcntl.h>
#include <unistd.h>
#include <termios.h>

#include <cstring>
#include <cstdio>
#include <iostream>

#include "ECError.h"

#define READ_BUF_SIZE 2014

/*
 *
 */
class ECClient {
public:

	/**
	 * @throws ECError If port could not be opened.
	 */
	ECClient();
	virtual ~ECClient();

	float getEVSpeed();

protected:
	int usb;

	/**
	 * @throws ECError If write failed.
	 */
	void writeToEC(uint16_t size, unsigned char * toWrite);

	void readFromEC(uint16_t size, unsigned char * toRead);
};

#endif /* SRC_ECCLIENT_H_ */
