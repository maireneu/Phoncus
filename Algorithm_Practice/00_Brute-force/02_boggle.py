import copy
IPD = [lines[:-1] for lines in open("./02_boggle.input",'r')]

def main():
  boggle_1()

def boggle_1():
  boggleList = []
  for i in range(1,6):
    boggleList.append([])
    for j in range(0,len(IPD[i])):
      boggleList[i-1].append(IPD[i][j])

  for i in range(7,13):
    word = IPD[i]
    for j in range(0, len(boggleList)):
      break_bool = False
      for k in range(0, len(boggleList[j])):
        #if match_1(word, 0, j, k, boggleList):
        if match_2(j, k, word, boggleList):
          print(f"{word} YES")
          break_bool = True
          break
      if break_bool == True:
        break
      elif j == len(boggleList)-1:
        print(f"{word} NO")


def match_1(word, n, i, j, boggleList):
  if len(word) == n:
    return True
  if i < 0 or j < 0 or i >= len(boggleList) or j>=len(boggleList[i]):
    return False
  if boggleList[i][j] == word[n]:
    if match_1(word, n+1, i+1, j+1, boggleList):
      return True
    if match_1(word, n+1, i+1, j, boggleList):
      return True
    if match_1(word, n+1, i+1, j-1, boggleList):
      return True
    if match_1(word, n+1, i, j+1, boggleList):
      return True
    if match_1(word, n+1, i, j-1, boggleList):
      return True
    if match_1(word, n+1, i-1, j+1, boggleList):
      return True
    if match_1(word, n+1, i-1, j, boggleList):
      return True
    if match_1(word, n+1, i-1, j-1, boggleList):
      return True
  return False

dx = [-1,-1,-1,0,0,1,1,1]
dy = [-1,0,1,-1,1,-1,0,1]

def inRagne(x, y, boggleList):
  if x < 0 or y < 0 or x >= len(boggleList) or y >= len(boggleList[0]):
    return False
  return True

def match_2(x, y, word, boggleList):
  if inRagne(x, y, boggleList) == False:
    return False
  if boggleList[x][y] != word[0]:
    return False
  if len(word) == 1:
    return True
  for direction in range(0,8):
    if match_2(x+dx[direction], y+dy[direction], word[1:], boggleList):
      return True
  return False



if __name__ == "__main__":
  main()
