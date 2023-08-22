package com.example.my_app.Server

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.my_app.IWebService
import com.example.my_app.LoginActivity
import org.json.JSONException

class Server(mainActivity: LoginActivity) : ServerInterface{

    // URL LOGIN: http://localhost:8080/api/auth/signin?usernameOrEmail=tienhoang2k3qx1@gmail.com&password=123456
    companion object {
        const val SERVER_NAME = "localhost"
        const val SERVER_POST = "8080"
        const val TAG = "Web Services"
        const val URL_LOGIN = "https://"+SERVER_NAME+":"+SERVER_POST+"/api/auth/signin?"
    }

    // CALL API WEB SERVICE
    private lateinit var jsonOnjectRequest: JsonObjectRequest
    private lateinit var requestQueue: RequestQueue
    protected lateinit var context: Context

    init {
        this.context = context
    }
    override fun loginWithEmailAndPassword(email: String, password: String) {
        requestQueue = Volley.newRequestQueue(context)
        jsonOnjectRequest = JsonObjectRequest(Request.Method.POST,
            URL_LOGIN+"email="+email+"&password="+password,
            null,
            Response.Listener { response ->
                try {
                    val message: String = response.getString("message")

                    // convert JsonObject => Uer object
                    val webService: IWebService? = context as? IWebService

                }catch (e: JSONException) {
                    Log.d(TAG, "Cannot login user. Error: " + e.toString())
                }
            },
            Response.ErrorListener { error ->
                // Xử lý lỗi ở đây
            }
            )
    }

}