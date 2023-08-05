package com.sp.base.network;


import com.google.gson.JsonElement;
import com.sp.base.ui.model.DummyResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.sp.base.network.NetworkingConstants.DUMMY;
import static com.sp.base.network.NetworkingConstants.GETPOCKEMON;

public interface ApiService {

//    @POST()
//    Observable<JsonElement> GetRouteList(@Url String url, @Body GetRouteListRequest param);

    @GET(GETPOCKEMON)
    Observable<JsonElement> getDataList();

    @GET(DUMMY)
    Observable<JsonElement> getDummyLIst(@Query("page") int page, @Query("limit") int lmit);//page=1&limit=10"
}