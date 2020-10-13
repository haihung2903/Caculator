package com.example.caculator;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    float numberA=0, numberB=0;
    double result=0;
    String nA ="",nB="";
    String characterCheck="";
    TextView textView;
    private int[] idButton = {
            R.id.button_0,
            R.id.button_1, R.id.button_2, R.id.button_3,
            R.id.button_4, R.id.button_5, R.id.button_6,
            R.id.button_7, R.id.button_8, R.id.button_9,
            R.id.button_cham,
            R.id.button_cong, R.id.button_tru, R.id.button_nhan, R.id.button_chia,
            R.id.button_ct, R.id.button_bang,
            R.id.button_bs, R.id.button_c, R.id.button_ce
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectView();
    }
    private void connectView() {
        textView = findViewById(R.id.text_view);
        Typeface tf = Typeface.createFromAsset(getAssets(), "DS-DIGII.TTF");
        textView.setTypeface(tf);
        for (int i = 0; i < idButton.length; i++) {
            findViewById(idButton[i]).setOnClickListener(this);
        }
        init();
    }
    private void init() {
        textView.setText("0");
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (textView.getText().toString().trim().equals("0")) {
            textView.setText("");
        }
        if(id == R.id.button_0) {
            textView.append("0");
        }else if(id == R.id.button_1) {
            textView.append("1");
        }else if(id == R.id.button_2) {
            textView.append("2");
        }else if(id == R.id.button_3) {
            textView.append("3");
        }else if(id == R.id.button_4) {
            textView.append("4");
        }else if(id == R.id.button_5) {
            textView.append("5");
        }else if(id == R.id.button_6) {
            textView.append("6");
        }else if(id == R.id.button_7) {
            textView.append("7");
        }else if(id == R.id.button_8) {
            textView.append("8");
        }else if(id == R.id.button_9) {
            textView.append("9");
        }else if(id == R.id.button_cong) {
            textView.append("+");
        }else if(id == R.id.button_tru) {
            textView.append("-");
        }else if(id == R.id.button_nhan) {
            textView.append("*");
        }else if(id == R.id.button_chia) {
            textView.append("/");
        }else if(id == R.id.button_cham) {
            textView.append(".");
        }else if (id == R.id.button_c) {
            init();
            return;
        }else if(id == R.id.button_bs) {
            String backSpaceCharacters = backSpace(textView.getText().toString());
            if(backSpaceCharacters==""){
                init();
            }else {
                textView.setText(backSpaceCharacters);
            }
        }else if(id == R.id.button_ct) {
        }else if(id == R.id.button_ce) {
            init();
            return;
        }else if(id == R.id.button_bang) {
            addOperation(textView.getText().toString());
            addNumber(textView.getText().toString());
            // Thuật toán tính toán biểu thức
            if(arrOperation.size()>=arrNumber.size() ||arrOperation.size()<1){
                textView.setText("Lỗi định dạng");
            }else {
                for (int i = 0; i < (arrNumber.size() - 1); i++) {
                    switch (arrOperation.get(i)) {
                        case "+":
                            if (i == 0) {
                                result = arrNumber.get(i) + arrNumber.get(i + 1);
                            } else {
                                result = result + arrNumber.get(i + 1);
                            }
                            break;
                        case "-":
                            if (i == 0) {
                                result = arrNumber.get(i) - arrNumber.get(i + 1);
                            } else {
                                result = result - arrNumber.get(i + 1);
                            }
                            break;
                        case "*":
                            if (i == 0) {
                                result = arrNumber.get(i) * arrNumber.get(i + 1);
                            } else {
                                result = result * arrNumber.get(i + 1);
                            }
                            break;
                        case "/":
                            if (i == 0) {
                                result = arrNumber.get(i) / arrNumber.get(i + 1);
                            } else {
                                result = result / arrNumber.get(i + 1);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
            textView.setText(result +"");
        }
    }

    public String backSpace(String number){
        int length = number.length();
        String tem = number.substring(0,length-1);
        return tem;
    }
    //Mảng chứa các phép tính +, - , x, /
    public ArrayList<String> arrOperation;
    //Mảng chứa các số
    public ArrayList<Double> arrNumber;
    //Lấy tất cả các phép tính lưu vào mảng arrOperation
    public int addOperation(String input) {
        arrOperation = new ArrayList<>();
        char[] cArray = input.toCharArray();
        for (int i = 0; i < cArray.length; i++) {
            switch (cArray[i]) {
                case '+':
                case '-':
                case '*':
                case '/':
                    arrOperation.add(cArray[i] + "");
                    break;
                default:
                    break;
            }
        }
        return 0;
    }
    //Lấy tất cả các số lưu vào mảng arrNumber
    public void addNumber(String stringInput) {
        arrNumber = new ArrayList<>();
        Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher matcher = regex.matcher(stringInput);
        while(matcher.find()){
            arrNumber.add(Double.valueOf(matcher.group(1)));
        }
    }
}