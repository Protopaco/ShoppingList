package com.example.shoppinglist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_shopping.view.*

class ShoppingAdapter (
    private val shoppingItems: MutableList<ShoppingItem>
) : RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder>() {

    class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        return ShoppingViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_shopping,
                parent,
                false
            )
        )
    }

    fun addShoppingItem(shoppingItem: ShoppingItem){
        shoppingItems.add(shoppingItem)
        notifyItemInserted(shoppingItems.size - 1)
    }

    fun deleteDoneItems() {
        shoppingItems.removeAll{shoppingItem -> shoppingItem.isChecked}
        notifyDataSetChanged()
    }
    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean){
        if (isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentItem = shoppingItems[position]
        holder.itemView.apply{
            tvShoppingTitle.text = currentItem.title
            cbDone.isChecked = currentItem.isChecked
            toggleStrikeThrough(tvShoppingTitle, currentItem.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvShoppingTitle, isChecked)
                currentItem.isChecked = !currentItem.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
       return shoppingItems.size
    }
}