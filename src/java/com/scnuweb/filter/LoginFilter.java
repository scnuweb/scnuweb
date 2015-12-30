package com.scnuweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scnuweb.entity.User;


public class LoginFilter implements Filter{

	private String excludedPages[] ;
	private String redirectPage;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest resq, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpServletRequest request = (HttpServletRequest) resq;
		String requestPath = request.getServletPath();
		for(String excludedPage:excludedPages) {
			if(excludedPage.equals(requestPath)) chain.doFilter(resq, resp);
		}
		User user = (User)request.getSession().getAttribute("currentUser");
		if(user==null) {
			response.sendRedirect(redirectPage);
		} else chain.doFilter(resq, resp);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		excludedPages = filterConfig.getInitParameter("excludedPages").split(",");
		redirectPage = filterConfig.getInitParameter("redirectPage");
	}

}

