package com.example.tradefast

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pantalla_registro.*
import android.app.ProgressDialog
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class PantallaRegistro : AppCompatActivity() {

    private lateinit var RegistroNombre: EditText
    private lateinit var regsitroApellido: EditText
    private lateinit var RegistroContraseña: EditText
    private lateinit var RegistroContraseña2: EditText
    private lateinit var registroCorreoElectronico: EditText
    private lateinit var registroEdad: EditText
    private lateinit var barraProgreso: ProgressBar
    private lateinit var dbreference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_registro)

        RegistroNombre = findViewById(R.id.RegistroNombre)
        regsitroApellido = findViewById(R.id.regsitroApellido)
        RegistroContraseña = findViewById(R.id.RegistroContraseña)
        RegistroContraseña2 = findViewById(R.id.RegistroContraseña2)
        registroCorreoElectronico = findViewById(R.id.registroCorreoElectronico)
        registroEdad = findViewById(R.id.registroEdad)

        barraProgreso= ProgressBar(this)

        database= FirebaseDatabase.getInstance()
        auth= FirebaseAuth.getInstance()

        dbreference=database.reference.child("Usuario")



        botonDeRegsitro1.setOnClickListener {
            val intre = Intent(this, MainActivity::class.java)
            startActivity(intre)
        }
    }

    fun botonDeRegsitro1(view:View){
        crearCuenta()
    }

    private fun crearCuenta(){
        val nombre:String=RegistroNombre.text.toString()
        val apellido:String=regsitroApellido.text.toString()
        val contraseña:String=RegistroContraseña.text.toString()
        val contraseña2:String=RegistroContraseña2.text.toString()
        val correo:String=registroCorreoElectronico.text.toString()
        val edad:String=registroEdad.text.toString()

        if(!TextUtils.isEmpty(nombre) && !TextUtils.isEmpty(apellido) &&  !TextUtils.isEmpty(contraseña) &&
            !TextUtils.isEmpty(contraseña2) &&  !TextUtils.isEmpty(correo) &&  !TextUtils.isEmpty(edad)){

            progressBar2.visibility=View.VISIBLE

            auth.createUserWithEmailAndPassword(correo,contraseña).addOnCompleteListener(this){
                task ->
                if (task.isComplete){
                    val usuario:FirebaseUser?=auth.currentUser
                    verificarEmail(usuario)

                    val usuarioBD=dbreference.child(usuario?.uid!!)
                    usuarioBD.child("Nombre").setValue(nombre)
                    usuarioBD.child("Apellido").setValue(apellido)
                    usuarioBD.child("Edad").setValue(edad)
                    vistaLogin()
                }
            }
        }


    }

    private fun vistaLogin(){
        startActivity(Intent(this,MainActivity::class.java))
    }
    private fun verificarEmail(usuario:FirebaseUser?){
        usuario?.sendEmailVerification()?.addOnCompleteListener(this){
            task->
            if(task.isComplete){
                Toast.makeText(this,"Tu correo se ha enviado correctamente",Toast.LENGTH_LONG)

            }else{
                Toast.makeText(this,"Ha ocurrido un error a mandar tu correo",Toast.LENGTH_LONG)
            }
        }
    }
}
