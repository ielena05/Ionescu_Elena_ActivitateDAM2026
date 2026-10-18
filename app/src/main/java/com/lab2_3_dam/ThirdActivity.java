package com.lab2_3_dam;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.widget.Toast;
import android.content.Intent;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);

        Bundle bundle= getIntent().getExtras();
        String mesaj= bundle.getString("mesaj");
        int valoare1= bundle.getInt("valoare1");
        int valoare2= bundle.getInt("valoare2");
        if(bundle!=null) {


            Toast.makeText(
                    ThirdActivity.this,
                    mesaj+ " "+ valoare1 + " " + valoare2,
                    Toast.LENGTH_LONG ).show();

        }

        Button btnTrimiteRezultat = findViewById(R.id.btnTrimiteRezultat);
        btnTrimiteRezultat.setOnClickListener( v -> {
            int suma= valoare1+valoare2;
            Intent intentRezultat= new Intent();
            intentRezultat.putExtra("mesajRezultat", "Rezultat Calculat");
            intentRezultat.putExtra("suma", suma);

            setResult(RESULT_OK, intentRezultat);
            finish();
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}