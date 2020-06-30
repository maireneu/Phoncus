import os

def sum(n):
  ret = 0
  for i in range(1, n+1):
    ret += i
  print(ret)

def main():
  a = 1
  b = 2
  print(f'{a+b}')

sum(10)
