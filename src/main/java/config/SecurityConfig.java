//package config;
//
//import com.todo.service.CustomUserDetailsService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                .authorizeHttpRequests(auth-> auth
//                                .antMatchers("/login", "/register", "/css/**", "/js/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .permitAll()
//                );
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    // UserDetailsService bean’i ekleyebilirsiniz
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new CustomUserDetailsService();
//    }
//}