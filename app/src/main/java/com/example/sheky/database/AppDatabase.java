package com.example.sheky.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.sheky.model.Folder;
import com.example.sheky.model.Item;

@Database(entities = {Folder.class, Item.class}, version = 5, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FolderDao folderDao();
    public abstract ItemDao itemDao();
}
