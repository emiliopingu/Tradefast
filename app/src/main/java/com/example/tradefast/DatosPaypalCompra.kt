package com.example.tradefast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import org.json.JSONException
import org.json.JSONObject

class DatosPaypalCompra : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_paypal_compra)
        val intent = intent

        try {
            val jsonDetails: JSONObject = JSONObject(intent.getStringExtra("PaymentDetails"))

            showDetails(jsonDetails.getJSONObject("response"), intent.getStringExtra("PaymentAmount"))
        } catch ( e: JSONException) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show();
        }
    }


    @Throws(JSONException::class)
    private fun showDetails(jsonDetails: JSONObject, paymentAmount: String) {

        val id = findViewById<View>(R.id.paymentId) as TextView
        val status = findViewById<View>(R.id.Status) as TextView
        val amount = findViewById<View>(R.id.Amount) as TextView

        //Showing the details from json object
        id.text = jsonDetails.getString("id")
       status.text = jsonDetails.getString("state")
        amount.text = "$paymentAmount USD"
    }
    }

