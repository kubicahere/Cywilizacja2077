package com.example.civ2k77

import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import com.example.civ2k77.databinding.FragmentRegisterBinding
import android.text.Editable

class RegisterFragmentClass() : Fragment(R.layout.fragment_register) {
    private lateinit var binding: FragmentRegisterBinding
    var loginTest: String = ""
    var passwordTest: String = ""
    var confPasswordTest: String = ""
    var isActive:Boolean = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)
        binding.registerLogin.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                loginTest = s.toString();
                //database = FirebaseDatabase.getInstance().getReference("Users")
                //val testUser = User(loginTest, "1234", "1234")
               // database.child(loginTest).setValue(testUser).addOnSuccessListener {
                 //   clearInputs()

               // }
            }
        })
        binding.registerPassword.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                passwordTest = s.toString();
            }
        })
        binding.registerConfirmPassword.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                confPasswordTest = s.toString();
            }
        })

    }

    fun clearInputs(){
        binding.registerLogin.text = null
        binding.registerPassword.text = null
        binding.registerConfirmPassword.text = null
    }

    fun checkLogin(): Boolean{
        //if jest taki w bazie danych
        //ret false

        return true
    }

    fun checkPasswords() : Boolean{
        if(passwordTest != confPasswordTest){
            return false
        }
        return true
    }

}