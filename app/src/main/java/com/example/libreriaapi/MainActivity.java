package com.example.libreriaapi;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.libreriaapi.Data.Volume;
import com.example.libreriaapi.Data.VolumesResponse;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int ITEMS_PER_PAGE = 10;
    TextView busqueda, autor;
    Button buscar, botonMas;
    RecyclerView listado;
    BookSearchViewModel vm;
    LiveData<VolumesResponse> data;


    int startIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_booksearch);

        busqueda = findViewById(R.id.idBusqueda);
        autor = findViewById(R.id.idAutor);
        buscar = findViewById(R.id.idBuscar);
        listado = findViewById(R.id.idList);
        botonMas = findViewById(R.id.botonMas);


        startIndex = 0;

        BookSearchResultsAdapter adapter = new BookSearchResultsAdapter();
        listado.setLayoutManager(new LinearLayoutManager(this));
        listado.setAdapter(adapter);

        vm = new ViewModelProvider(this).get(BookSearchViewModel.class);
        vm.init();
        data = vm.getVolumesResponseLiveData();
        data.observe(this, data -> {
            adapter.setResults(data.getItems());
        });

        buscar.setOnClickListener((v) -> {
            startIndex = 0;
            vm.searchVolumes(busqueda.getText().toString(), autor.getText().toString(), Integer.toString(startIndex));
        });
        botonMas.setOnClickListener(v -> {
            startIndex += ITEMS_PER_PAGE;
            vm.searchVolumes(busqueda.getText().toString(), autor.getText().toString(), Integer.toString(startIndex));
        });
        adapter.setClickListener(new BookSearchResultsAdapter.ItemClickListener() {
            @Override
            public void onClick(View v, Volume volume) {

                //Toast.makeText(MainActivity.this,"Pulsado "+volume.getId(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, Descripcion.class));
            }
        });


        listado.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (linearLayoutManager != null) {
                    int visibleItemCount = linearLayoutManager.getChildCount();
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {
                        startIndex += ITEMS_PER_PAGE;
                        vm.searchVolumes(busqueda.getText().toString(), autor.getText().toString(), Integer.toString(startIndex));
                    }
                }
            }

        });



    }
}