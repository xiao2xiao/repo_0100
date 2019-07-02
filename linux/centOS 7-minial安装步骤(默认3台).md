centOS 7-minial安装步骤及集群搭建(默认3台)

1、修改网卡，并设置静态ip

	vi /etc/sysconfig/network-scripts/ifcfg-ens33

	TYPE=Ethernet
	PROXY_METHOD=none
	BROWSER_ONLY=no
	BOOTPROTO=static
	IPADDR=192.168.254.137
	NETMASK=255.255.255.0
	GATEWAY=192.168.254.2
	DNS1=192.168.254.2
	DEFROUTE=yes
	IPV4_FAILURE_FATAL=no
	IPV6INIT=yes
	IPV6_AUTOCONF=yes
	IPV6_DEFROUTE=yes
	IPV6_FAILURE_FATAL=no
	IPV6_ADDR_GEN_MODE=stable-privacy
	NAME=ens33
	UUID=687e55b3-66c4-4a35-b814-7f174c9be3a7
	DEVICE=ens33
	ONBOOT=yes
	ZONE=public

	最后重启服务

	service network restart

2、安装vim 

	yum install -y vim

3、修改主机名

	vi /etc/hostname
	把三台IP和主机名写到里面
	vi /etc/hosts

	127.0.0.1   localhost localhost.localdomain localhost4 localhost4.localdomain4
	::1         localhost localhost.localdomain localhost6 localhost6.localdomain6
	
	192.168.254.137 ebooa
	192.168.254.138 eboob
	192.168.254.139 ebooc

	最后重启电脑

	reboot

4、设置这三台电脑ssh互相ping通

	scp /opt/aa.txt root@192.168.254.137:/opt

	在每台机器上执行

	进入~/.ssh目录
 	cd /root/.ssh
	ssh-keygen -t rsa
	ssh-copy-id -i node01
	ssh-copy-id -i node02
	ssh-copy-id -i node03

5、安装jdk1.8、redis、rabbitmq、zk环境

	#set zookeeper environment
	export ZOOKEEPER_HOME=/opt/app/zookeeper-3.4.13
	
	#set redis environment
	export REDIS_HOME=/opt/app/redis-4.0.2
	
	#set erlang
	export ERLANG_HOME=/opt/app/erlang-20.2/erinstall
	
	#set rabbitmq
	export RABBITMQ_HOME=/opt/app/rabbitmq_server-3.7.6
	
	#set java environment
	export JAVA_HOME=/opt/app/jdk1.8.0_191
	export JRE_HOME=${JAVA_HOME}/jre
	export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib:$ZOOKEEPER_HOME/bin:$REDIS_HOME
	export PATH=${JAVA_HOME}/bin:${ERLANG_HOME}/bin:${RABBITMQ_HOME}/sbin:$PATH
	
	#5.1 jdk1.8

	tar -zxvf jdk -C /opt/app/
	vim /etc/profile
	source /etc/profile

	#5.2 rabbitmq3.7.6
    #5.2.1 安装erlang20.2
	1、	
		yum install openssl
		yum search libtool
		yum search libtool-ltdl-devel
		yum install libtool
		yum install libtool-ltdl-devel
		yum install gcc-c++ 
		yum install erlang-doc                 
		yum install erlang-jinterface
		yum -y install make
		yum -y install gcc
		yum -y install gcc-c++
		yum -y install kernel-devel
		yum -y install m4
		yum -y install ncurses-devel
		yum -y install openssl-devel
		yum -y install unixODBC-devel
	2、wget http://erlang.org/download/otp_src_20.2.tar.gz
	3、tar -xvzf otp_src_20.2.tar.gz
	4、进入解压之后的文件
		./configure --prefix=/opt/app/erlang-20.2/erinstall --with-ssl -enable-threads -enable-smmp-support -enable-kernel-poll  --enable-hipe  --without-javac
	5、make
	6、make install
	7、在/etc/profile编辑	
	8、source /etc/profile
	9、erl

	#5.2.2 安装rabbitmq3.7.6
	1、wget https://github.com/rabbitmq/rabbitmq-server/releases/download/.7.6/rabbitmq-server-generic-unix-3.7.6.tar.xz
	2、
		xz -d rabbitmq-server-generic-unix-3.7.6.tar.xz 
		tar -xvf rabbitmq-server-generic-unix-3.7.6.tar
	3、进入sbin目录
		cd rabbitmq_server-3.7.6/sbin
	4、启用web管理界面
		rabbitmq-plugins enable rabbitmq_management
	5、启动
		rabbitmq-server -detached
	6、添加用户
		rabbitmqctl add_user admin admin
	7、设置权限
		rabbitmqctl set_user_tags admin administrator
		rabbitmqctl  set_permissions -p "/" admin ".*" ".*" ".*" 
	8、启动
		ip:15672

	开放端口
		firewall-cmd --zone=public --add-port=15672/tcp --permanent
		firewall-cmd --reload
		systemctl status firewalld.service

	原文：https://blog.csdn.net/hu19921016/article/details/81094463

	#5.3 zookeeper集群安装

		zoo.cfg配置文件

			# The number of milliseconds of each tick
			tickTime=2000
			# The number of ticks that the initial
			# synchronization phase can take
			initLimit=10
			# The number of ticks that can pass between
			# sending a request and getting an acknowledgement
			syncLimit=5
			# the directory where the snapshot is stored.
			# do not use /tmp for storage, /tmp here is just
			# example sakes.
			dataDir=/opt/app/zookeeper-3.4.13/data
			# the port at which the clients will connect
			clientPort=2181
			# the maximum number of client connections.
			# increase this if you need to handle more clients
			#maxClientCnxns=60
			#
			# Be sure to read the maintenance section of the
			# administrator guide before turning on autopurge.
			#
			# http://zookeeper.apache.org/doc/current/zookeeperAdmin.html#sc_maintenance
			#
			# The number of snapshots to retain in dataDir
			#autopurge.snapRetainCount=3
			# Purge task interval in hours
			# Set to "0" to disable auto purge feature
			#autopurge.purgeInterval=1
			server.0=ebooc:2888:3888
			server.1=eboob:2888:3888
			server.2=ebooa:2888:3888

		在每台机器下设置
			mkdir data
			cd data
			vim myid
		0或者1或者2等，与server.0和主机名对应

		开放端口
			firewall-cmd --zone=public --add-port=2181/tcp --permanent
			firewall-cmd --zone=public --add-port=2888/tcp --permanent
			firewall-cmd --zone=public --add-port=3888/tcp --permanent
			firewall-cmd --reload
			systemctl status firewalld.service

		查看zookeeper的启动状态

			/opt/app/zookeeper-3.4.13/bin/zkServer.sh start
			/opt/app/zookeeper-3.4.13/bin/zkServer.sh status
		应该是一个leader和2个follower

	#5.4 部署一个storm集群 

	1、修改storm配置文件（在每一台电脑中配置）

		mkdir /opt/app/apache-storm-1.2.2/data

		########### These MUST be filled in for a storm configuration
		 storm.zookeeper.servers:
		     - "192.168.254.139"
		     - "192.168.254.138"
		     - "192.168.254.137"
		
		 nimbus.seeds: ["192.168.254.139"]
		 storm.local.dir: "/opt/app/apache-storm-1.2.2/data"
		 ui.port: 8888
		 supervisor.slots.ports:
		     - 6700
		     - 6701
		     - 6702
		     - 6703

	2、启动storm集群和ui界面

		一个节点（139），/opt/app/apache-storm-1.2.2/bin/storm nimbus >/dev/null 2>&1 &
		三个节点，/opt/app/apache-storm-1.2.2/bin/storm supervisor >/dev/null 2>&1 &
		一个节点（139），/opt/app/apache-storm-1.2.2/bin/storm ui >/dev/null 2>&1 &
		三个节点（138、137），/opt/app/apache-storm-1.2.2/bin/storm logviewer >/dev/null 2>&1 &

	3、访问一下ui界面，8080端口

		http://192.168.254.139:8888/index.html

	## 5.5 安装mysql ##
	
		下载 mysql80-community-release-el7-2.noarch.rpm
		
		rpm -ivh mysql80-community-release-el7-2.noarch.rpm
		
		通过以下命令，完成对 mysql 数据库的初始化和相关配置
		
		mysqld --initialize;
		chown mysql:mysql /var/lib/mysql -R;
		systemctl start mysqld.service;
		systemctl  enable mysqld;
		
		通过 cat /var/log/mysqld.log | grep password 命令查看数据库的密码
		
		通过 mysql -uroot -p 敲回车键进入数据库登陆界面
		
		通过 ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'FEIfei123321!'; 命令来修改密码
		
		通过 exit; 命令退出 MySQL，然后通过新密码再次登陆
		
		# MySQL8.0允许外部访问 #
		登进MySQL之后，
		
		输入以下语句，进入mysql库：    
			use mysql
		
		更新域属性，'%'表示允许外部访问：   
			update user set host='%' where user ='root';
		
		执行以上语句之后再执行：     
			FLUSH PRIVILEGES;
		
		再执行授权语句：    
			GRANT ALL PRIVILEGES ON *.* TO 'root'@'%'WITH GRANT OPTION;
			然后外部就可以通过账户密码访问了。
		
		其它说明：      
			FLUSH PRIVILEGES; 命令本质上的作用是：将当前user和privilige表中的用户信息/权限设置从mysql库(MySQL数据库的内置库)中提取到内存里。MySQL用户数据和权限有修改后，希望在"不重启MySQL服务"的情况下直接生效，那么就需要执行这个命令。通常是在修改ROOT帐号的设置后，怕重启后无法再登录进来，那么直接flush之后就可以看权限设置是否生效。而不必冒太大风险。
		
		原文：https://blog.csdn.net/h996666/article/details/80921913 
		https://blog.csdn.net/weixin_42266606/article/details/80879571
