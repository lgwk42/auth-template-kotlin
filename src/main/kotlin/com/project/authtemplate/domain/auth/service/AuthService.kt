package com.project.authtemplate.domain.auth.service

import com.project.authtemplate.domain.auth.client.request.RefreshTokenRequest
import com.project.authtemplate.domain.auth.client.request.SignInRequest
import com.project.authtemplate.domain.auth.client.request.SignUpRequest
import com.project.authtemplate.domain.auth.service.response.JsonWebTokenResponse
import com.project.authtemplate.domain.auth.service.response.RefreshTokenResponse
import com.project.authtemplate.domain.user.domain.enum.UserRole
import com.project.authtemplate.domain.user.domain.repository.jpa.UserJpaRepository
import com.project.authtemplate.domain.user.dto.User
import com.project.authtemplate.domain.user.exception.PasswordWrongException
import com.project.authtemplate.domain.user.exception.UserExistException
import com.project.authtemplate.domain.user.exception.UserNotFoundException
import com.project.authtemplate.global.security.jwt.JwtExtract
import com.project.authtemplate.global.security.jwt.JwtProvider
import com.project.authtemplate.global.security.jwt.exception.TokenExpiredException
import com.project.authtemplate.global.security.jwt.exception.error.JwtErrorType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService (
    private val userJpaRepository: UserJpaRepository,
    private val encoder: PasswordEncoder,
    private val jwtProvider: JwtProvider,
    private val jwtExtract: JwtExtract,
) {

    fun signUp(request: SignUpRequest) {
        if(userJpaRepository.existsByEmail(request.email)) {
            throw UserExistException.EXCEPTION
        }
        save(request)
    }

    fun signIn(request: SignInRequest): JsonWebTokenResponse {
        val user: User = userJpaRepository.findByEmail(request.email)
            .map { userEntity -> User.toUser(userEntity) }
            ?.orElseThrow { UserNotFoundException.EXCEPTION }
            ?: throw UserNotFoundException.EXCEPTION
        val password: String = user.password
        if (!encoder.matches(request.password, password))
            throw PasswordWrongException.EXCEPTION
        return JsonWebTokenResponse(
            accessToken = jwtProvider.generateAccessToken(request.email, user.userRole),
            refreshToken = jwtProvider.generateRefreshToken(request.email, user.userRole),
            userRole = user.userRole)
    }

    fun refresh(request: RefreshTokenRequest): RefreshTokenResponse {
        val got = jwtExtract.getToken(request.refreshToken)
        val user = jwtExtract.findUserByEmail(got)
        if (jwtExtract.checkTokenInfo(got) == JwtErrorType.ExpiredJwtException) {
            throw TokenExpiredException.EXCEPTION
        }
        return RefreshTokenResponse(
                jwtProvider.generateAccessToken(user.email, user.userRole),
            )
    }

    fun save(request: SignUpRequest){
        userJpaRepository.save(User.toEntity(User(
            email = request.email,
            name = request.name,
            password = encoder.encode(request.password),
            userRole = UserRole.USER
        )))
    }

}