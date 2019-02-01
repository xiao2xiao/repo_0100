# centos 7默认是没有开启FTP服务的，我们必须手动开启。 #


具体安装开启步骤如下：				

1. 安装vsftpd
     
		yum -y install vsftpd       
		-y表示不用输入确定，直接一路安装到底

2. 设置开机启动ftp
    
		systemctl enable vsftpd

3. 启动ftp  
  
		systemctl start vsftpd.service

4. 查看ftp是否启动  
   
		ps -e |grep ftp

5. 开启防火墙，开放21端口
     
		firewall-cmd --zone=public --add-port=21/tcp --permanent    
		firewall-cmd --permanent --zone=public --add-service=ftp    
		firewall-cmd --reload  

6. 添加ftp用户
 
		useradd -g root -d /opt/pic/ftpuser -s /usr/sbin/nologin ftpuser
		注：表示新增一个ftpuser（用户名），
		且指定上传目录在/opt/pic/ftpuser下,
		/opt/pic 是我自己已经存在的路径名。这里可以根据自己随意设置

7. 设置用户密码  
 
		passwd ftpuser
 
8. 配置selinux 允许ftp访问home和外网访问

		setsebool -P allow_ftpd_full_access on
		setsebool -P ftp_home_dir on

9. 设置权限    

		chown -R ftpuser:root /opt/pic/ftpuser

10. 修改vsftp配置文件，禁用匿名登录 
  
		路径：/etc/vsftpd/vsftpd.conf
		anonymous_enable=YES改为： anonymous_enable=NO

11. 其他命令：

		重启 :     service vsftpd restart
		启动/停止: service vsftpd start/stop
		状态:      service vsftpd status   
		修改权限:    chmod 777 -R /opt/pic/ftpuser


		https://blog.csdn.net/u011939264/article/details/84527684
