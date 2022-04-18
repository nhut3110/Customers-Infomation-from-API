package com.midterm.vominhnhut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.midterm.vominhnhut.API.dataApi;
import com.midterm.vominhnhut.DB.AppDatabase;
import com.midterm.vominhnhut.DB.DataDao;
import com.midterm.vominhnhut.adapter.dataAdapter;
import com.midterm.vominhnhut.model.DataApi;
import com.midterm.vominhnhut.model.DataDB;

import java.util.ArrayList;
import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public RecyclerView rvData;

    private List<DataDB> dataApiList;
    public dataAdapter dataAdapter;

    private SearchView searchView;

    private AppDatabase appDatabase;
    private DataDao dataDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvData = findViewById(R.id.rv_data);

        rvData.setLayoutManager(new LinearLayoutManager(this));

        dataApiList = new ArrayList<DataDB>();
        dataAdapter = new dataAdapter(dataApiList, MainActivity.this);
        rvData.setAdapter(dataAdapter);


        appDatabase = AppDatabase.getInstance(this);
        dataDao = appDatabase.dataDao();

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvData.addItemDecoration(itemDecoration);

        dataApiList = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://staff.vnuk.edu.vn:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        dataApi dogApi = retrofit.create(dataApi.class);
        Call<List<DataApi>> call = dogApi.getData();
        call.enqueue(new Callback<List<DataApi>>() {
            Boolean like = false;
            @Override
            public void onResponse(Call<List<DataApi>> call, Response<List<DataApi>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<DataApi> dogList = response.body();
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        for (DataApi dataApi: dogList){
                            DataDB data = new DataDB(dataApi.getTitle(), dataApi.getDesc(), dataApi.getTimeStamp(), dataApi.getLat(), dataApi.getLng(), dataApi.getAddr(), dataApi.getE(), dataApi.getZip());
//                            myViewModel.addData(data);
                            dataDao.insertAll(data);

                        }
                        dataApiList = dataDao.getAllData();
                    }
                });
            }
            @Override
            public void onFailure(Call<List<DataApi>> call, Throwable t) {
                Log.d("ERROR", "onError: "+t.getMessage());
            }
        });
        dataApiList = dataDao.getAllData();
        dataAdapter.setData(dataApiList);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("position"));
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            Integer position = Integer.parseInt(intent.getStringExtra("pos"));
//            Toast.makeText(MainActivity.this,Integer.toString(position) ,Toast.LENGTH_SHORT).show();
            dataDao.delete(dataApiList.get(position));
            dataApiList = dataDao.getAllData();
            dataAdapter.setData(dataApiList);
        }
    };

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.search_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dataAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                dataAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }
}