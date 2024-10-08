package cloud.masteroflie.sgpa.config;

import cloud.masteroflie.sgpa.service.CustomUserDetailsService;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.lang.reflect.Method;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .sessionManagement(session -> session
                        .maximumSessions(1)
                        .expiredUrl("/auth")
                )
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form
                        .loginPage("/auth")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                        .failureUrl("/auth?erro=true")
                )
                .authorizeHttpRequests(authoriza -> authoriza
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("/admin/admin/departamento").hasRole("ADMIN")
                        .requestMatchers("/admin/admin/usuarios").hasRole("ADMIN")
                        .requestMatchers("/admin/servicos/**").hasRole("ADMIN")
                        .requestMatchers("/").authenticated()
                        .anyRequest().authenticated()

                )
                .build();
    }

}
