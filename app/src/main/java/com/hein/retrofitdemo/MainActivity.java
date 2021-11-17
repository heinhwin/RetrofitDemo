package com.hein.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    String url = "https://jsonplaceholder.typicode.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);
        tv.setText("");

        //  Object Creation
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //  Convert JSON data to Model Class Object
        API api = retrofit.create(API.class);

        //  Create a Call for Model Class
        //  Enqueue for Processing
      //    Receive Response Data in a Simple Model Type List
        Call<List<Model>> call = api.getModels();
        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {

                List<Model> data = response.body();

                for(int i=0; i < data.size(); i++) {
                   tv.append("ID: " + data.get(i).getId() + "\nTitle: " + data.get(i).getTitle()
                           + "\n\n\n");
                }
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {

            }
        });
    }
}