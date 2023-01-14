package com.example.civ2k17newgame

import android.app.AlertDialog
import android.content.Context

class RudaZelaza {
    var ma = MainActivity()

    fun CzyZajacRude(ctx: Context, id:Int, whoseTurn:Gracz){
        AlertDialog.Builder(ctx)
            .setTitle("Potwierdź")
            .setMessage("Czy chcesz zająć tę rudę żelaza?")
            .setPositiveButton("TAK") { _, _ ->
                ma = ctx as MainActivity
                ma.Turn(id.toInt(), whoseTurn)
            }
            .setNegativeButton("NIE") { _, _ ->
                return@setNegativeButton
            }.show()
    }
}