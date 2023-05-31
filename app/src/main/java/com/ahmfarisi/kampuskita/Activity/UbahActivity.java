package com.ahmfarisi.kampuskita.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ahmfarisi.kampuskita.API.APIRequestData;
import com.ahmfarisi.kampuskita.API.RetroServer;
import com.ahmfarisi.kampuskita.Model.ModelResponse;
import com.ahmfarisi.kampuskita.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahActivity extends AppCompatActivity {
    private String yId, yNama, yKota, yAlamat;
    private EditText etNama, etKota,etAlamat;
    private Button btnUbah;
    private String nama, kota, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        Intent ambil = getIntent();
        yId = ambil.getStringExtra("xId");
        yNama = ambil.getStringExtra("xNama");
        yKota = ambil.getStringExtra("xKota");
        yAlamat = ambil.getStringExtra("xAlamat");

        etNama = findViewById(R.id.et_nama);
        etKota = findViewById(R.id.et_kota);
        etAlamat = findViewById(R.id.et_alamat);
        btnUbah = findViewById(R.id.btn_ubah);

        etNama.setText((yNama));
        etKota.setText(yKota);
        etAlamat.setText(yAlamat);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                kota = etKota.getText().toString();
                alamat = etAlamat.getText().toString();

                if(nama.trim().isEmpty()){
                    etNama.setError("Nama Tidak Boleh Kosong!!!!!!");
                }
                else if (kota.trim().isEmpty()){
                    etKota.setError("Kota Tidak Boleh Kosonglah Bangg");
                }
                else if (alamat.trim().isEmpty()) {
                    etAlamat.setError("Alamat Jangan Kau Kosongin Juga");
                }
                else{
                    UbahKampus();
                }
            }
        });

    }

    private void UbahKampus(){
        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = API.ardUpdate(yId, nama, kota, alamat);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode, pesan;
                kode = response.body().getKode();
                pesan = response.body().getPesan();


                Toast.makeText(UbahActivity.this, "kode :" + kode + "Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(UbahActivity.this, "eror:Gagal Menghubungi server!", Toast.LENGTH_SHORT).show();
                Log.d("DISINI","Erornya itu:" + t.getMessage());
            }
        });
    }
}