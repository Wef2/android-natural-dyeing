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
import android.widget.ImageView;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;

import mcl.jejunu.naturaldyeing.R;
import mcl.jejunu.naturaldyeing.model.Product;

public class ProductActivity extends AppCompatActivity {

    private ImageView productImage;
    private TextView nameText, descriptionText;

    private Long productId;
    private Product product;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Intent intent = getIntent();
        productId = intent.getLongExtra("productId", 0);

        productImage = (ImageView) findViewById(R.id.product_image);
        nameText = (TextView) findViewById(R.id.name_text);
        descriptionText = (TextView) findViewById(R.id.description_text);

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("불러오는 중");
        progressDialog.setCancelable(false);

        new ProductRequestTask().execute();
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


    private class ProductRequestTask extends AsyncTask<Void, Void, Product> {

        @Override
        protected void onPreExecute() {
            progressDialog.show();
        }

        @Override
        protected Product doInBackground(Void... params) {
            try {
                final String url = getString(R.string.server_url) + "/select_product.php?id=" + productId;
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Product product = restTemplate.getForObject(url, Product.class);
                return product;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Product result) {
            product = result;

            nameText.setText(product.getName());
            descriptionText.setText(product.getDescription());

            new ImageTask(productImage).execute();
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
                InputStream in = new java.net.URL(url + product.getImage()).openStream();
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
