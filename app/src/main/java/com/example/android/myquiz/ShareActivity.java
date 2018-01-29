package com.example.android.myquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShareActivity extends AppCompatActivity {

    TextView resultMessage;
    int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        Intent intent = getIntent();
        points = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, 0);
        resultMessage = (TextView) findViewById(R.id.shareMessage);
        resultMessage.setText(getString(R.string.finalMessage, getString(R.string.results, points, 4)));
    }

    public void share(View view) {
        String message = getString(R.string.shareMessage, points, 4);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setType("text/plain");

        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }
    }

    public void restartQuiz(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
