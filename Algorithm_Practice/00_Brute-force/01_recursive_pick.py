def main():
  pick(1, 5, [], 3)
  print("")
  pick_2( 5, [], 3)


def pick(n, m, lPicked, iToPick):
  if iToPick == 0:
    print(lPicked)
  for i in range(n, m+1):
    lPicked.append(i)
    pick(i+1, m, lPicked, iToPick-1)
    lPicked.pop()

def pick_2(n, lPicked, iToPick):
  if iToPick == 0:
    print(lPicked)
  smallist = 0 if not lPicked else lPicked[-1]
  for i in range(smallist+1, n+1):
    lPicked.append(i)
    pick_2(n, lPicked, iToPick-1)
    lPicked.pop()

if __name__ == "__main__":
  main()
