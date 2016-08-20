package mcl.jejunu.naturaldyeing.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import io.realm.Realm;
import io.realm.RealmResults;
import mcl.jejunu.naturaldyeing.R;
import mcl.jejunu.naturaldyeing.adapter.ColorAdapter;
import mcl.jejunu.naturaldyeing.model.Color;

public class ColorListActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private RealmResults<Color> colors;

    private Realm realm;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_list);

        realm = Realm.getDefaultInstance();
        colors = realm.where(Color.class).findAll();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ColorAdapter(colors, this);
        recyclerView.setAdapter(adapter);
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

}
