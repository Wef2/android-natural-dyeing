package mcl.jejunu.naturaldyeing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.realm.Realm;
import mcl.jejunu.naturaldyeing.R;
import mcl.jejunu.naturaldyeing.model.Resource;

public class ResourceActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView resourceImage;
    private TextView nameText, scientificNameText, descriptionText;
    private LinearLayout colorLayout, fabricLayout;

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
        colorLayout = (LinearLayout) findViewById(R.id.color_layout);
        fabricLayout = (LinearLayout) findViewById(R.id.fabric_layout);

        String resourceImageName = "resource_" + resourceId;
        int imageId = getResources().getIdentifier(resourceImageName, "drawable", getPackageName());
        resourceImage.setImageResource(imageId);

        nameText.setText(resource.getName());
        scientificNameText.setText(resource.getScientificName());
        descriptionText.setText(resource.getDescription());

        colorLayout.setOnClickListener(this);
        fabricLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.color_layout:
                Intent intent = new Intent(ResourceActivity.this, ColorActivity.class);
                intent.putExtra("colorId", resourceId);
                startActivity(intent);
                break;

            case R.id.fabric_layout:
                Intent intent2 = new Intent(ResourceActivity.this, FabricActivity.class);
                intent2.putExtra("fabricId", resourceId);
                startActivity(intent2);
                break;
        }
    }
}