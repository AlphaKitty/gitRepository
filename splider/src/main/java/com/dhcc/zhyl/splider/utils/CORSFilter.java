package com.dhcc.zhyl.splider.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 * 解决AJAX跨域请求报错及sessionid丢失问题
 * 
 * @version 1.0
 */
@WebFilter(filterName = "CORSFilter", urlPatterns = "/*")
public class CORSFilter implements Filter {
	public CORSFilter() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");

		httpServletResponse.setHeader("Access-Control-Allow-Headers", "User-Agent,Origin,Cache-Control,Content-type,Date,Server,withCredentials,AccessToken");

		httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");

		httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");

		httpServletResponse.setHeader("Access-Control-Max-Age", "1209600");

		httpServletResponse.setHeader("Access-Control-Allow-Headers",
				"Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");

		httpServletResponse.setHeader("Access-Control-Expose-Headers", "accesstoken");

		httpServletResponse.setHeader("Access-Control-Request-Headers", "accesstoken");

		httpServletResponse.setHeader("Expires", "-1");

		httpServletResponse.setHeader("Cache-Control", "no-cache");

		httpServletResponse.setHeader("pragma", "no-cache");

		httpServletResponse.setHeader("P3P", "CP=CAO PSA OUR");

		httpServletResponse.setHeader("XDomainRequestAllowed", "1");

		chain.doFilter(request, response);

	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

	public void destroy() {
	}

}
