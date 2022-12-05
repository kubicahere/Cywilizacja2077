package com.example.civ2k77

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.fragment.app.Fragment
import com.example.civ2k77.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(binding.root)

        binding.firstButton.setOnClickListener {
            val text = binding.firstButton.text
            //replaceFragment(LoginFragmentClass())
            replaceFragment(text as String)
        }

        binding.secondButton.setOnClickListener {
            val text = binding.secondButton.text
            //replaceFragment(RegisterFragmentClass())
            replaceFragment(text as String)
        }
    }

    //private fun replaceFragment(fragment: Fragment, text:String)
    private fun replaceFragment(text:String) {
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
//        fragmentTransaction.commit()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        if(text == "Login"){
            fragmentTransaction.replace(R.id.fragmentContainer, LoginFragmentClass())
            binding.firstButton.text = "Back"
            binding.secondButton.text = "Try Login"
        }
        else if(text == "Register"){
            fragmentTransaction.replace(R.id.fragmentContainer, RegisterFragmentClass())
            binding.firstButton.text = "Back"
            binding.secondButton.text = "Try Register"
        }
        else if(text == "Back"){
            fragmentTransaction.replace(R.id.fragmentContainer, StartFragmentClass())
            binding.firstButton.text = "Login"
            binding.secondButton.text = "Register"

        }

        //elif try log
            //jakas funkcja logowania
            //sprawdzenie poprawnosci danych
        //elif try reg
            //jakas funkcja rejestracji
            //regexy hehe czy poprawnie all

        fragmentTransaction.commit()
    }
}