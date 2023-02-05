package com.example.libreriaapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.widget.TextView;
import com.example.libreriaapi.Data.Volume;

public class Descripcion extends AppCompatActivity {

    TextView titulo, autor, fecha,paginas, categorias;
    DescripcionViewModel vm;
    LiveData<Volume> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);

        titulo = findViewById(R.id.idTitulo);
        autor = findViewById(R.id.idAutor2);
        fecha = findViewById(R.id.idFecha);
        paginas = findViewById(R.id.idPaginas);
        categorias = findViewById(R.id.idCategorias);

        vm = new ViewModelProvider(this).get(DescripcionViewModel.class);
        vm.init();
        data = vm.getVolumesResponseLiveData2();
        data.observe(this, data -> {
            if (data != null) {
                titulo.setText(data.getVolumeInfo().getTitle());
                anadir();
                fecha.setText(data.getVolumeInfo().getPublishedDate());
                paginas.setText(data.getVolumeInfo().getPageCount());
                categorias();
            }
        });

       String id = String.valueOf(getIntent().getSerializableExtra("datos"));
     //   Toast.makeText(Descripcion.this,"ID: "+ id, Toast.LENGTH_SHORT).show();

        vm.buscarPorId(id);

    }

    private void categorias() {
        Volume volume = data.getValue();
        if (volume.getVolumeInfo().getCategories() != null) {
            String categoria = "";
            for (String a :volume.getVolumeInfo().getCategories() ){
                categoria += a + ", ";
            }
            categorias.setText(categoria);
        }
    }

    private void anadir() {
        Volume volume = data.getValue();
        if (volume.getVolumeInfo().getAuthors() != null) {
            String autores = "";
            for (String a :volume.getVolumeInfo().getAuthors() ){
                autores += a + ", ";
            }
            autor.setText(autores);
        }
    }
}