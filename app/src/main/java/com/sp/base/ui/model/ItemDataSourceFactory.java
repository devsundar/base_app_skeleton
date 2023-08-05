package com.sp.base.ui.model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.sp.base.network.Repository;

import org.jetbrains.annotations.NotNull;

public class ItemDataSourceFactory extends DataSource.Factory {
    private static final String TAG = ItemDataSourceFactory.class.getSimpleName();
    private MutableLiveData<PageKeyedDataSource<Integer, ListData>> itemMutableLiveData = new MutableLiveData<>();
    Repository repository;

    public ItemDataSourceFactory(Repository repository) {
        this.repository = repository;
    }

    @NotNull
    @Override
    public DataSource<Integer, ListData> create() {
        Log.e(TAG, "create: ");
        //getting our data source object
        ItemDataSource articleDataSource = new ItemDataSource(repository);
        //posting the data source to get the values
        itemMutableLiveData.postValue(articleDataSource);
        return articleDataSource;
    }

    //getter for itemlivedatasource
    public MutableLiveData<PageKeyedDataSource<Integer, ListData>> getItemLiveDataSource() {
        Log.e(TAG, "getItemLiveDataSource: ");
        return itemMutableLiveData;
    }
}
