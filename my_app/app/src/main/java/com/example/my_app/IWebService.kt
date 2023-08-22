package com.example.my_app

import java.util.Objects

interface IWebService {
    public fun getResponse(responseObject: Objects): Unit
}