package com.calculatricegobelins.user.testcalculatrice.fragments;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.calculatricegobelins.user.testcalculatrice.R;
import com.calculatricegobelins.user.testcalculatrice.models.OperationType;

public class MainFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        ImageView btn_historique = view.findViewById(R.id.bt_historique);

        // Ouvrir l'historique lorsqu'on clique sur le bouton
        btn_historique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHistorique();
            }
        });

        return view;
    }

    public void showHistorique(){
        HistoriqueFragment fragment = new HistoriqueFragment();

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, fragment);

        transaction.addToBackStack(null);

        transaction.commit();
    }



    /**
     * Permet de récupérer le type de l'opération actuelle et effectue l'opération
     *
     * @param btn Le bouton qui a été cliqué
     * @return String
     */
    public static OperationType handleOperation(Button btn) {
        int bt_id = btn.getId();
        switch (bt_id) {
            case R.id.bt_plus:
                return OperationType.ADDTION;

            case R.id.bt_minus:
                return OperationType.SOUSTRACTION;

            case R.id.bt_divide:
                return OperationType.DIVISION;

            case R.id.bt_multiply:
                return OperationType.MUTIPLICATION;

            case R.id.bt_percent:
                return OperationType.PERCENT;

            case R.id.bt_plusminus:
                return OperationType.INVERSE;
        }

        return OperationType.UNKOWN;
    }
}
