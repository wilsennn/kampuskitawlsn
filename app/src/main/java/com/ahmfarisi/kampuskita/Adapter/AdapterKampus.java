package com.ahmfarisi.kampuskita.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmfarisi.kampuskita.R;

public class AdapterKampus {

    public class VHKampus extends RecyclerView.ViewHolder{
        TextView tvId, tvNama, tvKota, tvAlamat;

        public VHKampus(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvKota = itemView.findViewById(R.id.tv_kota);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
        }
    }

}
