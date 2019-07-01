## 1.nginx+lua的安装 ##

	## 安装编译依赖 ##

	yum install libreadline-dev libncurses5-dev libpcre3-dev libssl-dev perl

	yum -y install openssl openssl-devel pcre

	## 创建目录 ##

	mkdir nginx_lua

	cd /opt/software/nginx_lua   

	## 下载OpenResty压缩文件 ##

	wget https://openresty.org/download/openresty-1.13.6.2.tar.gz	

	tar -zxvf openresty-1.13.6.2.tar.gz -C /opt/app/nginx-lua/  

	## 编译安装Luajit ##
	
	cd /opt/app/nginx-lua/openresty-1.13.6.2/bundle/LuaJIT-2.1-20180420   
	
	make clean && make && make install    

	ln -sf luajit-2.1.0-alpha /usr/local/bin/luajit

	## 下载ngx_cache_purge模块，该模块用于清理nginx缓存 ##

	cd /opt/app/nginx-lua/openresty-1.13.6.2/bundle   

	wget https://github.com/FRiCKLE/ngx_cache_purge/archive/2.3.tar.gz

	tar -xvf 2.3.tar.gz

	## 下载nginx_upstream_check_module模块，该模块用于ustream健康检查 ##
	
	cd /opt/app/nginx-lua/openresty-1.13.6.2/bundle   

	wget https://github.com/yaoweibin/nginx_upstream_check_module/archive/v0.3.0.tar.gz

	tar -xvf v0.3.0.tar.gz		

	## 安装OpenResty(重点注意:–prefix中的路径) ##

	cd /opt/app/nginx-lua/openresty-1.13.6.2

	./configure --prefix=/opt/app/nginx-lua --with-http_realip_module --with-pcre --with-luajit --add-module=./bundle/ngx_cache_purge-2.3/ --add-module=./bundle/nginx_upstream_check_module-0.3.0/ -j2

	make && make install

	## 启动nginx

	/opt/app/nginx-lua/nginx/sbin/nginx

 	/opt/app/nginx-lua/nginx/sbin/nginx -s reload

	测试是否成功

	curl http://192.168.254.137:80/

## 2.nginx+lua开发hello world ##

	vi /opt/app/nginx-lua/nginx/conf/nginx.conf
	
	在http部分添加：
	
	lua_package_path "/opt/app/nginx-lua/lualib/?.lua;;";  
	lua_package_cpath "/opt/app/nginx-lua/lualib/?.so;;"; 

	/opt/app/nginx-lua/nginx/conf下，创建一个lua.conf
	
	server {  
	    listen       80;  
	    server_name  _;  
	}  
	
	在nginx.conf的http部分添加：
	
	include lua.conf;
	
	验证配置是否正确：

	/opt/app/nginx-lua/nginx/sbin/nginx -t

	在lua.conf的server部分添加：
	
	location /lua {  
	    default_type 'text/html';  
	    content_by_lua 'ngx.say("hello world")';  
	} 
	
	/opt/app/nginx-lua/nginx/sbin/nginx -t  
	
	重新nginx加载配置
	
	/opt/app/nginx-lua/nginx/sbin/nginx -s reload  
	
	访问http: http://192.168.254.137/lua

这是分发层的nginx+lua

下面是应用层的nginx+lua(app-nginx)

和分发层的一样，只不过端口需要改为8000

## 3.工程化的nginx+lua项目结构 ##

	项目工程结构
	
	hello
	    hello.conf     
	    lua              
	      hello.lua
	    lualib            
	      *.lua
	      *.so
	
	放在/opt/app/lua-pro/hello目录下
	
	/opt/app/nginx-lua/nginx/conf/nginx.conf
	
	worker_processes  2;  
	
	error_log  logs/error.log;  
	
	events {  
	    worker_connections  1024;  
	}  
	
	http {  
	    include       mime.types;  
	    default_type  text/html;  
	  
	    lua_package_path "/opt/app/lua-pro/hello/lualib/?.lua;;";  
	    lua_package_cpath "/opt/app/lua-pro/hello/lualib/?.so;;"; 
	    include /opt/app/lua-pro/hello/hello.conf;  
	}  
	
	/opt/app/lua-pro/hello/hello.conf
	
	server {  
	    listen       80;  
	    server_name  _;  
	  
	    location /hello {  
	        default_type 'text/html';  
	        content_by_lua_file /opt/app/lua-pro/hello/lua/hello.lua;  
	    }  
	}  
	
	cp -r /opt/app/nginx-lua/lualib /opt/app/lua-pro/hello/lualib/
