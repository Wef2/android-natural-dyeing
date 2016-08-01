package mcl.jejunu.naturaldyeing.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mcl.jejunu.naturaldyeing.R;

public class FabricActivity extends AppCompatActivity {

    private Button productButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabric);

        productButton = (Button) findViewById(R.id.product_button);
        productButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FabricActivity.this, ProductActivity.class));
            }
        });
    }
}
