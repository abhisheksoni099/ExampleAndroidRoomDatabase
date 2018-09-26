package com.example.sheky.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.sheky.model.Item;

import java.util.List;

@Dao
public interface ItemDao {
    @Query("select * from Item where folderId = :folderId")
    List<Item> loadItemsByFolderId(int folderId);

    @Insert
    void insertItem(Item item);
}
