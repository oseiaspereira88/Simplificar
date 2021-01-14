package com.example.simplificar.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
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
import com.example.simplificar.models.Veiculo;

import java.util.ArrayList;

/**
 * Created by Oséias Pereira on 27/12/2020.
 */

public class VeiculoGridAdapter extends BaseAdapter {
    Activity act;
    Context c;
    ArrayList<Veiculo> veiculos;
    boolean isLongeClick;

    public VeiculoGridAdapter(Activity act, Context c, ArrayList<Veiculo> veiculos) {
        this.act = act;
        this.c = c;
        this.veiculos = veiculos;
        isLongeClick = false;
    }

    @Override
    public int getCount() {
        return veiculos.size();
    }

    @Override
    public Object getItem(int i) {
        return veiculos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(c).inflate(R.layout.veiculo_model, viewGroup, false);
        }

        final Veiculo veiculo = (Veiculo) this.getItem(i);
        final View finalView = view;

        //initViews
        TextView tvTitulo = (TextView) view.findViewById(R.id.tvVeiculoTitulo);
        ImageView imgVeiculo = (ImageView) view.findViewById(R.id.imgVeiculo);

        //setando views
        tvTitulo.setText(veiculo.getMarca() +" "+ veiculo.getModelo());
        if(veiculo.getTipo()=="Moto"){
            imgVeiculo.setImageResource(R.drawable.moto_card_bg);
        }


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLongeClick) {
                    isLongeClick = false;
                } else {
                    Toast.makeText(c, veiculo.getMarca() +" "+ veiculo.getModelo(), Toast.LENGTH_SHORT).show();
                    YoYo.with(Techniques.Landing)
                            .duration(400)
                            .repeat(0)
                            .playOn(finalView);

//                    Intent it = new Intent(act, GroupArea.class);
//                    Bundle b = new Bundle();
//                    b.putInt("id", veiculo.get_id());
//                    it.putExtra("id", b);
//                    act.startActivity(it);
//                    act.finish();
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

                Toast.makeText(c, "Você precionou " + veiculo.getMarca() +" "+ veiculo.getModelo(), Toast.LENGTH_SHORT).show();

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
