package com.sujeong.cafebar;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.sujeong.cafebar.database.InfoBaseHelper;

public class InfoActivity extends AppCompatActivity {

    TextView tvName, tvMethod;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tvName= (TextView)findViewById(R.id.tvName);
        tvMethod= (TextView)findViewById(R.id.tvMethod);
        iv= (ImageView)findViewById(R.id.iv);
        int imageLink=getIntent().getIntExtra("Image",1);

        Intent intent =getIntent();
        String name=intent.getStringExtra("Name");

        if (intent.getStringExtra("Info").equals("coffee")) {
            insertCoffee();
        }
        else if (intent.getStringExtra("Info").equals("cocktail")) {
            insertCocktail();
        }
        else if (intent.getStringExtra("Info").equals("sojuCocktail")) {
            insertSojuCocktail();
        }
        else;

        tvName.setText(name);
        iv.setImageResource(imageLink);
        tvMethod.setText(result(name));
    }

    public void insertCoffee() {
        InfoBaseHelper db = new InfoBaseHelper(this, "infoBase.db", null, 1);
        db.insert("아메리카노", "물8:커피2 (혹은 물150ml+커피30ml)");
        db.insert("카페라떼", "우유8:커피2 + 커피시럽 2스푼");
        db.insert("에스프레소", "커피10");
        db.insert("카라멜 마끼야또", "우유7:커피3 + 카라멜시럽30ml + 휘핑크림 (혹은 우유320ml+커피3/2온스+카라멜+휘핑)");
        db.insert("카푸치노", "우유2:커피1 + 휘핑크림,시나몬가루 소량");
        db.insert("카페모카", "우유8:커피2 + 초코시럽 2큰술, 코코아파우더 소량");
        db.insert("프라푸치노", "우유4:커피6 + 갈아넣은 얼음 + 코코아파우터, 초코시럽 2~3스푼\n바나나 프라푸치노를 원한다면 우유5:얼린커피5 + 얼린바나나 1개(갈아넣기)");
    }

    public void insertCocktail() {
        InfoBaseHelper db = new InfoBaseHelper(this, "infoBase.db", null, 1);
        db.insert("코로나리타", "코로나:사이다:라임주스:데낄라=1:1:1:1+레몬즙");
        db.insert("예거밤", "예거마이스터:핫식스=1:3");
        db.insert("칼리모쵸", "레드와인:콜라=1:1");
        db.insert("버니니펀치", "버니니:사이다:화채(or후르츠)=1.5:1:1");
        db.insert("레몬펀치", "화이트와인:레몬즙:탄산수=1:1.5:1+레몬2조각");
        db.insert("핑크펀치", "화이트와인:자몽즙:탄산수=1:1.5:1+레몬2조각");
        db.insert("상그리아", "(오렌지와 레몬을 넣어 하루 재운)와인:사이다=2:3");
        db.insert("블랙러시안", "보드카1온스+깔루아1/2온스");
        db.insert("애플마티니", "보드카1온스+애플퍽1온스+라임주스1/2온스");
        db.insert("롱아일랜드아이스티", "보드카1/2온스+데낄라1/2온스+진1/2온스+럼1/2온스+트리플섹1/2온스+스윗앤사우어믹스3/2온스 (맨 위에 콜라)");
        db.insert("바카디", "바카디럼7/4온스+라임주스3/4온스+그레나딘시럽1작은술");
        db.insert("피나콜라다", "럼5/4온스+피나콜라다믹스2온스+파인주스2온스");
        db.insert("블루하와이안", "럼1온스+블루큐라소1온스+코코넛럼1온스+파인주스5/2온스");
        db.insert("데낄라선라이즈", "데낄라3/2온스+오렌지주스로채움+그레나딘시럽1/2온스");
        db.insert("마가리타", "데낄라3/2온스+트리플섹1/2온스+라임주스1/2온스");
        db.insert("준벅", "미도리1온스+코코넛럼1/2온스+바나나리큐르1/2온스+파인주스2온스+스윗앤사우어믹스2온스");
        db.insert("아구아밤", "아구아:핫식스=1:3");
        db.insert("미도리샤워", "미도리1.5온스+스윗앤사우어믹스2온스");
        db.insert("피치크러쉬", "피치트리3/2온스+사우어믹스2온스+크렌베리주스2온스");
        db.insert("진토닉", "진2온스+토닉워터5온스+라임조각");
        db.insert("깔루아밀크", "깔루아1온스+우유3온스");
    }

    public void insertSojuCocktail() {
        InfoBaseHelper db = new InfoBaseHelper(this, "infoBase.db", null, 1);
        db.insert("우쭈쭈 메로니", "메로나+소주50ml+사이다30ml");
        db.insert("버블탱크", "소주:사이다:탱크보이=1:1:1");
        db.insert("스크류키스", "스크류바+소주2잔+사이다1/2캔");
        db.insert("고진감래주", "콜라40ml+소주40ml+맥주80ml (소주잔을 두개 겹쳐 아래-콜라 위-소주)");
        db.insert("소원주", "소주:원두커피=1:5");
        db.insert("에너자이저", "핫식스:소주:파워에이드=2:1:2 (층지게 쌓는 것이 포인트)");
        db.insert("밀키스주", "소주20ml+맥주40ml+사이다40ml (폭탄주처럼 휴지로 잔 입구를 막고 바닥에 탁 내려놓음)");
        db.insert("죠스바주", "죠스바+소주2잔+사이다1/2캔");
        db.insert("홍초불막주", "식혜:홍초:막걸리:번=10:20:50:25 (층지게)");
        db.insert("소백산맥주", "소주:백세주:산사춘:맥주=1:1:1:1");
        db.insert("레드아이", "맥주:토마토주스=1:1");
        db.insert("더티호", "호가든:기네스=1:1 (층지게 만들기)");
        db.insert("샌디게프", "맥주:진저에일=1:1");
        db.insert("선셋", "밀러150ml+자몽주스250ml+딸기시럽2큰술 (층지게)\n");
    }

    public String result(String name) {
        InfoBaseHelper db = new InfoBaseHelper(this, "infoBase.db", null, 1);
        return db.getMethod(name).toString();
    }
}
