package com.example.civ2k77

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import androidx.appcompat.app.AlertDialog
import com.example.civ2k77.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var loginfc = LoginFragmentClass()
    var registerfc = RegisterFragmentClass()
    var afterLoginfc = AfterLoginUserViewClass()
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
                if(tryRegister(registerfc))
                    replaceFragment(text as String)
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
        else if(text == "Try Register")
        {
            fragmentTransaction.replace(R.id.fragmentContainer, startfc)
            registerfc.isActive = false
            binding.firstButton.text = "Login"
            binding.secondButton.text = "Register"
        }
        else if(text == "Try Login")
        {
            fragmentTransaction.replace(R.id.fragmentContainer, afterLoginfc)
            loginfc.isActive = false
            binding.firstButton.visibility = View.INVISIBLE
            binding.secondButton.visibility = View.INVISIBLE
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

    @SuppressLint("Range")
    private fun tryRegister(form: RegisterFragmentClass) : Boolean{
        if(form.loginTest.isEmpty() || form.passwordTest.isEmpty() || form.confPasswordTest.isEmpty())
        {
            return false
        }
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Androidly Alert")
        val db = DBHelper(this, null)
        //te buildery do wyjebania
        if(form.checkPasswords()){
            val cursor = db.checkIfLoginExists(form.loginTest)
            if(cursor?.count == 0) {
                Log.e("SQLITE EXISTS", "FALSE")
                db.addUser(form.loginTest, form.passwordTest)
                builder.setMessage("Pomyślnie dodano użytkownika!")
                //cursor.moveToLast()
                form.clearInputs()
                cursor.close()
                builder.show()
                return true
            }
            else {
                builder.setMessage("Podany login już istnieje!")
                form.clearInputs()
                cursor?.close()
                builder.show()
                return false //skip if login exists
            }
            //Log.e("SQLITE", db.checkIfLoginExists(cursor.getString((cursor.getColumnIndex(DBHelper.LOGIN_COLUMN)))).toString())
            //builder.setMessage(db.getName(form.loginTest.toString(), form.passwordTest.toString()))
            //form.loginTest + "\n" + form.passwordTest + "\n" + form.confPasswordTest
            //Log.e("SQLITE", cursor.getString((cursor.getColumnIndex(DBHelper.LOGIN_COLUMN))))
            //Log.e("SQLITE", cursor.getString((cursor.getColumnIndex(DBHelper.PASSWORD_COLUMN))))
            //cursor.getString((cursor.getColumnIndex(DBHelper.LOGIN_COLUMN)))
        }
        else{
            builder.setMessage("Hasła nie są poprawne")
            form.clearInputs()
            builder.show()
            return false
        }
    }
    private fun tryLogin(form: LoginFragmentClass) : Boolean{
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Androidly Alert")
        val db = DBHelper(this, null)
        val cursor = db.getUser(form.loginLogin, form.loginPassword)
        if(cursor?.count == 0) {
            builder.setMessage("Błędny login lub hasło!")
            builder.show()
            form.clearInputs()
            cursor.close()
            return false
        }
        else{
            builder.setMessage("Pomyślnie zalogowano!")
            builder.show()
            form.clearInputs()
            cursor?.close()
            return true
        }
    }
}