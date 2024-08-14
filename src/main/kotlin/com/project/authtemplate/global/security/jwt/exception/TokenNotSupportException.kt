package com.project.authtemplate.global.security.jwt.exception

import com.project.authtemplate.global.exception.BusinessException
import com.project.authtemplate.global.security.jwt.exception.error.JwtTokenError

object TokenNotSupportException : BusinessException(JwtTokenError.JWT_NOT_SUPPORT) {

    private fun readResolve(): Any = TokenNotSupportException

    val EXCEPTION: TokenNotSupportException = TokenNotSupportException

}