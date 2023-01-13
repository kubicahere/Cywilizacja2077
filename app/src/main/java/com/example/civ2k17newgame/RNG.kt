package com.example.civ2k17newgame

import android.app.AlertDialog
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random

class RNG {

    companion object Object{
        //val firQua = mutableListOf<Int>(0,1,2,3,8,9,10,11,16,17,18,19,24,25,26,27)
        var firQua = mutableListOf<Int>(1,2,3,8,9,10,11,16,17,18,19,24,25,26,27)
        var secQua = mutableListOf<Int>(4,5,6,7,12,13,14,15,20,21,22,23,28,29,30,31)
        var thiQua = mutableListOf<Int>(32,33,34,35,40,41,42,43,48,49,50,51,56,57,58,59)
        //val fouQua = mutableListOf<Int>(36,37,38,39,44,45,46,47,52,53,54,55,60,61,62,63)
        var fouQua = mutableListOf<Int>(36,37,38,39,44,45,46,47,52,53,54,55,60,61,62)

        //1 cwiartka 0,1,2,3,8,9,10,11,16,17,18,19,24,25,26,27
        //2 cwiartka 4,5,6,7,12,13,14,15,20,21,22,23,28,29,30,31
        //3 cwiartka 32,33,34,35,40,41,42,43,48,49,50,51,56,57,58,59
        //4 cwiartka 36,37,38,39,44,45,46,47,52,53,54,55,60,61,62,63
        fun OddajLosoweSurowce(arg: List<Pole>){
            firQua.shuffle()
            secQua.shuffle()
            thiQua.shuffle()
            fouQua.shuffle()
            for(i in 0..3){
                for(j in 0..1){
                    var randItem = 0
                    if(i == 0) randItem = firQua.random()
                    else if(i == 1) randItem = secQua.random()
                    else if(i == 2) randItem = thiQua.random()
                    else randItem = fouQua.random()

                    if(j == 0){
                        arg.elementAt(randItem).whatIsThat = Las()
                    } else {
                        arg.elementAt(randItem).whatIsThat = RudaZelaza()
                    }

                    if(i == 0) firQua.remove(randItem)
                    else if(i == 1) secQua.remove(randItem)
                    else if(i == 2) thiQua.remove(randItem)
                    else fouQua.remove(randItem)
                }
            }




        }
    }
}