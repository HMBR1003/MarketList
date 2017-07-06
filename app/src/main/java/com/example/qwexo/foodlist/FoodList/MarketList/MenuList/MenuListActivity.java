package com.example.qwexo.foodlist.FoodList.MarketList.MenuList;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import com.example.qwexo.foodlist.FoodList.MarketList.MarketList;
import com.example.qwexo.foodlist.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class MenuListActivity extends AppCompatActivity {
    Fragment fragment;
    DatabaseReference fireDB;
    ValueEventListener listener;
    MarketList market;
    StorageReference fireStorage;
    Intent intent;
    Bundle bundle;
    String userID;

    TextView marketNameText, marketAdressText, tellText, minPriceText;
    ImageView marketImage;
    ViewPager viewPager;
    ImageButton menuButton,inforButton,reviewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        marketNameText = (TextView) findViewById(R.id.marketName);
        marketAdressText = (TextView) findViewById(R.id.marketAdress);
        tellText = (TextView) findViewById(R.id.tellText);
        minPriceText = (TextView) findViewById(R.id.minPrice);
        marketImage = (ImageView) findViewById(R.id.marketImage);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        menuButton = (ImageButton)findViewById(R.id.menuButton);
        inforButton = (ImageButton)findViewById(R.id.inforButton);
        reviewButton = (ImageButton)findViewById(R.id.reviewButton);

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch(position){
                    case 0:
                        fragment = new MenuListFragment();
                        fragment.setArguments(bundle);
                        return fragment;
//                        return new MenuListFragment();
                    case 1:
                        fragment = new MenuListFragment();
                        fragment.setArguments(bundle);
                        return fragment;
//                        return new MenuListFragment();
                    case 2:
                        fragment = new ReviewFragment();
                        fragment.setArguments(bundle);
                        return fragment;
//                        return new ReviewFragment();
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
        viewPager.setCurrentItem(0);

//        menuButton.setTag(0);
//        inforButton.setTag(1);
//        reviewButton.setTag(2);

        intent = getIntent();
        userID = intent.getStringExtra("id");
        final String marketName = intent.getStringExtra("name");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);      //툴바설정
        toolbar.setTitle(marketName);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        bundle = new Bundle();       //프래그먼트에 넘겨줄 값
        bundle.putString("id", userID);

//        fragment = new MenuListFragment();      //온크리티 시 보여줄 프래그먼트 설정
//        fragment.setArguments(bundle);
//        fmanager = getFragmentManager();
//        ftransaction = fmanager.beginTransaction();
//        ftransaction.add(R.id.viewPager, fragment);
//        ftransaction.commit();

        ////마켓 정보 가져오기
        fireDB = FirebaseDatabase.getInstance().getReference().child("market").child(userID);
        listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                market = dataSnapshot.getValue(MarketList.class);

                marketNameText.setText(market.marketName);
                marketAdressText.setText(market.marketAddress1 + "\n" + market.marketAddress2);
                tellText.setText(market.marketTel);
                minPriceText.setText(market.minPrice + "원");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        fireDB.addValueEventListener(listener);

        ////마켓 대표이미지 가져오기
        fireStorage = FirebaseStorage.getInstance().getReference().child("market").child(userID).child(userID + ".jpg");
        fireStorage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext())
                        .load(uri)
                        .into(marketImage, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                Toast.makeText(MenuListActivity.this, "이미지 가져오기 실패", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MenuListActivity.this, "서버 연결 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {   //툴바 이벤트
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        fireDB.removeEventListener(listener);
    }

    public void onCilcked(View v) {
        Log.d("adad","뷰클릭");
        switch (v.getId()) {
            case R.id.menuButton:
                viewPager.setCurrentItem(0);
                break;
            case R.id.inforButton:
                viewPager.setCurrentItem(1);
                break;
            case R.id.reviewButton:
                viewPager.setCurrentItem(2);
                break;
        }
    }
}
