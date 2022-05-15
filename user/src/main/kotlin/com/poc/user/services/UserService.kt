package com.poc.user.services

import com.poc.user.dto.UserDTO
import com.poc.user.dto.UserRequestDTO
import com.poc.user.dto.UserUpdateRequestDTO
import com.poc.user.dto.extensions.toUserSave
import com.poc.user.dto.extensions.toUserUpdate
import com.poc.user.handler.Exceptions.DatabaseException
import com.poc.user.repositories.UserRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository) {

    fun save(userRequest: UserRequestDTO): UserDTO {
        val userSaved = repository.save(userRequest.toUserSave());
        return UserDTO(userSaved.userId, userSaved.username, userSaved.email);
    }

    fun update(id: Long, userUpdateRequest: UserUpdateRequestDTO): UserDTO {
        val userUpdate = repository.findByIdOrNull(id) ?: throw DatabaseException("Recurso não encontrado");
        userUpdate.username = userUpdateRequest.username
        userUpdate.email = userUpdateRequest.email;
        userUpdate.password = userUpdateRequest.password;
        val userSaved = repository.save(userUpdate);
        return UserDTO(userSaved.userId, userSaved.username, userSaved.email);
    }

}