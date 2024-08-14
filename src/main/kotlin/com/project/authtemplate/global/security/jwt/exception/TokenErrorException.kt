package com.project.authtemplate.global.security.jwt.exception

import com.project.authtemplate.global.exception.BusinessException
import com.project.authtemplate.global.security.jwt.exception.error.JwtTokenError

object TokenErrorException : BusinessException(JwtTokenError.JWT_TOKEN_ERROR) {

    private fun readResolve(): Any = TokenErrorException

    val EXCEPTION: TokenErrorException = TokenErrorException

}