package mcl.jejunu.naturaldyeing.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.realm.Realm;
import mcl.jejunu.naturaldyeing.R;
import mcl.jejunu.naturaldyeing.model.Color;

public class ColorActivity extends AppCompatActivity {

    private Button circleButton, resourceButton;
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

        String[] colorValues = color.getRgb().split(" ");
        int r = Integer.parseInt(colorValues[0]);
        int g = Integer.parseInt(colorValues[1]);
        int b = Integer.parseInt(colorValues[2]);

        circleButton = (Button) findViewById(R.id.circle_button);
        circleButton.setBackgroundColor(android.graphics.Color.rgb(r,g, b));

        nameText = (TextView) findViewById(R.id.name_text);
        rgbText = (TextView) findViewById(R.id.rgb_text);
        hexText = (TextView) findViewById(R.id.hex_text);
        hvcText = (TextView) findViewById(R.id.hvc_text);
        labText = (TextView) findViewById(R.id.lab_text);
        resourceButton = (Button) findViewById(R.id.resource_button);

        nameText.setText(color.getName());
        rgbText.setText(color.getRgb());
        hexText.setText(color.getHexhtml());
        hvcText.setText(color.getHvc());
        labText.setText(color.getLab());
        resourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ColorActivity.this, ResourceActivity.class);
                intent.putExtra("resourceId", colorId);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.default_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                Intent intent = new Intent(this, SearchResultsActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
