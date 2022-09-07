package com.example.ugd1_a_kelompok9.entity

class User(var username: String, var password: String, var email: String) {

    companion object{
        @JvmField
        var listOfUser = arrayOf(
            User("user1","user1", "user1@gmail.com"),
            User("user2", "user2", "user2@gmail.com"),
            User("user3", "user3", "user3@gmail.com"),
            User("user4", "user4", "user4@gmail.com")
        )
    }
}