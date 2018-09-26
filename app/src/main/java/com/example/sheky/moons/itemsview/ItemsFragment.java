package com.example.sheky.moons.itemsview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sheky.model.Item;
import com.example.sheky.moons.R;
import com.example.sheky.moons.mainfolderview.DataAdapter;
import com.example.sheky.moons.mainfolderview.RecyclerItemClickListener;
import com.example.sheky.utilities.Toaster;

import java.util.ArrayList;
import java.util.List;

public class ItemsFragment extends Fragment implements ItemsView {
    private ItemsPresenter presenter;
    private RecyclerView itemRecyclerView;
    private List<String> itemNames;
    private List<Item> items;
    private DataAdapter itemAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_items, container, false);
        presenter = new ItemsPresenter(this);
        itemRecyclerView = view.findViewById(R.id.itemRecyclerView);
        itemRecyclerView.setHasFixedSize(true);
        itemRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        itemNames = new ArrayList<>();
        items = new ArrayList<>();
        itemAdapter = new DataAdapter(itemNames, R.layout.item);
        itemRecyclerView.setAdapter(itemAdapter);
        itemRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), itemRecyclerView,new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toaster.showToast("Link to " + items.get(position).getUrl(), view);
            }
            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));
        presenter.loadItems(getArguments().getInt("folderid"), getContext());
        return view;
    }

    @Override
    public void refreshItems(List<Item> items) {
        itemNames.clear();
        this.items = items;
        for(Item item: items) {
            itemNames.add(item.getName());
        }
        itemAdapter.notifyDataSetChanged();
    }
}
