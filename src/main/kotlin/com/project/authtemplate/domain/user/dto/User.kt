package com.project.authtemplate.domain.user.dto

import com.project.authtemplate.domain.user.domain.entity.UserEntity
import com.project.authtemplate.domain.user.domain.enum.UserRole
import org.springframework.stereotype.Component

data class User(

    val email: String,
    val name: String,
    val password: String,
    val userRole: UserRole

){

    @Component
    companion object{
        fun toUser(userEntity: UserEntity): User {
            return User(
                email = userEntity.email,
                name = userEntity.name,
                password = userEntity.password,
                userRole = userEntity.userRole
            )
        }

        fun toEntity(user: User): UserEntity {
            return UserEntity(
                email = user.email,
                name = user.name,
                password = user.password,
                userRole = user.userRole
            )
        }
    }

}