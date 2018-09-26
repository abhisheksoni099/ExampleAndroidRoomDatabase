package com.example.sheky.moons.itemadditionview;

import android.content.Context;
import android.os.AsyncTask;

import com.example.sheky.model.Folder;
import com.example.sheky.service.OperationService;

import java.util.List;

public class ItemAdditionPresenter {
    private ItemAdditionView view;

    public ItemAdditionPresenter(ItemAdditionView view) {
        this.view = view;
    }

    public void addItem(final String folderName, final String itemName, final String url, final Context context) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                new OperationService().addItem(folderName, itemName, url, context);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                view.itemAdded();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void loadFolders(final Context context) {
        new AsyncTask<Void, Void, List<Folder>>() {
            @Override
            protected List<Folder> doInBackground(Void... voids) {
                return new OperationService().getFolders(context);
            }

            @Override
            protected void onPostExecute(List<Folder> folders) {
                super.onPostExecute(folders);
                view.setupSpinner(folders);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
