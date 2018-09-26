package com.example.sheky.moons.mainfolderview;

import android.content.Context;
import android.os.AsyncTask;

import com.example.sheky.model.Folder;
import com.example.sheky.service.OperationService;

import java.util.List;

public class FoldersPresenter {
    private FoldersView view;

    public FoldersPresenter(FoldersView view) {
        this.view = view;
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
                view.refreshFolders(folders);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
