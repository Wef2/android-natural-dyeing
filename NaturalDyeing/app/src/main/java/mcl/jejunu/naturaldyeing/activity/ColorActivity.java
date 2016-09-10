package mcl.jejunu.naturaldyeing.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import mcl.jejunu.naturaldyeing.R;
import mcl.jejunu.naturaldyeing.model.Color;

public class ColorActivity extends AppCompatActivity {

    private Button circleButton, resourceButton;
    private TextView nameText, rgbText, hexText, hvcText, labText;

    private Long colorId;
    private Color color;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        nameText = (TextView) findViewById(R.id.name_text);
        rgbText = (TextView) findViewById(R.id.rgb_text);
        hexText = (TextView) findViewById(R.id.hex_text);
        hvcText = (TextView) findViewById(R.id.hvc_text);
        labText = (TextView) findViewById(R.id.lab_text);

        Intent intent = getIntent();
        colorId = intent.getLongExtra("colorId", 0);

        resourceButton = (Button) findViewById(R.id.resource_button);
        resourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ColorActivity.this, ResourceActivity.class);
                intent.putExtra("resourceId", colorId);
                startActivity(intent);
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("불러오는 중");
        progressDialog.setCancelable(false);

        new ColorRequestTask().execute();
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

    private class ColorRequestTask extends AsyncTask<Void, Void, Color> {

        @Override
        protected void onPreExecute() {
            progressDialog.show();
        }

        @Override
        protected Color doInBackground(Void... params) {
            try {
                final String url = getString(R.string.server_url) + "/select_color.php?id=" + colorId;
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Color color = restTemplate.getForObject(url, Color.class);
                return color;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Color result) {
            color = result;

            nameText.setText(color.getName());
            rgbText.setText(color.getRgb());
            hexText.setText(color.getHexhtml());
            hvcText.setText(color.getHvc());
            labText.setText(color.getLab());

            String[] colorValues = color.getRgb().split(" ");
            int r = Integer.parseInt(colorValues[0]);
            int g = Integer.parseInt(colorValues[1]);
            int b = Integer.parseInt(colorValues[2]);

            circleButton = (Button) findViewById(R.id.circle_button);
            circleButton.setBackgroundColor(android.graphics.Color.rgb(r, g, b));

            progressDialog.dismiss();
        }
    }


}