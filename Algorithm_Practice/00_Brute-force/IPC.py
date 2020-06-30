class IDC:
  lt = []
  def __init__(self, path):
    with open(f"./{path}",'r') as inputFile:
      slines = inputFile.readlines()
      self.lt.append(slines[:-1])

def ID(path):
   return [lines[:-1] for lines in open(f"./{path}",'r')]
