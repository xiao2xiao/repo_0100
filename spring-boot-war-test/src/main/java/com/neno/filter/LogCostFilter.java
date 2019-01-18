package com.neno.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author: root
 * @Date: 2019/1/14 16:58
 */
public class LogCostFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(LogCostFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        long end = System.currentTimeMillis();
        logger.info("LogCostFilter execute cost = " + (end - start) + "ms");
    }

    @Override
    public void destroy() {

    }
}
