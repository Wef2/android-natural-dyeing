package mcl.jejunu.naturaldyeing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import io.realm.Realm;
import mcl.jejunu.naturaldyeing.R;
import mcl.jejunu.naturaldyeing.model.Product;

public class ProductActivity extends AppCompatActivity {

    private TextView nameText, descriptionText;

    private Realm realm;
    private Long productId;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Intent intent = getIntent();
        productId = intent.getLongExtra("productId", 0);

        nameText = (TextView) findViewById(R.id.name_text);
        descriptionText = (TextView) findViewById(R.id.description_text);

        realm = Realm.getDefaultInstance();

        product = realm.where(Product.class).equalTo("id", productId).findFirst();
        nameText.setText(product.getName());
        descriptionText.setText(product.getDescription());
    }
}
