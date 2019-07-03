# spring boot获取ApplicationContext上下文的方法 #

## 1.第一种 ##

	@Component
	public class BeanUtils implements ApplicationContextAware {
	    @Override
	    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	        if (SpringContext.getApplicationContext() == null) {
	            SpringContext.setApplicationContext(applicationContext);
	        }
	    }
	}
	
	
	
	public class SpringContext {
	
	    private static ApplicationContext applicationContext;
	
	    public static ApplicationContext getApplicationContext() {
	        return applicationContext;
	    }
	
	    public static void setApplicationContext(ApplicationContext applicationContext) {
	        SpringContext.applicationContext = applicationContext;
	    }
	
	    public static Object getBean(String name) {
	        return getApplicationContext().getBean(name);
	    }
	
	    public static <T> T getBean(Class<T> clazz) {
	        return getApplicationContext().getBean(clazz);
	    }
	
	    public static <T> T getBean(String name, Class<T> clazz) {
	        return getApplicationContext().getBean(name, clazz);
	    }
	}


## 2.第二种 ##

	@Component
	public class InitListener implements ServletContextListener {
	    @Override
	    public void contextInitialized(ServletContextEvent sce) {
	        ServletContext servletContext = sce.getServletContext();
	        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	        SpringContext.setApplicationContext(applicationContext);
	    }
	
	    @Override
	    public void contextDestroyed(ServletContextEvent sce) {
	    }
	}
	
	
	public class SpringContext {
	
	    private static ApplicationContext applicationContext;
	
	    public static ApplicationContext getApplicationContext() {
	        return applicationContext;
	    }
	
	    public static void setApplicationContext(ApplicationContext applicationContext) {
	        SpringContext.applicationContext = applicationContext;
	    }
	
	    public static Object getBean(String name) {
	        return getApplicationContext().getBean(name);
	    }
	
	    public static <T> T getBean(Class<T> clazz) {
	        return getApplicationContext().getBean(clazz);
	    }
	
	    public static <T> T getBean(String name, Class<T> clazz) {
	        return getApplicationContext().getBean(name, clazz);
	    }
	}

	在启动类里面写入该bean

	@Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new InitListener());
        return servletListenerRegistrationBean;
    }

	引入依赖

		<dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.1.3.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
            <version>5.1.3.RELEASE</version>
        </dependency>

## 区别：第一种可以在单元测试中使用，第二种不能 ##