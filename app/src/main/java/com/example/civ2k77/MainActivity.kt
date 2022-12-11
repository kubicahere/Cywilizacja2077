package com.example.civ2k77

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.example.civ2k77.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    var loginfc = LoginFragmentClass()
    var registerfc = RegisterFragmentClass()
    var searchfc = SearchFragmentClass()
    var startfc = StartFragmentClass()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY)
        setContentView(binding.root)
        binding.firstButton.setOnClickListener {
            val text = binding.firstButton.text
            replaceFragment(text as String)
        }
        binding.secondButton.setOnClickListener {
            val text = binding.secondButton.text
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Androidly Alert")
            builder.setMessage("Message")
            if(text == "Try Register"){
                tryRegister(registerfc)
                return@setOnClickListener
            }
            else if(text == "Try Login"){
                tryLogin(loginfc)
                return@setOnClickListener
            }
            replaceFragment(text as String)
        }
    }

    private fun replaceFragment(text:String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        if(text == "Login"){
            fragmentTransaction.replace(R.id.fragmentContainer, loginfc)
            loginfc.isActive = true
            binding.firstButton.text = "Menu"
            binding.secondButton.text = "Try Login"
        }
        else if(text == "Register"){
            fragmentTransaction.replace(R.id.fragmentContainer, registerfc)
            registerfc.isActive = true
            binding.firstButton.text = "Menu"
            binding.secondButton.text = "Try Register"
        }
        else if(text == "Menu"){
            if(registerfc.isActive){
                registerfc.clearInputs()
                registerfc.isActive = false
            }
            else if(loginfc.isActive){
                loginfc.clearInputs()
                loginfc.isActive = false
            }
            fragmentTransaction.replace(R.id.fragmentContainer, startfc)
            binding.firstButton.text = "Login"
            binding.secondButton.text = "Register"
        }
        fragmentTransaction.commit()
    }

    private fun tryRegister(form: RegisterFragmentClass){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Androidly Alert")
        //te buildery do wyjebania
        if(form.checkPasswords()){
            //builder.setMessage("Hasło jest poprawne")
            builder.setMessage(form.loginTest + "\n" + form.passwordTest + "\n" + form.confPasswordTest)
            builder.show()
        }
        else{
            builder.setMessage("Hasła nie są poprawne")
            builder.show()
        }
    }
    private fun tryLogin(form: LoginFragmentClass){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Androidly Alert")
        if(form.checkData()){
            builder.setMessage(form.loginLogin + "\n" + form.loginPassword)
            builder.show()
        }
    }
}