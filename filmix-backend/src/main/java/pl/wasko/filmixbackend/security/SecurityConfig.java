package pl.wasko.filmixbackend.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserDetailService userDetailService;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.antMatchers(HttpMethod.POST, "/movies").hasAuthority("admin");
                    auth.antMatchers(HttpMethod.POST, "/actors").hasAuthority("admin");
                    auth.antMatchers(HttpMethod.POST, "/roles").hasAuthority("admin");
                    auth.antMatchers(HttpMethod.POST, "/news").hasAuthority("admin");
                    auth.antMatchers(HttpMethod.POST, "/categories").hasAuthority("admin");
                    auth.antMatchers(HttpMethod.GET, "/roles").hasAuthority("admin");
                    auth.antMatchers(HttpMethod.GET, "/users").hasAuthority("admin");

                    auth.antMatchers(HttpMethod.POST, "/rates").hasAnyAuthority("admin","user");
                    auth.antMatchers(HttpMethod.POST, "/opinions").hasAnyAuthority("admin","user");

                    auth.antMatchers(HttpMethod.GET, "/movies").permitAll();
                    auth.antMatchers(HttpMethod.GET, "/opinions").permitAll();
                    auth.antMatchers(HttpMethod.GET, "/rates").permitAll();
                    auth.antMatchers(HttpMethod.GET, "/news").permitAll();
                    auth.antMatchers(HttpMethod.GET, "/categories").permitAll();

                    auth.antMatchers(HttpMethod.POST, "/registration").permitAll();
                    auth.antMatchers(HttpMethod.POST, "/users").permitAll();
                })
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
