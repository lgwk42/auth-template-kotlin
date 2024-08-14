package com.project.authtemplate.global.security.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.project.authtemplate.global.security.jwt.JwtExtract
import com.project.authtemplate.global.security.jwt.filter.CoroutineSecurityFilter
import com.project.authtemplate.global.security.jwt.filter.JwtAuthenticationFilter
import com.project.authtemplate.global.security.jwt.handler.JwtAuthenticationEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.context.SecurityContextHolder.MODE_INHERITABLETHREADLOCAL
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
class SecurityConfig (
    private val objectMapper: ObjectMapper,
    private val jwtEntryPoint: JwtAuthenticationEntryPoint,
    private val jwtExtract: JwtExtract,
    private val coroutineSecurityFilter: CoroutineSecurityFilter
) {

    companion object {
        private const val USER = "ROLE_USER"
        private const val TEACHER = "ROLE_TEACHER"
        private const val ADMIN = "ROLE_ADMIN"
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity
    ): SecurityFilterChain {

        SecurityContextHolder.setStrategyName(MODE_INHERITABLETHREADLOCAL)

        return http
            .csrf { it.disable() }
            .cors { corsConfigurationSource() }
            .formLogin { it.disable() }
            .securityContext { it.requireExplicitSave(true) }
            .sessionManagement { session ->
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            }
            .exceptionHandling { exceptionHandlingConfigurer: ExceptionHandlingConfigurer<HttpSecurity> ->
                exceptionHandlingConfigurer.authenticationEntryPoint(jwtEntryPoint)
            }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/swagger-ui/**", "/v3/**").permitAll()
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/check/generate").permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(JwtAuthenticationFilter(objectMapper, jwtExtract), UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(coroutineSecurityFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.addAllowedOriginPattern("*")
        corsConfiguration.addAllowedHeader("*")
        corsConfiguration.addAllowedMethod("*")
        corsConfiguration.allowCredentials = true

        val urlBasedCorsConfigurationSource = UrlBasedCorsConfigurationSource()
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration)

        return urlBasedCorsConfigurationSource
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

}
