package ru.mityushkin.medicationmanagementbackend.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

//@Profile("!security_disabled")
//@Configuration
//@EnableWebSecurity
//class WebSecurityConfig(
//    private val userDetailsService: UserDetailsService
//) {
//    @Bean
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        http
//            .csrf { csrf -> csrf.disable() }
//            .authorizeHttpRequests { authz ->
//                authz
//                    .requestMatchers("/api/v1/users/registration").permitAll()
//                    .requestMatchers("/srv/v1/**").hasRole("ADMIN")
//                    .anyRequest().authenticated()
//            }
//            .httpBasic(Customizer.withDefaults())
//            .formLogin { form -> form.disable() }
//            .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
//
//        http.userDetailsService(userDetailsService)
//            .authenticationProvider(daoAuthenticationProvider())
//
//        return http.build()
//    }
//
//    @Bean
//    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
//
//    @Bean
//    fun daoAuthenticationProvider(): DaoAuthenticationProvider {
//        val provider = DaoAuthenticationProvider()
//        provider.setUserDetailsService(userDetailsService)
//        provider.setPasswordEncoder(passwordEncoder())
//        return provider
//    }
//}

@Profile("security_disabled")
@EnableWebSecurity
@Configuration
class TestWebSecurityDisabledConfig {

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { authz -> authz.anyRequest().permitAll() } // Разрешаем все запросы
            .csrf { csrf -> csrf.disable() } // Отключаем CSRF защиту
            .formLogin { form -> form.disable() } // Отключаем форму логина
            .httpBasic {httpBasic -> httpBasic.disable() } // Отключаем HTTP Basic аутентификацию
            .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }

        return http.build()
    }
}