package com.example.post

interface ProductCallback {
    fun onSuccess()
    fun onFailure(error: String)
}