package com.example.pita_mtihani

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import org.json.JSONObject

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup2)
        //find the ids
        var username=findViewById<EditText>(R.id.username)
        var email =findViewById<EditText>(R.id.email)
        var password =findViewById<EditText>(R.id.password1)
        var confirm = findViewById<EditText>(R.id.password2)
        var signup =findViewById<Button>(R.id.signup)
        var progress =findViewById<ProgressBar>(R.id.progress)
        //hide the progress bar
        progress.visibility= View.GONE //on opening the signup hide progress bar
        //justpaste.it/9eiki
        //loopj -library
        signup.setOnClickListener {
            //start the progress bar
            progress.visibility = View.VISIBLE //show progress bar
            val body = JSONObject()
            val client = AsyncHttpClient(true,80,443)
//            access the details inserted by user -values from the edittexts
//            put them details inside a body of json object
            body.put("username", username.text.toString())
            body.put("email", email.text.toString())
            body.put("password", password.text.toString())
            body.put("password2", confirm.text.toString())
            val con_body = StringEntity(body.toString())
            client.post(this,"https://dansana.pythonanywhere.com/signup",con_body,
                "application/json",
                object : JsonHttpResponseHandler(){
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Array<out Header>?,
                        response: JSONObject?
                    ) {
                        if (statusCode ==200){
                            Toast.makeText(applicationContext, "Registration Successful. Response= $statusCode" , Toast.LENGTH_SHORT).show()
                            val intent= Intent(applicationContext,login::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(applicationContext, "Failed: Registration not Successful "+ statusCode , Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onFailure(
                        statusCode: Int,
                        headers: Array<out Header>?,
                        throwable: Throwable?,
                        errorResponse: JSONObject?
                    ) {
                        Toast.makeText(applicationContext, "Server Error", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }


        }
    }
