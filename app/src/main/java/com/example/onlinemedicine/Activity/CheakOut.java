package com.example.onlinemedicine.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlinemedicine.Models.Model;
import com.example.onlinemedicine.R;

import java.util.ArrayList;

public class CheakOut extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Model>arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheak_out);
        recyclerView=findViewById(R.id.selected_item_list);
        layoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        arrayList=new ArrayList<>();
        for (int i=0;i<10;i++)
        {
            Model model=new Model();
            model.setName("CombiFlame");
            model.setPrice("500");
            arrayList.add(model);
            OrderAdapter medicineAdapter=new OrderAdapter(getApplicationContext(),arrayList);
            recyclerView.setAdapter(medicineAdapter);

        }
    }

    public void go_to_back(View view) {
        Intent back=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(back);
        finish();
    }

    public void go_to_pay(View view) {
        Intent back=new Intent(getApplicationContext(), PayActivity.class);
        startActivity(back);
        finish();
    }

    class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>
    {
        Context context;

        public OrderAdapter(Context context, ArrayList<Model> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
        }

        ArrayList<Model>arrayList;
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            View view=layoutInflater.inflate(R.layout.select_item_list,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
 holder.medicine_name.setText(arrayList.get(position).getName());
 holder.price.setText(arrayList.get(position).getPrice());
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView medicine_name,price;
            ImageView delete_item;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                medicine_name=itemView.findViewById(R.id.cart_prtitle);
                price=itemView.findViewById(R.id.cart_prprice);
                delete_item=itemView.findViewById(R.id.deletecard);
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent back=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(back);
        finish();
    }
}