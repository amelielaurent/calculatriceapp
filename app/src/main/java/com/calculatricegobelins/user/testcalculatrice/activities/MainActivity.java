package com.calculatricegobelins.user.testcalculatrice.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.calculatricegobelins.user.testcalculatrice.R;
import com.calculatricegobelins.user.testcalculatrice.fragments.MainFragment;
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
        setContentView(R.layout.main_activity);

        TextView result = findViewById(R.id.tv_result);
        //result.setText("0");

        showMainScreen();

    }

    private void showMainScreen(){
        MainFragment fragment = new MainFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, fragment);

        transaction.addToBackStack(null);

        transaction.commit();
    }


    /**
     * Est appelée lorsque l'on clique sur un bouton
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
                MainFragment.handleOperation(button);
                break;

            case "AL":
               /* if(result.getText().toString().equals("")){
                    result.setText("0");
                }else{
                    String[] parti = result.getText().toString().split(getSplitter());
                    double test = Double.parseDouble(parti[0]);
                    test = test * (-1);
                    String testfi = String.valueOf(test);
                    result.setText(testfi);
                }*/
                break;

            case "OP": // Opération
                operationType = MainFragment.handleOperation(button);
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


