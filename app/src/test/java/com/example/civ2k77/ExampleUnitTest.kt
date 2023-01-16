package com.example.civ2k77

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun test_checkPasswords() {
        val form = RegisterFragmentClass()
        form.passwordTest = "123"
        form.confPasswordTest = "123"
        assertEquals(form.checkPasswords(), true)
    }
    @Test
    fun test_clearingStrings() {
        val form = RegisterFragmentClass()
        form.loginTest = "testRegister"
        form.passwordTest = "123"
        form.confPasswordTest = "123"
        form.clearInputs()
        assertEquals(form.loginTest, null)
        assertEquals(form.passwordTest, null)
        assertEquals(form.confPasswordTest, null)
    }
    @Test
    fun check_clearingStringsLogin() {
        val form = LoginFragmentClass()
        form.loginLogin = "testLogin"
        form.loginPassword = "testPassword"
        form.clearInputs()
        assertEquals(form.loginLogin, null)
        assertEquals(form.loginPassword, null)
    }
}