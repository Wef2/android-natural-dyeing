package mcl.jejunu.naturaldyeing.activity;

import android.content.Intent;
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

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mcl.jejunu.naturaldyeing.R;
import mcl.jejunu.naturaldyeing.adapter.ColorAdapter;
import mcl.jejunu.naturaldyeing.model.Color;

public class ColorListActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Color> colors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_list);

        colors = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ColorAdapter(colors, this);
        recyclerView.setAdapter(adapter);

        new ColorListRequestTask().execute();
    }

    @Override
    public void onClick(View v) {
        Color color = (Color) v.getTag();
        Intent intent = new Intent(ColorListActivity.this, ColorActivity.class);
        intent.putExtra("colorId", color.getId());
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

    private class ColorListRequestTask extends AsyncTask<Void, Void, List<Color>> {

        @Override
        protected List<Color> doInBackground(Void... params) {
            try {
                final String url = getString(R.string.server_url) + "/select_color_list.php";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Color[] colors = restTemplate.getForObject(url, Color[].class);
                return Arrays.asList(colors);
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Color> results) {
            colors.addAll(results);
            adapter.notifyDataSetChanged();
        }
    }

}
