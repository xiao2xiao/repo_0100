# tomcat8.5进入管理界面 #      

1. 首先修改 conf/tomcat-users.xml 文件

		<role rolename="manager-gui"/>
		<role rolename="manager-script"/>
		<role rolename="admin-gui"/>
		<role rolename="admin-script"/>
		<user username="tomcat" password="tomcat" roles="tomcat,manager-gui,manager-script,admin-gui,admin-script"/>
2. 如果想要进入/manager/html页面，则新建或编辑conf/Catalina/localhost/manager.xml     
   如果想要进入/host-manager/html页面，则新建或编辑conf/Catalina/localhost/host-manager.xml

		<?xml version="1.0" encoding="UTF-8"?>
		<Context privileged="true" antiResourceLocking="false" docBase="${catalina.home}/webapps/manager">
			<Valve className="org.apache.catalina.valves.RemoteAddrValve" allow="^.*$" />
		</Context>
