package com.redveloper.movies.ui.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.redveloper.movies.R
import com.redveloper.movies.domain.entity.TestModel

class TestAdapter : RecyclerView.Adapter<TestAdapter.TestViewHolder>(), Filterable {

    private var data: ArrayList<TestModel> = arrayListOf()
    private var dataFilter: ArrayList<TestModel> = arrayListOf()

    private var callback: Callback? = null

    fun setData(data: List<TestModel>?){
        if(!data.isNullOrEmpty()){
            this.data.addAll(data)
            this.dataFilter.addAll(data)
            notifyDataSetChanged()
        }
    }

    fun setCallback(callback: Callback){
        this.callback = callback
    }

    class TestViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        lateinit var tvTitle: TextView
        lateinit var tvDesc: TextView

        fun bind(){
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvDesc = itemView.findViewById(R.id.tvDesc)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        return TestViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_test_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return dataFilter.size
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.bind()

        holder.tvTitle.text = dataFilter[position].title
        holder.tvDesc.text = dataFilter[position].body

        holder.itemView.setOnClickListener {
            if(this.callback != null)
                this.callback?.onClickItem(dataFilter[position])
        }
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) dataFilter = data
                else {
                    val filteredList = ArrayList<TestModel>()
                    data
                        .filter {
                            (it.title.contains(constraint?: ""))
                        }
                        .forEach { filteredList.add(it) }
                    dataFilter = filteredList

                }
                return FilterResults().apply { values = dataFilter }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataFilter = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<TestModel>
                notifyDataSetChanged()
            }

        }
    }

    interface Callback{
        fun onClickItem(data: TestModel)
    }
}