package mcl.jejunu.naturaldyeing.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import mcl.jejunu.naturaldyeing.R;
import mcl.jejunu.naturaldyeing.adapter.ResourceAdapter;
import mcl.jejunu.naturaldyeing.model.Resource;

public class ResourceListActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private RealmResults<Resource> resources;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_list);

        realm = Realm.getDefaultInstance();
        resources = realm.where(Resource.class).findAll();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ResourceAdapter(resources, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Resource resource = (Resource) v.getTag();
        Intent intent = new Intent(ResourceListActivity.this, ResourceActivity.class);
        intent.putExtra("resourceId", resource.getId());
        startActivity(intent);
    }
}