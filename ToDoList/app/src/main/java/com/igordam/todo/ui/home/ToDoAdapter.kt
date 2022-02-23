package com.igordam.todo.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.igordam.todo.R
import com.igordam.todo.data.model.response.ToDoListResponse

class ToDoAdapter(
    private val itemCallback: (id: Int) -> Unit,
    private val editCallback: (id: Int, name: String) -> Unit,
    private val deleteCallback: (id: Int) -> Unit
) : RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {

    private var dataList = listOf<ToDoListResponse>()

    internal fun setDataList(dataList: List<ToDoListResponse>) {
        this.dataList = dataList
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: AppCompatTextView = itemView.findViewById(R.id.text_name)
        var edit: AppCompatImageButton = itemView.findViewById(R.id.image_button_edit)
        var delete: AppCompatImageButton = itemView.findViewById(R.id.image_button_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = dataList[position]
        holder.title.text = data.name
        holder.title.setOnClickListener { itemCallback(data.id) }
        holder.edit.setOnClickListener { editCallback(data.id, data.name) }
        holder.delete.setOnClickListener { deleteCallback(data.id) }
    }

    //  total count of items in the list
    override fun getItemCount() = dataList.size
}