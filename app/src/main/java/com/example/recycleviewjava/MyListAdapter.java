package com.example.recycleviewjava;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    ArrayList<HashMap<String, String>> arrayList;

    // 綁定 layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity, parent, false);
        return new ViewHolder(view);
    }

    // 寫入每筆資料
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int avgC = Integer.parseInt(arrayList.get(position).get("Avg"));
        Log.d("Ray","avg value ="+avgC);
        if (avgC >= 80) {
            holder.top.setBackgroundResource(R.color.red);
        } else if (avgC >= 60) {
            holder.top.setBackgroundResource(R.color.blue);
        } else if (avgC >= 40) {
            holder.top.setBackgroundResource(R.color.yellow);
        } else if (avgC >= 20) {
            holder.top.setBackgroundResource(R.color.green);
        }else {
            holder.top.setBackgroundResource(R.color.graw);
        }

        holder.tvId.setText(arrayList.get(position).get("id"));
        holder.tvSub1.setText(arrayList.get(position).get("sub1"));
        holder.tvSub2.setText(arrayList.get(position).get("sub2"));
        holder.tvAvg.setText(arrayList.get(position).get("Avg"));
        holder.mView.setOnClickListener((v)->{
//            Toast.makeText( Context , holder.tvAvg.getText(), Toast.LENGTH_SHORT).show();
            Log.d("Ray","be Onclick"+arrayList.get(position));
        });
    }

    // 告訴 adapter item 數量 亦可控制顯示的筆數
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    // 指定每個 item
    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView top, tvId, tvSub1, tvSub2, tvAvg;
        private View mView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            top = itemView.findViewById(R.id.top);
            tvId = itemView.findViewById(R.id.number);
            tvSub1 = itemView.findViewById(R.id.text1);
            tvSub2 = itemView.findViewById(R.id.text2);
            tvAvg = itemView.findViewById(R.id.text3);
            mView = itemView;
        }
    }

    // 從 Activity 傳進 List
    public void getArray(ArrayList<HashMap<String, String>> num) {
        arrayList = num;
    }
}
