package com.example.libreriaapi.Api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.libreriaapi.Data.Volume;
import com.example.libreriaapi.Data.VolumesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookRepository {
    private static final String BOOK_SEARCH_SERVICE_BASE_URL = "https://www.googleapis.com/";

    private BookSearchService bookSearchService;
    private MutableLiveData<VolumesResponse> volumesResponseLiveData;

    private MutableLiveData<Volume> volumesResponseLiveData2;

    public BookRepository() {
        volumesResponseLiveData = new MutableLiveData<>();
        volumesResponseLiveData2 = new MutableLiveData<>();
        bookSearchService = new retrofit2.Retrofit.Builder()
                .baseUrl(BOOK_SEARCH_SERVICE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BookSearchService.class);
    }


    public void searchVolumes(String keyword, String author, String startIndex) {
        bookSearchService.searchVolumes(keyword, author,startIndex)
                .enqueue(new Callback<VolumesResponse>() {
                    @Override
                    public void onResponse(Call<VolumesResponse> call, Response<VolumesResponse> response) {
                        if (response.body() != null) {
                            volumesResponseLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<VolumesResponse> call, Throwable t) {
                        volumesResponseLiveData.postValue(null);
                    }
                });
    }
    public void buscarPorId(String id){
        bookSearchService.buscarPorId(id)
                .enqueue(new Callback<Volume>() {
                    @Override
                    public void onResponse(Call<Volume> call, Response<Volume> response) {
                        if (response.body() != null) {
                            volumesResponseLiveData2.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Volume> call, Throwable t) {
                        volumesResponseLiveData2.postValue(null);
                    }
                });
    }
    public LiveData<VolumesResponse> getVolumesResponseLiveData() {
        return volumesResponseLiveData;
    }

    public LiveData<Volume> getVolumesResponseLiveData2() {
        return volumesResponseLiveData2;
    }
}