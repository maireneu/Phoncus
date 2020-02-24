#include "ClientSocket.h"
#include "Exception.h"

ClientSocket::ClientSocket (std::string sHost, int iPort) {
  if ( !Socket::create() ) 
    throw Exception ("Can't create socket.");
  if ( !Socket::connect(sHost,iPort) ) 
    throw Exception ("Can't bind to port.");
}

const ClientSocket& ClientSocket::operator << ( const std::string& sMsg ) const {
  if ( ! Socket::send(sMsg) ) 
    throw SocketException("Can't send");
  return *this;
}

const ClientSocket& ClientSocket::operator >> ( std::string& sMsg ) const {
  if ( !Socket::recv(sMsg) )
      throw SocketException ("Can't receive");
  return *this;
}