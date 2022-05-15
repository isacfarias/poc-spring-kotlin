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
    constructor(username: String, email: String) : this() {
        this.username = username;
        this.email = email;

    }
    constructor(userId: Long, username: String, email: String) : this() {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }
}