from collections import Counter
def palindrome(s):
    char_fre = Counter(s)
    
    odd_fre = 0

    half = []

    mid = []

    for char, fre in char_fre.items():
        if fre % 2 == 0:
            half.append(char * (fre // 2))
        else:
            mid.append(char * fre)
            odd_fre += 1
    
    if odd_fre > 1:
        return "NO SOLUTION"
    
    else:
        return "".join(half) + "".join(mid) + "".join(half[::-1])
    

    
if __name__ == "__main__":
    s = input()
    print(palindrome(s))
