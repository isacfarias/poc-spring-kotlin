package com.poc.user.creators

import com.poc.user.dto.UserDTO
import com.poc.user.dto.UserRequestDTO
import com.poc.user.dto.UserUpdateRequestDTO
import com.poc.user.model.User

class UserCreator {

    fun createUserRequest(): UserRequestDTO {
        return UserRequestDTO("Asdrubaldp", "asdrubaldo@mail.com");
    }

    fun createUserSave(): User {
        return User(createUserRequest().username, createUserRequest().email);
    }

    fun createUserSaved(): User {
        return User(1, createUserRequest().username, createUserRequest().email);
    }

   fun createUserDTO(): UserDTO {
       return UserDTO(createUserSaved().userId, createUserSaved().username, createUserSaved().email)
   }

    fun createUserUpdateRequest(): UserUpdateRequestDTO {
        return UserUpdateRequestDTO(createUserRequest().username, createUserRequest().email)
    }

}