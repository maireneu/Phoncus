def FirstIndex(lt, elem):
    for i in range(0, len(lt)):
        if lt[i] == elem : return i
    return -1

lt = [2,3,4,5,6]
print(FirstIndex(lt, 1))