package com.example.pita_mtihani

import android.content.Context
import android.widget.Toast
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import org.json.JSONArray
import org.json.JSONObject
class ApiHelper(var context: Context) {
    //POST
    //post function that has loopj code to post employee details
    fun post(api: String, jsonData: JSONObject) { //the api and json data (object)
        Toast.makeText(context, "Please Wait for response", Toast.LENGTH_LONG).show()
        val client = AsyncHttpClient(true, 80, 443)
        val con_body = StringEntity(jsonData.toString())
        //post to API

        client.post(context, api, con_body, "application/json",
            object : JsonHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    response: JSONObject?
                ) {
                    Toast.makeText(context, "Response $response ", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    throwable: Throwable?,
                    errorResponse: JSONObject?
                ) {
                    //super.onFailure(statusCode, headers, throwable, errorResponse)
                    //Todo handle the error
                    Toast.makeText(
                        context,
                        "Error Occurred" + throwable.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                    // progressbar.visibility = View.GONE
                }
            })
    }//END POST


    //post function that has loopj code to post employee details
    fun post2(api: String, jsonData: JSONObject, callBack2: CallBack2) { //the api and json data (object)
        Toast.makeText(context, "Please Wait for response", Toast.LENGTH_LONG).show()
        val client = AsyncHttpClient(true, 80, 443)
        val con_body = StringEntity(jsonData.toString())
        //post to API

        client.post(context, api, con_body, "application/json",
            object : JsonHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    response: JSONObject?
                ) {
                    if (statusCode == 200){
                        callBack2.onSuccess("2")
                        Toast.makeText(context, "Response $response ", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        callBack2.onFailure("0")
                        Toast.makeText(context, "Response $response ", Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    throwable: Throwable?,
                    errorResponse: JSONObject?
                ) {
                    callBack2.onFailure("0")
                    //super.onFailure(statusCode, headers, throwable, errorResponse)
                    //Todo handle the error
                    Toast.makeText(
                        context,
                        "Error Occurred" + throwable.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                    // progressbar.visibility = View.GONE
                }
            })
    }//END POST

    //GET
    fun get(api: String, callBack: CallBack) {
        val client = AsyncHttpClient(true, 80, 443)
        //post to API
        client.get(context, api, null, "application/json",
            object : JsonHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    response: JSONArray
                ) {
                    callBack.onSuccess(response.toString())
                    //Toast.makeText(context, "Response $response ", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseString: String?,
                    throwable: Throwable?
                ) {
                    Toast.makeText(
                        context,
                        "Error Occurred" + throwable.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }

            })

    }//END GET

    //PUT
    fun put(api: String, jsonData: JSONObject) {
        Toast.makeText(context, "Please Wait for response", Toast.LENGTH_LONG).show()
        val client = AsyncHttpClient(true, 80, 443)
        val con_body = StringEntity(jsonData.toString())
        //post to API
        client.put(context, api, con_body, "application/json",
            object : JsonHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    response: JSONObject?
                ) {
                    Toast.makeText(context, "Response $response ", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    throwable: Throwable?,
                    errorResponse: JSONObject?
                ) {
                    //super.onFailure(statusCode, headers, throwable, errorResponse)
                    //Todo handle the error
                    Toast.makeText(
                        context,
                        "Error Occurred" + throwable.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                    // progressbar.visibility = View.GONE
                }
            })
    }//END POST

    //DELETE
    fun delete(api: String, jsonData: JSONObject) {
        Toast.makeText(context, "Please Wait for response", Toast.LENGTH_LONG).show()
        val client = AsyncHttpClient(true, 80, 443)
        val con_body = StringEntity(jsonData.toString())
        //post to API
        client.delete(context, api, con_body, "application/json",
            object : JsonHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    response: JSONObject?
                ) {
                    Toast.makeText(context, "Response $response ", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    throwable: Throwable?,
                    errorResponse: JSONObject?
                ) {
                    //super.onFailure(statusCode, headers, throwable, errorResponse)
                    //Todo handle the error
                    Toast.makeText(
                        context,
                        "Error Occurred" + throwable.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                    // progressbar.visibility = View.GONE
                }
            })
    }//END POST

    //Interface to used by the GET function above.
    interface CallBack {
        fun onSuccess(result: String?)
    }

    interface CallBack2 {
        fun onSuccess(result: String?)
        fun onFailure(result: String?)
    }
}