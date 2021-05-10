package com.example.ksiekowoscmobile.ui.login


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.ksiekowoscmobile.R
import com.example.ksiekowoscmobile.ui.home.HomeActivity
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.Button_login)
        val url = "http://127.0.0.1:8000/api/auth/login"
        val jsonobj = JSONObject()

            login.setOnClickListener {

                jsonobj.put("email", username.text)
                jsonobj.put("password",password.text)

                val que = Volley.newRequestQueue(this@LoginActivity)
                val req = JsonObjectRequest(Request.Method.POST,url,jsonobj,
                    {

                        val toast = Toast.makeText(applicationContext, "Udało się", Toast.LENGTH_SHORT)
                        toast.show()
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    }, {
                    val toast = Toast.makeText(applicationContext, "Nie udało się", Toast.LENGTH_SHORT)
                    toast.show()
                })
                que.add(req)

            //    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
            }
        }
    }
