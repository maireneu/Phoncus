ip = [72,75,74,79,81,75,71]
sum = ip[0] + ip[1] + ip[2]
average = [sum/3]
for i in range(3, len(ip)):
    sum = sum + ip[i] - ip[i-3]
    average.append(sum / 3)
print(average)