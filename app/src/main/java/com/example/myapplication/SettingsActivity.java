package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void saveData() {
        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("addition", "" + ((CheckBox)findViewById(R.id.addition)).isChecked());
        intent.putExtra("subtraction", "" + ((CheckBox)findViewById(R.id.subtraction)).isChecked());
        intent.putExtra("multiplication", "" + ((CheckBox)findViewById(R.id.multiplication)).isChecked());
        intent.putExtra("division", "" + ((CheckBox)findViewById(R.id.division)).isChecked());

        setResult(RESULT_OK, intent);
        SettingsActivity.super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        saveData();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                saveData();
                return true;
        }

        return true;
    }
}