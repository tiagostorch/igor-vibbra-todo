package com.igordam.todo.ui.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.igordam.todo.R
import com.igordam.todo.data.model.common.ItemModel

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private var dataList = listOf<ItemModel>()

    internal fun setDataList(dataList: List<ItemModel>) {
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

        // Get the data model based on position
        val data = dataList[position]
        data.item?.let { name ->
            holder.title.text = name
        } ?: run { holder.title.text = NO_TITLE }
    }

    //  total count of items in the list
    override fun getItemCount() = dataList.size

    companion object {
        private const val NO_TITLE = "Sem Título"
    }
}