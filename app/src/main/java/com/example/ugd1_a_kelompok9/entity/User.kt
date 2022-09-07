package com.example.ugd1_a_kelompok9.entity

class User(var username: String, var password: String, var email: String) {

    companion object{
        @JvmField
        var listOfUser = arrayOf(
            User("user1","user1", "user1@gmail.com"),
            User("user2", "user2", "user2@gmail.com"),
            User("user3", "user3", "user3@gmail.com"),
            User("user4", "user4", "user4@gmail.com"),
            User("user5", "user5", "user5@gmail.com"),
            User("user6", "user6", "user6@gmail.com"),
            User("user7", "user7", "user7@gmail.com"),
            User("user8", "user8", "user8@gmail.com"),
            User("user9", "user9", "user9@gmail.com"),
            User("user0", "user0", "user0@gmail.com")
        )
    }
}