package mcl.jejunu.naturaldyeing.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.realm.Realm;
import mcl.jejunu.naturaldyeing.R;
import mcl.jejunu.naturaldyeing.model.Fabric;

public class FabricActivity extends AppCompatActivity {

    private TextView nameText, descriptionText;
    private Button productButton;

    private Realm realm;
    private Long fabricId;
    private Fabric fabric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabric);

        Intent intent = getIntent();
        fabricId = intent.getLongExtra("fabricId", 0);

        nameText = (TextView) findViewById(R.id.name_text);
        descriptionText = (TextView) findViewById(R.id.description_text);
        productButton = (Button) findViewById(R.id.product_button);

        realm = Realm.getDefaultInstance();
        fabric = realm.where(Fabric.class).equalTo("id", fabricId).findFirst();

        nameText.setText(fabric.getName());
        descriptionText.setText(fabric.getDescription());
        productButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(FabricActivity.this, ProductActivity.class);
                newIntent.putExtra("productId", fabricId);
                startActivity(newIntent);
            }
        });
    }
}