package com.kxwl.kxpro;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kxwl.kxpro.entity.Extractor;
import com.kxwl.kxpro.entity.Goods;
import com.kxwl.kxpro.retrofit.GoodsInfoService;
import com.kxwl.kxpro.retrofit.GoodsService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private EditText barCodeET;
    private EditText nameET;
    private EditText classificationET;
    private EditText sepificationET;
    private Button loadBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initViews();
    }

    private void findViews() {
        barCodeET = (EditText) findViewById(R.id.et_barcode);
        nameET = (EditText) findViewById(R.id.et_name);
        classificationET = (EditText) findViewById(R.id.et_classification);
        sepificationET = (EditText) findViewById(R.id.et_sepification);
        loadBTN = (Button) findViewById(R.id.loadBTN);
    }

    private void initViews(){
        loadBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit=new Retrofit.Builder().baseUrl("http://search.anccnet.com/").build();
                GoodsService service=retrofit.create(GoodsService.class);
                Call<ResponseBody> call=service.getGoodsBaseId(barCodeET.getText().toString());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String rs=response.body().string();
                            extractBaseId(rs);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });
    }

    public void extractBaseId(String str){
        Document doc=Jsoup.parse(str);
        Elements as = doc.getElementsByTag("a");
        String infoUrl=as.get(3).attr("href");
        Log.e("infoUrl",infoUrl);
        String baseId=infoUrl.split("=")[1];
        loadGoodsInfo(baseId);
    }



    public void loadGoodsInfo(String baseId){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(" http://www.anccnet.com/").build();
        GoodsInfoService service=retrofit.create(GoodsInfoService.class);
        Call<ResponseBody> call=service.getGoodsDetailInfo(baseId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String rs=response.body().string();
                    Goods goods=Extractor.extractFromStr(rs);
                    Log.e("商品描述",goods.toString());
                    showGoodsInfo(goods);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void showGoodsInfo(Goods goods){
        nameET.setText(goods.getBrand()+goods.getName());
        sepificationET.setText(goods.getSpecification());
        classificationET.setText(goods.getClassification());
    }
}
