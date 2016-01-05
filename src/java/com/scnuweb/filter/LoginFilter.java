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
import com.scnuweb.util.StaticVar;


public class LoginFilter implements Filter{

	private String excludedPages[] ;
	private String redirectBaseUrl;
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
			if(excludedPage.equals(requestPath)) {
				chain.doFilter(resq, resp);
				return;
			}
		}
		User user = (User)request.getSession().getAttribute("currentUser");
		if(user==null) {
			response.sendRedirect(redirectBaseUrl+"login.html");
		} else  {
			if(user.getUserType()==StaticVar.USER_TYPE_ADMIN) {
				if(!requestPath.startsWith("/admin/"))response.sendRedirect(redirectBaseUrl+"admin/index.html");
				else chain.doFilter(resq, resp);
			} else {
				if(!requestPath.startsWith("/candidate/"))response.sendRedirect(redirectBaseUrl+"candidate/index.html");
				else chain.doFilter(resq, resp);
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		excludedPages = filterConfig.getInitParameter("excludedPages").split(",");
		redirectBaseUrl = filterConfig.getInitParameter("redirectBaseUrl");
	}

}

