package be.vdab.cultuurhuis.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    JdbcDaoImpl jdbcDaoImpl(DataSource dataSource){
        var impl = new JdbcDaoImpl();
        impl.setDataSource(dataSource);
        impl.setUsersByUsernameQuery("select gebruikersnaam as username, paswoord as password" +
                ", 'true' as enabled from klanten where gebruikersnaam = ?");
//        impl.setEnableAuthorities(false);
//        SecurityContextHolder.getContext().setAuthentication(
//                new UsernamePasswordAuthenticationToken(username, null, null));
        return impl;
    }

    @Override
    public void configure(WebSecurity web){
        web.ignoring().mvcMatchers("/images/**")
                .mvcMatchers("/css/**")
                .mvcMatchers("/js/**");
    }

    protected void configure(HttpSecurity http) throws Exception{
        http.formLogin(login -> login.loginPage("/login").defaultSuccessUrl("/bevestig"));
        http.authorizeRequests(requests -> requests
                .mvcMatchers("/","/login","/genres/**","/reserveren/**","/mandje/**","/nieuweklant/**").permitAll()
                .mvcMatchers("/**").authenticated());
    }

}
