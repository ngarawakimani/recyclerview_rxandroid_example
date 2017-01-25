package com.wakimani.recyclerviewrxandroidexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String SERVER_URL = "http://192.168.43.59/android/tvshows.php";
    RecyclerView recyclerView;
    ItemsAdapter  itemsAdapter;
    private List<ItemsList> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.myrecycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Observable<List<ItemsList>> observable = Observable.create(new ObservableOnSubscribe<List<ItemsList>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ItemsList>> e) throws Exception {
                try {
                    //String data = "";
                    e.onNext(getData(SERVER_URL));
                    e.onComplete();
                } catch (Exception ex){
                    e.onError(ex);
                    Toast.makeText(getApplicationContext(),"Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ItemsList>>() {
                    @Override
                    public void accept(List<ItemsList> list) throws Exception {
                        itemsAdapter = new ItemsAdapter(getApplicationContext(),list);
                        recyclerView.setAdapter(itemsAdapter);
                    }
                });

    }

    public List<ItemsList> getData(String url) throws IOException, JSONException {

        OkHttpClient client = new OkHttpClient();
        Response response = null;
        ItemsList items;
        try {

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            response = client.newCall(request).execute();


        }catch (Exception e){

        }

        String result = response.body().string();

        JSONArray posts = new JSONArray(result);
        JSONObject object;

        listItems = new ArrayList<ItemsList>();


        for (int i = 0; i < posts.length(); i++) {
            object = posts.getJSONObject(i);

            items = new ItemsList(object.getString("image"), object.getString("title"), object.getString("description"), object.getString("station"));

            listItems.add(items);

        }

        return listItems;
    }
}
