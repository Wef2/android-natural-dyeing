package mcl.jejunu.naturaldyeing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.realm.Realm;
import mcl.jejunu.naturaldyeing.R;
import mcl.jejunu.naturaldyeing.model.Resource;

public class ResourceActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView resourceImage;
    private TextView nameText, scientificNameText, descriptionText;
    private Button colorButton, fabricButton;

    private Long resourceId;
    private Resource resource;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource);

        realm = Realm.getDefaultInstance();

        Intent intent = getIntent();
        resourceId = intent.getLongExtra("resourceId", 0);

        resource = realm.where(Resource.class).equalTo("id", resourceId).findFirst();

        resourceImage = (ImageView) findViewById(R.id.resource_image);
        nameText = (TextView) findViewById(R.id.name_text);
        scientificNameText = (TextView) findViewById(R.id.scientific_name_text);
        descriptionText = (TextView) findViewById(R.id.description_text);
        colorButton = (Button) findViewById(R.id.color_button);
        fabricButton = (Button) findViewById(R.id.fabric_button);

        String resourceImageName = "resource_" + resourceId;
        int imageId = getResources().getIdentifier(resourceImageName, "drawable", getPackageName());
        resourceImage.setImageResource(imageId);

        nameText.setText(resource.getName());
        scientificNameText.setText(resource.getScientificName());
        descriptionText.setText(resource.getDescription());

        colorButton.setOnClickListener(this);
        fabricButton.setOnClickListener(this);
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
}