package com.example.simplificar.ui.agenda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.simplificar.R;
import com.example.simplificar.adapters.AgendamentoListAdapter;
import com.example.simplificar.models.Agendamento;

import java.util.ArrayList;

public class AgendaFragment extends Fragment {
    private AgendamentoListAdapter adapter;
    private ArrayList<Agendamento> agenda;
    private ListView lvAgenda;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_agenda, container, false);
        initViews(root);
        return root;
    }

    private void initViews(View root) {
        lvAgenda = root.findViewById(R.id.lvAgenda);
        agenda = new ArrayList<>();
        agenda.add(new Agendamento("Oficina", "Honda Civic 2012", "21/09/2021", "17:00"));
        agenda.add(new Agendamento("Lavajato", "Honda Civic 2012", "09/08/2021", "17:00"));
        agenda.add(new Agendamento("Borracharia", "Yamaha Crosser 170RX", "22/08/2021", "17:00"));

        adapter = new AgendamentoListAdapter(getActivity(), getContext(), agenda);
        lvAgenda.setAdapter(adapter);
    }

    private void animateClick(View view){
        YoYo.with(Techniques.Pulse)
                .duration(300)
                .repeat(0)
                .playOn(view);
    }
}