package com.sp.base.ui.model;


import android.util.Log;

import androidx.paging.PageKeyedDataSource;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.sp.base.network.Repository;

import org.jetbrains.annotations.NotNull;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

class ItemDataSource extends PageKeyedDataSource<Integer, ListData> {
    public static final int PAGE_SIZE = 50;
    public static final int FIRST_PAGE = 0;
    private static final String TAG = ItemDataSource.class.getSimpleName();
    Repository repository;

    public ItemDataSource(Repository repo) {
        repository = repo;
    }


    @Override
    public void loadAfter(@NotNull LoadParams<Integer> loadParams, @NotNull LoadCallback<Integer, ListData> loadCallback) {
        Log.e(TAG, "loadAfter: ");
        repository.executeGetDymmyList(loadParams.key, PAGE_SIZE).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<JsonElement>() {
            @Override
            public void accept(JsonElement jsonElement) throws Throwable {
//                Integer key = (loadParams.key == loadParams.body().getTotalResults()) ? null : params.key + 1;
                DummyResponse dummyResponse = new Gson().fromJson(jsonElement.toString(), DummyResponse.class);

                loadCallback.onResult(dummyResponse.getResults(), loadParams.key + 1);
                Log.e(TAG, "accept: ");
            }
        });


//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe((d) -> responseLiveData.setValue(ApiResponse.loading()))
//                .subscribe(
//                        result ->                             loadCallback.onResult(ApiResponse.success().body().getArticles(), null, ArticleMovieConstants.FIRST_PAGE + 1);
//,
//                        throwable -> responseLiveData.setValue(ApiResponse.error(throwable))
//                );
    }

    @Override
    public void loadBefore(@NotNull LoadParams<Integer> loadParams, @NotNull LoadCallback<Integer, ListData> loadCallback) {
        Log.e(TAG, "loadBefore: ");
    }

    @Override
    public void loadInitial(@NotNull LoadInitialParams<Integer> loadInitialParams, @NotNull LoadInitialCallback<Integer, ListData> loadInitialCallback) {
        Log.e(TAG, "loadInitial: ");
        repository.executeGetDymmyList(FIRST_PAGE, PAGE_SIZE).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<JsonElement>() {
            @Override
            public void accept(JsonElement apiResponse) throws Throwable {
                DummyResponse dummyResponse = new Gson().fromJson(apiResponse.toString(), DummyResponse.class);

                loadInitialCallback.onResult(dummyResponse.getResults(), null, FIRST_PAGE);
                Log.e(TAG, "accept: ");

            }
        });
    }
}
