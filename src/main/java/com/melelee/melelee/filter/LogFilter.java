package com.melelee.melelee.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Log filter.
 */
@WebFilter(filterName = "logFilter", urlPatterns = {"/*"})
@Order(1)
@Slf4j
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;


        String requestUri = request.getRequestURI();
        requestUri = request.getQueryString() == null ? requestUri : (requestUri + "?" + request.getQueryString());

        log.info("{} visit {} with {} method", request.getRemoteAddr(), requestUri, request.getMethod());
        chain.doFilter(request, response);
        log.info("{} visit {} with {} method finish", request.getRemoteAddr(), requestUri, request.getMethod());
    }

    @Override
    public void destroy() {
    }
}
