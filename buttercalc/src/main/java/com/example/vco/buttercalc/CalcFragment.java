package com.example.vco.buttercalc;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.javamex.classmexer.MemoryUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by cedric on 13-03-15.
 */
public class CalcFragment extends Fragment implements Observer {
    @OnClick({R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDot})
    public void numberClick(View v){
        Button button = (Button)v;
        calculator.setInput(button.getText().toString());
    }
    @OnClick({R.id.btnAdd, R.id.btnSub, R.id.btnMul, R.id.btnDiv})
    public void opeClick(View v){
        Button button = (Button)v;
        calculator.setOperator(button.getText().toString());
    }

    @InjectView(R.id.tvScreen)
    TextView tvScreen;

    @OnClick(R.id.btnClear)
    public void clear(){
        calculator.undo();
    }

    @OnLongClick(R.id.btnClear)
    public boolean reset(){
        calculator.reset();
        Toast.makeText(getActivity().getApplicationContext(), "RESEt", Toast.LENGTH_SHORT).show();
        return true;
    }

    @OnClick(R.id.btnEq)
    public void equals(){
        calculator.getResult();
    }

    private Calculator calculator;

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
        ButterKnife.inject(this, root);
        return root;
    }
}
