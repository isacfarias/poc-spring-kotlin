package com.poc.user.dto.extensions

import com.poc.user.dto.UserRequestDTO
import com.poc.user.dto.UserUpdateRequestDTO
import com.poc.user.model.User


fun UserRequestDTO.toUserSave() = User(
    username = username,
    email = email
)

fun UserUpdateRequestDTO.toUserUpdate() = User(
    username = username,
    email = email
)

