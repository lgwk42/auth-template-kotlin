package com.project.authtemplate.domain.user.exception

import com.project.authtemplate.domain.user.exception.error.UserError
import com.project.authtemplate.global.exception.BusinessException

object UserExistException : BusinessException(UserError.USER_EXIST) {

    private fun readResolve(): Any = UserExistException

    val EXCEPTION: UserExistException = UserExistException

}