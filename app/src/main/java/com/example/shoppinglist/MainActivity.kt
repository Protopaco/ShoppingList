package com.example.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var shoppingAdapter: ShoppingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        shoppingAdapter = ShoppingAdapter(mutableListOf())

        rvShoppingItems.adapter = shoppingAdapter
        rvShoppingItems.layoutManager = LinearLayoutManager(this)

        btnAddShoppingItem.setOnClickListener {
            val shoppingItemTitle = etNewTitle.text.toString()
            if(shoppingItemTitle.isNotEmpty()){
                val shoppingItem = ShoppingItem(shoppingItemTitle)
                shoppingAdapter.addShoppingItem(shoppingItem)
                etNewTitle.text.clear()
            }
        }

        btnDeleteDoneShoppingItem.setOnClickListener {
            shoppingAdapter.deleteDoneItems()
        }
    }
}