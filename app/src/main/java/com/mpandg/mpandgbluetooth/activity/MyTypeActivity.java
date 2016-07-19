package com.mpandg.mpandgbluetooth.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.mpandg.mpandgbluetooth.Const;
import com.mpandg.mpandgbluetooth.R;
import com.mpandg.mpandgbluetooth.Utils;
import com.mpandg.mpandgbluetooth.model.BodyType;

public class MyTypeActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_type);

        // register onClick listeners for card views.

        CardView general = (CardView) findViewById(R.id.card_general_user);
        general.setOnClickListener(this);

        CardView bodyBuilder = (CardView) findViewById(R.id.card_body_builder);
        bodyBuilder.setOnClickListener(this);

        CardView runner = (CardView) findViewById(R.id.card_runner);
        runner.setOnClickListener(this);

        CardView biker = (CardView) findViewById(R.id.card_biker);
        biker.setOnClickListener(this);

        CardView aged = (CardView) findViewById(R.id.card_aged);
        aged.setOnClickListener(this);

        CardView addType = (CardView) findViewById(R.id.card_add_type);
        addType.setOnClickListener(this);
    }

    /**
     * Handle cardView clicks to open corresponding activities.
     */
    @Override
    public void onClick(View view) {

        BodyType bodyType;
        switch (view.getId()) {
            case R.id.card_general_user:
                // send the corresponding type for navigation purposes.
                bodyType = new BodyType(getResources().getString(R.string.general_user), R.color.general_user, R.drawable.general_user);
                TypeDetailActivity.navigate(this, view.findViewById(R.id.iv_general_user), bodyType);
                break;
            case R.id.card_body_builder:
                bodyType = new BodyType(getResources().getString(R.string.body_builder), R.color.body_builder, R.drawable.bodybuilder);
                TypeDetailActivity.navigate(this, view.findViewById(R.id.iv_body_builder), bodyType);
                break;
            case R.id.card_runner:
                bodyType = new BodyType(getResources().getString(R.string.runner), R.color.runner, R.drawable.runner);
                TypeDetailActivity.navigate(this, view.findViewById(R.id.iv_runner), bodyType);
                break;
            case R.id.card_biker:
                bodyType = new BodyType(getResources().getString(R.string.biker), R.color.biker, R.drawable.biker);
                TypeDetailActivity.navigate(this, view.findViewById(R.id.iv_biker), bodyType);
                break;
            case R.id.card_aged:
                bodyType = new BodyType(getResources().getString(R.string.aged), R.color.aged, R.drawable.aged);
                TypeDetailActivity.navigate(this, view.findViewById(R.id.iv_aged), bodyType);
                break;
            case R.id.card_add_type:
                bodyType = new BodyType(getResources().getString(R.string.add_new_type), R.color.primary, R.drawable.ic_add_circle_outline_white_48dp);
                TypeDetailActivity.navigate(this, view.findViewById(R.id.iv_add_new), bodyType);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == Const.REQUEST_CODE_TYPE_DETAIL && resultCode == RESULT_OK) {

            // save the body type chosen by user and close the activity with RESULT_OK.
            Utils.saveUserType(this, data.getStringExtra(Const.KEY_USER_TYPE));
            setResult(RESULT_OK);
            finish();
        }
    }
}
