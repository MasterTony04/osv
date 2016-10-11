/*
 * ECError.h
 *
 *  Created on: 11 oct. 2016
 *      Author: guillaumelg
 */

#ifndef SRC_ECERROR_H_
#define SRC_ECERROR_H_



#include <exception>
#include <string>

/*
 *
 */
class ECError: public std::exception {
public:
	ECError(const std::basic_string<char> desc = "");
	virtual ~ECError() throw ();

	const char * what() const throw ();

protected:
	const std::basic_string<char> description;
};

#endif /* SRC_ECERROR_H_ */
