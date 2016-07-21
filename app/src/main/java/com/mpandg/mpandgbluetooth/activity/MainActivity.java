package com.mpandg.mpandgbluetooth.activity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mpandg.mpandgbluetooth.Const;
import com.mpandg.mpandgbluetooth.R;
import com.mpandg.mpandgbluetooth.Utils;

public class MainActivity extends DeviceControlActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.app_name);

        ImageView bottle = (ImageView) findViewById(R.id.bottle);
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
        Toast.makeText(this, "message:" + message, Toast.LENGTH_SHORT).show();
    }
}
