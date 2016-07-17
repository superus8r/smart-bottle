package com.mpandg.mpandgbluetooth.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.mpandg.mpandgbluetooth.R;
import com.mpandg.mpandgbluetooth.model.BodyType;

public class MyTypeActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_type);

        CardView general = (CardView) findViewById(R.id.card_general_user);
        general.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        BodyType bodyType;
        switch (view.getId()) {

            case R.id.card_general_user:
                bodyType = new BodyType();
                bodyType.setColor(R.color.general_user);
                bodyType.setName(getResources().getString(R.string.general_user));
                bodyType.setPhoto(R.drawable.general_user);
                TypeDetailActivity.navigate(this, view.findViewById(R.id.iv_general_user), bodyType);
        }
    }
}
