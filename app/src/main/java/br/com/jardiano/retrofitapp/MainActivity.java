package br.com.jardiano.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.textView);
        txt.setText("Carregando");
        //Retrofit builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://run.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // instance for interface
        MyAPICall myAPICall = retrofit.create(MyAPICall.class);
        Call<DataModel> call = myAPICall.getData();

        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {

                //Checking for the response

                if (response.code() != 200) {
                    txt.setText("Você esta sem internet!");
                } else {

                    /// get the data into textview
                    String jsony = "";
                    System.out.println("response: " + response.body().getId());

                    jsony = "ID" + response.body().getId()
                            + "\nuserID" + response.body().getUserId()
                            + "\ntitle" + response.body().getTitle()
                            + "\ncompleted" + response.body().isStatus();

                    txt.setText(jsony);
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                System.out.println("falha");

            }
        });

    }
}