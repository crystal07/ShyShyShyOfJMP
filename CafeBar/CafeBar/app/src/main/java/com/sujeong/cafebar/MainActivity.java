package com.sujeong.cafebar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAGS = "SJS";

    private PopupWindow popUp;
    Button btnClose;
    TextView tvInfo;
    private int mWidthPixels, mHeightPixels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        WindowManager w = getWindowManager();
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
        // since SDK_INT = 1;
        mWidthPixels = metrics.widthPixels;
        mHeightPixels = metrics.heightPixels;

        // 상태바와 메뉴바의 크기를 포함해서 재계산
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17)
        try {
            mWidthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
            mHeightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
        } catch (Exception ignored) {
        }

        // 상태바와 메뉴바의 크기를 포함
        if (Build.VERSION.SDK_INT >= 17)
        try {
            Point realSize = new Point();
            Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
            mWidthPixels = realSize.x;
            mHeightPixels = realSize.y;
        } catch (Exception ignored) {}
    }

    private void initiatePopupWindow(int id) {
        Log.e(TAGS, "initiatePopupWindow start");
        try {
            //  LayoutInflater 객체와 시킴
            LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup_info, (ViewGroup) findViewById(R.id.linearEntire));

            popUp = new PopupWindow(layout, mWidthPixels-100, mHeightPixels-500, true);
            popUp.showAtLocation(layout, Gravity.CENTER, 0, 0);

            tvInfo = (TextView) layout.findViewById(R.id.tvInfo);
            switch (id) {
                case R.id.btnAppInfo : {
                    tvInfo.setText("이것은 앱의 정보입니다.");
                    break;
                }
                case R.id.btnCocktailInfo : {
                    tvInfo.setText("이것은 칵테일의 정보입니다.");
                    break;
                }
                case R.id.btnCoffeeInfo : {
                    tvInfo.setText("이것은 커피의 정보입니다.");
                    break;
                }
            }


            btnClose = (Button) layout.findViewById(R.id.btnClose);
            btnClose.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    popUp.dismiss();
                }
            });
        } catch (Exception e) {
            Log.e(TAGS, "popup : "+e);
        }

        Log.e(TAGS, "initiatePopupWindow end");
    }

    public void onClick(View view){

        Intent intent;

        switch(view.getId()){
            case R.id.imgBtnCocktail:{
                Log.e(TAGS, "onClick: "+"cocktailBtn");
                intent = new Intent(this, CocktailListActivity.class);
                startActivity(intent);
                Log.e(TAGS, "onClick: "+"cocktailBtn");
                break;
            }

            case R.id.imgBtnCoffee:{
                Log.e(TAGS, "onClick: "+"coffeeBtn");
                intent = new Intent(this, CoffeeListActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.imgBtnSojuCocktail:{
                Log.e(TAGS, "onClick: "+"sojuCocktailBtn");
                intent = new Intent(this, SojuCocktailListActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.btnAppInfo : {
                Log.e(TAGS, "click btnAppInfo");
                initiatePopupWindow(R.id.btnAppInfo);
                break;
            }

            case R.id.btnCocktailInfo : {
                initiatePopupWindow(R.id.btnCocktailInfo);
                break;
            }

            case R.id.btnCoffeeInfo : {
                initiatePopupWindow(R.id.btnCoffeeInfo);
                break;
            }

        }
    }
}

