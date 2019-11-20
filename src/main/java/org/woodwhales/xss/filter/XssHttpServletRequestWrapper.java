package org.woodwhales.xss.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.text.StringEscapeUtils;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }
    
    @Override
    public String getHeader(String name) {
    	return StringEscapeUtils.escapeHtml4(super.getHeader(name));
    }
    
    @Override
    public String getQueryString() {
        return StringEscapeUtils.escapeHtml4(super.getQueryString());
    }

    @Override
    public String getParameter(String name) {
        return StringEscapeUtils.escapeHtml4(super.getParameter(name));
    }
    
    @Override
    public ServletInputStream getInputStream() throws IOException {
    	return super.getInputStream();
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] parameterValues = super.getParameterValues(name);
        if (Objects.isNull(parameterValues)) {
            return null;
        }
        
        for (int i = 0; i < parameterValues.length; i++) {
            parameterValues[i] = StringEscapeUtils.escapeHtml4(parameterValues[i]);
        }
        
        return parameterValues;
    }

}