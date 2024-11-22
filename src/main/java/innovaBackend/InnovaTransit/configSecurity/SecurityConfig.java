//package innovaBackend.InnovaTransit.configSecurity;
//
//import innovaBackend.InnovaTransit.service.AuthService;
//
//import java.util.Arrays;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final AuthService authService;
//    private final JwtUtil jwtUtil;
//
//    public SecurityConfig(AuthService authService, JwtUtil jwtUtil) {
//        this.authService = authService;
//        this.jwtUtil = jwtUtil;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .requestMatchers("/api/auth/login").permitAll()
//                .requestMatchers("/api/auth/primeiro-acesso").permitAll()
//                .requestMatchers("/api/auth/altera-senha").permitAll()
//                .requestMatchers("/api/motorista/por-email").permitAll()
//                .requestMatchers("/api/folha-servico/obter-por-matricula-e-data").permitAll()
//                .requestMatchers("/api/folha-servico/iniciar").permitAll()
//                .requestMatchers("/api/folha-servico/por-data").permitAll()
//                .requestMatchers("/api/folha-servico/iniciar-tarefa").permitAll()
//                .requestMatchers("/api/folha-servico/finalizar-tarefa").permitAll()
//                .requestMatchers("/api/folha-servico/cancelar-tarefa").permitAll()
//                .requestMatchers("/api/folha-servico/finalizar").permitAll()
//                .requestMatchers("/api/empresa").permitAll()
//                .requestMatchers("/api/scheduler/schedule-task").permitAll()
//                .requestMatchers("/api/gerente/salvar").permitAll()
//                .requestMatchers("/api/scheduler").permitAll()
//                .requestMatchers("/api/motorista/**").permitAll()
//                .requestMatchers("/send-email").permitAll()
//                .requestMatchers("/api/motorista/**").hasRole("MOTORISTA")
//                .requestMatchers("/api/gerente/**").hasRole("GERENTE")
//                .anyRequest().authenticated()
//                .and()
//                .addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//    
//    @Bean
//    public JwtRequestFilter jwtRequestFilter() {
//        return new JwtRequestFilter(authService, jwtUtil);
//    }
//
//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.userDetailsService(authService).passwordEncoder(passwordEncoder());
//        return authenticationManagerBuilder.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}



//
//package innovaBackend.InnovaTransit.configSecurity;
//
//import innovaBackend.InnovaTransit.service.AuthService;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final AuthService authService;
//    private final JwtUtil jwtUtil;
//
//    public SecurityConfig(AuthService authService, JwtUtil jwtUtil) {
//        this.authService = authService;
//        this.jwtUtil = jwtUtil;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        // Configuração de CSRF desabilitada e configuração de permissões
//        http.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(authz -> authz
//                    .requestMatchers("/api/auth/login").permitAll()
//                    .requestMatchers("/api/auth/primeiro-acesso").permitAll()
//                    .requestMatchers("/api/auth/altera-senha").permitAll()
//                    .requestMatchers("/api/motorista/por-email").permitAll()
//                    .requestMatchers("/api/folha-servico/obter-por-matricula-e-data").permitAll()
//                    .requestMatchers("/api/folha-servico/iniciar").permitAll()
//                    .requestMatchers("/api/folha-servico/por-data").permitAll()
//                    .requestMatchers("/api/folha-servico/iniciar-tarefa").permitAll()
//                    .requestMatchers("/api/folha-servico/finalizar-tarefa").permitAll()
//                    .requestMatchers("/api/folha-servico/cancelar-tarefa").permitAll()
//                    .requestMatchers("/api/folha-servico/finalizar").permitAll()
//                    .requestMatchers("/api/empresa").permitAll()
//                    .requestMatchers("/api/scheduler/schedule-task").permitAll()
//                    .requestMatchers("/api/gerente/salvar").permitAll()
//                    .requestMatchers("/api/scheduler").permitAll()
//                    .requestMatchers("/api/motorista/**").permitAll()
//                    .requestMatchers("/send-email").permitAll()
//                    .requestMatchers("/api/motorista/**").hasRole("MOTORISTA")
//                    .requestMatchers("/api/gerente/**").hasRole("GERENTE")
//                    .anyRequest().authenticated()
//                )
//                .addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public JwtRequestFilter jwtRequestFilter() {
//        return new JwtRequestFilter(authService, jwtUtil);
//    }
//
//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.userDetailsService(authService).passwordEncoder(passwordEncoder());
//        return authenticationManagerBuilder.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}


package innovaBackend.InnovaTransit.configSecurity;

import innovaBackend.InnovaTransit.service.AuthService;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public SecurityConfig(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers("/api/auth/login").permitAll()
                .requestMatchers("/api/auth/primeiro-acesso").permitAll()
                .requestMatchers("/api/auth/altera-senha").permitAll()
                .requestMatchers("/api/motorista/por-email").permitAll()
                .requestMatchers("/api/folha-servico/por-data").permitAll()
                .requestMatchers("/api/folha-servico/obter-por-matricula-e-data").permitAll()
                .requestMatchers("/api/folha-servico/iniciar").permitAll()
                .requestMatchers("/api/folha-servico/iniciar-tarefa").permitAll()
                .requestMatchers("/api/folha-servico/finalizar-tarefa").permitAll()
                .requestMatchers("/api/folha-servico/cancelar-tarefa").permitAll()
                .requestMatchers("/api/folha-servico/finalizar").permitAll()
                .requestMatchers("/api/automacao/configurar").permitAll()
                .requestMatchers("/api/automacao/atualizar-dados/{idEmpresa}").permitAll()
                .requestMatchers("/api/empresa").permitAll()
                .requestMatchers("/api/motorista/**").permitAll()
                .requestMatchers("/send-email").permitAll()
                .requestMatchers("/api/motorista/**").hasRole("MOTORISTA")
                .requestMatchers("/api/gerente/**").hasRole("GERENTE")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    
    @Bean
    public JwtRequestFilter jwtRequestFilter() {
        return new JwtRequestFilter(authService, jwtUtil);
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(authService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
