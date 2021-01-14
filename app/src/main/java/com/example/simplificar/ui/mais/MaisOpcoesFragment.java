package com.example.simplificar.ui.mais;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.simplificar.R;

public class MaisOpcoesFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mais_opcoes, container, false);
        initViews(root);

        return root;
    }

    public void initViews(View root){
        LinearLayout llOp = (LinearLayout) root.findViewById(R.id.llOptions);
        animateClick(llOp);

        for(int i=0; i < ((ViewGroup) llOp).getChildCount(); i++) {
            View viewOp = ((ViewGroup) llOp).getChildAt(i);
            viewOp.setOnClickListener(getOnClickListenerOp(i));
        }
    }

    private View.OnClickListener getOnClickListenerOp(int indexOp){
        switch (indexOp){
            case 1:
                return new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        animateClick(view);
                    }
                };

            case 2:
                return new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        animateClick(view);
                    }
                };

            default:
                return new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        animateClick(view);
                    }
                };
        }
    }

    private void animateClick(View view){
        YoYo.with(Techniques.Pulse)
                .duration(300)
                .repeat(0)
                .playOn(view);
    }

    private void slideFragment(View view){
        YoYo.with(Techniques.SlideInLeft)
                .duration(1000)
                .repeat(0)
                .playOn(view);
    }
}