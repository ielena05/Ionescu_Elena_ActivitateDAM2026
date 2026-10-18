package com.lab2_3_dam;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.widget.Button;
import android.os.Bundle;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        Button btnOpenThird= findViewById(R.id.btnOpenThird);

        btnOpenThird.setOnClickListener( v ->  {
            Intent intent= new Intent(SecondActivity.this, ThirdActivity.class);
            Bundle bundle= new Bundle();

            bundle.putString("mesaj", "Buna din activitate2");
                    bundle.putInt("valoare1", 10);
            bundle.putInt("valoare2", 20);
            intent.putExtras(bundle);

            startActivityForResult(intent, 100);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode==RESULT_OK){
            String mesaj= data.getStringExtra("mesajRezultat");
            int suma= data.getIntExtra("suma", 0);

            Toast
                    .makeText(
                    SecondActivity.this,
                    mesaj + "Suma este :" + suma,
                    Toast.LENGTH_LONG
            ) .show();
        }
    }



}