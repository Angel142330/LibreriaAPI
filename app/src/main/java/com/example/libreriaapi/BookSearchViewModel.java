package com.example.libreriaapi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.libreriaapi.Api.BookRepository;
import com.example.libreriaapi.Data.Volume;
import com.example.libreriaapi.Data.VolumesResponse;

import java.util.List;

public class BookSearchViewModel extends AndroidViewModel {
    private BookRepository bookRepository;
    private LiveData<VolumesResponse> volumesResponseLiveData;

    public BookSearchViewModel(@NonNull Application application) {
        super(application);
    }

    //crea el objeto repositorio y obtener un puntero para los datos en vivo
    public void init() {
        bookRepository = new BookRepository();
        volumesResponseLiveData = bookRepository.getVolumesResponseLiveData();
    }

    //para buscar
    public void searchVolumes(String keyword, String author, String startIndex) {
        bookRepository.searchVolumes(keyword, author,startIndex);
}

    //obtener informacion
    public LiveData<VolumesResponse> getVolumesResponseLiveData() {
        return volumesResponseLiveData;
    }

}