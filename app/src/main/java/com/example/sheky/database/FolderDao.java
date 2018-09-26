package com.example.sheky.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.sheky.model.Folder;

import java.util.List;

@Dao
public interface FolderDao {
    @Query("select * from Folder")
    List<Folder> loadFolders();

    @Query("select * from Folder where id = :id")
    Folder loadFolderById(int id);

    @Query("select * from Folder where name = :name")
    Folder loadFolderByName(String name);

    @Insert
    long insertFolder(Folder folder);
}
