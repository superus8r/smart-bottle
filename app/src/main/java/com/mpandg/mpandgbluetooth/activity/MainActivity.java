package com.mpandg.mpandgbluetooth.activity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mpandg.mpandgbluetooth.Const;
import com.mpandg.mpandgbluetooth.R;
import com.mpandg.mpandgbluetooth.Utils;
import com.squareup.picasso.Picasso;

public class MainActivity extends DeviceControlActivity {

    private ImageView bottle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.app_name);

        bottle = (ImageView) findViewById(R.id.bottle);
        bottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // refresh bottle level whenever it's being clicked.
                sendCommand(Const.BOTTLE_REQUEST_LEVEL);
            }
        });

        TextView ibmTv = (TextView) findViewById(R.id.tv_ibm);
        double ibm = Utils.evaluateIbm(this);
        String ibmDescription = getResources().getString(R.string.your_ibm) + "\n" + ibm + "\n" +
                (ibm > 0 ? getResources().getString(R.string.overweight) : getResources().getString(R.string.underweight));
        ibmTv.setText(ibmDescription);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_search:
                if (super.isAdapterReady()) {
                    if (isConnected()) stopConnection();
                    else startDeviceListActivity();
                } else {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                }
                return true;

            case R.id.action_terminal:
                startActivity(new Intent(this, DeviceControlActivity.class));
                return true;

            case R.id.action_registration:
                startActivity(new Intent(this, RegistrationActivity.class));
                return true;

            case R.id.action_my_type:
                startActivity(new Intent(this, MyTypeActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void receive(String message) {
        super.receive(message);
        //Toast.makeText(this, "message:" + message, Toast.LENGTH_SHORT).show();

        switch (message.charAt(0)) {
            case '0':

                Picasso.with(this)
                        .load(R.drawable.bottle_10)
                        .placeholder(R.drawable.bottle_100)
                        .error(R.drawable.bottle_10)
                        .into(bottle);
                break;
            case '1':

                Picasso.with(this)
                        .load(R.drawable.bottle_20)
                        .placeholder(R.drawable.bottle_100)
                        .error(R.drawable.bottle_10)
                        .into(bottle);
                break;
            case '2':

                Picasso.with(this)
                        .load(R.drawable.bottle_30)
                        .placeholder(R.drawable.bottle_100)
                        .error(R.drawable.bottle_10)
                        .into(bottle);
                break;
            case '3':

                Picasso.with(this)
                        .load(R.drawable.bottle_40)
                        .placeholder(R.drawable.bottle_100)
                        .error(R.drawable.bottle_10)
                        .into(bottle);
                break;
            case '4':

                Picasso.with(this)
                        .load(R.drawable.bottle_50)
                        .placeholder(R.drawable.bottle_100)
                        .error(R.drawable.bottle_10)
                        .into(bottle);
                break;
            case '5':

                Picasso.with(this)
                        .load(R.drawable.bottle_60)
                        .placeholder(R.drawable.bottle_100)
                        .error(R.drawable.bottle_10)
                        .into(bottle);
                break;
            case '6':

                Picasso.with(this)
                        .load(R.drawable.bottle_70)
                        .placeholder(R.drawable.bottle_100)
                        .error(R.drawable.bottle_10)
                        .into(bottle);
                break;
            case '7':

                Picasso.with(this)
                        .load(R.drawable.bottle_80)
                        .placeholder(R.drawable.bottle_100)
                        .error(R.drawable.bottle_10)
                        .into(bottle);
                break;
            case '8':

                Picasso.with(this)
                        .load(R.drawable.bottle_90)
                        .placeholder(R.drawable.bottle_100)
                        .error(R.drawable.bottle_10)
                        .into(bottle);
                break;
            case '9':

                Picasso.with(this)
                        .load(R.drawable.bottle_100)
                        .placeholder(R.drawable.bottle_100)
                        .error(R.drawable.bottle_10)
                        .into(bottle);
                break;
        }
    }
}
