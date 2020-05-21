package ru.itlab.forandroid

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    var emailText: TextView? = null
    var passwordText: TextView? = null
    var signInButton: Button? = null
    val APP_PREFERENCES = "current_user"
    lateinit var pref: SharedPreferences

    var data = mutableListOf(
        Person("a@mail.ru", "Abc123", "TestName1 TestSurname1", R.drawable.a),
        Person("b@mail.ru", "Abc123", "TestName2 TestSurname2", R.drawable.b),
        Person("c@mail.ru", "Abc123", "TestName3 TestSurname3", R.drawable.c)
    )

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        if (pref.contains("img")) {
            startActivity(Intent(this, Profile::class.java))
        }

        emailText = findViewById(R.id.email)
        passwordText = findViewById(R.id.password)
        signInButton = findViewById(R.id.sign_in)

        signInButton?.setOnClickListener { login() }
    }

    private fun login() {
        val email = emailText!!.text.toString()
        val password = passwordText!!.text.toString()
        var finded = false

        if (email.isNotEmpty() && password.isNotEmpty()) {
            if (Regex("([0-9a-zA-Z]+[\\.\\-_]?)*[0-9a-zA-Z]+@([0-9a-zA-Z]+[\\.\\-_]?)+\\.[0-9a-zA-Z]+").find(email) != null
                && Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$").find(password) != null) {
                for (person in data) {
                    if (person.email == email && person.password == password) {
                        val editor = pref.edit()
                        editor.putInt("img", person.avatar)
                        editor.putString("email", email)
                        editor.putString("name", person.name)
                        editor.apply()
                        editor.commit()
                        finded = true
                        startActivity(Intent(this, Profile::class.java))
                        Toast.makeText(this, "Successful signed in", Toast.LENGTH_SHORT).show()
                        break
                    }
                }

                if (!finded) Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Invalid format of login or password", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }
}
