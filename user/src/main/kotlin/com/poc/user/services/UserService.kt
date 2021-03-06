package com.poc.user.services

import com.poc.user.dto.UserDTO
import com.poc.user.dto.UserRequestDTO
import com.poc.user.dto.UserUpdateRequestDTO
import com.poc.user.dto.extensions.toUserSave
import com.poc.user.handler.Exceptions.DatabaseException
import com.poc.user.repositories.UserRepository
import com.poc.user.repositories.specification.UserSpecification
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val repository: UserRepository) {

    fun all(userId: Optional<Long>,
            username: Optional<String>,
            pageRequest: PageRequest) : Page<UserDTO> {
        return repository.findAll(UserSpecification(userId, username), pageRequest).map { u,  -> UserDTO(u.userId, u.username, u.email) };
    }

    fun save(userRequest: UserRequestDTO): UserDTO {
        val userSaved = repository.save(userRequest.toUserSave());
        return UserDTO(userSaved.userId, userSaved.username, userSaved.email);
    }

    fun update(id: Long, userUpdateRequest: UserUpdateRequestDTO): UserDTO {
        val userUpdate = repository.findByIdOrNull(id) ?: throw DatabaseException("Recurso não encontrado");
        userUpdate.username = userUpdateRequest.username
        userUpdate.email = userUpdateRequest.email;
        val userSaved = repository.save(userUpdate);
        return UserDTO(userSaved.userId, userSaved.username, userSaved.email);
    }

    fun remove(id: Long,) {
        repository.delete(repository.findByIdOrNull(id) ?: throw DatabaseException("Recurso não encontrado"));
    }

}