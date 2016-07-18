package com.mpandg.mpandgbluetooth.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.mpandg.mpandgbluetooth.Const;
import com.mpandg.mpandgbluetooth.R;
import com.mpandg.mpandgbluetooth.Utils;

public class RegistrationActivity extends AppCompatActivity {

    private Spinner spinner;
    private TextInputLayout nameLayout;
    private EditText name;
    private TextInputLayout weightLayout;
    private EditText weight;
    private TextInputLayout heightLayout;
    private EditText height;
    private TextInputLayout ageLayout;
    private EditText age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setTitle(R.string.registeration);

        spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sex_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        nameLayout = (TextInputLayout) findViewById(R.id.layout_name);
        name = (EditText) findViewById(R.id.et_name);

        weightLayout = (TextInputLayout) findViewById(R.id.layout_weight);
        weight = (EditText) findViewById(R.id.et_weight);

        heightLayout = (TextInputLayout) findViewById(R.id.layout_height);
        height = (EditText) findViewById(R.id.et_height);

        ageLayout = (TextInputLayout) findViewById(R.id.layout_age);
        age = (EditText) findViewById(R.id.et_age);
    }

    private boolean checkInput() {

        // remove old errors.
        nameLayout.setErrorEnabled(false);
        weightLayout.setErrorEnabled(false);
        heightLayout.setErrorEnabled(false);
        ageLayout.setErrorEnabled(false);

        boolean flag = true;

        if (name.getText().toString().equals("")) {
            nameLayout.setErrorEnabled(true);
            nameLayout.setError(getResources().getString(R.string.name_is_required));
            flag = false;
        }

        if (weight.getText().toString().equals("")) {
            weightLayout.setErrorEnabled(true);
            weightLayout.setError(getResources().getString(R.string.name_is_required));
            flag = false;
        }

        if (height.getText().toString().equals("")) {
            heightLayout.setErrorEnabled(true);
            heightLayout.setError(getResources().getString(R.string.name_is_required));
            flag = false;
        }

        if (age.getText().toString().equals("")) {
            ageLayout.setErrorEnabled(true);
            ageLayout.setError(getResources().getString(R.string.name_is_required));
            flag = false;
        }

        return flag;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_registration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_done:
                if (checkInput()) {

                    // save the data if there was no errors.
                    Utils.saveUserInfo(this, name.getText().toString(),
                            spinner.getSelectedItem().toString(),
                            Integer.parseInt(weight.getText().toString()),
                            Integer.parseInt(height.getText().toString()),
                            Integer.parseInt(age.getText().toString()));

                    // the data is now saved, open my type activity to choose user type.
                    startActivityForResult(new Intent(this, MyTypeActivity.class), Const.REQUEST_CODE_CHOOSE_TYPE);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Const.REQUEST_CODE_CHOOSE_TYPE && resultCode == RESULT_OK) {

            // type has been chosen successfully, return result ok to calling activity.
            setResult(RESULT_OK);
            finish();
        }
    }
}
