def main():
  print(normalSum(10))
  print(recursiveSum(10))
  print(pythonicSum(10))
  print(test())

def normalSum(i):
  ret = 0
  for j in range(i+1):
    ret += j
  return ret

def pythonicSum(i):
  return sum(n for n in range(1, i+1))

def recursiveSum(i):
  if i == 0 :
    return 0
  return i + recursiveSum(i-1)

def test():
  a = [10,2,3,4,5]
  print(sum(n for n in a))

if __name__ == "__main__":
  main()
