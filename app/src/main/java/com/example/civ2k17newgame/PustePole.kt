package com.example.civ2k17newgame

import android.app.AlertDialog
import android.content.Context
import android.os.Handler
import android.os.Message


class PustePole {

    var ma = MainActivity()

    fun CzyZajac(ctx: Context, id:Int, whoseTurn:Gracz):Boolean{
        val res = false
        AlertDialog.Builder(ctx)
            .setTitle("Potwierdź")
            .setMessage("Czy chcesz zająć to pole?")
            .setPositiveButton("TAK") { _, _ ->
                ma = ctx as MainActivity
                ma.Turn(id.toInt(), whoseTurn)
            }
            .setNegativeButton("NIE") { _, _ ->
                return@setNegativeButton
            }.show()
        return res
    }

}