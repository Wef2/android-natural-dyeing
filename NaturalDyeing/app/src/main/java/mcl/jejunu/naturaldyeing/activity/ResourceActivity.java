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

import mcl.jejunu.naturaldyeing.R;
import mcl.jejunu.naturaldyeing.model.Resource;

public class ResourceActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView resourceImage;
    private TextView nameText, scientificNameText, descriptionText;
    private Button colorButton, fabricButton;

    private Long resourceId;
    private Resource resource;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource);

        Intent intent = getIntent();
        resourceId = intent.getLongExtra("resourceId", 0);

        resourceImage = (ImageView) findViewById(R.id.resource_image);
        nameText = (TextView) findViewById(R.id.name_text);
        scientificNameText = (TextView) findViewById(R.id.scientific_name_text);
        descriptionText = (TextView) findViewById(R.id.description_text);
        colorButton = (Button) findViewById(R.id.color_button);
        fabricButton = (Button) findViewById(R.id.fabric_button);

        colorButton.setOnClickListener(this);
        fabricButton.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("불러오는 중");
        progressDialog.setCancelable(false);

        new ResourceRequestTask().execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.color_button:
                Intent intent = new Intent(ResourceActivity.this, ColorActivity.class);
                intent.putExtra("colorId", resourceId);
                startActivity(intent);
                break;

            case R.id.fabric_button:
                Intent intent2 = new Intent(ResourceActivity.this, FabricActivity.class);
                intent2.putExtra("fabricId", resourceId);
                startActivity(intent2);
                break;
        }
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

    private class ResourceRequestTask extends AsyncTask<Void, Void, Resource> {

        @Override
        protected void onPreExecute(){
            progressDialog.show();
        }

        @Override
        protected Resource doInBackground(Void... params) {
            try {
                final String url = getString(R.string.server_url) + "/select_resource.php?id=" + resourceId;
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Resource resource = restTemplate.getForObject(url, Resource.class);
                return resource;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Resource result) {
            resource = result;
            nameText.setText(resource.getName());
            scientificNameText.setText(resource.getScientificName());
            descriptionText.setText(resource.getDescription());
            new ImageTask(resourceImage).execute();
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
                InputStream in = new java.net.URL(url + resource.getImage()).openStream();
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