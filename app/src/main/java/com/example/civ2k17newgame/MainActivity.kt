package com.example.civ2k17newgame

import android.app.AlertDialog
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var listOfButtons = mutableListOf<Button>()
    var listOfCells = mutableListOf<Pole>()
    var whoseTurn = "RED"
    var currentTurnTextColor: TextView? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportActionBar()?.hide();
        }

        currentTurnTextColor = findViewById(R.id.textView2);

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Androidly Alert")
        for(i in 0..63){
            listOfCells.add(Pole(i))
        }
        listOfCells.elementAt(0).isClickable = false
        listOfCells.elementAt(0).whoCanTake.add("RED")
        listOfCells.elementAt(63).isClickable = false
        listOfCells.elementAt(63).whoCanTake.add("BLUE")

        for(i in 0..63){
            val strId = "b$i"
            val buttonID = resources.getIdentifier(strId, "id", packageName)
            listOfButtons.add(findViewById(buttonID))
        }

        listOfButtons.forEach(){
            val idS = it.resources.getResourceEntryName(it.id)
            val id = idS.substring(1)
            it.isEnabled = listOfCells[id.toInt()].isClickable
            it.setOnClickListener {
                //jesli RED/BLUE nie moze zajac to return


                if(listOfCells.elementAt(id.toInt()).isClickable){
                    //CheckClickable(id.toInt())
                    //it.setBackgroundColor(Color.RED)
                    if(listOfCells.elementAt(id.toInt()).isOccupied == false){
                        Turn(id.toInt(), whoseTurn)
                    }
                    // a jesli jest occupied to co zrobic
                    // np niebieski kliknie w swoje niebieskie pole i bedzie mogl
                    // wybudowac koszary
                    // albo chate drwala w miejscu (occupied) lasu
                }
            }
        }
        listOfButtons.elementAt(0).background.setTint(Color.RED)
        listOfButtons.elementAt(63).background.setTint(Color.BLUE)
        //CheckClickable(0)
        //CheckClickable(63)
        Turn(0, "RED")
        Turn(63, "BLUE")

    }

    private fun Turn(id:Int, who:String){
        if(!listOfCells.elementAt(id).whoCanTake.contains(who)){
            return
        }

        if (who == "RED") {
            currentTurnTextColor?.setTextColor(Color.BLUE);
            currentTurnTextColor?.setText("Gracz 2");
        } else {
            currentTurnTextColor?.setTextColor(Color.RED);
            currentTurnTextColor?.setText("Gracz 1");
        }

        if(who == "RED"){
            listOfButtons.elementAt(id).background.setTint(Color.RED)
        }
        else listOfButtons.elementAt(id).background.setTint(Color.BLUE)
        SetClickable(id, who)
        listOfCells.elementAt(id).isOccupied = true

        if(who == "RED") whoseTurn = "BLUE"
        else whoseTurn = "RED"
    }

    private fun SetClickable(id:Int, who:String){
        if(id - 1 >= 0 && id%8 > 0){
            listOfButtons.elementAt(id - 1).isEnabled = true
            listOfCells.elementAt(id - 1).isClickable = true
            listOfCells.elementAt(id - 1).whoCanTake.add(who)
        }
        if(id + 1 <= 63 && (id+1)%8 != 0){
            listOfButtons.elementAt(id + 1).isEnabled = true
            listOfCells.elementAt(id + 1).isClickable = true
            listOfCells.elementAt(id + 1).whoCanTake.add(who)
        }
        if(id - 8 >= 0){
            listOfButtons.elementAt(id - 8).isEnabled = true
            listOfCells.elementAt(id - 8).isClickable = true
            listOfCells.elementAt(id - 8).whoCanTake.add(who)
        }
        if(id + 8 <= 63){
            listOfButtons.elementAt(id + 8).isEnabled = true
            listOfCells.elementAt(id + 8).isClickable = true
            listOfCells.elementAt(id + 8).whoCanTake.add(who)
        }
    }

}



