package mcl.jejunu.naturaldyeing.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import mcl.jejunu.naturaldyeing.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button meaningButton, historyButton, resourceButton, colorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meaningButton = (Button) findViewById(R.id.meaning_button);
        historyButton = (Button) findViewById(R.id.history_button);
        resourceButton = (Button) findViewById(R.id.resource_button);
        colorButton = (Button) findViewById(R.id.color_button);

        meaningButton.setOnClickListener(this);
        historyButton.setOnClickListener(this);
        resourceButton.setOnClickListener(this);
        colorButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.meaning_button:
                startActivity(new Intent(MainActivity.this, MeaningActivity.class));
                break;
            case R.id.history_button:
                startActivity(new Intent(MainActivity.this, HistoryActivity.class));
                break;
            case R.id.resource_button:
                startActivity(new Intent(MainActivity.this, ResourceListActivity.class));
                break;
            case R.id.color_button:
                startActivity(new Intent(MainActivity.this, ColorListActivity.class));
                break;
        }
    }

}
