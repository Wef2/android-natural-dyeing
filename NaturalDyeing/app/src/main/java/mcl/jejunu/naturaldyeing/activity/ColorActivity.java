package mcl.jejunu.naturaldyeing.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import mcl.jejunu.naturaldyeing.R;

public class ColorActivity extends AppCompatActivity {

    private Button circleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        circleButton = (Button) findViewById(R.id.circle_button);
    }
}
