package com.example.intern.intern;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 */
public class listFragment extends Fragment implements View.OnClickListener {

    String[] list = { "Aerith Gainsborough", "Barret Wallace", "Cait Sith"
            , "Cid Highwind", "Cloud Strife", "RedXIII", "Sephiroth"
            , "Tifa Lockhart", "Vincent Valentine", "Yuffie Kisaragi"
            , "ZackFair"};

    ArrayList<String> listDataContact = new ArrayList<>();
    ArrayList<ContactData> contactData;
    Map<String, Integer> mapIndex;
    listViewAdapter adapter;
    ListView listView;
    View rootView;
    EditText search;


    public listFragment() {
        // Required empty public constructor
    }

    public ArrayList<ContactData> section(String[] strName) {
        listDataContact = new ArrayList<>(Arrays.asList(strName));
        ContactData data;
        String prev = "";
        ArrayList<ContactData> all = new ArrayList<>();
        for (String name : strName) {
            String alpha = name.substring(0, 1).toUpperCase();
            if (!alpha.equalsIgnoreCase(prev)) {
                prev = alpha;
                all.add(new ContactData(alpha, true));
            }
            all.add(new ContactData(name, false));
        }

       /* Iterator itr = hSetData.iterator();
        while(itr.hasNext()) {
            listDataContact.add((String) itr.next());
        }
        Collections.sort(listDataContact);
*/
        return all;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        contactData = section(list);
        rootView = inflater.inflate(R.layout.fragment_list, container, false);
        adapter = new listViewAdapter(getActivity(), contactData);
        listView = (ListView)rootView.findViewById(R.id.listView);
        search = (EditText)rootView.findViewById(R.id.search);




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = listView.getItemAtPosition(position);
                Intent goContactView = new Intent(getActivity() , viewContact.class);
                Log.i("click",position+"");
                goContactView.putExtra("id", contactData.get(position).getValue());
                startActivity(goContactView);
            }
        });


         search.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {
                 adapter.getFilter().filter(s);
             }

             @Override
             public void afterTextChanged(Editable s) {

             }
         });

        getIndexList();
        displayIndex();
        listView.setAdapter(adapter);
        return rootView;
    }


    private void getIndexList() {
        mapIndex = new LinkedHashMap<String, Integer>();
       String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        for (String anAlphabet : alphabet) {
            for (int j = 0; j < contactData.size(); j++) {
                Log.i("Pang",contactData.get(j).getValue());
                if (anAlphabet.equals(contactData.get(j).getValue())) {
                    mapIndex.put(contactData.get(j).getValue(), j);
                }
            }

            if (mapIndex.get(anAlphabet) == null) {
                mapIndex.put(anAlphabet, null);
            }
        }
    }



    private void displayIndex() {
        LinearLayout indexLayout = (LinearLayout) rootView.findViewById(R.id.side_index);
        TextView textView;
        List<String> indexList = new ArrayList<String>(mapIndex.keySet());
        for (String index : indexList) {
            textView = (TextView) getActivity().getLayoutInflater().inflate(
                    R.layout.side_index_item, null);
            textView.setText(index);
            textView.setOnClickListener(this);
            indexLayout.addView(textView);
        }
    }

    public void onClick(View view) {
        TextView selectedIndex = (TextView) view;
        if(mapIndex.get(selectedIndex.getText())!= null) {
            listView.setSelection(mapIndex.get(selectedIndex.getText()));
        }
        //listView.mapIndex.get(selectedIndex.getText());

        Log.i("Pang", mapIndex.get(selectedIndex.getText())+"");
        listView.getSelectedItem();
        //Log.i("Pang",mapIndex.get(selectedIndex.getText())+"");
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.menu_tab, menu);
        return true;
    }

}

