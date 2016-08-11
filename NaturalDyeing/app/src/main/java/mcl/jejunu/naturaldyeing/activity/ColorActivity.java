package mcl.jejunu.naturaldyeing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import io.realm.Realm;
import mcl.jejunu.naturaldyeing.R;
import mcl.jejunu.naturaldyeing.model.Color;

public class ColorActivity extends AppCompatActivity {

    private Button circleButton;
    private TextView nameText, rgbText, hexText, hvcText, labText;

    private Realm realm;
    private Long colorId;
    private Color color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        realm = Realm.getDefaultInstance();

        Intent intent = getIntent();
        colorId = intent.getLongExtra("colorId", 0);
        color = realm.where(Color.class).equalTo("id", colorId).findFirst();

        circleButton = (Button) findViewById(R.id.circle_button);
        nameText = (TextView) findViewById(R.id.name_text);
        rgbText = (TextView) findViewById(R.id.rgb_text);
        hexText = (TextView) findViewById(R.id.hex_text);
        hvcText = (TextView) findViewById(R.id.hvc_text);
        labText = (TextView) findViewById(R.id.lab_text);

        nameText.setText(color.getName());
        rgbText.setText(color.getRgb());
        hexText.setText(color.getHexhtml());
        hvcText.setText(color.getHvc());
        labText.setText(color.getLab());
    }
}
