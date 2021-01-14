package com.example.simplificar.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.simplificar.R;
import com.example.simplificar.adapters.VeiculoGridAdapter;
import com.example.simplificar.models.User;
import com.example.simplificar.models.Veiculo;
import com.example.simplificar.util.FirebaseConnection;
import com.example.simplificar.util.LibraryClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private FirebaseDatabase firebaseBD;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    private LinearLayout llMenu2;
    private TextView tvVerTodas;
    private ImageView acao1, acao2, acao3, acao4;
    private ImageView acao5, acao6, acao7, acao8;
    private GridView gvVeiculos;
    private ArrayList<Veiculo> veiculos;
    private VeiculoGridAdapter gridAdapter;
    private TextView tvInfoVeiculos;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        initFirebase();
        initViews(root);
        setListeners();

        return root;
    }

    private void initFirebase() {
        firebaseBD = LibraryClass.getFirebaseDB();
        mAuth = FirebaseConnection.getFirebaseAuth();
        user = mAuth.getCurrentUser();
    }

    private void addVehicleInFirebase(Veiculo veiculo){
        //Salvando no Firebase
        DatabaseReference bdRef = LibraryClass.getFirebaseDB().getReference();
        bdRef.child("vehicles").child(user.getUid()).setValue(veiculo);
    }

    private void initViews(View root) {
        tvVerTodas = root.findViewById(R.id.tvVerTodas);
        llMenu2 = root.findViewById(R.id.llMenu2);

        acao1 = root.findViewById(R.id.imgAcao1);
        acao2 = root.findViewById(R.id.imgAcao2);
        acao3 = root.findViewById(R.id.imgAcao3);
        acao4 = root.findViewById(R.id.imgAcao4);
        acao5 = root.findViewById(R.id.imgAcao5);
        acao6 = root.findViewById(R.id.imgAcao6);
        acao7 = root.findViewById(R.id.imgAcao7);
        acao8 = root.findViewById(R.id.imgAcao8);

        gvVeiculos = root.findViewById(R.id.gvVeiculos);
        tvInfoVeiculos = root.findViewById(R.id.tvInfoVeiculos);
    }

    private void setListeners() {

        //animate show/hide menu2
        tvVerTodas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(llMenu2.getVisibility()==View.GONE){
                    llMenu2.setVisibility(View.VISIBLE);
                    tvVerTodas.setText("VER MENOS");

                    //slideDown animate
                    YoYo.with(Techniques.FadeInDown)
                            .duration(300)
                            .repeat(0)
                            .playOn(llMenu2);
                } else{
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            llMenu2.setVisibility(View.GONE);
                        }
                    }, 300);
                    tvVerTodas.setText("VER TODAS");

                    //slideUp animate
                    YoYo.with(Techniques.FadeOutUp)
                            .duration(300)
                            .repeat(0)
                            .playOn(llMenu2);
                }
            }
        });

        //seting Listeners Action
        acao1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateClick(acao1);
            }
        });
        acao2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateClick(acao2);
            }
        });
        acao3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateClick(acao3);
            }
        });
        acao4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateClick(acao4);
            }
        });
        acao5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateClick(acao5);
            }
        });
        acao6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateClick(acao6);
            }
        });
        acao7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateClick(acao7);
            }
        });
        acao8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateClick(acao8);
            }
        });

        veiculos = new ArrayList<>();
        //getList in DB
        veiculos.add(new Veiculo("Carro", "Honda", "Civic", "Preto", 2014));
        veiculos.add(new Veiculo("Moto", "Yamaha", "Crosser", "Azul", 2017));
        veiculos.add(new Veiculo("Carro", "Volkswagen", "Gol", "Vermelho", 2019));
        veiculos.add(new Veiculo("Carro", "Honda", "Civic", "Preto", 2014));
        veiculos.add(new Veiculo("Moto", "Yamaha", "Crosser", "Azul", 2017));
        veiculos.add(new Veiculo("Carro", "Volkswagen", "Gol", "Vermelho", 2019));

        addVehicleInFirebase(veiculos.get(0));

        if(veiculos.size()!=0){
            tvInfoVeiculos.setVisibility(View.GONE);
            gridAdapter = new VeiculoGridAdapter(getActivity(), getContext(), veiculos);
            gvVeiculos.setAdapter(gridAdapter);
        }
    }

    private void animateClick(View view){
        YoYo.with(Techniques.Pulse)
                .duration(300)
                .repeat(0)
                .playOn(view);
    }
}