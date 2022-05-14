package com.poc.user.model

import lombok.Data
import javax.persistence.*

@Data
@Entity
@Table(name = "user")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long = 0;
    var username: String = "";
    @Column(unique = true)
    var email: String = "";
    @Column(nullable = false)
    var password: String = "";
}