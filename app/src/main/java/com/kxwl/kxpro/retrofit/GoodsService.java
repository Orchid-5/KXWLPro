package com.kxwl.kxpro.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by SEELE on 2017/2/19.
 */
public interface GoodsService {
    @GET("searchResult2.aspx")
    Call<ResponseBody> getGoodsBaseId(@Query("keyword") String barCode);
}
