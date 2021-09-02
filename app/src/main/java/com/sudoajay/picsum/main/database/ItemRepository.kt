package com.sudoajay.picsum.main.database

import androidx.lifecycle.LiveData
import com.sudoajay.picsum.main.model.Item


class ItemRepository(private val itemDoa: ItemDoa) {


    suspend fun getItemList() : LiveData<List<Item>> {
        return itemDoa.getItemList()
    }

    suspend fun insert(item: Item) {
        itemDoa.insert(item)
    }

    
}