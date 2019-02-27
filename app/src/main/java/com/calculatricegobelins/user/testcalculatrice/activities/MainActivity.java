package com.calculatricegobelins.user.testcalculatrice.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.calculatricegobelins.user.testcalculatrice.R;
import com.calculatricegobelins.user.testcalculatrice.fragments.MainFragment;
import com.calculatricegobelins.user.testcalculatrice.models.Calcul;
import com.calculatricegobelins.user.testcalculatrice.models.Operation;
import com.calculatricegobelins.user.testcalculatrice.models.OperationType;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe MainActivity
 *
 * @author mickaeldebalme
 * @author robinsimonklein
 * @author amelielaurent
 * Points d'entrée et coeur de l'application
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    List<Operation> historique = new ArrayList<>();
    public boolean firstOperation;
    public double total;
    public String totalString = "";
    public String calculString = "";
    OperationType operationType = OperationType.UNKOWN;
    OperationType lastOperationType = OperationType.UNKOWN;

    /**
     * A la création de l'activité.
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        firstOperation = true;
        TextView result = findViewById(R.id.tv_result);
        showMainScreen();
    }

    /**
     * Affiche le fragment principal.
     */
    private void showMainScreen() {
        MainFragment fragment = new MainFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    /**
     * Est appelée lorsque l'on clique sur un bouton
     *
     * @param view La vue à l'origine de l'événement
     */
    @Override
    public void onClick(View view) {

        if (!(view instanceof Button)) {
            return;
        }

        String tag = view.getTag().toString();
        Button button = (Button) view;
        TextView tvResult = findViewById(R.id.tv_result);
        int buttonId = button.getId();

        if (tvResult.getText().toString().equals("0")) {
            if (buttonId != R.id.bt_point) {
                tvResult.setText("");
            }
        }

        if (view.getTag() == null) {
            return;
        }

        //Compare le tag
        switch (tag) {
            case "AC": // Effacement
                this.handleACClick(tvResult, button);
                break;

            case "AL": // Inverse
                this.handleInvertClick(tvResult);
                break;

            case "PE": // Pourcent
                this.handlePercentClick(tvResult);
                break;

            case "OP": // Opération
                this.handleOperationClick(button, tvResult);
                break;

            case "EQ": // Egal
                this.handleEqualClick(tvResult);
                break;

            case "NB": // Chiffre
                this.handleNumberClick(tvResult, button);
                break;

            default:
                break;
        }
    }

    /**
     * Gestion du click sur le bouton AC.
     * @param tvResult TextView
     * @param button Button
     */
    private void handleACClick(TextView tvResult, Button button) {
        tvResult.setText("0");
        MainFragment.handleOperation(button);
        resetCalcul();
    }

    /**
     * Gestion du click sur le bouton inverse.
     * @param tvResult TextView
     */
    private void handleInvertClick(TextView tvResult) {
        if (tvResult.getText().toString().equals("")) {
            tvResult.setText("0");
        } else {
            String[] parti = tvResult.getText().toString().split(getSplitter(operationType));
            double inverse = Double.parseDouble(parti[0]);
            inverse = inverse * (-1);
            String inversefi = String.valueOf(inverse);
            tvResult.setText(inversefi);
        }
    }

    /**
     * Gestion du click sur le bouton pourcentage.
     * @param tvResult TextView
     */
    private void handlePercentClick(TextView tvResult) {
        String[] partPe = tvResult.getText().toString().split(getSplitter(operationType));
        double percent = Double.parseDouble(partPe[0]);
        percent = percent / 100;
        String percentified = String.valueOf(percent);
        tvResult.setText(percentified);
    }

    /**
     * Gestion du click sur une opération.
     * @param button Button
     * @param tvResult TextView
     */
    private void handleOperationClick(Button button, TextView tvResult) {
        //On détecte de quel opérateur il s'agit
        operationType = MainFragment.handleOperation(button);
        System.out.println(operationType);

        String[] queryParts;
        if (lastOperationType == operationType.UNKOWN) {
            queryParts = tvResult.getText().toString().split(getSplitter(operationType));
        } else {
            queryParts = tvResult.getText().toString().split(getSplitter(lastOperationType));
        }

        if (queryParts.length < 2) {
            System.out.println("nope");
            tvResult.append(operationType.toString());
        } else {
            double number1 = Double.parseDouble(queryParts[0]);
            double number2 = Double.parseDouble(queryParts[1]);
            doOperation(number1, number2);
            displayResult(totalString);
            tvResult.append(operationType.toString());
        }

        lastOperationType = operationType;
    }

    /**
     * Gestion du click sur égal.
     * @param tvResult TextView
     */
    private void handleEqualClick(TextView tvResult) {
        String[] parts;
        if (lastOperationType == operationType.UNKOWN) {
            parts = tvResult.getText().toString().split(getSplitter(operationType));
        } else {
            parts = tvResult.getText().toString().split(getSplitter(lastOperationType));
        }

        if (parts.length < 2) {
            System.out.println("fuck you");
            return;
        } else {
            double number1 = Double.parseDouble(parts[0]);
            double number2 = Double.parseDouble(parts[1]);
            doOperation(number1, number2);
            displayResult(totalString);
            resetCalcul();
        }
    }

    /**
     * Gestion du click sur un chiffre.
     * @param tvResult TextView
     * @param button Button
     */
    private void handleNumberClick(TextView tvResult, Button button) {
        //Limiter le nombre de chiffres affichés
        if (tvResult.length() < 20) {
            tvResult.append(button.getText());
        } else {
            tvResult.append("");
        }
    }

    /**
     * Effectue une opération.
     *
     * @param number1 double
     * @param number2 double
     */
    public void doOperation(double number1, double number2) {
        if (firstOperation) {
            // Si on commence une nouvelle opération
            total = number1;
            firstOperation = false;
        }
        Operation operation;

        if (lastOperationType == operationType.UNKOWN) {
            operation = new Operation(total, number2, operationType);

        } else {
            operation = new Operation(total, number2, lastOperationType);

        }

        total = operation.getResult();
        totalString = String.format(operation.getResult().toString());
    }

    /**
     * Affiche le résultat.
     *
     * @param text String
     */
    public void displayResult(String text) {
        TextView tvResult = findViewById(R.id.tv_result);
        tvResult.setText(text);
    }

    /**
     * Réinitialise le calcul.
     */
    public void resetCalcul() {
        firstOperation = true;
        total = 0.0;
        totalString = "";
        calculString = "";
        operationType = OperationType.UNKOWN;
        lastOperationType = OperationType.UNKOWN;
    }

    /**
     * Gère les cas problématiques de regex avec les opérateurs
     *
     * @return OperationType
     */
    private String getSplitter(OperationType type) {
        switch (type) {
            case ADDTION:
                return "\\+";
            case SOUSTRACTION:
                return "\\-";
            case DIVISION:
                return "\\/";
            case MUTIPLICATION:
                return "\\*";
            default:
                return operationType.toString();
        }
    }

}


