package com.example.tradefast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.paypal.android.sdk.payments.PayPalConfiguration
import com.paypal.android.sdk.payments.PayPalPayment
import configuracion.Paypal
import java.math.BigDecimal
import android.app.Activity
import org.json.JSONException
import android.widget.Toast
import com.paypal.android.sdk.payments.PaymentActivity
import com.paypal.android.sdk.payments.PaymentConfirmation
import android.content.Intent



class RealizarCompra : AppCompatActivity() {


    val precio = intent.getIntExtra("precioCompra", 0)
    val p = Paypal()
    var PAYPAL_REQUEST_CODES: Int = 7171
    var congiguracion: PayPalConfiguration =
        PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(p.paypal_Client_ID) //Para utilizar cuenta sandbox

    private lateinit var precioPagar: TextView
    private lateinit var botonPagar: Button
    private lateinit var cuenta: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realizar_compra)

        precioPagar=findViewById(R.id.tvCompra)
        botonPagar=findViewById(R.id.botonPagar)
        cuenta=findViewById(R.id.tasa)


    }

    fun procesarPago(){

        val precioDecimal:BigDecimal=precio.toBigDecimal()
        val p=PayPalPayment(precioDecimal,"MX","Pagado por x ",PayPalPayment.PAYMENT_INTENT_SALE)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK) {
            val confirm = data!!
                .getParcelableExtra<PaymentConfirmation>(PaymentActivity.EXTRA_RESULT_CONFIRMATION)
            if (confirm != null) {
                try {

                    // informacion extra del pedido
                    println(confirm.toJSONObject().toString(4))
                    println(
                        confirm.payment.toJSONObject()
                            .toString(4)
                    )

                    Toast.makeText(
                        applicationContext, "Orden procesada",
                        Toast.LENGTH_LONG
                    ).show()

                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            println("El usuario canceló el pago")
        }
    }

}
