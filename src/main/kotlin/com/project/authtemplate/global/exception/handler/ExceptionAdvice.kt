package com.project.authtemplate.global.exception.handler

import com.project.authtemplate.global.exception.BusinessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionAdvice {

    data class ErrorResponse(
        val status: Int,
        val message: String
    )

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(ex: BusinessException): ResponseEntity<ErrorResponse> {
        println("Handling BusinessException: ${ex.message}")  // 디버깅용 로그 추가
        val response = ErrorResponse(
            status = ex.error.status.value(),
            message = ex.error.message
        )
        return ResponseEntity(response, ex.error.status)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponse> {
        println("Handling Generic Exception: ${ex.message}")  // 디버깅용 로그 추가
        val response = ErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = "An unexpected error occurred."
        )
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }

}