package com.melelee.melelee.filter;

import com.melelee.melelee.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 记录网站访问历史,注册器名称为recordFilter,拦截的url为所有
 *
 * @author mengll
 * @create 2018-11-13 20:34
 **/
@WebFilter(filterName = "recordFilter", urlPatterns = {"/*"})
@Slf4j
public class RecordFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("recordFilter init");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		log.info("recordFilter   doFilter");
		log.info("getOsInfo {}", RequestUtils.getOsInfo((HttpServletRequest) servletRequest));
		log.info("getBrowserInfo {}", RequestUtils.getBrowserInfo((HttpServletRequest) servletRequest));
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
		log.info("recordFilter destroy");
	}
}
