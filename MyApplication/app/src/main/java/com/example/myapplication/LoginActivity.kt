package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPass = findViewById<EditText>(R.id.editTextPass)

        val signBtn = findViewById<Button>(R.id.buttonSingIn)

        val firebaseAuth = FirebaseAuth.getInstance()


        signBtn.setOnClickListener {
            if(editTextEmail.text.toString() == "")
                Toast.makeText(this, "Поле \"Email\" не заполнено", Toast.LENGTH_SHORT).show()
            else if(editTextPass.text.toString() == "")
                Toast.makeText(this, "Поле \"Пароль\" не заполнено", Toast.LENGTH_SHORT).show()
            else {
                if (editTextEmail.text.toString().contains("@")) {
                    firebaseAuth.signInWithEmailAndPassword(
                        editTextEmail.text.toString(),
                        editTextPass.text.toString()
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }
                        else
                            Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
                    }
                }
                else
                    Toast.makeText(this, "Email введен неверно", Toast.LENGTH_SHORT).show()
            }
        }
        val textViewRegister = findViewById<TextView>(R.id.textViewLoginRegister)
        textViewRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}