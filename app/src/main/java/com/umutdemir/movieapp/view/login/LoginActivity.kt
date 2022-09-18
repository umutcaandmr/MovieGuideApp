package com.umutdemir.movieapp.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.umutdemir.movieapp.R
import com.umutdemir.movieapp.model.login.LoginModel
import com.umutdemir.movieapp.view.MainActivity
import com.umutdemir.movieapp.viewmodel.login.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var viewModel: LoginViewModel
    lateinit var username : String
    lateinit var password : String
    lateinit var token : String
    lateinit var hesap : LoginModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)




        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.getToken()

        viewModel.respondToken.observe(this){
            token = it.request_token
        }

        viewModel.errorToken.observe(this){
            val alert = AlertDialog.Builder(this)
            alert.setTitle("API HATASI")
            alert.setMessage("Uygulamayi kullanabilmek icin lutfen gelistiriyle irtibata gecin!")
            alert.setCancelable(true)
            alert.setOnCancelListener(){
                finish()
            }
            alert.show()
        }

        loginButton.setOnClickListener(){
            //username = usernameText.text.toString()
            //password = passwordText.text.toString()
            username = "umutcaandmr"
            password = "kn*4h!#4-5PMS\$h"
            hesap = LoginModel(username,password,token)

            viewModel.sendLogin(hesap)

            viewModel.respondLogin.observe(this){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            viewModel.errorLogin.observe(this){
                Toast.makeText(this,"Kullanici adi / sifre kontrol edin", Toast.LENGTH_SHORT).show()
            }
        }

    }
}