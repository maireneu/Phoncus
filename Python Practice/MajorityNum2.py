A = [1, 2, 3, 4, 5, 6 , 5, 3, 2, 4, 4, 4]
N = len(A)
majority, majorityCount = -1, 0
for elem1 in range(0, 100):
    count = 0
    for elem2 in A:
        if elem1 == elem2:
            count += 1
    if count > majorityCount:
        majority, majorityCount = elem1, count
print(majority)