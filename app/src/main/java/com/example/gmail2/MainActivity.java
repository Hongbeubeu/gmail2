package com.example.gmail2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.gmail2.adapters.EmailItemAdapter;
import com.example.gmail2.models.EmailItemModel;

import java.util.ArrayList;
import java.util.List;

import io.bloco.faker.Faker;

public class MainActivity extends AppCompatActivity {
    List<EmailItemModel> items;
    EmailItemAdapter adapter;
    boolean isShowingFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            items.add(new EmailItemModel(faker.name.name(), faker.lorem.sentence(), faker.lorem.paragraph(), "12:34 PM"));
        }
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new EmailItemAdapter(items);
        recyclerView.setAdapter(adapter);

        isShowingFavorites = false;

        findViewById(R.id.btn_favorite).requestFocus();

        findViewById(R.id.btn_favorite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShowingFavorites = !isShowingFavorites ;
                if(isShowingFavorites)
                    adapter.showFavories();
                else
                    adapter.showAll();
            }
        });{

        }
        EditText editKeyword = findViewById(R.id.search_edit_frame);
        editKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String keyword = s.toString();
                if(keyword.length() > 2)
                    adapter.search(keyword);
                else
                    adapter.showAll();
            }
        });
    }
}
