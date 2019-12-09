package com.example.tradefast

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.tradefast.pantallasPrincipales.PantallaPrincipalNovedades
import com.paypal.android.sdk.payments.*
import kotlinx.android.synthetic.main.activity_pantalla_compra_con_tarjeta.*
import org.json.JSONException
import java.math.BigDecimal


class PantallaCompraConTarjeta : AppCompatActivity() {

    private var pagar: Button? = null
    private var paymentAmount: String? = null

    private val config = PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
        .clientId("Abx3SpSOCqlhfA4PtPzUeZXc4pLiqfiHwZ--RKqSK8d1_PUiWBC6TJia3Ls3r9xcLmKniuk6Z3Gy5LEL")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_compra_con_tarjeta)

        pagar = findViewById(R.id.botonConfirmarCompra)
        val precioCompra = intent.getDoubleExtra("precioNovedadCompra", 0.0)

        paymentAmount = precioCompra.toString()

        val i = Intent(this, PayPalService::class.java)
        i.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
        startService(i)


        val payment = PayPalPayment(
            BigDecimal((paymentAmount.toString())), "USD", "Simplified Coding Fee",
            PayPalPayment.PAYMENT_INTENT_SALE
        )


        val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment)
        startActivityForResult(intent,4/* REQUEST_CODE_PAYMENT*/)



        botonConfirmarCompra.setOnClickListener {


        }

        botonCancelarCompra.setOnClickListener {
            val intCancelarCompra = Intent(this, PantallaPrincipalNovedades::class.java)
            startActivity(intCancelarCompra)
        }
    }

    public override fun onDestroy() {
        stopService(Intent(this, PayPalService::class.java))
        super.onDestroy()
    }

 override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1/*REQUEST_CODE_PAYMENT*/) {

            if (resultCode == Activity.RESULT_OK) {

                val confirm: PaymentConfirmation = data!!.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION)

                if (confirm != null) {

                    try {
                        val paymentDetalles = confirm.toJSONObject().toString(4)
                        Log.i("paymentEjemplo", paymentDetalles)


                        startActivity(
                            Intent(this, DatosPaypalCompra::class.java)
                                .putExtra("PaymentDetalles", paymentDetalles)
                                .putExtra("PaymentCuenta", paymentAmount)
                        )

                    } catch (e: JSONException) {
                        Log.e("paymentExample", "fallo poco comun ", e)
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentEjemplo", "Usuario cancelado");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i(
                    "paymentEjemplo",
                    "pago invalido"
                )
            }
        }
    }
}
