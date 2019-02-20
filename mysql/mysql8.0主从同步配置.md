#主库

	#/etc/my.cnf
	
	#设置mysql的id
	server-id=1

	#打开mysql的二进制
	log-bin=master-bin
	log-bin-index=master-bin.index
	
	#重启mysql
	service mysqld start；
	
	#显示主节点状态
	show master status； 
	
	#主库
	如果从库是以repl账号来登录的额，就对主库上的所有数据进行同步

	#mysql8.0
	CREATE USER 'repl'@'192.168.254.136' IDENTIFIED WITH 'mysql_native_password' BY 'feifei123321!';

	GRANT REPLICATION SLAVE ON *.* TO 'repl'@'192.168.254.136';

	flush privileges;

	#mysql8.0以前
	CREATE USER 'repl'@'192.168.254.136';

	GRANT REPLICATION SLAVE ON *.* TO 'repl'@'192.168.254.136' IDENTIFIED BY 'feifei123321!';

	flush privileges;

#从库

	#/etc/my.cnf
	
	server-id=2
	relay-log-index=slave-relay-bin.index
	relay-log=slave-relay-bin
	
	#登录从库mysql
	change master to master_host='192.168.254.135',master_port=3306,master_user='repl',master_password='feifei123321!',master_log_file='master-bin.000001',master_log_pos=0;
	
	start slave;
	#\G表示分行显示
	show slave status \G；

