package com.example.sheky.service;

import android.content.Context;

import com.example.sheky.database.FolderDao;
import com.example.sheky.database.ItemDao;
import com.example.sheky.model.Folder;
import com.example.sheky.model.Item;

import java.util.List;

public class OperationService {
    private FolderDao getFolderDao(Context context) {
        return DatabaseService.getInstance().getApplicationDatabase(context).folderDao();
    }

    private ItemDao getItemDao(Context context) {
        return DatabaseService.getInstance().getApplicationDatabase(context).itemDao();
    }

    public void addItem(String folderName, String itemName, String url, Context context) {
        Folder folder = getFolderDao(context).loadFolderByName(folderName);
        long insertedFolderRowId;
        if(folder == null)  {
            Folder newFolder = new Folder();
            newFolder.setName(folderName);
            insertedFolderRowId = getFolderDao(context).insertFolder(newFolder);
        } else {
            insertedFolderRowId = folder.getId();
        }
        Item item = new Item();
        item.setFolderId((int)insertedFolderRowId);
        item.setName(itemName);
        item.setUrl(url);
        getItemDao(context).insertItem(item);
    }

    public List<Folder> getFolders(Context context) {
        return getFolderDao(context).loadFolders();
    }

    public List<Item> getItems(int folderId, Context context) {
        return getItemDao(context).loadItemsByFolderId(folderId);
    }
}
