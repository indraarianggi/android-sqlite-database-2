package com.example.indraarianggi.inventorikaryawan.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.indraarianggi.inventorikaryawan.R;

/**
 * Created by indraarianggi on 05/12/17.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    // ViewHolder akan mendeskripsikan item view.
    TextView tvNama, tvEmail, tvDepee, tvPeree;
    ImageView imageView;
    RelativeLayout list_item;
    ImageButton btnDel;

    public RecyclerViewHolder(View itemView) {
        super(itemView);

        tvNama = (TextView)itemView.findViewById(R.id.tv_nama);
        tvEmail = (TextView)itemView.findViewById(R.id.tv_email);
        tvDepee = (TextView)itemView.findViewById(R.id.tv_depee);
        tvPeree = (TextView)itemView.findViewById(R.id.tv_peree);
        list_item = (RelativeLayout)itemView.findViewById(R.id.list_item);
        btnDel = (ImageButton)itemView.findViewById(R.id.btn_del);
    }
}
