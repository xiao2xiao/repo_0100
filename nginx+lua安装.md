
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
