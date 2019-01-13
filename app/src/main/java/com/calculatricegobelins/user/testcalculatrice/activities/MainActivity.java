package com.calculatricegobelins.user.testcalculatrice.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.calculatricegobelins.user.testcalculatrice.R;
import com.calculatricegobelins.user.testcalculatrice.models.Operation;
import com.calculatricegobelins.user.testcalculatrice.models.OperationType;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    List<Operation> historique = new ArrayList<>();
    String calcul = "";
    OperationType operationType = OperationType.UNKOWN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView result = findViewById(R.id.tv_result);
        result.setText("0");
    }


    /**
     * Est appelées lorsque l'on clique sur un bouton
     *
     * @param v La vue à l'origine de l'événement
     */
    @Override
    public void onClick(View v) {

        if (!(v instanceof Button)) {
            return;
        }

        String tag = v.getTag().toString();
        Button button = (Button) v;
        TextView result = findViewById(R.id.tv_result);
        int bt_id = button.getId();


        if (result.getText().toString().equals("0")) {

            if(bt_id != R.id.bt_point){
                result.setText("");
            }
        }

        if (v.getTag() == null) {
            return;
        }

        switch (tag) {
            case "AC": // Effacement

                result.setText("0");
                this.handleOperation(button);
                break;

            case "OP": // Opération
                operationType = this.handleOperation(button);
                result.append(operationType.toString());
                break;

            case "EQ": // Egal

                String[] parts = result.getText().toString().split(getSplitter());

                if(parts.length <2) {
                    System.out.println("fuck you");
                    return;
                }else {

                    double Number1 = Double.parseDouble(parts[0]);
                    double Number2 = Double.parseDouble(parts[1]);

                    Operation theOperation = new Operation(Number1, Number2, operationType);
                    String resultFinal = String.format(theOperation.getResult().toString());
                    historique.add(theOperation);
                    result.setText(resultFinal);
                }


                break;


            case "NB": // Chiffre
                result.append(button.getText());
                break;
            default:
                break;
        }


    }


    /**
     * Permet de récupérer le type de l'opération actuelle et effectue l'opération
     *
     * @param btn Le bouton qui a été cliqué
     * @return String
     */
    private OperationType handleOperation(Button btn) {
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

        }

        return OperationType.UNKOWN;
    }

    /**
     * Gère les cas problématiques de regex avec les opérateurs
     *
     * @return
     */
    private String getSplitter() {
        switch (operationType) {
            case ADDTION:
                return "\\+";
            case DIVISION:
                return "\\/";
            case MUTIPLICATION:
                return "\\*";
            default:
                return operationType.toString();
        }
    }

}


