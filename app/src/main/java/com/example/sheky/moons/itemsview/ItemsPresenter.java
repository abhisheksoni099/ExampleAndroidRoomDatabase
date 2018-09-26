package com.example.sheky.moons.itemsview;

import android.content.Context;
import android.os.AsyncTask;

import com.example.sheky.model.Item;
import com.example.sheky.service.OperationService;

import java.util.List;

public class ItemsPresenter {
    private ItemsView view;

    public ItemsPresenter(ItemsView view) {
        this.view = view;
    }

    public void loadItems(final int folderId, final Context context) {
        new AsyncTask<Void, Void, List<Item>>() {
            @Override
            protected List<Item> doInBackground(Void... voids) {
                return new OperationService().getItems(folderId, context);
            }

            @Override
            protected void onPostExecute(List<Item> items) {
                super.onPostExecute(items);
                view.refreshItems(items);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
