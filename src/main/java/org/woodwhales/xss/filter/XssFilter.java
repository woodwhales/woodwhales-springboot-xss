package org.woodwhales.xss.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

/**
 * 如果配置的是："/*", 那么注意过滤掉静态文件的拦截
 * @author woodwhales.github.io
 *
 */
@WebFilter(filterName = "xssFilter", urlPatterns = "/*", asyncSupported = true)
public class XssFilter implements Filter {
	
	private static List<String> exclusionsUrls = Lists.newArrayList(new String[] {".js",".gif",".jpg",".png",".css",".ico"});
	
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
	
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        
        String path = request.getServletPath();
        
        if(CollectionUtils.isNotEmpty(exclusionsUrls)) {
        	for (String exclusionsUrl : exclusionsUrls) {
    	        if (path.contains(exclusionsUrl)) {
    	        	filterChain.doFilter(servletRequest,servletResponse);
    	        	return;
    	        }
        	}
        }
        
        XssHttpServletRequestWrapper xssHttpServletRequestWrapper = new XssHttpServletRequestWrapper(request);
        filterChain.doFilter(xssHttpServletRequestWrapper, servletResponse);
    }
    
    @Override
    public void destroy() {

    }
}