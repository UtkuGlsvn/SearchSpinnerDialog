package com.utkuglsvn.searchspinnerdialog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.utkuglsvn.searchspinnerdialog.R
import com.utkuglsvn.searchspinnerdialog.interfaces.OnSpinnerItemClick
import java.util.*
import kotlin.collections.ArrayList

class SpinnerAdapter constructor(
    private var list: List<String>,
    private val listener: OnSpinnerItemClick
) :
    RecyclerView.Adapter<SpinnerAdapter.ModelViewHolder>(), Filterable {

    private var filterList = list

    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val countryName: TextView = view.findViewById(R.id.textItem)
        fun bindItems(item: String) {
            countryName.text = item
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return ModelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bindItems(filterList[position])
        holder.itemView.setOnClickListener {
            listener.onItemClick(filterList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                filterList = if (charSearch.isEmpty()) {
                    list
                } else {
                    val resultList = ArrayList<String>()
                    for (row in list) {
                        if (row.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as ArrayList<String>
                notifyDataSetChanged()
            }

        }
    }
}