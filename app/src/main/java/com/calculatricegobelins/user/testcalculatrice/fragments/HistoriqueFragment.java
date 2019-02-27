package com.calculatricegobelins.user.testcalculatrice.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.calculatricegobelins.user.testcalculatrice.R;

/**
 * Classe HistoriqueFragment
 * @author mickaeldebalme
 * @author robinsimonklein
 * @author amelielaurent
 * Fragment destiné à l'historique.
 */
public class HistoriqueFragment extends Fragment {

    /**
     * A la création du fragment.
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return View
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.historique_fragment, container, false);

        return view;
    }
}
