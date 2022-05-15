package com.poc.user.resources

import com.poc.user.dto.UserDTO
import com.poc.user.dto.UserRequestDTO
import com.poc.user.dto.UserUpdateRequestDTO
import com.poc.user.services.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort.Direction
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/user")
class UserResource(private val service: UserService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun all(@RequestParam(value = "page", defaultValue = "0") page: Int,
            @RequestParam(value = "userId", required = false) userId: Optional<Long>,
            @RequestParam(value = "username", required = false) username: Optional<String>,
            @RequestParam(value = "linesPerPage", defaultValue = "12") linesPerPage: Int,
            @RequestParam(value = "orderBy", defaultValue = "username") orderBy: String,
            @RequestParam(value = "direction", defaultValue = "DESC") direction: String): Page<UserDTO> {
        return service.all(userId,
            username,
            PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy))
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody userRequestDTO: UserRequestDTO): UserDTO {
        return service.save(userRequestDTO)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(@PathVariable id: Long, @RequestBody userUpdateRequestDTO: UserUpdateRequestDTO): UserDTO {
        return service.update(id, userUpdateRequestDTO)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        val userSaved = service.remove(id)
    }

}