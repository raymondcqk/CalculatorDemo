package com.example.keihongchan.calculator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 计算器Demo
 *
 * @author 大云屋 陈其康
 * @email keihong_chan@outlook.com
 * @github https://github.com/raymondcqk
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //界面控件的引用
    private TextView tvResult;
    private Button btnClear;
    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btnDiv;
    private Button btnMul;
    private Button btnDel;
    private Button btnPlus;
    private Button btnEqual;
    private Button btnDot;

    private Button btnSkin;

    private int[] backgrounds = {
            R.drawable.bg, R.drawable.bg_src_morning, R.drawable.bg_src_tianjin
    };
    private int[] color = {Color.parseColor("#FFADCA58"),
            Color.parseColor("#345269"),
            Color.parseColor("#F28D31")};

    //结果记录
    private float result;
    //当前计算功能
    private Function lastFun;

    //换肤
    private GridLayout root;
    private int skinIndex = 1;

    //枚举类型，用于记录当前被点击的 操作符
    private enum Function {
        INIT, PLUS, DEL, MUL, DIV, CLEAR, EQUAL
    }

    /**
     * Android程序入口
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化界面控件
        initView();
        //初始化计算器
        initCalculator();
    }

    /**
     * 初始化计算器
     */
    private void initCalculator() {
        lastFun = Function.INIT;
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        tvResult = (TextView) findViewById(R.id.tv_result);
        btnClear = (Button) findViewById(R.id.btn_clear);
        btn0 = (Button) findViewById(R.id.btn_0);
        btn1 = (Button) findViewById(R.id.btn_1);
        btn2 = (Button) findViewById(R.id.btn_2);
        btn3 = (Button) findViewById(R.id.btn_3);
        btn4 = (Button) findViewById(R.id.btn_4);
        btn5 = (Button) findViewById(R.id.btn_5);
        btn6 = (Button) findViewById(R.id.btn_6);
        btn7 = (Button) findViewById(R.id.btn_7);
        btn8 = (Button) findViewById(R.id.btn_8);
        btn9 = (Button) findViewById(R.id.btn_9);
        btnDiv = (Button) findViewById(R.id.btn_division);
        btnMul = (Button) findViewById(R.id.btn_multiply);
        btnDel = (Button) findViewById(R.id.btn_del);
        btnPlus = (Button) findViewById(R.id.btn_plus);
        btnEqual = (Button) findViewById(R.id.btn_equal);
        btnDot = (Button) findViewById(R.id.btn_dot);


        //给按钮控件绑定点击事件的监听者（this 代表监听者为当前类）
        btnClear.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        btnEqual.setOnClickListener(this);

        //背景布局
        root = (GridLayout) findViewById(R.id.root);

        btnSkin = (Button) findViewById(R.id.btn_change_skin);
        btnSkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skinIndex++;
                root.setBackgroundResource(backgrounds[skinIndex%backgrounds.length]);
                tvResult.setBackgroundColor(color[skinIndex%backgrounds.length]);

            }
        });
    }


    /**
     * 按键点击处理
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_0:
                cal(0);
                break;
            case R.id.btn_1:
                cal(1);
                break;
            case R.id.btn_2:
                cal(2);
                break;
            case R.id.btn_3:
                cal(3);
                break;
            case R.id.btn_4:
                cal(4);
                break;
            case R.id.btn_5:
                cal(5);
                break;
            case R.id.btn_6:
                cal(6);
                break;
            case R.id.btn_7:
                cal(7);
                break;
            case R.id.btn_8:
                cal(8);
                break;
            case R.id.btn_9:
                cal(9);
                break;
            case R.id.btn_dot:
                break;
            case R.id.btn_plus:
                lastFun = Function.PLUS;
                break;
            case R.id.btn_del:
                lastFun = Function.DEL;
                break;
            case R.id.btn_multiply:
                lastFun = Function.MUL;
                break;
            case R.id.btn_division:
                lastFun = Function.DIV;
                break;
            case R.id.btn_equal:
                lastFun = Function.EQUAL;
                break;
            case R.id.btn_clear:
                lastFun = Function.CLEAR;
                result = 0;
                //注意，下面方法中 0+""的意义
                // TextView需要传入字符串类型数据，数值类型+"" 可直接将数值类型转化为字符串类型
                tvResult.setText(0 + "");
                break;
            default:
                break;
        }
    }

    /**
     * 计算逻辑
     *
     * @param i 新的操作数
     */
    private void cal(int i) {
        switch (lastFun) {
            case PLUS:
                result += i;
                break;
            case DEL:
                result -= i;
                break;
            case MUL:
                result *= i;
                break;
            case DIV:
                //除数不能为0
                if (i == 0) {
                    //弹出提示泡泡
                    Toast.makeText(this, "没学过小学数学？", Toast.LENGTH_SHORT).show();
                    //结束
                    break;
                }
                result /= i;
                break;
            case EQUAL:
                // TODO: 2017/10/27 完善计算器功能
                break;
            case CLEAR:
                result = i;
                break;
            case INIT:
                result = i;
                break;
        }
        //计算完后，需要再次点击 功能按钮 才能继续计算，否则认为是新的计算
        lastFun = Function.INIT;
        tvResult.setText(result + "");
    }
}
