package ru.neoflex.nfcore.base.configuration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ru.neoflex.nfcore.base.services.UserDetail;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        http
                    .httpBasic()
                .and()
                     .authorizeRequests()
                     .anyRequest().authenticated()
                .and()
                     .cors().configurationSource(source)
                .and()
                     .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).deleteCookies("JSESSIONID").clearAuthentication(true).invalidateHttpSession(true).logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                .and()
                     .csrf().disable();
    }

    @Configuration
    protected static class BaseConfiguration extends
            GlobalAuthenticationConfigurerAdapter {

        @Autowired
        private UserDetail userDetailService;

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.eraseCredentials(false).userDetailsService(userDetailService);
        }
    }

    @Configuration
    protected static class LdapConfiguration extends
            GlobalAuthenticationConfigurerAdapter {

        @Value("${ldap.host:}")
        private String host;
        @Value("${ldap.domain:}")
        private String domain;
        @Value("${ldap.port:}")
        private String port;
        @Value("${ldap.base:}")
        private String base;

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            if (!StringUtils.isAnyEmpty(host, port, domain, base)) {
                final String url = "ldap://" + host + ":" + port;
                final ActiveDirectoryLdapAuthenticationProvider provider =
                        new ActiveDirectoryLdapAuthenticationProvider(domain, url, base);
                final LdapUserDetailsMapper ldapUserDetailsMapper = new LdapUserDetailsMapper();

                provider.setUserDetailsContextMapper(new UserDetailsContextMapper() {

                    @Override
                    public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
                        UserDetails userDetails = ldapUserDetailsMapper.mapUserFromContext(ctx, username, authorities);

                        Collection<GrantedAuthority> au = new ArrayList<>();
                        au.add(new SimpleGrantedAuthority("ADMIN"));

                        for (GrantedAuthority grantedAuthority : userDetails.getAuthorities()) {
                            au.add(new SimpleGrantedAuthority(grantedAuthority.getAuthority()));
                        }

                        LdapUserDetailsImpl.Essence essence = new LdapUserDetailsImpl.Essence((LdapUserDetails) userDetails);
                        essence.setAuthorities(au);
                        return essence.createUserDetails();
                    }

                    @Override
                    public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
                        ldapUserDetailsMapper.mapUserToContext(user, ctx);
                    }
                });
                auth.eraseCredentials(false).authenticationProvider(provider);
            }
        }
}
}

