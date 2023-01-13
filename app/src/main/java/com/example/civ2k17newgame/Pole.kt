package com.example.civ2k17newgame

import android.app.AlertDialog
import android.graphics.Color
import android.widget.Button

class Pole(id:Int) {
    public var isClickable = false
    public var isOccupied = false
    public var whoCanTake = mutableListOf<String>()

}