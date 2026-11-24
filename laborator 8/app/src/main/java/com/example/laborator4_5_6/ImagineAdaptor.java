package com.example.laborator4_5_6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImagineAdaptor extends BaseAdapter {
    private List<ImaginiTelevizoare> imagini = null;
    private Context ctx;
    private int resursaLayout;

    public ImagineAdaptor(List<ImaginiTelevizoare> imagini,
                          Context ctx, int resursaLayout) {
        this.imagini = imagini;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return imagini.size();
    }

    @Override
    public Object getItem(int i) {
        return imagini.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(ctx);

        View v = inflater.inflate(resursaLayout, viewGroup, false);

        ImageView imagine = v.findViewById(R.id.imgTelevizor);
        TextView descriere = v.findViewById(R.id.tvDescriere);

        ImaginiTelevizoare obiect = imagini.get(i);

        imagine.setImageBitmap(obiect.getImagine());
        descriere.setText(obiect.getTextAfisat());

        return v;
    }
}
