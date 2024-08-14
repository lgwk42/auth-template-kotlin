package com.project.authtemplate.domain.user.exception

import com.project.authtemplate.domain.user.exception.error.UserError
import com.project.authtemplate.global.exception.BusinessException

object UserNotFoundException : BusinessException(UserError.USER_NOT_FOUND) {

    private fun readResolve(): Any = UserNotFoundException

    val EXCEPTION: UserNotFoundException = UserNotFoundException

}