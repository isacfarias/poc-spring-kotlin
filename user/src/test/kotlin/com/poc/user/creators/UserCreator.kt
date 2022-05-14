package com.poc.user.creators

import com.poc.user.dto.UserDTO
import com.poc.user.dto.UserRequestDTO
import com.poc.user.model.User

class UserCreator {

    fun createUserRequest(): UserRequestDTO {
        return UserRequestDTO("Asdrubaldp", "asdrubaldo@mail.com", "@sd4b@d0");
    }

    fun createUserSave(): User {
        return User(createUserRequest().username, createUserRequest().email, createUserRequest().password);
    }

    fun createUserSaved(): User {
        return User(1, createUserRequest().username, createUserRequest().email, createUserRequest().password);
    }

   fun createUserDTO(): UserDTO {
       return UserDTO(createUserSave().userId, createUserSave().username, createUserSave().password)
   }

}