package ru.itlab.forandroid

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Profile : AppCompatActivity() {

    var avatar : ImageView? = null
    var emailText : TextView? = null
    var nameText : TextView? = null
    var signOutButton : Button? = null

    val APP_PREFERENCES = "current_user"
    lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        avatar = findViewById(R.id.avatar)
        emailText = findViewById(R.id.email)
        nameText = findViewById(R.id.name)
        signOutButton = findViewById(R.id.sign_out)

        avatar!!.setImageDrawable(getDrawable(pref.getInt("img", 0)))
        emailText!!.text = pref.getString("email", "null")
        nameText!!.text = pref.getString("name", "null")

        signOutButton!!.setOnClickListener { logout() }
    }

    private fun logout(){
        val editor = pref.edit()
        editor.clear()
        editor.apply()
        editor.commit()
        startActivity(Intent(this, MainActivity :: class.java))
    }
}