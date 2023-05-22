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

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.usermane)
        val password = findViewById<EditText>(R.id.password)
        val signup = findViewById<Button>(R.id.signup)
        val login = findViewById<Button>(R.id.login)

        val progress =findViewById<ProgressBar>(R.id.progress)
        progress.visibility = View.GONE
        signup.setOnClickListener {
            val i = Intent(applicationContext, SignupActivity::class.java)
            startActivity(i)
            finish() //kills the previous activity
            //find email,password
        }
        login.setOnClickListener {
            progress.visibility= View.VISIBLE //show progress bar
            val client = AsyncHttpClient(true,80,443)
            val body = JSONObject()
            //access the details inserted by user -values from the edittexts
            //put them details inside a body of json object
            body.put("username",username.text.toString())
            body.put("password",password.text.toString())
             val con_body = StringEntity(body.toString())
             client.post(this,"https://dansana.pythonanywhere.com/login",con_body,
             "application/json",
             object : JsonHttpResponseHandler() {
             //create a function for onsuccess
             override fun onSuccess(
             statusCode: Int,
             headers: Array<out Header>?,
             response: JSONObject?
             ) {
             //check if status code is success (200)
             if (statusCode == 200){

                 Toast.makeText(applicationContext,"You have signed in successfully",
                 Toast.LENGTH_LONG).show()
                 val i = Intent(applicationContext,MainActivity::class.java)
                 startActivity(i)
             } //end of if
             else{
             Toast.makeText(applicationContext,"Wrong credentials: Please try again "+ statusCode,
             Toast.LENGTH_LONG).show()
             }
             //super.onSuccess(statusCode, headers, response)
             } //end of onsuccess
             override fun onFailure(
             statusCode: Int,
             headers: Array<out Header>?,
             throwable: Throwable?,
             errorResponse: JSONObject?
             ) {
             progress.visibility = View.GONE
             Toast.makeText(applicationContext,"Something went wrong from the Application side"
             + " " + statusCode,
             Toast.LENGTH_LONG).show()
             //super.onFailure(statusCode, headers, throwable, errorResponse)
             }
             }
             )
             }

//            apihelper.post2(api,body, object : ApiHelper.CallBack2{
//                override fun onSuccess(result: String?) {
//                    progress.visibility=View.GONE
//                    Toast.makeText(applicationContext, "The callback result is: $result", Toast.LENGTH_SHORT).show()
//                    if (result =="2"){
//                        val intent=Intent(applicationContext, MainActivity::class.java)
//                        startActivity(intent)
//                    }
//                    else{
//                        Toast.makeText(applicationContext, "Failed: Request not successful", Toast.LENGTH_SHORT).show()
//                    }
//
//                }
//
//                override fun onFailure(result: String?) {
//                    Toast.makeText(applicationContext, "Server Error", Toast.LENGTH_SHORT).show()
//                }
//
//            })
//
//
//
//        }
//
//        //stop progress
//        progress.visibility = View.GONE
//
//    }
    }
}
