package com.example.android.myquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    boolean[] q1RightAnswer = {false, true, false};
    boolean[] q2RightAnswer = {true, false, false};
    String q3RightAnswer;
    boolean[] q4RightAnswer = {false, true, true};
    String additionalMsg = "";
    RadioButton q1a1;
    RadioButton q1a2;
    RadioButton q1a3;
    RadioButton q2a1;
    RadioButton q2a2;
    RadioButton q2a3;
    TextView q3a1;
    CheckBox q4a1;
    CheckBox q4a2;
    CheckBox q4a3;
    public static final String EXTRA_MESSAGE = "com.example.MyQuiz.MESSAGE";
    int points;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        q1a1 = (RadioButton) findViewById(R.id.q1a1);
        q1a2 = (RadioButton) findViewById(R.id.q1a2);
        q1a3 = (RadioButton) findViewById(R.id.q1a3);
        q2a1 = (RadioButton) findViewById(R.id.q2a1);
        q2a2 = (RadioButton) findViewById(R.id.q2a2);
        q2a3 = (RadioButton) findViewById(R.id.q2a3);
        q3a1 = (TextView) findViewById(R.id.q3a1);
        q4a1 = (CheckBox) findViewById(R.id.q4a1);
        q4a2 = (CheckBox) findViewById(R.id.q4a2);
        q4a3 = (CheckBox) findViewById(R.id.q4a3);
        q3RightAnswer = getString(R.string.q3RightAnswer);
    }

    public void submitQuiz(View view) {
        boolean a1 = Arrays.equals(q1RightAnswer, getQuestion1Answer());
        Log.v("MainActivity.java", "A1" + a1);
        boolean a2 = Arrays.equals(q2RightAnswer, getQuestion2Answer());
        Log.v("MainActivity.java", "A2" + a2);
        boolean a3 = q3RightAnswer.equals(getQuestion3Answer().toLowerCase());
        Log.v("MainActivity.java", "A3" + a3);
        boolean a4 = Arrays.equals(q4RightAnswer, getQuestion4Answer());
        if (a1 && a2 && a3 && a4) {
            additionalMsg = "\n" + getString(R.string.success);
        } else {
            additionalMsg = "\n" + getString(R.string.failure);
        }
        points = ((a1 ? 1 : 0) + (a2 ? 1 : 0) + (a3 ? 1 : 0) + (a4 ? 1 : 0));
        displayToastMessage(getString(R.string.results,points , 4) + additionalMsg);

        openShareActivity(points);
    }

    public void displayToastMessage(String textMessage) {
        Context context = getApplicationContext();
        CharSequence message = textMessage;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    public boolean[] getQuestion1Answer() {
        return new boolean[]{q1a1.isChecked(), q1a2.isChecked(), q1a3.isChecked()};
    }

    public boolean[] getQuestion2Answer() {
        return new boolean[]{q2a1.isChecked(), q2a2.isChecked(), q2a3.isChecked()};
    }

    public String getQuestion3Answer() {
        return q3a1.getText().toString();
    }

    public boolean[] getQuestion4Answer() {
        return new boolean[]{q4a1.isChecked(), q4a2.isChecked(), q4a3.isChecked()};
    }

    public void resetQuiz(View view) {
        points = 0;
        q1a1.setChecked(false);
        q1a2.setChecked(false);
        q1a3.setChecked(false);
        q2a1.setChecked(false);
        q2a2.setChecked(false);
        q2a3.setChecked(false);
        q3a1.setText("", TextView.BufferType.EDITABLE);
        q4a1.setChecked(false);
        q4a2.setChecked(false);
        q4a3.setChecked(false);
        displayToastMessage(getString(R.string.reset_success));
    }

    public void openShareActivity(int points) {
        Intent intent = new Intent(this, ShareActivity.class);
        intent.putExtra(EXTRA_MESSAGE, points);
        startActivity(intent);
    }


}
