package com.example.sheky.moons.mainfolderview;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sheky.model.Folder;
import com.example.sheky.moons.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class FoldersFragment extends Fragment implements FoldersView {
    private RecyclerView folderRecyclerView;
    private FoldersPresenter presenter;
    private DataAdapter folderAdapter;
    private List<String> folderNames;
    private List<Folder> folders;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_folders, container, false);
        presenter = new FoldersPresenter(this);
        folderRecyclerView = view.findViewById(R.id.folderRecyclerView);
        folderRecyclerView.setHasFixedSize(true);
        folderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        folderNames = new ArrayList<>();
        folders = new ArrayList<>();
        folderAdapter = new DataAdapter(folderNames, R.layout.folder);
        folderRecyclerView.setAdapter(folderAdapter);
        folderRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), folderRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                openFolder(folders.get(position).getId());
            }
            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));
        presenter.loadFolders(getContext());
        return view;
    }

    private void openFolder(int folderId) {
        ((MainActivity)(getActivity())).openFolder(folderId);
    }

    @Override
    public void refreshFolders(List<Folder> folders) {
        folderNames.clear();
        this.folders = folders;
        for(Folder folder: folders) {
            folderNames.add(folder.getName());
        }
        folderAdapter.notifyDataSetChanged();
    }
}
