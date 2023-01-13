package com.example.civ2k17newgame

import android.app.AlertDialog
import android.graphics.Color
import android.widget.Button

class Pole(id:Int) {
    public var isClickable = false
    public var isOccupied = false
    public var whoCanTake = mutableListOf<Gracz>()
    public var whoTake: Gracz = Gracz("")
    public var whatIsThat:Any = PustePole()
//    public var action:Any = PustePole().CzyZajac()
//
//    public fun changeAction(arg:Any){
//        if(arg == ZajetePole()){
//            action = ZajetePole().CzyZbudowac()
//        }
//    }

}