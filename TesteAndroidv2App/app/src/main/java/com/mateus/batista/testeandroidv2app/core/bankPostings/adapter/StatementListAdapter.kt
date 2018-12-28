package com.mateus.batista.testeandroidv2app.core.bankPostings.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mateus.batista.testeandroidv2app.R
import com.mateus.batista.testeandroidv2app.base.view.listeners.OnItemClickListener
import com.mateus.batista.testeandroidv2app.data.local.entity.LocalStatement

class StatementListAdapter(
    val statementList: List<LocalStatement>,
    val listener: OnItemClickListener<LocalStatement>
) : RecyclerView.Adapter<StatementListAdapter.StatementListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatementListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_statement, parent, false)
        return StatementListViewHolder(view)
    }

    override fun getItemCount(): Int = statementList.size

    override fun onBindViewHolder(holder: StatementListViewHolder, position: Int) {
        val statement = statementList[position]
        holder.bindItemView(statement) { listener.onItemClick(statement, position) }
    }

    class StatementListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val desc: TextView = view.findViewById(R.id.desc)
        val date: TextView = view.findViewById(R.id.date)
        val value: TextView = view.findViewById(R.id.value)

        fun bindItemView(statement: LocalStatement, onItemClick: () -> Unit) {
            title.text = statement.title
            desc.text = statement.desc
            date.text = statement.date
            value.text = statement.value
            view.setOnClickListener { onItemClick }
        }
    }
}