package com.example.recycleviewjava;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecucleView;
    MyListAdapter myListAdapter;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    SwipeRefreshLayout swip;
    boolean reflesh = false;
    Timer timer = new Timer();
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addList();

        mRecucleView = findViewById(R.id.recycleview);
        mRecucleView.setLayoutManager(new LinearLayoutManager(this));
        mRecucleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        myListAdapter = new MyListAdapter();
        mRecucleView.setAdapter(myListAdapter);

        swip = findViewById(R.id.swipRefresh);

        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                addOne();
                // 0.5 秒後刷新頁面
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        close();
                        reFresh();
                    }
                }, 500);
            }
        });
        myListAdapter.getArray(arrayList);
    }

    private void addList() {
        for (int i = 0; i < 2; i++) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", "座號：" + String.format("%02d", i + 1));
            hashMap.put("sub1", String.valueOf(new Random().nextInt(80) + 20));
            hashMap.put("sub2", String.valueOf(new Random().nextInt(80) + 20));
            hashMap.put("Avg", String.valueOf((Integer.parseInt(hashMap.get("sub1")) + Integer.parseInt(hashMap.get("sub2"))) / 2));
//            Log.d("Ray","value"+hashMap);
            arrayList.add(hashMap);
        }
    }

    private void addOne() {
        reflesh = true;
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", "座號：" + String.format("%02d", 1));
        hashMap.put("sub1", String.valueOf(new Random().nextInt(80) + 20));
        hashMap.put("sub2", String.valueOf(new Random().nextInt(80) + 20));
        hashMap.put("Avg", String.valueOf((Integer.parseInt(hashMap.get("sub1")) + Integer.parseInt(hashMap.get("sub2"))) / 2));
//            Log.d("Ray","value"+hashMap);
        arrayList.add(hashMap);

    }

    private void reFresh() {
        myListAdapter.notifyDataSetChanged();
    }

    private void close() {
        if (reflesh) {
            reflesh = false;
            swip.setRefreshing(false);
        }
    }
}