package com.project.authtemplate.global.exception

import com.project.authtemplate.global.exception.error.ErrorProperty

open class BusinessException(val error: ErrorProperty) : RuntimeException() {

}