import re

#lưu ý: hàm re.compile() sẽ trả về một pattern object, pattern object này sẽ có những phương thức như là match, search, findall, finditer

#tương tự thì các hàm search, match sẽ trả về các match object, các match object này thì cũng có các phương thức
#như là .span, .start, .end, .group

#lưu ý: thằng re.finditer nó trả về iterator chứ không phải match object

dates = '''
01.04.2020

2020.04.01

2020-04-01
2020-05-23
2020-06-11
2020-07-11
2020-08-11

2020/04/02

2020_04_04
2020_04_04
.....
'''

my_string = """
hello world
1223
2020-05-20
Mr Simpson
Mrs Simpson
Mr. Brown
Ms Smith
Mr. T
pythonengineer@gmail.com
Python-engineer@gmx.de
python_engineer123@my-domain.org
"""

text = "Hợp lệ: 'abc', \"xyz\". Sai: '123\""

date = "Hôm nay là 25-12-2025"

html_tags = "<h1>Tiêu đề đúng</h1> <h1>Tiêu đề sai</h2>"

urls = """
hello
2020-05-20
http://python-engineer.com
https://www.python-engineer.com
http://www.pyeng.net
"""


test_string = "123abc456789abc123ABC"

pattern = re.compile(r"\d{4}[-/_.]0[5-7][-/_.]\d{2}")

pattern2 = re.compile(r"(Mr|Ms|Mrs)\.?\s\w+")

pattern3 = re.compile(r"([a-zA-Z0-9-]+)@([a-z-]+)\.(\w+)")

pattern4 = re.compile(r"abc")

pattern5 = re.compile(r"world")

pattern6 = re.compile(r"https?://(www\.)?([a-zA-Z-]+)(\.\w+)")

pattern7 = re.compile(r"(['\"]).*?\1")

pattern8 = re.compile(r"<(\w+)>.*?</\1>")

pattern9 = re.compile(r"(.*?)(\d{2})-(\d{2})-(\d{4})")

pattern10 = r"(\d{2}+)-(\d{2}+)-(\d{4}+)"

pattern11 = re.compile(r"world", re.IGNORECASE)

test_sub = "hello world, you are the best world"

test_flag = "World WoRld WORLD world"

subbed = re.sub(pattern10, r"\1/\2/\3", date)

res = pattern11.findall(test_flag)

dangerous_string = "C++ * Python * Java"

save_string = re.escape(dangerous_string)

print(save_string)


