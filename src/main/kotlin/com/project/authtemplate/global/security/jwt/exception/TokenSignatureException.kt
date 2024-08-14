package com.project.authtemplate.global.security.jwt.exception

import com.project.authtemplate.global.exception.BusinessException
import com.project.authtemplate.global.security.jwt.exception.error.JwtTokenError

object TokenSignatureException : BusinessException(JwtTokenError.JWT_TOKEN_SIGNATURE_ERROR) {
    private fun readResolve(): Any  = TokenSignatureException
    val EXCEPTION : TokenSignatureException = TokenSignatureException
}