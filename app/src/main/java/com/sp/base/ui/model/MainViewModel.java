package com.sp.base.ui.model;


import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.sp.base.network.ApiResponse;
import com.sp.base.network.Repository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static com.sp.base.ui.model.ItemDataSource.PAGE_SIZE;


public class MainViewModel extends ViewModel {
    private static final String TAG = MainViewModel.class.getSimpleName();
    public Repository repository;

    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();

    public LiveData<PagedList<ListData>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, ListData>> liveDataSource;

    @ViewModelInject
    public MainViewModel(Repository repository) {
        this.repository = repository;

    }

    public MutableLiveData<ApiResponse> getResponse() {
        return responseLiveData;
    }

//    @SuppressLint("CheckResult")
//    public void getRouteList(GetRouteListRequest getRouteListRequest) {
//        repository.executeGetRouteListAPI(getRouteListRequest)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe((d) -> responseLiveData.setValue(ApiResponse.loading()))
//                .subscribe(
//                        result -> responseLiveData.setValue(ApiResponse.success(result)),
//                        throwable -> responseLiveData.setValue(ApiResponse.error(throwable))
//                );
//
//    }

    public void getDataList(int page, int limit) {
      /*  repository.executeGetDymmyList(page, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> responseLiveData.setValue(ApiResponse.loading()))
                .subscribe(
                        result -> responseLiveData.setValue(ApiResponse.success(result)),
                        throwable -> responseLiveData.setValue(ApiResponse.error(throwable))
                );*/
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory(repository);
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();
        // Getting PagedList configuration
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(true)
                        .setPageSize(PAGE_SIZE).build();
        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig)).build();
        Log.e(TAG, "MainViewModel: ");
    }

}