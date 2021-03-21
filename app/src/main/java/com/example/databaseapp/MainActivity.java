package com.example.databaseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.example.databaseapp.Database.Database;
import com.example.databaseapp.Search.SearchInput;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchInput input;

    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();

    Database database;

    Button helpButton;
    Button aboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // There we will init the View
        recyclerView = (RecyclerView)findViewById(R.id.recycler_search);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        materialSearchBar = (MaterialSearchBar)findViewById(R.id.search_bar);

        // Initialisation of the database
        database = new Database(this );

        // Search bar
        materialSearchBar.setHint("Search");
        materialSearchBar.setCardViewElevation(10);
        loadSuggestList();
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                List<String> suggest = new ArrayList<>();
                for (String search:suggestList)
                {
                    if (search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled)
            {
                if(!enabled)
                {
                    input = new SearchInput(getBaseContext(), database.getDrivers());
                    recyclerView.setAdapter(input);
                }

            }

            @Override
            public void onSearchConfirmed(CharSequence text)
            {
                startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        //Init first page (display all drivers)
        input = new SearchInput(this, database.getDrivers());
        recyclerView.setAdapter(input);

        setupControls();
    }

    private void setupControls()
    {
        helpButton = findViewById(R.id.helpButton);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), HelpActivity.class);
                startActivity(intent);
            }
        });

        aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AboutActivity.class);
                startActivity(intent);
            }
        });
    }

    private void startSearch(String text)
    {
        input = new SearchInput(this, database.getDriverByName(text));
        recyclerView.setAdapter(input);
    }

    private void loadSuggestList()
    {
        suggestList = database.getNames();
        materialSearchBar.setLastSuggestions(suggestList);
    }
}