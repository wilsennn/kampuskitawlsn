package com.ahmfarisi.kampuskita.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ahmfarisi.kampuskita.API.APIRequestData;
import com.ahmfarisi.kampuskita.API.RetroServer;
import com.ahmfarisi.kampuskita.Adapter.AdapterKampus;
import com.ahmfarisi.kampuskita.Model.ModelKampus;
import com.ahmfarisi.kampuskita.Model.ModelResponse;
import com.ahmfarisi.kampuskita.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvKampus;
    private FloatingActionButton fabTambah;
    private ProgressBar pbKampus;

    private RecyclerView.Adapter adKampus;
    private RecyclerView.LayoutManager lmKampus;
    private List<ModelKampus> listKampus = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvKampus = findViewById(R.id.rv_kampus);
        fabTambah = findViewById(R.id.fab_tambah);
        pbKampus = findViewById(R.id.pb_kampus);

        lmKampus = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        rvKampus.setLayoutManager(lmKampus);
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveKampus();
    }

    public void retrieveKampus(){
        pbKampus.setVisibility(View.VISIBLE);

        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = API.ardRetrieve();

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listKampus = response.body().getData();

                adKampus = new AdapterKampus(MainActivity.this, listKampus);
                rvKampus.setAdapter(adKampus);
                adKampus.notifyDataSetChanged();

                pbKampus.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "gagal menghubungi server!",Toast.LENGTH_SHORT).show();
                pbKampus.setVisibility(View.GONE);
            }
        });
    }
}