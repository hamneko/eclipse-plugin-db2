// Fake includes to allow parsing of the DB2 embedded sql files.
// Copy this file to include directory.
struct sqlca {
	char sqlcaid;
	long sqlabc;
	long sqlcode;
	long sqlerrml;
	char sqlerrmc;
	char sqlerrp;
	long sqlerrd;
	char sqlwarn;
	char sqlstate;
};

long SQLCODE;
char SQLSTATE;

typedef char sqlint8;
typedef unsigned char sqluint8;

typedef short sqlint16;
typedef unsigned short sqluint16;

typedef int sqlint32;
typedef unsigned int sqluint32;

typedef long sqlint64;
typedef unsigned long sqluint64;
