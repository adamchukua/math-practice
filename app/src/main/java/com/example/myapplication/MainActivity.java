package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String addition, subtraction, multiplication, division;

    ActivityResultLauncher<Intent> activityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();

                        if (data != null) {
                            addition = data.getStringExtra("addition");
                            subtraction = data.getStringExtra("subtraction");
                            multiplication = data.getStringExtra("multiplication");
                            division = data.getStringExtra("division");
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Практика");
        generateTask("");

        View btn = findViewById(R.id.settingsBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                activityLauncher.launch(intent);
            }
        });
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void check(View v) {
        TextView number1 = (TextView)findViewById(R.id.number1);
        TextView number2 = (TextView)findViewById(R.id.number2);
        TextView type = (TextView)findViewById(R.id.type);
        EditText answer_input = (EditText)findViewById(R.id.answer_input);
        TextView info_view = (TextView)findViewById(R.id.info);

        int number1Value = Integer.parseInt(number1.getText().toString());
        int number2Value = Integer.parseInt(number2.getText().toString());

        if (type.getText().equals("*")) {
            if (answer_input.getText().toString().equals(String.valueOf(number1Value * number2Value))) {
                answer_input.setText("");
                info_view.setText("");
                generateTask("");
                Toast.makeText(this, "Молодець :D", Toast.LENGTH_SHORT).show();
            } else {
                info_view.setText("Спробуй ще раз!");
            }
        } else if (type.getText().equals("/")) {
            if (answer_input.getText().toString().equals(String.valueOf(number1Value / number2Value))) {
                answer_input.setText("");
                info_view.setText("");
                generateTask("");
                Toast.makeText(this, "Молодець :D", Toast.LENGTH_SHORT).show();
            } else {
                info_view.setText("Спробуй ще раз!");
            }
        }
    }

    public void generateTask(String type) {
        TextView number1_view = (TextView)findViewById(R.id.number1);
        TextView number2_view = (TextView)findViewById(R.id.number2);
        TextView type_view = (TextView)findViewById(R.id.type);
        Random random = new Random();

        if (type.equals("*")) {
            type_view.setText("*");
            if (random.nextBoolean()) {
                number1_view.setText(String.valueOf(getRandomNumber(0, 7)));
                number2_view.setText(String.valueOf(getRandomNumber(0, 11)));
            } else {
                number1_view.setText(String.valueOf(getRandomNumber(0, 11)));
                number2_view.setText(String.valueOf(getRandomNumber(0, 7)));
            }
        } else if (type.equals("/")) {
            int tempNumber1, tempNumber2;
            type_view.setText("/");

            tempNumber2 = getRandomNumber(0, 7);
            tempNumber1 = getRandomNumber(0, 11) * tempNumber2;

            if ((tempNumber2 != 0) && (((double)tempNumber1 / tempNumber2) == (int)(tempNumber1 / tempNumber2))) {
                number1_view.setText(String.valueOf(tempNumber1));
                number2_view.setText(String.valueOf(tempNumber2));
            } else {
                generateTask("/");
            }
        } else {
            String tempType = (random.nextBoolean()) ? "*" : "/";
            generateTask(tempType);
        }
    }
}