package com.example.civ2k17newgame

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var listOfButtons = mutableListOf<Button>()
    var listOfCells = mutableListOf<Pole>()
    val g1 = Gracz("RED");
    val g2 = Gracz("BLUE");
    var whoseTurn = g1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //region Przypisanie danych
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Androidly Alert")
        for(i in 0..63){
            listOfCells.add(Pole(i))
        }

        whoseTurn = g1
        listOfCells.elementAt(0).isClickable = false
        listOfCells.elementAt(0).whoCanTake.add(g1)
        listOfCells.elementAt(0).whoTake = g1
        listOfCells.elementAt(0).whatIsThat = ZajetePole()
        listOfCells.elementAt(63).isClickable = false
        listOfCells.elementAt(63).whoCanTake.add(g2)
        listOfCells.elementAt(63).whoTake = g2;
        listOfCells.elementAt(63).whatIsThat = ZajetePole()
        RNG.OddajLosoweSurowce(listOfCells)

        for(i in 0..63){
            val strId = "b$i"
            val buttonID = resources.getIdentifier(strId, "id", packageName)
            listOfButtons.add(findViewById(buttonID))
        }
        //endregion

        listOfButtons.forEach(){
            val idS = it.resources.getResourceEntryName(it.id)
            val id = idS.substring(1)
            it.isEnabled = listOfCells[id.toInt()].isClickable
            it.setOnClickListener {

                val builderAlert = AlertDialog.Builder(this)
                builderAlert.setTitle("Androidly Alert")
                builderAlert.setMessage(listOfCells.elementAt(id.toInt()).whatIsThat.toString())

                //jesli RED/BLUE nie moze zajac to return
                if(!listOfCells.elementAt(id.toInt()).isClickable){
                    return@setOnClickListener
                }
                if(listOfCells.elementAt(id.toInt()).isOccupied) {
                    return@setOnClickListener
                }

                if(!listOfCells.elementAt(id.toInt()).whoCanTake.contains(whoseTurn)){
                    return@setOnClickListener
                }


                builderAlert.show()
                if(listOfCells.elementAt(id.toInt()).whatIsThat is PustePole){
                    (listOfCells.elementAt(id.toInt()).whatIsThat
                            as PustePole).CzyZajac(this, id.toInt(), whoseTurn)
                }
            }
            //Turn(id.toInt(), whoseTurn)
        }
        listOfButtons.elementAt(0).setBackgroundColor(Color.RED)
        listOfButtons.elementAt(63).setBackgroundColor(Color.BLUE)
        Turn(0, g1)
        Turn(63, g2)

    }

    fun Turn(id:Int, who:Gracz){
        if(who.nazwa == "RED"){
            listOfButtons.elementAt(id).setBackgroundColor(Color.RED)
        }
        else listOfButtons.elementAt(id).setBackgroundColor(Color.BLUE)
        SetClickable(id, who)
        listOfCells.elementAt(id).isOccupied = true
        listOfCells.elementAt(id).whoTake = who
        if(listOfCells.elementAt(id).whatIsThat is PustePole){
            listOfCells.elementAt(id).whatIsThat = ZajetePole()
        }
        else if(listOfCells.elementAt(id).whatIsThat is Las){

        }
        else if(listOfCells.elementAt(id).whatIsThat is RudaZelaza){

        }

        who.listaPol.add(listOfCells.elementAt(id))

        if(who.nazwa == "RED") whoseTurn = g2
        else whoseTurn = g1
    }

    private fun SetClickable(id:Int, who:Gracz){
        if(id - 1 >= 0 && id%8 > 0){
            listOfButtons.elementAt(id - 1).isEnabled = true
            listOfCells.elementAt(id - 1).isClickable = true
            listOfCells.elementAt(id - 1).whoCanTake.add(who)

            //listOfCells.elementAt(id - 1).whatIsThat = PustePole()
        }
        if(id + 1 <= 63 && (id+1)%8 != 0){
            listOfButtons.elementAt(id + 1).isEnabled = true
            listOfCells.elementAt(id + 1).isClickable = true
            listOfCells.elementAt(id + 1).whoCanTake.add(who)

            //listOfCells.elementAt(id - 1).whatIsThat = PustePole()
        }
        if(id - 8 >= 0){
            listOfButtons.elementAt(id - 8).isEnabled = true
            listOfCells.elementAt(id - 8).isClickable = true
            listOfCells.elementAt(id - 8).whoCanTake.add(who)

            //listOfCells.elementAt(id - 1).whatIsThat = PustePole()
        }
        if(id + 8 <= 63){
            listOfButtons.elementAt(id + 8).isEnabled = true
            listOfCells.elementAt(id + 8).isClickable = true
            listOfCells.elementAt(id + 8).whoCanTake.add(who)

            //listOfCells.elementAt(id - 1).whatIsThat = PustePole()
        }
    }


}



