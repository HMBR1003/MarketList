package com.example.qwexo.foodlist.FoodList;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.qwexo.foodlist.FoodList.MarketList.MarketListActivity;
import com.example.qwexo.foodlist.R;

public class FoodActivity extends AppCompatActivity {

    ImageButton chickenButton, pizzaButton, hamButton, footButton, button1,takeoutButton;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        chickenButton = (ImageButton)findViewById(R.id.chickenButton);
        pizzaButton = (ImageButton)findViewById(R.id.pizzaButton);
        hamButton = (ImageButton)findViewById(R.id.hamButton);
        footButton = (ImageButton)findViewById(R.id.footButton);
        button1 = (ImageButton)findViewById(R.id.button1);
        takeoutButton = (ImageButton)findViewById(R.id.takeoutButton);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("메뉴선택");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    void onClicked(View v){
        Intent intent = new Intent(getApplicationContext(),MarketListActivity.class);
        if(v == chickenButton){
            intent.putExtra("menu","치킨");
            startActivity(intent);
        }else if(v == pizzaButton){
            intent.putExtra("menu","피자");
            startActivity(intent);
        }else if(v == hamButton){
            intent.putExtra("menu","햄버거");
            startActivity(intent);
        }else if(v == footButton){
            intent.putExtra("menu","족발");
            startActivity(intent);
        }else if(v == button1){
            intent.putExtra("menu","기타음식");
            startActivity(intent);
        }else if(v==takeoutButton){
            intent.putExtra("menu","테이크아웃");
            startActivity(intent);
        }
    }
}
