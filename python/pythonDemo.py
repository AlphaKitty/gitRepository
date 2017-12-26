'''
Created on 2017年12月12日

@author: Administrator
'''
from builtins import str

# python的基本类型转换函数非常简洁实用
# python可以给函数起别名

# 连续打印字符串,单引号和双引号都可以,逗号充当空格
# print('hello','world',"tomorrow");

# 变量定义和使用
# name = input();
# print('hello' , name);

# 字符转义
# print('i am','\"ok!\"');

# 引号前面加个"r"代表字符串不转义
# print(r'\qwe\rtt');

# 分行显示的语法,同样加r有不转义的作用
# print(r'''li\\\ne1
# line2
# li\\\ne3''');

# Python中的布尔类型是开头大写的,连接符用and or等
# print(3>5 or 6>7);

# 这代表"地板除",两个整数的除法仍然是整数
# print(10//3);

# Unicode编码转换
# print(chr(9527));
# print(ord('张'));
# print(ord('友'));
# print(ord('谅'));

# 在知道编码的情况下可以这么输出文字
# print('\u4e2d\u6587');

# 打印中文或其他文字的编码,然后转换,如果decode中有错误字符会报错,这时候加个errors='ignore'就好了
# print('中文'.encode('utf-8'));
# print(b'\xe4\xb8\xad\xe6\x96\x87'.decode('utf-8'));
# print(b'\xe4\xb8\xad\xff'.decode('utf-8', errors='ignore'));

# 可以看到,python的方法是这么用的
# print(len('abc'));

# python没有类似于字符串和数字相加的功能,只能用C的%转义来实现
# name = '三叶';
# age=16;
# print('你的名字:%s,你今年%d岁' % (name, age));
# print('你的名字:'+name+',你今年'+age+'岁');

# 这里比较重要的一点:{}里面装的是set集合,[]里面装的是list列表,负数直接取list倒数几个元素,list可嵌套
# tmpList=[213,'34',3.4];
# print(tmpList);
# print(len(tmpList));
# print(tmpList[2]);
# print(tmpList[-1]);
# tmpList.insert(1, '张友谅')
# print(tmpList);
# tmpList.append('object')
# print(tmpList)
# print(tmpList.index('34', ));
# tmpList.pop();
# print(tmpList)
# tmpList.pop(1)
# print(tmpList)
# tmpList[0]='zyl'
# print(tmpList)
# tmpList1=['123','456',tmpList]
# print(tmpList1)
# print(tmpList1[2][2])

# tuple:也是一种list,不过这种list一旦被初始化就不能再被修改,tuple用小括号定义
# 如果定义只有一个元素的tuple,会和定义变量产生歧义,为了消除歧义,在元素末尾加个逗号表示定义的是单个元素的tuple
# 定义空tuple反而没影响
# tmpTuple=(123,"123",'zyl');
# print(tmpTuple)
# tmpTuple1=(123,)
# print(tmpTuple1)
# tmpTuple2=()
# print(tmpTuple2)

# 前面说的tuple内容不变实质上是指引用不变,即如果tuple中有list的引用,那么这个引用不再变,但list的内容可以变
# tmpTuple3=('a','b',['x','y']);
# tmpTuple3[2][1]='zyl';
# print(tmpTuple3)

# 从以下代码可以看出,条件判断的范围依据是缩进,条件后面一定要有个冒号,java中的else if在这也成了elif
# if 3<4:
#     print(False)
#     print('3<4')
# else:
#     print(True)
# print('3>4')

# 打印False的情况:0,'',{},[],()等所属类为空的时候if判定为False
# x = {};
# if(x):
#     print(True);
# else:
#     print(False);

# input(prompt)用来从控制台输入并赋值,输入的内容都是字符串,变成其他需要转义
# birth=input('birth:');
# if(17>int(birth)):
#     print('00后')
# else:
#     print('00前')
# 根据年龄判断生年
# birth=input('birth:');
# print(str(2017-int(birth))[-2:]+'年生人')

# 循环 int类型不能直接做循环计数器,需要用range来定范围,除了for in之外还有while循环
# names=['123','456','789']
# for name in names:
#     print(name)
# for i in range(100):
#     print(i)

# dict字典,是一种key-value键值对,查找速度极快,缺点是占内存多,key允许重复,找到的value是最后一个,如果按key不存在,找value会报错,为了避免这种报错,应该先判断一下这个key是否存在,dict是一种典型的空间换时间的做法
# dic={'zyl':'123','qwe':213,'zyl':'17664519701'};
# print(dic.get('2',-1))# ①get方法判断key是否存在,第二个参数是不存在时候的返回值
# print('zyl' in dic)# ②in关键字也可以判断key是否存在,返回值为Boolean
# print(dic['zyl'])
# dic.pop('zyl');# dict和list还有一个区别是dict的插入顺序是没有关系的,pop会删除dict中所有对应的key
# print(dic)

# 以下是set的两种初始化方式,第一种是原生方法,第二种需要依赖一个list,set会自动过滤掉重复值,set中的值没有顺序
# tmpSet={'213','250','fuck','213'};
# tmpSet1=set(['235',234,'66','213'])
# print(tmpSet)
# print(tmpSet1)
# print(tmpSet & tmpSet1)# set集合可以直接进行交并运算,集合储存的类似于dict的key,所以不能存储可变元素,比如list
# print(tmpSet | tmpSet1)

# 函数定义基础,编译器只能检查出参数个数对不对,并不能检查出参数类型对不对
# def my_abs(x):
#     if(x>=0):
#         return x
#     else:
#         return -x
# print(my_abs(10))
# print(my_abs(-3.2))
# print(my_abs(0))

# 这是一个空函数,它什么也没做,有用吗?有用.在没有清楚方法要怎么写的时候,可以先这么写让程序跑下去
# def my_nothing_method():
#     pass
# if(3>4):# pass也可以用在像这样的地方,跟java差不多
#     pass

# python函数里非常亮眼的一个功能,默认参数值.可以在不破坏原有函数功能的情况下对函数进行功能上的扩展,要注意的是默认参数值要放在后面,变化小的参数放在后面
# def power(x,n=2):
#     s=1;
#     while(n>0):
#         n=n-1;
#         s=x*s;
#     return s;
# print(power(2,10))

# 默认参数值顺序很有讲究,即使默认参数值是字符串,调用的时候传整型它也会转义后强行赋值而不是自动找下一个整型参数(并没有想象中的智能)
# def info(name,gender='男',age=22,city='北京'):
#     print('name:%s,gender:%s,age:%d,city:%s' %(name,gender,age,city))
# info('张友谅')
# info('张友谅',18)

# 默认参数值如果可变,在调用函数后默认参数值就会发生变化,所以,默认参数必须指向不变对象!(None就是不变对象)
# def default_list(L=None):
#     if(L is None):
#         L=[]
#     L.append('END')
#     return L;
# print(default_list([213,'222','332']))
# print(default_list([221,'000']))
# print(default_list())
# print(default_list())
# print(default_list())












