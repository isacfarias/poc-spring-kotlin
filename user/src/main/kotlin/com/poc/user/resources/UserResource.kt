package com.poc.user.resources

import com.poc.user.dto.UserDTO
import com.poc.user.dto.UserRequestDTO
import com.poc.user.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserResource(private val service: UserService) {

    @PostMapping
    fun create(@RequestBody userRequestDTO: UserRequestDTO): ResponseEntity<UserDTO> {
        val userSaved = service.save(userRequestDTO)
        return ResponseEntity(userSaved, HttpStatus.CREATED)
    }
}