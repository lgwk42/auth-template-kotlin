package com.project.authtemplate.domain.auth.service.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.project.authtemplate.domain.user.domain.enum.UserRole
import lombok.Builder

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
class JsonWebTokenResponse(
    val accessToken: String,
    val refreshToken: String,
    val userRole: UserRole
)