#include "Socket.h"
#include <iostream>
#include <string>

int main ( int argc, char** argv )
{

      Socket client_socket ( "127.0.0.1", 30000 );

      std::string reply;

	  client_socket << "Test message.";
	  client_socket >> reply;

      std::cout << "We received this response from the server:\n\"" << reply << "\"\n";;


  return 0;
}