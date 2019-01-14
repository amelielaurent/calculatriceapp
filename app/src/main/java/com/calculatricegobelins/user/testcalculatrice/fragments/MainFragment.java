package com.calculatricegobelins.user.testcalculatrice.fragments;

import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.calculatricegobelins.user.testcalculatrice.R;
import com.calculatricegobelins.user.testcalculatrice.models.OperationType;

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);


        return view;
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
