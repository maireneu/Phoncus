#include "Socket.h"
#include <string.h>
#include <errno.h>
#include <fcntl.h>
#include <iostream>

Socket::Socket( std::string sHost, int iPort ) : iSocket(-1) { 
  memset(&iAddr, 0, sizeof(iAddr)); 
  this->create();
  this->connect(sHost, iPort);
}
Socket::~Socket() { if (is_valid())::close (iSocket); }

bool Socket::create() {
  //TCP, IPv4
  iSocket = socket (AF_INET, SOCK_STREAM, 0);

  int iOptVal = 1;
  if ( setsockopt (iSocket, SOL_SOCKET, SO_REUSEADDR, ( const char* ) &iOptVal, sizeof ( iOptVal ) ) == -1 )
    return false;
  return true;
}

bool Socket::send ( const std::string sMsg ) const {
  int status = ::send ( iSocket, sMsg.c_str(), sMsg.size(), MSG_NOSIGNAL );
  if ( status == -1 )
    return false;
  else
    return true;
}

int Socket::receive ( std::string& sMsg ) {
  char cBuf [ MAXRECV + 1 ];
  memset ( cBuf, 0, MAXRECV + 1 );

  int status = recv( iSocket, cBuf, MAXRECV, 0 );

  if ( status == -1 ) {
    std::cout << "status == -1   errno == " << errno << "  in Socket::recv\n";
    return 0;
  }
  else if ( status == 0 )
    return 0;
  else
    return status;
}

bool Socket::connect ( const std::string sHost, const int iPort ) {

  iAddr.sin_family = AF_INET;
  iAddr.sin_port = htons ( iPort );

  int status = inet_pton ( AF_INET, sHost.c_str(), &iAddr.sin_addr );

  if ( errno == EAFNOSUPPORT ) return false;

  status = ::connect ( iSocket, ( sockaddr* ) &iAddr, sizeof ( iAddr ) );

  if ( status == 0 )
    return true;
  else
    return false;
}

const Socket& Socket::operator << ( const std::string& sMsg ) const {
  this->send(sMsg);
  return *this;
}

const Socket& Socket::operator >> ( std::string& sMsg ) const {
  this->receive(sMsg);
  return *this;
}