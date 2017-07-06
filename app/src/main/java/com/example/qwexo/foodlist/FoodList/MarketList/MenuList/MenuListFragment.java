package com.example.qwexo.foodlist.FoodList.MarketList.MenuList;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.example.qwexo.foodlist.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by qwexo on 2017-06-23.
 */

public class MenuListFragment extends Fragment {

    DatabaseReference fireDB;
    ValueEventListener listener;
    MenuList menu;
    MenuGridAdapter adapter;
    Context context;
    String userID;

    public MenuListFragment(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.menu_list_fragment,container,false);
        context = container.getContext();
        GridView grid = (GridView)view.findViewById(R.id.menuGrid);
        adapter = new MenuGridAdapter();
        grid.setAdapter(adapter);

        userID = getArguments().getString("id");

        fireDB = FirebaseDatabase.getInstance().getReference();
        listener = new ValueEventListener() {       //메뉴 리스트 가져오기
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                String menuID;
                for(DataSnapshot data:dataSnapshot.getChildren()){
                    menu = data.getValue(MenuList.class);

                    if(menu.menuName != null){
                        menuID = data.getKey();
                        adapter.addItem(userID,menuID,menu.menuName,menu.menuPrice);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        fireDB.child("market").child(userID).child("menu").addValueEventListener(listener);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        fireDB.removeEventListener(listener);
    }

}
