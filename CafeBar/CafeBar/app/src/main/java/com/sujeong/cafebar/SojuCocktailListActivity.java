package com.sujeong.cafebar;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.Random;

public class SojuCocktailListActivity extends AppCompatActivity implements SensorEventListener {

    Button btn;

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private double sensor_x;
    private double prev_x = -3;
    Button choosenBtn;
    ScrollView svSojuCocktail;

    private int numOfMenu;
    private int count; //흔들림개수 카운팅

    private int choosenNum;
    private String choosenId=new String();
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soju_cocktail_list);

        svSojuCocktail= (ScrollView)findViewById(R.id.svSojuCocktail);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        count = 0;
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, InfoActivity.class);
        btn = (Button)findViewById(view.getId());
        intent.putExtra("Name", btn.getText().toString());
        intent.putExtra("Info", "sojuCocktail");

        switch(view.getId()){
            case R.id.btn1:{
                intent.putExtra("Image",R.drawable.americano);
                break;
            }
            case R.id.btn2:{
                intent.putExtra("Image",R.drawable.americano);
                break;
            }
            case R.id.btn3:{
                intent.putExtra("Image",R.drawable.americano);
                break;
            }
            case R.id.btn4:{
                intent.putExtra("Image",R.drawable.americano);
                break;
            }
            case R.id.btn5:{
                intent.putExtra("Image",R.drawable.americano);
                break;
            }
            case R.id.btn6:{
                intent.putExtra("Image",R.drawable.energizer);
                break;
            }
            case R.id.btn7:{
                intent.putExtra("Image",R.drawable.americano);
                break;
            }
            case R.id.btn8:{
                intent.putExtra("Image",R.drawable.americano);
                break;
            }
            case R.id.btn9:{
                intent.putExtra("Image",R.drawable.americano);
                break;
            }
            case R.id.btn10:{
                intent.putExtra("Image",R.drawable.americano);
                break;
            }
            case R.id.btn11:{
                intent.putExtra("Image",R.drawable.americano);
                break;
            }
            case R.id.btn12:{
                intent.putExtra("Image",R.drawable.dirty_hoe);
                break;
            }
            case R.id.btn13:{
                intent.putExtra("Image",R.drawable.americano);
                break;
            }
            case R.id.btn14:{
                intent.putExtra("Image",R.drawable.sunset);
                break;
            }
        }

        startActivityForResult(intent, 1000);
    }

    //선가속 센서에 가속도 감지되면 실행되는 함수
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //가속 수치. 흔들어본다음에 범위를 설정했다 너무 작게하면 카운팅이 심하게 올라가서 적정 범위를 설정해 준 것이다
        sensor_x = sensorEvent.values[0];

        Log.i("SENSOR", sensor_x + "");

        if ( prev_x < -2 && sensor_x > 2 ){
            count++;
            prev_x = sensor_x;
        } else if ( prev_x > 2 && sensor_x < -2 ){
            count++;
            prev_x = sensor_x;
        }

        //열번 흔들면 랜덤값가져옴.
        if ( count > 5 ) {
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
            numOfMenu= svSojuCocktail.getChildCount();
            choosenNum=random.nextInt(numOfMenu)+1;
            choosenId="btn"+choosenNum;

            int resId = getResources().getIdentifier(choosenId, "id", getPackageName());

            choosenBtn=(Button)findViewById(resId);

            onClick(choosenBtn);

            count = 0;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
