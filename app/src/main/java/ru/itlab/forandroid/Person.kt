package ru.itlab.forandroid

import android.widget.ImageView

class Person(email: String, password: String, name: String, avatar: Int) {
    val email: String
    val password: String
    val name: String
    val avatar: Int

    init{
        this.name = name
        this.email = email
        this.password = password
        this.avatar = avatar
    }

}