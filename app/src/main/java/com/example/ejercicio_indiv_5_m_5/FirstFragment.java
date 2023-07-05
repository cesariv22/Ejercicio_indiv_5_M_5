package com.example.ejercicio_indiv_5_m_5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ejercicio_indiv_5_m_5.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment implements AdapterRecycler.ItemClickListener{
    private final List<String> dataList = new ArrayList<>();
    private AdapterRecycler adapterRecycler;
    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapterRecycler = new AdapterRecycler(setData(), this);
        binding.Rv.setAdapter(adapterRecycler);
        binding.Rv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.Rv.setHasFixedSize(true);

        binding.floatingActionButton3.setOnClickListener(view1 -> {
            String newWord = "+ Word"+" "+dataList.size();
            dataList.add(newWord);
            adapterRecycler.notifyItemInserted(dataList.size()-1);
            binding.Rv.scrollToPosition(dataList.size()-1);
        });
    }

    //2.- Crear listado de palabras
    private List<String> setData() {
        for(int i=0; i<99; i++) {
            dataList.add("Word "+i);
        }
        return dataList;
    }
    @Override
    public void onItemClick(int position) {
        String clickedWord = dataList.get(position); // Obtener la palabra clickeada
        String updatedWord = "Clicked!"+" "+clickedWord; // Agregar " clicked" a la palabra
        dataList.set(position, updatedWord); // Actualizar la palabra en el ArrayList
        adapterRecycler.notifyItemChanged(position); // Notificar al adaptador sobre el cambio en el dato
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}