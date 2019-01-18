# Postman时间参数 #

    有时要传递一些非可直接输入的参数，比如日期时间之类的这些可由JavaScript代码生成的测试。
    可在Pre-request Script中书写JavaScript代码，最后通过调用postman的setGlobalVariable
    函数置入全局变量：postman.setGlobalVariable("now", new Date());
    在调用接口时传入些类参数时，直接引用全局变量：{{now}}