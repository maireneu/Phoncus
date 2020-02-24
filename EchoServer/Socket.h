#pragma once

#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <unistd.h>
#include <string>
#include <arpa/inet.h>

const int MAXRECV = 10000;

class Socket {
 public:
  Socket( std::string sHost, int iPort );
  ~Socket();

  bool create();
  bool connect ( const std::string sHost, const int iPort );
  bool send ( const std::string ) const;
  int receive ( std::string& );

  bool is_valid() const { return iSocket != -1; }

  Socket& operator << ( const std::string& ) const;
  const Socket& operator >> ( std::string& ) const;

 private:
  int iSocket;
  sockaddr_in iAddr;

};