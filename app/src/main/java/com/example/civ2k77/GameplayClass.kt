package com.example.civ2k77

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment

class GameplayClass:Fragment(R.layout.gameplay) {
    private final val originX = 20f
    private final val originY = 200f
    private final val cellSide = 130f

    /*override fun onDraw(canvas: Canvas?) { wg tutorialu ta funkcja, ale tu nie lapie override; tylko tam jest bez fragmentow + wylacznie ten widok

    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val paint = Paint()
        val canvas = Canvas()
        Log.e("INFO", "TEST") //wchodzi do funkcji ale brak efektu
        for (i in 0..7)
            for (j in 0..7){
                paint.color = if ((i + j) % 2 == 1) Color.DKGRAY else Color.LTGRAY
                canvas?.drawRect(originX + i * cellSide, originY + j * cellSide, originX + (i + 1) * cellSide, originY + (j + 1) * cellSide, paint)
            }
    }
}