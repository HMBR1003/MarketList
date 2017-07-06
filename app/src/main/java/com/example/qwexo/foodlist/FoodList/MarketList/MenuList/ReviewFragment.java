package com.example.qwexo.foodlist.FoodList.MarketList.MenuList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.qwexo.foodlist.R;

/**
 * Created by qwexo on 2017-06-23.
 */

public class ReviewFragment extends Fragment {

    public ReviewFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.review_fragment,container,false);
        ListView list = (ListView)view.findViewById(R.id.listView);


        return view;
    }
}
