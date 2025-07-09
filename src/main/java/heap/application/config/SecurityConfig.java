package heap.application.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import heap.application.security.filter.SecurityAuthenticationFilter;

@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    private final SecurityAuthenticationFilter securityAuthenticationFilter;
    private final AccessDeniedHandler accessDeniedHandler;

    public SecurityConfig(
            SecurityAuthenticationFilter securityAuthenticationFilter,
            AccessDeniedHandler accessDeniedHandler) {

        this.securityAuthenticationFilter = securityAuthenticationFilter;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationEntryPoint authenticationEntryPoint) throws Exception {
        http    
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .logout(logout -> logout.disable())
                .headers(headers -> headers
        .           httpStrictTransportSecurity(hsts -> hsts.disable())
                )
                .addFilterBefore(securityAuthenticationFilter, AuthorizationFilter.class)
                .authorizeHttpRequests(
                        matcher -> matcher
                                .requestMatchers(
                                    "/delete/**"
                                ).hasRole("ADMIN")
                                .requestMatchers(
                                    "/logout",
                                    "user/delete/**",
                                    "user/reviews",
                                    "user/favourites",
                                    "user/add_review"
                                ).authenticated()
                                .anyRequest().permitAll()
                                )
                .headers(header -> header.cacheControl(
                    cache -> cache.disable()
                ))
                .sessionManagement(
                        configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(
                        customizer -> customizer
                                .accessDeniedHandler(accessDeniedHandler)
                                .authenticationEntryPoint(authenticationEntryPoint));
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
    
}
