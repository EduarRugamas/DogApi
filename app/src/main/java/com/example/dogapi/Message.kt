package com.example.dogapi

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Message (url:String, altText:String) {
    var url : String = ""
    var altText : String = ""

    init {
        @Expose
        @SerializedName("url")
        this.url = url
        @Expose
        @SerializedName("altText")
        this.altText = altText
    }
}