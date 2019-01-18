package com.neno.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author: root
 * @Date: 2019/1/14 17:15
 */
@WebFilter(urlPatterns = "/*", filterName = "LogCostFilter2")
public class LogCostFilter2 implements Filter {

    private static Logger logger = LoggerFactory.getLogger(LogCostFilter2.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        long end = System.currentTimeMillis();
        logger.info("LogCostFilter2 cost time = " + (end - start) + " ms");
    }

    @Override
    public void destroy() {

    }
}
