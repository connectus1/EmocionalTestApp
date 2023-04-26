package com.example.testapp.ui.home.Recycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.R;
import com.example.testapp.TestPager.TestActivity;

import java.util.ArrayList;
import java.util.List;

public class RvTest extends RecyclerView.Adapter<RvTest.TestHolder>{
    private List<ItemTest> list;
    private Context context;

    public RvTest(ArrayList<ItemTest> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TestHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test_target,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TestHolder holder, int position) {
        holder.txtTitulo.setText(list.get(position).getTitulo());
        holder.cardTest.setOnClickListener(clickCard);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //Evento click para abrir el activity Test
    private View.OnClickListener clickCard = view ->  {
        Intent i = new Intent(this.context, TestActivity.class);
        this.context.startActivity(i);
    };

    protected static class TestHolder extends RecyclerView.ViewHolder{
        private ImageView imgTest;
        private TextView txtTitulo;
        private CardView cardTest;

        public TestHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTituloCard);
            imgTest = itemView.findViewById(R.id.imgTestCard);
            cardTest = itemView.findViewById(R.id.cardTest);
        }
    }
}
