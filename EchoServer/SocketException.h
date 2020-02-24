#pragma once
#include <string>

class Exception {
 public:
  SocketException(std::string s) : m_s(s) {};
  ~SocketException() {};

  std::string description() { return m_s; }

 private:
  std::string m_s;
};
