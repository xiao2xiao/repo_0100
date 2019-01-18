# 在tomcat上部署spring boot项目


## 1）在启动类中也就是main函数所在的类继承类SpringBootServletInitializer
## 2）然后重写configure（）方法
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

        return builder.sources(主类名.class);
    }
    
## 3）在pom文件中修改依赖

      <packaging>war</packaging>


        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-tomcat</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.1.0</version>
            <scope>provided</scope>
        </dependency>
    
## 4）该项目的端口一定要和tomcat一致
    
    
    