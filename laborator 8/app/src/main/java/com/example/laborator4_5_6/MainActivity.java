package com.example.laborator4_5_6;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button adauga;
    private Button lista;
    private Button adaugaImagine;

    private Intent intent;
    private static final int REQUEST_CODE_ADD=1;
    private static final int REQUEST_CODE_LIST = 2;
    private ArrayList<Televizor> listaTelevizor= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lista= findViewById(R.id.btnListaId);

        lista.setOnClickListener(v -> {
            Intent intentLista = new Intent(this, ListActivity.class);
            intentLista.putExtra("listaTelevizoare", listaTelevizor);
            startActivityForResult(intentLista, REQUEST_CODE_LIST);
        });
        adaugaImagine= findViewById(R.id.btnImagineId);
        adaugaImagine.setOnClickListener(view-> {
            intent = new Intent(this, ImagineActivity.class);
            startActivity(intent);
        });
        adauga=findViewById(R.id.btnAdaugaId);
        adauga.setOnClickListener(view->{
            intent = new Intent(this, AddActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD);

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK) {

            if (data != null) {
                Televizor televizor =
                        (Televizor) data.getSerializableExtra(AddActivity.ADD_TELEVIZOR);
                if (televizor != null) {
                    listaTelevizor.add(televizor);


                    Toast.makeText(this, televizor.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
        if (requestCode == REQUEST_CODE_LIST && resultCode == RESULT_OK) {

            if (data != null) {

                ArrayList<Televizor> listaActualizata =
                        (ArrayList<Televizor>) data.getSerializableExtra("listaTelevizoare");

                if (listaActualizata != null) {
                    listaTelevizor = listaActualizata;
                }
            }
        }
    }
}