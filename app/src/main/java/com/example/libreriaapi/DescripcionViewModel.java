package com.example.libreriaapi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.libreriaapi.Api.BookRepository;
import com.example.libreriaapi.Data.Volume;
import com.example.libreriaapi.Data.VolumesResponse;

public class DescripcionViewModel extends AndroidViewModel {

    private BookRepository bookRepository;
    private LiveData<Volume> volumesResponseLiveData2;

    public DescripcionViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        bookRepository = new BookRepository();
        volumesResponseLiveData2 = bookRepository.getVolumesResponseLiveData2();
    }

    //para buscar
    public void buscarPorId(String id) {
        bookRepository.buscarPorId(id);
    }

    //obtener informacion
    public LiveData<Volume> getVolumesResponseLiveData2() {
        return volumesResponseLiveData2;
    }
}
