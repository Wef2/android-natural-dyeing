package mcl.jejunu.naturaldyeing.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mcl.jejunu.naturaldyeing.R;
import mcl.jejunu.naturaldyeing.adapter.ResourceAdapter;
import mcl.jejunu.naturaldyeing.model.Resource;

public class ResourceListActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Resource> resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_list);

        resources = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ResourceAdapter(resources, this);
        recyclerView.setAdapter(adapter);

        new ResourceListRequestTask().execute();
    }

    @Override
    public void onClick(View v) {
        Resource resource = (Resource) v.getTag();
        Intent intent = new Intent(ResourceListActivity.this, ResourceActivity.class);
        intent.putExtra("resourceId", resource.getId());
        startActivity(intent);
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

    private class ResourceListRequestTask extends AsyncTask<Void, Void, List<Resource>> {

        @Override
        protected List<Resource> doInBackground(Void... params) {
            try {
                final String url = getString(R.string.server_url) + "/select_resource_list.php";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Resource[] resources = restTemplate.getForObject(url, Resource[].class);
                return Arrays.asList(resources);
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Resource> results) {
            resources.addAll(results);
            adapter.notifyDataSetChanged();
        }
    }


}