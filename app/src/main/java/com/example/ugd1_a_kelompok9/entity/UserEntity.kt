package com.example.ugd1_a_kelompok9.entity

class UserEntity(var username: String, var password: String, var email: String) {

    companion object{
        @JvmField
        var listOfUser = arrayOf(
            UserEntity("user1","user1", "user1@gmail.com"),
            UserEntity("user2", "user2", "user2@gmail.com"),
            UserEntity("user3", "user3", "user3@gmail.com"),
            UserEntity("user4", "user4", "user4@gmail.com"),
            UserEntity("user5", "user5", "user5@gmail.com"),
            UserEntity("user6", "user6", "user6@gmail.com"),
            UserEntity("user7", "user7", "user7@gmail.com"),
            UserEntity("user8", "user8", "user8@gmail.com"),
            UserEntity("user9", "user9", "user9@gmail.com"),
            UserEntity("user0", "user0", "user0@gmail.com")
        )
    }
}