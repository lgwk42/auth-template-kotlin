package com.project.authtemplate.global.security.jwt.exception

import com.project.authtemplate.global.exception.BusinessException
import com.project.authtemplate.global.security.jwt.exception.error.JwtTokenError

object TokenTypeException : BusinessException(JwtTokenError.JWT_TOKEN_ERROR) {

    private fun readResolve(): Any = TokenTypeException

    val EXCEPTION: TokenTypeException = TokenTypeException

}