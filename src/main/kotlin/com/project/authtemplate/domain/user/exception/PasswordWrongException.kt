package com.project.authtemplate.domain.user.exception

import com.project.authtemplate.domain.user.exception.error.UserError
import com.project.authtemplate.global.exception.BusinessException

object PasswordWrongException : BusinessException(UserError.PASSWORD_WRONG) {

    private fun readResolve(): Any = PasswordWrongException

    val EXCEPTION: PasswordWrongException = PasswordWrongException

}