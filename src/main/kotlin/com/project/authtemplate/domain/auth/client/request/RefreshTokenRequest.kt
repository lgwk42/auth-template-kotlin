package com.project.authtemplate.domain.auth.client.request

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

data class RefreshTokenRequest(
    @JsonProperty("refreshToken")
    val refreshToken: @NotBlank String
)