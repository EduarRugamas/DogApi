package com.example.dogapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        peticion.setOnClickListener {
            val dogItem = spinner.selectedItem.toString()
            Log.d("spinner", dogItem)

            if (dogItem == "Selecciona un perro"){
                Toast.makeText(this, "Seleciona una opcion distinta", Toast.LENGTH_SHORT).show()
            }else{
                PeticionVolley(dogItem)
            }

        }
    }

    private fun PeticionVolley(Item:String) {
        val queue = Volley.newRequestQueue(this)
        val url = "https://dog.ceo/api/breed/${Item}/images/random/1/alt"

        val stringRequest = StringRequest(Request.Method.GET,url,{result ->
            Log.d("respuesta",result)
            val gson = Gson()
            val dog = gson.fromJson(result, ApiDog::class.java)
            Log.d("gson", dog.message!!.get(0).url)


            Glide.with(this).load(dog.message?.get(0)?.url).into(image)
            nombre.text = dog.message?.get(0)?.altText


        },{
            Log.d("fallo",it.message.toString())
        })
        queue.add(stringRequest)
    }


}