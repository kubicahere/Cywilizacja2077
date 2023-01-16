package com.example.civ2k17newgame

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable


class MainActivity : AppCompatActivity() {
    var listOfButtons = mutableListOf<Button>()
    var listOfCells = mutableListOf<Pole>()
    val g1 = Gracz("RED");
    val g2 = Gracz("BLUE");
    var whoseTurn = g1
    var ktoraTura = 0;
    //var ileDoKonca = 0;
    var currentTurnTextColor: TextView? = null;

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportActionBar()?.hide();
        }

        currentTurnTextColor = findViewById(R.id.textView2);

        //region Przypisanie danych
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Androidly Alert")
        for(i in 0..63){
            listOfCells.add(Pole(i))
        }
        //ileDoKonca = listOfCells.size

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

//        findViewById<Button>(R.id.bInfo).setOnClickListener {
//            val builderAlert = AlertDialog.Builder(this)
//            builderAlert.setTitle("Androidly Alert")
//            builderAlert.setMessage("${whoseTurn.nazwa} \n" +
//                    "${whoseTurn.drewno} \n" +
//                    "${whoseTurn.zelazo}")
//            builderAlert.show()
//        }

        listOfButtons.forEach(){
            val idS = it.resources.getResourceEntryName(it.id)
            val id = idS.substring(1)
            it.isEnabled = listOfCells[id.toInt()].isClickable
            it.setOnClickListener {

                val builderAlert = AlertDialog.Builder(this)
                builderAlert.setTitle("Androidly Alert")
                builderAlert.setMessage(listOfCells.elementAt(id.toInt()).whatIsThat.toString())
                //builderAlert.show()
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



                if(listOfCells.elementAt(id.toInt()).whatIsThat is PustePole){
                    (listOfCells.elementAt(id.toInt()).whatIsThat
                            as PustePole).CzyZajac(this, id.toInt(), whoseTurn)
                }
                else if(listOfCells.elementAt(id.toInt()).whatIsThat is Las){
                    (listOfCells.elementAt(id.toInt()).whatIsThat
                            as Las).CzyZajacLas(this, id.toInt(), whoseTurn)
                }
                else if(listOfCells.elementAt(id.toInt()).whatIsThat is RudaZelaza){
                    (listOfCells.elementAt(id.toInt()).whatIsThat
                            as RudaZelaza).CzyZajacRude(this, id.toInt(), whoseTurn)
                }
            }
            //Turn(id.toInt(), whoseTurn)
        }
        listOfButtons.elementAt(0).background.setTint(Color.RED)
        listOfButtons.elementAt(63).background.setTint(Color.BLUE)
        Turn(0, g1)
        Turn(63, g2)

    }

    fun Turn(id:Int, who:Gracz){
        if(who.nazwa == "RED"){
            listOfButtons.elementAt(id).background.setTint(Color.RED);
            currentTurnTextColor?.setTextColor(Color.BLUE);
            currentTurnTextColor?.setText("Gracz 2");
        }
        else {
            listOfButtons.elementAt(id).background.setTint(Color.BLUE);
            currentTurnTextColor?.setTextColor(Color.RED);
            currentTurnTextColor?.setText("Gracz 1");
        }

        SetClickable(id, who)
        listOfCells.elementAt(id).isOccupied = true
        listOfCells.elementAt(id).whoTake = who
        if(listOfCells.elementAt(id).whatIsThat is PustePole){
            listOfCells.elementAt(id).whatIsThat = ZajetePole()
        }
        else if(listOfCells.elementAt(id).whatIsThat is Las){
            listOfCells.elementAt(id).whatIsThat = ChataDrwala()
            listOfButtons.elementAt(id).background.setTint(Color.GREEN)
        }
        else if(listOfCells.elementAt(id).whatIsThat is RudaZelaza){
            listOfCells.elementAt(id).whatIsThat = KopalniaZelaza()
            listOfButtons.elementAt(id).background.setTint(Color.GRAY)
        }
        who.listaPol.add(listOfCells.elementAt(id))

        ktoraTura++
        if(ktoraTura == 2){
            g1.listaPol.forEach(){
                if(it.whatIsThat is ChataDrwala){
                    (it.whatIsThat as ChataDrwala).PrzydzielDrewno(g1)
                }
                else if(it.whatIsThat is KopalniaZelaza){
                    (it.whatIsThat as KopalniaZelaza).PrzydzielZelazo(g1)
                }
            }
            g2.listaPol.forEach(){
                if(it.whatIsThat is ChataDrwala){
                    (it.whatIsThat as ChataDrwala).PrzydzielDrewno(g2)
                }
                else if(it.whatIsThat is KopalniaZelaza){
                    (it.whatIsThat as KopalniaZelaza).PrzydzielZelazo(g2)
                }
            }
            ktoraTura = 0
        }

        //ileDoKonca--
        if(who.nazwa == "RED") whoseTurn = g2
        else whoseTurn = g1
    }

    private fun SetClickable(id:Int, who:Gracz){
        val tab = mutableListOf<Int>()
        if(id - 1 >= 0 && id%8 > 0){
            tab.add(id - 1)
        }
        if(id + 1 <= 63 && (id+1)%8 != 0){
            tab.add(id + 1)
        }
        if(id - 8 >= 0){
            tab.add(id-8)
        }
        if(id + 8 <= 63){
            tab.add(id + 8)
        }
        for(el in tab) {
            if(listOfCells.elementAt(el).whatIsThat is ZajetePole) {
            }
            else {
                var flag = 0
                if(listOfCells.elementAt(el).isClickable && !listOfCells.elementAt(el).whoCanTake.contains(who) && listOfCells.elementAt(el).whatIsThat !is ZajetePole && listOfCells.elementAt(el).whatIsThat !is KopalniaZelaza && listOfCells.elementAt(el).whatIsThat !is RudaZelaza && listOfCells.elementAt(el).whatIsThat !is Las && listOfCells.elementAt(el).whatIsThat !is ChataDrwala)
                    flag = 1
                listOfButtons.elementAt(el).isEnabled = true
                listOfCells.elementAt(el).isClickable = true
                listOfCells.elementAt(el).whoCanTake.add(who)

                if(flag == 1){
                    listOfButtons.elementAt(el).background.setTint(Color.WHITE)
                }
                else if (who.nazwa == "RED" && listOfCells.elementAt(el).whatIsThat !is ZajetePole && listOfCells.elementAt(el).whatIsThat !is KopalniaZelaza && listOfCells.elementAt(el).whatIsThat !is RudaZelaza && listOfCells.elementAt(el).whatIsThat !is Las && listOfCells.elementAt(el).whatIsThat !is ChataDrwala) {
                    listOfButtons.elementAt(el).background.setTint(Color.parseColor("#f5b5b5"))
                }
                else if (who.nazwa == "BLUE" && listOfCells.elementAt(el).whatIsThat !is ZajetePole && listOfCells.elementAt(el).whatIsThat !is KopalniaZelaza && listOfCells.elementAt(el).whatIsThat !is RudaZelaza && listOfCells.elementAt(el).whatIsThat !is Las && listOfCells.elementAt(el).whatIsThat !is ChataDrwala){
                    listOfButtons.elementAt(el).background.setTint(Color.parseColor("#a5cdfa"))
                }
            }
        }

    }


}



