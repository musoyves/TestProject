package com.example.vco.calc;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by cedric on 13-03-15.
 */
public class CalcFragment extends Fragment implements Observer {
    private TextView tvScreen;
    private Calculator calculator;
    private View.OnClickListener numberClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button)v;
            calculator.setInput(button.getText().toString());
        }
    };
    private View.OnClickListener operatorClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Button button = (Button)v;
            calculator.setOperator(button.getText().toString());
        }
    };

    public CalcFragment(){
        calculator = new Calculator();
        calculator.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object data) {
        tvScreen.setText((String) data);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_calc, container,false);
        /*BUTTONS*/
        final List<Button> numbers = new ArrayList<>();
        numbers.add((Button) root.findViewById(R.id.btn0));
        numbers.add((Button) root.findViewById(R.id.btn1));
        numbers.add((Button) root.findViewById(R.id.btn2));
        numbers.add((Button) root.findViewById(R.id.btn3));
        numbers.add((Button) root.findViewById(R.id.btn4));
        numbers.add((Button) root.findViewById(R.id.btn5));
        numbers.add((Button) root.findViewById(R.id.btn6));
        numbers.add((Button) root.findViewById(R.id.btn7));
        numbers.add((Button) root.findViewById(R.id.btn8));
        numbers.add((Button) root.findViewById(R.id.btn9));
        numbers.add((Button) root.findViewById(R.id.btnDot));

        /*OPERATORS*/
        final List<Button> operators = new ArrayList<>();
        operators.add((Button) root.findViewById(R.id.btnAdd));
        operators.add((Button) root.findViewById(R.id.btnSub));
        operators.add((Button) root.findViewById(R.id.btnMul));
        operators.add((Button) root.findViewById(R.id.btnDiv));

        /*OTHERS*/
        final Button btnClear = (Button) root.findViewById(R.id.btnClear);
        final Button btnEq = (Button) root.findViewById(R.id.btnEq);
        tvScreen = (TextView)root.findViewById(R.id.tvScreen);

        for (Button b:numbers){
            b.setOnClickListener(numberClickListener);
        }

        for (Button b:operators){
            b.setOnClickListener(operatorClickListener);
        }

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.undo();
            }
        });
        btnClear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                calculator.reset();
                Toast.makeText(CalcFragment.this.getActivity().getApplicationContext(), "Reset", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        btnClear.setLongClickable(true);

        btnEq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.getResult();
            }
        });

        /*END*/
        return root;
    }
}
