package com.project.authtemplate.domain.auth.client.api

import com.project.authtemplate.domain.auth.client.request.RefreshTokenRequest
import com.project.authtemplate.global.common.dto.response.BaseResponse
import com.project.authtemplate.global.common.dto.response.BaseResponseData
import com.project.authtemplate.domain.auth.client.request.SignInRequest
import com.project.authtemplate.domain.auth.client.request.SignUpRequest
import com.project.authtemplate.domain.auth.service.AuthService
import com.project.authtemplate.domain.auth.service.response.JsonWebTokenResponse
import com.project.authtemplate.domain.auth.service.response.RefreshTokenResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController (private val authService: AuthService) {

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    fun signUp(@RequestBody request: SignUpRequest): BaseResponse {
        authService.signUp(request)
        return BaseResponse.created("회원가입 성공")
    }

    @PostMapping("/sign-in")
    fun signIn(@RequestBody request: SignInRequest): BaseResponseData<JsonWebTokenResponse> {
        return BaseResponseData.ok(
            message = "로그인 성공",
            data = authService.signIn(request))
    }

    @PostMapping("/refresh")
    fun refresh(@RequestBody request: RefreshTokenRequest): BaseResponseData<RefreshTokenResponse> {
        return BaseResponseData.ok(
            message = "토큰 재발급 성공",
            data = authService.refresh(request))
    }

}