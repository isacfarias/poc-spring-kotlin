package com.poc.user.services

import com.poc.user.dto.UserDTO
import com.poc.user.dto.UserRequestDTO
import com.poc.user.model.User
import com.poc.user.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository) {

    fun save(userRequest: UserRequestDTO): UserDTO {
        val userSaved = repository.save(User(userRequest.username, userRequest.email, userRequest.password));
        return UserDTO(userSaved.userId, userSaved.username, userSaved.email);
    }




}