package com.biuropracy.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
//przekierowywanie użytkowników do odpowiednich adresów URL na podstawie roli
@Configuration
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    protected void handle(HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse,
                          Authentication authentication) throws IOException {

        String targetUrl = determineTargetUrl(authentication);

        if (httpServletResponse.isCommitted()) {
            return;
        }

        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {

        String url = "/login?error=true";

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> role = new ArrayList<String>();
        for (GrantedAuthority a : authorities) {
            role.add(a.getAuthority());
        }

        if (role.contains("ADMIN")) {
            url = "/admin/adminHome";
        } else if (role.contains("USER")) {
            url = "/user/userHome";
        } else if (role.contains("EMPLOYER")) {
            url = "/employer/employerHome/";
        }
        return url;
    }
}
