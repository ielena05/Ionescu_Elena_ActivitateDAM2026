package com.example.laborator4_5_6;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity {
    protected static final String ADD_TELEVIZOR="addT";
    Intent intent;
     EditText nume;
     CheckBox smart;
     Spinner firmaTelevizor;
    RadioGroup dimensiuneTelevizor;
    Button btnSalveaza;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        intent= getIntent();
        nume=findViewById(R.id.editNumeId);
        smart=findViewById(R.id.checkHdId);
        firmaTelevizor=findViewById(R.id.spinnerFirmaId);
        ArrayAdapter<Firma> adapter= new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,Firma.values());
        firmaTelevizor.setAdapter(adapter);
        dimensiuneTelevizor=findViewById(R.id.rgDimensiuneId);
        Televizor televizorEdit = (Televizor)
                getIntent().getSerializableExtra("televizorEdit");

        if (televizorEdit != null) {

            nume.setText(televizorEdit.getNumeClient());

            smart.setChecked(televizorEdit.isVreiSmart());

            for (int i = 0; i < firmaTelevizor.getCount(); i++) {
                if (firmaTelevizor.getItemAtPosition(i).equals(televizorEdit.getFirma())) {
                    firmaTelevizor.setSelection(i);
                    break;
                }
            }
            for (int i = 0; i < dimensiuneTelevizor.getChildCount(); i++) {

                RadioButton radioButton =
                        (RadioButton) dimensiuneTelevizor.getChildAt(i);

                if (radioButton.getText().toString()
                        .equals(televizorEdit.getDimensiune().name())) {

                    radioButton.setChecked(true);
                    break;
                }
            }for (int i = 0; i < dimensiuneTelevizor.getChildCount(); i++) {

                RadioButton radioButton =
                        (RadioButton) dimensiuneTelevizor.getChildAt(i);

                if (radioButton.getText().toString()
                        .equals(televizorEdit.getDimensiune().name())) {

                    radioButton.setChecked(true);
                    break;
                }
            }

        }
        btnSalveaza= findViewById(R.id.btnSaveId);
        btnSalveaza.setOnClickListener(v->{
            String numeClient= nume.getText().toString();
            boolean vreiSmart=smart.isChecked();
            Firma firma= (Firma) firmaTelevizor.getSelectedItem();
            RadioButton rBdimensiune= findViewById(dimensiuneTelevizor.getCheckedRadioButtonId());
            Dimensiune dimensiune= Dimensiune.valueOf(rBdimensiune.getText().toString());
            Televizor televizor;

            if (televizorEdit != null) {
                televizorEdit.setNumeClient(numeClient);
                televizorEdit.setFirma(firma);
                televizorEdit.setVreiSmart(vreiSmart);
                televizorEdit.setDimensiune(dimensiune);

                televizor = televizorEdit;
            } else {

                televizor = new Televizor(numeClient, firma, vreiSmart, dimensiune);
            }

            Intent rezultat = new Intent();
            rezultat.putExtra(ADD_TELEVIZOR, televizor);
            rezultat.putExtra("pozitie", getIntent().getIntExtra("pozitie", -1));
            setResult(RESULT_OK, rezultat);
            finish();
        });


    }
}