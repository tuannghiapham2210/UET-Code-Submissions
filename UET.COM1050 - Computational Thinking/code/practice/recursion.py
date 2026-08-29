import ast
from functools import lru_cache

def tong(lst, l = 0):
    if l == len(lst):
        return 0
    else:
        return lst[l] + tong(lst, l + 1)
    
#[1, 2, 3]
#lst[0] + tong(lst, 1) --> return 6
#lst[1] + tong(lst, 2) --> return 5
#lst[2] + tong(lst, 3) --> return 3



def dao_nguoc(s, r):
    if r < 0:
        return ''
    else:
        return s[r] + dao_nguoc(s, r - 1)
#Python --> nohtyP

#1221
#12321

def is_palindrome(s, l, r):
    if l >= r:
        return True 
    if s[l] != s[r]:
        return False
    return is_palindrome(s, l + 1, r - 1)



def binary_search(lst, target, l, r):
    if l > r:
        return -1
    mid = (l + r) // 2

    if lst[mid] == target:
        return mid
    
    elif lst[mid] > target: 
        return binary_search(lst, target, l, mid - 1)
    
    else:
        return binary_search(lst, target, mid + 1, r)



def fib(n, memo = {}):
    #if the element is already calculated, use the existing answer
    if n in memo:
        return memo[n]
    
    #base case
    if n == 1: return 1
    if n == 0: return 0

    #calculate the answer, then memorize it for future use
    res = fib(n - 1, memo) + fib(n - 2, memo)

    memo[n] = res

    #return the answer

    return res


def climbing_stairs(n, memo = {}):
    #if the answer is already calculated, use it
    if n in memo:
        return memo[n]
    
    #base case
    if n == 1: return 1
    if n == 2: return 2

    #calculate the answers and memorize it for future use
    res = climbing_stairs(n - 1) + climbing_stairs(n - 2)

    memo[n] = res 

    return res


def memoize(func):
    memo = {}

    def wrapper(n):
        
        if n not in memo:
            memo[n] = func(n)
        
        return memo[n]
    
    return wrapper


#climbing_stairs2 = memoize(climbing_stairs2)
@memoize
def climbing_stairs2(n):
    if n == 1: return 1
    if n == 2: return 2
    return climbing_stairs2(n - 1) + climbing_stairs2(n - 2)



@lru_cache
def fib2(n):
    if n == 0: return 0
    if n == 1: return 1
    return fib2(n - 1) + fib2(n - 2)



if __name__ == "__main__":
    n = int(input())
    print(fib2(n))



