package com.example.sheky.moons.itemadditionview;

import android.inputmethodservice.InputMethodService;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.sheky.model.Folder;
import com.example.sheky.moons.R;
import com.example.sheky.utilities.Dump;
import com.example.sheky.utilities.Toaster;
import com.example.sheky.utilities.ViewHelper;

import java.util.ArrayList;
import java.util.List;

public class ItemAdditionFragment extends Fragment implements ItemAdditionView {
    private EditText folderNameEditText;
    private EditText itemNameEditText;
    private EditText urlEditText;
    private Button addButton;
    private Spinner folderSpinner;
    private List<Folder> folders;
    private List<String> folderNames;
    ArrayAdapter<String> folderSpinnerAdapter;
    private ItemAdditionPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_itemaddition, container, false);
        presenter = new ItemAdditionPresenter(this);
        folderNameEditText = view.findViewById(R.id.folderNameEditText);
        itemNameEditText = view.findViewById(R.id.itemNameEditText);
        urlEditText = view.findViewById(R.id.urlEditText);
        folderSpinner = view.findViewById(R.id.folderSpinner);
        folderNames = new ArrayList<>();
        folderSpinnerAdapter = new ArrayAdapter<>(container.getContext(), R.layout.support_simple_spinner_dropdown_item, folderNames);
        folderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        folderSpinner.setAdapter(folderSpinnerAdapter);
        folderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                folderNameEditText.setText(folders.get(position).getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        presenter.loadFolders(getContext());
        addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!folderNameEditText.getText().toString().trim().equals("")
                        && !itemNameEditText.getText().toString().trim().equals("")
                        && !urlEditText.getText().toString().trim().equals("")) {
                    presenter.addItem(folderNameEditText.getText().toString(), itemNameEditText.getText().toString(), urlEditText.getText().toString(), getContext());
                    Toaster.showToast("Item added", container);
                } else {
                    Toaster.showToast("Please fill all the entries", container);
                }
                ViewHelper.hideKeyboard(view);
            }
        });
        return view;
    }

    @Override
    public void setupSpinner(List<Folder> folders) {
        this.folders = folders;
        folderNames.clear();
        Folder fillerFoler = new Folder();
        fillerFoler.setName("");
        folders.add(0, fillerFoler);
        for(Folder folder: folders) {
            folderNames.add(folder.getName());
        }
        folderSpinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void itemAdded() {
        presenter.loadFolders(getContext());
        folderSpinner.setSelection(0);
        folderNameEditText.setText("");
        itemNameEditText.setText("");
        urlEditText.setText("");
    }
}
