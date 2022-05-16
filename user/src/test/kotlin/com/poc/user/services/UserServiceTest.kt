package com.poc.user.services

import com.poc.user.creators.UserCreator
import com.poc.user.handler.Exceptions.DatabaseException
import com.poc.user.model.User
import com.poc.user.repositories.UserRepository
import com.poc.user.repositories.specification.UserSpecification
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.Optional

@ExtendWith(SpringExtension::class)
class UserServiceTest {

    val repository: UserRepository = mockk();
    val service = UserService(repository);
    val userCreator = UserCreator();


    @Test
    fun saveShouldReturnUserDTO_WhenSucessfull() {
        every { repository.save(any()) } returns userCreator.createUserSaved();
        val userSaved = service.save(userCreator.createUserRequest())
        assertEquals(userCreator.createUserDTO(), userSaved)
    }

    @Test
    fun updateShouldReturnDatabaseException_WhenDataIsInvalid() {
        every { repository.findByIdOrNull(1) } throws DatabaseException("Recurso não encontrado");
        assertThrows<DatabaseException> { service.update(1, userCreator.createUserUpdateRequest()) }
    }

    @Test
    fun updateShouldReturnUserDTO_WhenSucessfull() {
        every { repository.findByIdOrNull(1) } returns userCreator.createUserSaved()
        every {repository.save(any()) } returns userCreator.createUserSaved()
        val userSaved = service.update(1, userCreator.createUserUpdateRequest())
        assertEquals(userCreator.createUserDTO(), userSaved)
    }

    @Test
    fun removeShouldReturnDatabaseException_WhenDataIsInvalid() {
        every { repository.findByIdOrNull(1) } throws DatabaseException("Recurso não encontrado");
        assertThrows<DatabaseException> { service.remove(1) }
    }

}