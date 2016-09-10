package mcl.jejunu.naturaldyeing.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;

import io.realm.Realm;
import mcl.jejunu.naturaldyeing.R;
import mcl.jejunu.naturaldyeing.model.Fabric;

public class FabricActivity extends AppCompatActivity {

    private ImageView fabricImage;
    private TextView nameText, descriptionText;
    private Button productButton;

    private Realm realm;
    private Long fabricId;
    private Fabric fabric;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabric);

        Intent intent = getIntent();
        fabricId = intent.getLongExtra("fabricId", 0);

        realm = Realm.getDefaultInstance();
        fabric = realm.where(Fabric.class).equalTo("id", fabricId).findFirst();

        fabricImage = (ImageView) findViewById(R.id.fabric_image);
        nameText = (TextView) findViewById(R.id.name_text);
        descriptionText = (TextView) findViewById(R.id.description_text);
        productButton = (Button) findViewById(R.id.product_button);

        productButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(FabricActivity.this, ProductActivity.class);
                newIntent.putExtra("productId", fabricId);
                startActivity(newIntent);
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("불러오는 중");
        progressDialog.setCancelable(false);

        new FabricRequestTask().execute();
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

    private class FabricRequestTask extends AsyncTask<Void, Void, Fabric> {

        @Override
        protected Fabric doInBackground(Void... params) {
            try {
                final String url = getString(R.string.server_url) + "/select_fabric.php?id=" + fabricId;
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Fabric fabric = restTemplate.getForObject(url, Fabric.class);
                return fabric;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Fabric result) {
            fabric = result;

            nameText.setText(fabric.getName());
            descriptionText.setText(fabric.getDescription());

            new ImageTask(fabricImage).execute();
        }
    }

    private class ImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public ImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String... urls) {
            final String url = getString(R.string.storage_url);
            Bitmap bitmap = null;
            try {
                InputStream in = new java.net.URL(url + fabric.getImage()).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
            progressDialog.dismiss();
        }
    }

}