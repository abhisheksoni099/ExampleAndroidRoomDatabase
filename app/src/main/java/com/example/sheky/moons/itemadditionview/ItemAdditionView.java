package com.example.sheky.moons.itemadditionview;

import com.example.sheky.model.Folder;

import java.util.List;

public interface ItemAdditionView {
    void setupSpinner(List<Folder> folders);
    void itemAdded();
}
