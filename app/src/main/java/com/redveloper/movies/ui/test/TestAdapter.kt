package com.redveloper.movies.ui.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.redveloper.movies.R
import com.redveloper.movies.domain.entity.TestModel

class TestAdapter : RecyclerView.Adapter<TestAdapter.TestViewHolder>() {

    private var data: List<TestModel> = listOf()

    fun setData(data: List<TestModel>?){
        if(!data.isNullOrEmpty())
            this.data = data
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
        return data.size
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.bind()

        holder.tvTitle.text = data[position].title
        holder.tvDesc.text = data[position].body
    }
}