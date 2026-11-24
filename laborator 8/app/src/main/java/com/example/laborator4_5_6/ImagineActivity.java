package com.example.laborator4_5_6;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ImagineActivity extends AppCompatActivity {
    private ListView  listaImagini;
    private WebView webView;
    private Intent intent;
    private List<ImaginiTelevizoare> lista = new ArrayList<>();

    private Executor executor = Executors.newSingleThreadExecutor();

    private Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imagine);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listaImagini = findViewById(R.id.lvImaginiId);
        String[] descrieri = {
                "Televizor Philips",
                "Televizor TCL",
                "Televizor AllView",
                "Televizor Sony",
                "Televizor Sony"
        };

        String[] linkuriImagini = {
                "https://s13emagst.akamaized.net/products/132257/132256476/images/res_bcc887ae06d2f450ffde12536859c9f7.png",
                "https://cdn.dedeman.ro/media/catalog/product/8/1/8129798.jpg",
                "https://www.allview.ro/android-tv/images/img1-11.png",
                "https://www.sony.ro/image/f2c6487074ea1880e9a080c798d01ee5?fmt=png",
                "https://www.flanco.ro/media/catalog/product/cache/e53d4628cd85067723e6ea040af871ec/1/4/146415-1.jpg"
        };

        String[] linkuriPagini = {
                "https://www.emag.ro/",
                "https://www.dedeman.ro/ro",
                "https://www.allview.ro/",
                "https://www.sony.ro/",
                "https://www.flanco.ro/"
        };

        executor.execute(new Runnable() {
            @Override
            public void run() {

                List<Bitmap> imagini = new ArrayList<>();

                for (String link : linkuriImagini) {
                    imagini.add(descarcaImagine(link));
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        for (int i = 0; i < descrieri.length; i++) {
                            lista.add(new ImaginiTelevizoare(
                                    descrieri[i],
                                    imagini.get(i),
                                    linkuriPagini[i]
                            ));
                        }

                        ImagineAdaptor adaptor = new ImagineAdaptor(
                                lista,
                                getApplicationContext(),
                                R.layout.adaptorpersonalizat
                        );

                        listaImagini.setAdapter(adaptor);
                    }
                });
            }
        });
        listaImagini.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);

                intent.putExtra("link", lista.get(i).getLink());

                startActivity(intent);
            }
        });

    }
    private Bitmap descarcaImagine(String link) {
        try {
            URL url = new URL(link);
            InputStream inputStream = url.openStream();
            Bitmap imagine = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return imagine;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}