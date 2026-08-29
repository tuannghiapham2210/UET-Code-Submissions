
def solve(a, b):
    #mỗi lần bỏ đi 3 coin --> để hết được coin thì số lượng coin
    #lấy ra phải đúng bằng bội của 3
    #--> tổng 2 đống coin phải chia hết cho 3
    if (2 * b - a) % 3 != 0 or (2 * a - b) % 3 != 0:
        return "NO"
    #nếu số coin của một bên mà nhiều hơn 2 lần số coin của đống kia
    #thì một bên sẽ bị về 0 trước mất
    if (b > 2 * a or 2 * b < a):
        return "NO"
    return "YES"


n = int(input())

testcases = [map(int, input().split()) for _ in range(n)]

for i in testcases:
    a, b = i
    print(solve(a, b))