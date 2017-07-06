package com.example.qwexo.foodlist.FoodList.MarketList.MenuList;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by qwexo on 2017-06-22.
 */

public class MenuGridAdapter extends BaseAdapter {

    private ArrayList<MenuListItem> list = new ArrayList<>();
    StorageReference fireStorage;
    Context context;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void clear() {
        list.clear();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        context = parent.getContext();

        final MenuListView view = new MenuListView(context);

        MenuListItem item = list.get(position);

        String userId = item.getMenuUserId();
        String menuId = item.getMenuId();

        fireStorage = FirebaseStorage.getInstance().getReference().child("market").child(userId).child("menu")
                .child(menuId + ".jpg");
        fireStorage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(context)
                        .load(uri)
                        .into(view.menuImage, new Callback() {
                            @Override
                            public void onSuccess() {
                            }

                            @Override
                            public void onError() {
                                Toast.makeText(context, "이미지 가져오기 실패", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "서버 연결 실패", Toast.LENGTH_SHORT).show();
            }
        });
        view.menuName.setText(item.getMenuName());
        view.menuPrice.setText(item.getMenuPrice() + "원");

        return view;
    }

    public void addItem(String menuUserId, String menuId, String menuName, String menuPrice) {
        MenuListItem item = new MenuListItem();

        item.setMenuName(menuName);
        item.setMenuPrice(menuPrice);
        item.setMenuUserId(menuUserId);
        item.setMenuId(menuId);

        list.add(item);
    }
}
