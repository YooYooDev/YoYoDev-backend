package com.yooyoo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.yooyoo.service.SessionService;

@WebFilter(
        urlPatterns = "/mobile/*",
        filterName = "mobileFilter",
        description = "Filter all mobile URLs"       
)
public class YooYooFilter implements Filter{
	
	@Autowired
	SessionService service;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
        System.out.println("It is YooYooFilter...");
        HttpServletRequest request = (HttpServletRequest) req;
        final String val = request.getHeader("accessToken");
        System.out.println("It is YooYooFilter...Token is :-"+val);
        boolean isVaidToken = service.findSessionByToken(val);
        if (!isVaidToken) {
            ((HttpServletResponse) res).sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
        } else {
            chain.doFilter(req, res);
        }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
