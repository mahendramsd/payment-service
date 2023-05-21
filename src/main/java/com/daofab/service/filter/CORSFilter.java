package com.daofab.service.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mahendra on 5/17/2023
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter{

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    HttpServletRequest req = (HttpServletRequest) request;
    httpResponse.setHeader("Access-Control-Allow-Origin", "*");
    httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
    httpResponse.setHeader("Access-Control-Allow-Headers", "bearer-access-token, Access-Control-Max-Age, Access-Control-Allow-Methods, Access-Control-Allow-Credentials, Access-Control-Expose-Headers, Access-Control-Allow-Origin, Content-Type, Access-Control-Allow-Headers, Authorization,  X-Requested-With");
    httpResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
    httpResponse.setHeader("Access-Control-Allow-Credentials", "false");
    httpResponse.setHeader("Access-Control-Max-Age", "4800");
    chain.doFilter(request, response);
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }
  @Override
  public void destroy() {
  }

}
