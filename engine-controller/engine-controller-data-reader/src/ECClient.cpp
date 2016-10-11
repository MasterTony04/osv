/*
 * ECClient.cpp
 *
 *  Created on: 11 oct. 2016
 *      Author: guillaumelg
 */

#include "ECClient.h"

/*
 * From http://stackoverflow.com/questions/18108932/linux-c-serial-port-reading-writing
 */
ECClient::ECClient() {

	if (!(usb = open("/dev/pty0", O_RDWR | O_NOCTTY))) {
		throw ECError("Failed to open /dev/ttyUSB0");
	}

	struct termios tty;
	// TODO Is this necessary ?
//	memset(&tty, 0, sizeof tty);

	/* Set Baud Rate */
	cfsetospeed(&tty, (speed_t) B38400);
	cfsetispeed(&tty, (speed_t) B38400);

	/* Setting other Port Stuff */
	tty.c_cflag &= ~PARENB;            // Make 8n1
	tty.c_cflag &= ~CSTOPB;
	tty.c_cflag &= ~CSIZE;
	tty.c_cflag |= CS8;

	tty.c_cflag &= ~CRTSCTS;           // no flow control
	tty.c_cc[VMIN] = 1;                  // read doesn't block
	tty.c_cc[VTIME] = 5;                  // 0.5 seconds read timeout
	tty.c_cflag |= CREAD | CLOCAL;     // turn on READ & ignore ctrl lines

	/* Make raw */
	cfmakeraw(&tty);

	/* Flush Port, then applies attributes */
	tcflush(usb, TCIFLUSH);
	if (tcsetattr(usb, TCSANOW, &tty) != 0) {
		throw ECError("Error " + std::to_string(errno) + " from tcsetattr: " + std::strerror(errno));
	}
}

ECClient::~ECClient() {
	close(usb);
}

float ECClient::getEVSpeed() {
	// TODO auto-generated
	unsigned char cmdBuf[] = {0x53, 0x04, 0x03, 0x05, 0x16, 0x00, 0xe3, 0x9d};
	unsigned char resBuf[READ_BUF_SIZE] = {};

	writeToEC(8, cmdBuf);

	readFromEC(22, resBuf);

	uint16_t val = resBuf[12] << 8 | resBuf[13];

	return (float) val / 10.0;
}

void ECClient::writeToEC(uint16_t size, unsigned char* toWrite) {
	if (!write(usb, toWrite, sizeof(unsigned char) * size)) {
		throw ECError("Write failed");
	}
}

void ECClient::readFromEC(uint16_t size, unsigned char* toRead) {
	// DEBUG
//	if (read(usb, toRead, size) < 0) {
//		throw ECError("Read failed");
//	}

	unsigned char values[] = {0x53, 0x04, 0x06, 0x03, 0x05, 0x16, 0x00, 0x03, 0x00, 0x05, 0x00, 0x00, 0x01, 0x04, 0x00, 0x00, 0x00, 0x01, 0xea, 0x89, 0xd8, 0x62};

	for(uint16_t i = 0 ; i < size ; i++) {
		toRead[i] = values[i];
	}
}
