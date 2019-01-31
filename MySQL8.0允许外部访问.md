# MySQL8.0允许外部访问 #
1. 登进MySQL之后，

2. 输入以下语句，进入mysql库：    
	use mysql

3. 更新域属性，'%'表示允许外部访问：   
	update user set host='%' where user ='root';

4. 执行以上语句之后再执行：     
	FLUSH PRIVILEGES;

5. 再执行授权语句：    
	GRANT ALL PRIVILEGES ON *.* TO 'root'@'%'WITH GRANT OPTION;
	然后外部就可以通过账户密码访问了。

6. 其它说明：      
	FLUSH PRIVILEGES; 命令本质上的作用是：将当前user和privilige表中的用户信息/权限设置从mysql库(MySQL数据库的内置库)中提取到内存里。MySQL用户数据和权限有修改后，希望在"不重启MySQL服务"的情况下直接生效，那么就需要执行这个命令。通常是在修改ROOT帐号的设置后，怕重启后无法再登录进来，那么直接flush之后就可以看权限设置是否生效。而不必冒太大风险。

7.	原文：https://blog.csdn.net/h996666/article/details/80921913 
