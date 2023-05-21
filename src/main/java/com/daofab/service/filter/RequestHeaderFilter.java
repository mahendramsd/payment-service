package com.daofab.service.filter;

import com.daofab.service.config.SecurityConfig;
import com.daofab.service.domain.User;
import com.daofab.service.service.UserService;
import com.daofab.service.util.Constants;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Mahendra on 5/17/2023
 */
@Component
public class RequestHeaderFilter implements Filter {

    @Autowired
    private SecurityConfig jwtTokenConfig;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(RequestHeaderFilter.class);

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String hashToken = jwtTokenConfig.getBearerToken(httpRequest);
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String username = null;
        if (hashToken != null) {
            /**
             * Remove Bearer word and get only the Token
             */
            try {
                username = jwtTokenConfig.getUsernameFromToken(hashToken);
            } catch (IllegalArgumentException e) {
                logger.error("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                logger.error("JWT Token has expired");
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                Optional<User> userOptional = userService.findByUser(username, hashToken);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    if (jwtTokenConfig.validateToken(hashToken, user)) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                user, null, user.getAuthorities());
                        usernamePasswordAuthenticationToken
                                .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                        httpRequest.setAttribute(Constants.USER_ID, user.getId());
                        httpRequest.setAttribute(Constants.BEARER_ACCESS_TOKEN, hashToken);
                    } else {
                        HttpServletResponse httpResponse = (HttpServletResponse) response;
                        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                                "JWT token replaced or expired.");
                        return;
                    }
                } else {
                    HttpServletResponse httpResponse = (HttpServletResponse) response;
                    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                            "Valid User token Not found.");
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {

    }


}
