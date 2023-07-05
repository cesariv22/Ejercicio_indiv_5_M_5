package com.example.ejercicio_indiv_5_m_5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejercicio_indiv_5_m_5.databinding.ItemListBinding;

import java.util.List;

//Extender la clase Adapter Recycler
public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.WordViewHolder>{

    private final List<String> mwordlist;
    private final ItemClickListener itemClickListener;
    public AdapterRecycler (List<String>mwordlist, ItemClickListener itemClickListener){
        this.mwordlist=mwordlist;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListBinding binding = ItemListBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new WordViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        String element = mwordlist.get(position);
        holder.textView.setText(element);
    }

    @Override
    public int getItemCount() {
        return mwordlist.size();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;
        public WordViewHolder(@NonNull ItemListBinding binding) {
            super(binding.getRoot());
            textView = binding.textView;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener.onItemClick(position);
                }
            }
        }
    }
    public interface ItemClickListener {
        void onItemClick(int position);
    }
}
