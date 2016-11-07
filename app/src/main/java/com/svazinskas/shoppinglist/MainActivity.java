package com.svazinskas.shoppinglist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> itemArray;
    private ArrayAdapter<String> arrayAdapter;
    private ListView listView;

    List<String> meatList;
    List<String> pastryList;
    List<String> vegetableList;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addItemTypesToSpin();

        prepareListData();
        setExpListAdapter();
    }

    private void setExpListAdapter() {
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<>();

        // Adding child data
        listDataHeader.add("Mesa");
        listDataHeader.add("Kepiniai");
        listDataHeader.add("Darzoves");

        // Adding child data
        meatList = new ArrayList<>();
        pastryList = new ArrayList<>();
        vegetableList = new ArrayList<>();

        listDataChild = new HashMap<>();
        listDataChild.put(listDataHeader.get(0), meatList); // Header, Child data
        listDataChild.put(listDataHeader.get(1), pastryList);
        listDataChild.put(listDataHeader.get(2), vegetableList);
    }

    private void addItemTypesToSpin() {
        List<String> list = new ArrayList<>();
        list.add("KEPINIAI");
        list.add("MESA");
        list.add("DARZOVES");
        list.add("PIENO");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getSpinnerItemType().setAdapter(dataAdapter);

    }

    private Spinner getSpinnerItemType() {
        return (Spinner) findViewById(R.id.spin_itemType);
    }

    public void addToList(View view){
        EditText itemInput = (EditText) findViewById(R.id.itemInput);
        if("MESA".equals(getSpinnerItemType().getSelectedItem().toString()))
            meatList.add(itemInput.getText().toString());
        if("KEPINIAI".equals(getSpinnerItemType().getSelectedItem().toString()))
            pastryList.add(itemInput.getText().toString());
        if("DARZOVES".equals(getSpinnerItemType().getSelectedItem().toString()))
            vegetableList.add(itemInput.getText().toString());
        listAdapter.notifyDataSetChanged();
    }
}
