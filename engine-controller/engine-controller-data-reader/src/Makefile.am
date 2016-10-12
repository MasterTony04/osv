bin_PROGRAMS = osv_engine_controller_data_reader

osv_engine_controller_data_reader_CPPFLAGS = \
	$(curlpp_CFLAGS)

AM_CXXFLAGS = -std=c++11 -Wall

osv_engine_controller_data_reader_SOURCES = \
	DashboardClient.h \
	ECClient.h \
	ECError.h \
	DashboardClient.cpp \
	ECClient.cpp \
	ECError.cpp \
	Main.cpp

osv_engine_controller_data_reader_LDADD = \
	$(curlpp_LIBS)
