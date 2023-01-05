package com.example.civ2k77

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import com.example.civ2k77.databinding.FragmentLoginBinding

class LoginFragmentClass:Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    var loginLogin: String = ""
    var loginPassword:String = ""
    var isActive:Boolean = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        binding.loginLogin.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                loginLogin = s.toString();
            }
        })
        binding.loginPassword.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                loginPassword = s.toString();
            }
        })
    }

    fun clearInputs(){
        binding.loginLogin.text = null
        binding.loginPassword.text = null
    }
    fun checkData():Boolean{
        return true
    }

}