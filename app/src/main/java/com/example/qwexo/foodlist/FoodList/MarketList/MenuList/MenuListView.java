package com.example.qwexo.foodlist.FoodList.MarketList.MenuList;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qwexo.foodlist.R;

/**
 * Created by qwexo on 2017-06-22.
 */

public class MenuListView extends LinearLayout{

    TextView menuName,menuPrice;
    ImageView menuImage;
    public MenuListView(Context context){
        super(context);

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.menu_grid_item, this, true);

        menuName = (TextView)findViewById(R.id.menuName);
        menuPrice = (TextView) findViewById(R.id.menuPrice);
        menuImage = (ImageView)findViewById(R.id.menuImage);
    }
}
