package com.example.civ2k17newgame

import android.app.AlertDialog
import android.content.Context

class Las {
    var ma = MainActivity()

    fun CzyZajacLas(ctx: Context, id:Int, whoseTurn:Gracz){
        AlertDialog.Builder(ctx)
            .setTitle("Potwierdź")
            .setMessage("Czy chcesz zająć ten las?")
            .setPositiveButton("TAK") { _, _ ->
                ma = ctx as MainActivity
                ma.Turn(id.toInt(), whoseTurn)
            }
            .setNegativeButton("NIE") { _, _ ->
                return@setNegativeButton
            }.show()
    }
}