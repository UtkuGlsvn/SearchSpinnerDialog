package com.utkuglsvn.searchspinnerdialog.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utkuglsvn.searchspinnerdialog.R
import com.utkuglsvn.searchspinnerdialog.adapter.SpinnerAdapter
import com.utkuglsvn.searchspinnerdialog.interfaces.OnDialogItemListener
import com.utkuglsvn.searchspinnerdialog.interfaces.OnSpinnerItemClick

class SearchSpinnerDialog(
    _list: MutableList<String>,
    _listener: OnDialogItemListener
) : DialogFragment(), OnSpinnerItemClick {

    private var list = _list
    private var listener: OnDialogItemListener = _listener

    private lateinit var adapter: SpinnerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.setContentView(R.layout.dialog_search_spinner)
        initDialog()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun initDialog() {
        adapter = SpinnerAdapter(list, this)
        closeDialog()
        searchItem()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerView = dialog!!.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView?.adapter = adapter
    }

    private fun searchItem() {
        val searchView = dialog!!.findViewById<SearchView>(R.id.searchView)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })
    }

    private fun closeDialog() {
        val closeText = dialog!!.findViewById<TextView>(R.id.closeTxtView)
        val closeImage = dialog!!.findViewById<ImageView>(R.id.closeImgView)
        closeImage.setOnClickListener {
            dialog!!.hide()
        }
        closeText.setOnClickListener {
            dialog!!.hide()
        }
    }

    override fun onStart() {
        //Dialog custom size

//        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
//        val height = (resources.displayMetrics.widthPixels * 0.9).toInt()
//        dialog!!.window?.setLayout(width, height)
        super.onStart()
    }

    override fun onItemClick(item: String, position: Int) {
        listener.onItemDialogClick(item, position)
        dialog?.hide()
    }

}