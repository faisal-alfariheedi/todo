package com.example.todo

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.sql.Array

class MainActivity : AppCompatActivity() {
    ///////////////vars/////////////////////
    private lateinit var rvm: RecyclerView
    lateinit var todoin: ArrayList<items>
    private lateinit var ad: FloatingActionButton
    var del: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ///////////////////var init/////////////////////

        ad=findViewById(R.id.addbut)
        rvm = findViewById(R.id.rv)

        todoin= ArrayList()
        rvm.adapter=RVAdapter(todoin)
        rvm.layoutManager= LinearLayoutManager(this)



        ////////////////////main//////////////////////////
        ad.setOnClickListener{
            alert()
        }








    }

    //////////////////////functions////////////////////////

    fun alert(){
        // first we create a variable to hold an AlertDialog builder
        val dialogBuilder = AlertDialog.Builder(this)
        var it= items("a",false)
        // set input on dialog
        val input= EditText(this)


        // if the dialog is cancelable
        dialogBuilder.setCancelable(false)
        // positive button text and action

        dialogBuilder.setPositiveButton("ADD", DialogInterface.OnClickListener {
                dialog, id ->  it.item=input.text.toString();todoin.add(it);rvm.adapter?.notifyDataSetChanged()
        })
        // negative button text and action
        .setNegativeButton("No", DialogInterface.OnClickListener {
                dialog, id -> dialog.cancel()
        })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box

        alert.setTitle("New Item")

        alert.setView(input)

        // show alert dialog
        alert.show()

    }






    /////////////////////menu////////////////////////////

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.del, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var dellist = ArrayList<Int>()
        when (item.itemId) {
            R.id.dele -> {
                for (i in todoin) {
                if(i.chek){
                    dellist.add(todoin.indexOf(i))

                }

                }
                dellist.sortDescending() // i choose to do this because it is kinda easier to implement
                for (i in dellist){
                    todoin.removeAt(i)
                }
                rvm.adapter?.notifyDataSetChanged()
            }

        }
        return super.onOptionsItemSelected(item)
    }

}

