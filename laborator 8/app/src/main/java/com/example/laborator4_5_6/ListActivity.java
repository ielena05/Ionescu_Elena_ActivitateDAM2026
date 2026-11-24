package com.example.laborator4_5_6;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private ListView listViewTelevizoare;
    private ArrayList<Televizor> listaTelevizor;
    private ArrayAdapter<Televizor> adapter;
    private Intent intent;
    private static final int REQUEST_CODE_EDIT=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listViewTelevizoare= findViewById(R.id.lvTelevizoareId);
        ArrayList<Televizor> listaTelevizor =

                (ArrayList<Televizor>) getIntent().getSerializableExtra("listaTelevizoare");
        Toast.makeText(this,
                "Numar televizoare: " +
                        (listaTelevizor == null ? "null" : listaTelevizor.size()),
                Toast.LENGTH_LONG).show();
        if (listaTelevizor != null) {

            ArrayAdapter<Televizor> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    listaTelevizor
            );

            listViewTelevizoare.setAdapter(adapter);
            listViewTelevizoare.setOnItemClickListener((parent, view, position, id) -> {

                Televizor televizor = listaTelevizor.get(position);

                Intent intent = new Intent(this, AddActivity.class);

                intent.putExtra("televizorEdit", televizor);
                intent.putExtra("pozitie", position);

                startActivityForResult(intent, REQUEST_CODE_EDIT);

            });
            listViewTelevizoare.setOnItemLongClickListener((parent, view, position, id) -> {

                listaTelevizor.remove(position);

                adapter.notifyDataSetChanged();
                Intent rezultat = new Intent();
                rezultat.putExtra("listaTelevizoare", listaTelevizor);
                setResult(RESULT_OK, rezultat);

                Toast.makeText(this,
                        "Televizor sters cu succes!",
                        Toast.LENGTH_SHORT).show();

                return true;
            });

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_EDIT &&
                resultCode == RESULT_OK && data != null) {

            Televizor televizorModificat =
                    (Televizor) data.getSerializableExtra(AddActivity.ADD_TELEVIZOR);

            int pozitie = data.getIntExtra("pozitie", -1);

            if (televizorModificat != null &&
                    pozitie >= 0 && pozitie < listaTelevizor.size()) {

                listaTelevizor.set(pozitie, televizorModificat);
                adapter.notifyDataSetChanged();
            }
        }
    }
}