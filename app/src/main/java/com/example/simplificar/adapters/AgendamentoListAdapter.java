package com.example.simplificar.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.simplificar.R;
import com.example.simplificar.models.Agendamento;
import com.example.simplificar.models.Veiculo;

import java.util.ArrayList;

/**
 * Created by Oséias Pereira on 27/12/2020.
 */

public class AgendamentoListAdapter extends BaseAdapter {
    Activity act;
    Context c;
    ArrayList<Agendamento> agendamentos;
    boolean isLongeClick;

    public AgendamentoListAdapter(Activity act, Context c, ArrayList<Agendamento> agendamentos) {
        this.act = act;
        this.c = c;
        this.agendamentos = agendamentos;
        isLongeClick = false;
    }

    @Override
    public int getCount() {
        return agendamentos.size();
    }

    @Override
    public Object getItem(int i) {
        return agendamentos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(c).inflate(R.layout.agendamento_model, viewGroup, false);
        }

        final Agendamento agendamento = (Agendamento) this.getItem(i);
        final View finalView = view;

        //initViews
        TextView tvServico = (TextView) view.findViewById(R.id.tvServico);
        TextView tvVeiculo = (TextView) view.findViewById(R.id.tvVeiculo);
        TextView tvData = (TextView) view.findViewById(R.id.tvData);

        //setando views
        tvServico.setText(agendamento.getTipo());
        tvVeiculo.setText(agendamento.getVeiculo());
        tvData.setText(agendamento.getData());


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLongeClick) {
                    isLongeClick = false;
                } else {
                    Toast.makeText(c, agendamento.getVeiculo(), Toast.LENGTH_SHORT).show();
                    YoYo.with(Techniques.Landing)
                            .duration(400)
                            .repeat(0)
                            .playOn(finalView);
                }
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                isLongeClick = true;
                YoYo.with(Techniques.Wave)
                        .duration(700)
                        .repeat(0)
                        .playOn(finalView);

                Toast.makeText(c, "Você precionou um serviço de " + agendamento.getTipo(), Toast.LENGTH_SHORT).show();

//                Intent it = new Intent(act, GroupCreator.class);
//                Bundle b = new Bundle();
//                b.putInt("id", veiculo.get_id());
//                it.putExtra("id", b);
//                act.startActivity(it);
//                act.finish();
                return false;
            }
        });

        return view;
    }

}
