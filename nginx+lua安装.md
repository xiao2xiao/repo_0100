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

## 4.部署分发层nginx以及基于lua完成基于商品id的定向流量分发 ##

	使用137:80做流量分发nginx，使用137:8000,138:8000做应用层nginx服务器

	cd /opt/app/lua-pro/hello/lualib/resty

	引入lua的http包
	
	从下面的网站下载之后，然后上传到上面的目录中
	https://github.com/ledgetech/lua-resty-http/blob/master/lib/resty/http_headers.lua
	https://github.com/ledgetech/lua-resty-http/blob/master/lib/resty/http.lua

	流量分发代码
	
	local uri_args = ngx.req.get_uri_args()
	local productId = uri_args["productId"]
	
	local hosts = {"192.168.254.137:8000","192.168.254.138:8000"}
	local hash = ngx.crc32_long(productId)
	local index  = (hash % 2) + 1
	local backend = "http://"..hosts[index]
	
	local requestPath = uri_args["requestPath"]
	requestPath = "/"..requestPath.."?productId="..productId
	
	local http = require("resty.http")
	local httpc = http:new()
	
	local resp,err = httpc:request_uri(backend, {
	    method = "GET",
	    path = requestPath
	})
	
	if not resp then
	    ngx.say("request error : ", err)
	    return
	end
	
	ngx.say(resp.body)
	
	httpc:close()

## 5.安装twemproxy插件 ##

	为redis主机群配置(在137下)
	cd /opt/app/twemproxy/
	
	wget https://github.com/twitter/twemproxy/archive/master.zip
	
	unzip master.zip
	
	cd /opt/app/twemproxy/twemproxy-master
	
	autoreconf -fvi
	
	./configure && make
	
	cd /opt/app/twemproxy/twemproxy-master/conf
	
	cp nutcracker.yml nutcracker.bak.yml
	
	vim nutcracker.yml
	 
	alpha:
	  listen: 192.168.254.137:7011
	  hash: fnv1a_64
	  distribution: ketama
	  auto_eject_hosts: true
	  redis: true
	  hash_tag: "::"
	  server_retry_timeout: 2000
	  server_failure_limit: 1
	  servers:
	   - 192.168.254.137:6380:1 redis-master01
	   - 192.168.254.137:6381:1 reids-master02
	
	cd /opt/app/twemproxy/twemproxy-master/src
	
	启动twemproxy   
	./nutcracker -d -c ../conf/nutcracker.yml
	
	ps -ef | grep nutcracker
	
	cd /opt/app/redis-4.0.2/
	
	启动redis
	
	src/redis-cli -h 192.168.254.137 -p 7011
	
	使用redis mget优化思路，使用mget可能使相同商品的属性在2个redis主实例上，
	twemproxy中配置一个东西，hash_tag: "::"
	那么hash的时候是按照::中间的值进行hash的，因此相同的id肯定路由到同一个主实例上，如下
	product:1:
	product_property:1: