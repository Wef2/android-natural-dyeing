package mcl.jejunu.naturaldyeing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import mcl.jejunu.naturaldyeing.R;

public class ResourceActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout colorLayout, fabricLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource);

        colorLayout = (LinearLayout) findViewById(R.id.color_layout);
        fabricLayout = (LinearLayout) findViewById(R.id.fabric_layout);

        colorLayout.setOnClickListener(this);
        fabricLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.color_layout:
                startActivity(new Intent(ResourceActivity.this, ColorActivity.class));
                break;

            case R.id.fabric_layout:
                startActivity(new Intent(ResourceActivity.this, FabricActivity.class));
                break;
        }
    }
}
