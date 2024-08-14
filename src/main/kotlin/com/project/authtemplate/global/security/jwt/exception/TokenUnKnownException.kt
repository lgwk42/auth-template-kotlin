package com.project.authtemplate.global.security.jwt.exception

import com.project.authtemplate.global.exception.BusinessException
import com.project.authtemplate.global.security.jwt.exception.error.JwtTokenError

object TokenUnKnownException: BusinessException(JwtTokenError.JWT_UNKNOWN_EXCEPTION) {
    private fun readResolve(): Any = TokenUnKnownException
    val EXCEPTION: TokenUnKnownException = TokenUnKnownException
}