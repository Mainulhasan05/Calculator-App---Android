package com.example.calculator;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0;
    Button btnMultiply,btnEqual,btnDot,btnPlus,btnMinus,btnDivide;
    TextView btnAc;
//
//    Stack<String> valueStack=new Stack<>();
//    Stack<String> operatorStack=new Stack<>();

    TextView inputText,outputText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TextViews initialization
        inputText=findViewById(R.id.inputTextView);
        outputText=findViewById(R.id.outputTextView);
//        digits initialization
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);
        btn7=findViewById(R.id.btn7);
        btn8=findViewById(R.id.btn8);
        btn9=findViewById(R.id.btn9);
        btn0=findViewById(R.id.btnZero);

//        operator buttons initialization
        btnPlus=findViewById(R.id.btnPlus);
        btnMinus=findViewById(R.id.btnMinus);
        btnDivide=findViewById(R.id.btnDivide);
        btnMultiply=findViewById(R.id.btnMultiply);
        btnDot=findViewById(R.id.btnDot);
        btnEqual=findViewById(R.id.btnEqual);
        btnAc=findViewById(R.id.btnAc);

        btnAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText.setText("");
                outputText.setText("");
            }
        });
    }
    //    checks if last digit is a operator]
    public boolean checkOperator(char ch){
        if(ch=='+' || ch=='-' || ch=='x' || ch=='÷'){
            return  true;
        }
        else{
            return false;
        }
    }
    public void DeleteBtn(View view){

        String text1="";
        text1=inputText.getText().toString();
        if(!text1.equals("")){
            String text2=text1.substring(0,text1.length()-1);
            inputText.setText(text2);
        }

    }
    public void calculate(View view){
        String text1="";
        text1=inputText.getText().toString();
        if(!text1.equals("")){
            if(!checkOperator(text1.charAt(text1.length()-1))){

                Stack<String> st=new Stack<>();
                Stack<String> valueStack=new Stack<>();
                int len=text1.length();
                String temp="";
                char[] text=text1.toCharArray();
                for (int i = 0; i < len; i++) {

                    if(text[i]>='0' && text[i]<='9' || text[i]=='.'){
                        temp=temp+text[i];
                    }
                    else if(text[i]=='+' ||text[i]=='-' ||text[i]=='x' ||text[i]=='÷'){
                        valueStack.push(temp);
                        temp="";

                        if(st.empty()){
                            st.push(String.valueOf(text[i]));
                        }
                        else if((text[i]=='x' ||text[i]=='÷') && (st.peek().equals("+") || st.peek().equals("-"))){
                            st.push(String.valueOf(text[i]));
                        }
                        else if((text[i]=='x' ||text[i]=='÷') && (st.peek().equals("x") || st.peek().equals("÷"))){
                            double v2=Double.parseDouble(valueStack.pop());
                            double v1=Double.parseDouble(valueStack.pop());
                            char op=st.pop().charAt(0);
                            if(op=='x'){
                                valueStack.push(String.valueOf(v1*v2));
                            }
                            else if(op=='÷'){
                                valueStack.push(String.valueOf(v1/v2));
                            }
                            st.push(String.valueOf(text[i]));
                        }
                        else if((text[i]=='+' ||text[i]=='-') && (!st.empty())){
                            double v2=Double.parseDouble(valueStack.pop());
                            double v1=Double.parseDouble(valueStack.pop());
                            char op=st.pop().charAt(0);
                            if(op=='+'){
                                valueStack.push(String.valueOf(v1+v2));
                            }
                            else if(op=='-'){
                                valueStack.push(String.valueOf(v1-v2));
                            }
                            else if(op=='x'){
                                valueStack.push(String.valueOf(v1*v2));
                            }
                            else if(op=='÷'){
                                valueStack.push(String.valueOf(v1/v2));
                            }
                            st.push(String.valueOf(text[i]));

                        }

                    }


                }
                valueStack.push(temp);

                while(!st.empty()){
                    double v2=Double.parseDouble(valueStack.pop());
                    double v1=Double.parseDouble(valueStack.pop());
                    char op=st.pop().charAt(0);
                    if(op=='+'){
                        valueStack.push(String.valueOf(v1+v2));
                    }
                    else if(op=='-'){
                        valueStack.push(String.valueOf(v1-v2));
                    }
                    else if(op=='x'){
                        valueStack.push(String.valueOf(v1*v2));
                    }
                    else if(op=='÷'){
                        valueStack.push(String.valueOf(v1/v2));
                    }
                }


                double ans1=Double.parseDouble(valueStack.get(0));
                //int ans2=Integer.parseInt(valueStack.get(0));
                long ans=(long)ans1;
                if(ans1==ans){
                    outputText.setText(String.valueOf(ans));
                }
                else{
                    outputText.setText(valueStack.get(0));
                }


            }
            else{
                Toast.makeText(this, "Invalid format", Toast.LENGTH_SHORT).show();
            }

        }

    }
    public void MyBtnClicked(View view){
        String text=inputText.getText().toString();
//        calculate(text);
        if(view.getId()==R.id.btnZero){
            if(!text.equals("")){
                text=text+"0";
                inputText.setText(text);
            }

        }
        else if(view.getId()==R.id.btn1) {
            text=text+"1";
            inputText.setText(text);
        }
        else if(view.getId()==R.id.btn2) {
            text=text+"2";
            inputText.setText(text);
        }
        else if(view.getId()==R.id.btn3) {
            text=text+"3";
            inputText.setText(text);
        }
        else if(view.getId()==R.id.btn4) {
            text=text+"4";
            inputText.setText(text);
        }
        else if(view.getId()==R.id.btn5) {
            text=text+"5";
            inputText.setText(text);
        }
        else if(view.getId()==R.id.btn6) {
            text=text+"6";
            inputText.setText(text);
        }
        else if(view.getId()==R.id.btn7) {
            text=text+"7";
            inputText.setText(text);
        }
        else if(view.getId()==R.id.btn8) {
            text=text+"8";
            inputText.setText(text);
        }
        else if(view.getId()==R.id.btn9) {
            text=text+"9";
            inputText.setText(text);
        }
        else if(view.getId()==R.id.btnDot) {
            text=text+".";
            inputText.setText(text);
        }
    }
    //
    public void OperatorClicked(View view){
        String text=inputText.getText().toString();
        int length=text.length();
        char text1[]=text.toCharArray();
        if(length>0){
            //      plus clicked
            if(view.getId()==R.id.btnPlus){
                if(checkOperator(text.charAt(length-1))){
                    text1[length-1]='+';
                    inputText.setText(String.valueOf(text1));
                }
                else{
                    text=text+"+";
                    inputText.setText(text);
                }

            }
            else if(view.getId()==R.id.btnMinus){
                if(checkOperator(text.charAt(length-1))){
                    text1[length-1]='-';
                    inputText.setText(String.valueOf(text1));
                }
                else{
                    text=text+"-";
                    inputText.setText(text);
                }
            }
            else if(view.getId()==R.id.btnDivide){
                if(checkOperator(text.charAt(length-1))){
                    text1[length-1]='÷';
                    inputText.setText(String.valueOf(text1));
                }
                else{
                    text=text+"÷";
                    inputText.setText(text);
                }
            }
            else if(view.getId()==R.id.btnMultiply){
                if(checkOperator(text.charAt(length-1))){
                    text1[length-1]='x';
                    inputText.setText(String.valueOf(text1));
                }
                else{
                    text=text+"x";
                    inputText.setText(text);
                }
            }

        }

    }
}
