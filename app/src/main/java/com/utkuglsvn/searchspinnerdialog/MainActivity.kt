package com.utkuglsvn.searchspinnerdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.utkuglsvn.searchspinnerdialog.dialog.SearchSpinnerDialog
import com.utkuglsvn.searchspinnerdialog.interfaces.OnDialogItemListener

class MainActivity : AppCompatActivity(), OnDialogItemListener {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        //prepare list
        val nameList = mutableListOf<String>()
        for (enum in Name.values()) {
            nameList.add(enum.toString())
        }

        textView = findViewById(R.id.textView)
        textView.setOnClickListener {
            val dialog = SearchSpinnerDialog(nameList, this)
            dialog.show(supportFragmentManager, "searchSpinner")
        }
    }

    override fun onItemDialogClick(item: String, position: Int) {
        Toast.makeText(applicationContext, "position: $position value:$item", Toast.LENGTH_SHORT)
            .show()
    }


    //Name List
    enum class Name {
        Liam,
        Noah,
        Oliver,
        Elijah,
        William,
        James,
        Benjamin,
        Lucas,
        Henry,
        Alexander,
        Utku,
        Glsvn,
        Joseph,
        John,
        David,
        Wyatt,
        Matthew,
        Luke,
        Asher,
        Carter,
        Julian,
        Grayson,
        Leo
    }
}