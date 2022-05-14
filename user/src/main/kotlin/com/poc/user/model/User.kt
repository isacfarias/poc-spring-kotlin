package com.poc.user.model

import javax.persistence.*

@Entity
@Table(name = "user")
class User() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long? = null;
    var username: String? = null;
    @Column(unique = true)
    var email: String? = null;
    @Column(nullable = false)
    var password: String = "";
    constructor(username: String, email: String, password: String) : this()
    constructor(userId: Long, username: String, email: String, password: String) : this()
}