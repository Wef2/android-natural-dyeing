package mcl.jejunu.naturaldyeing.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import mcl.jejunu.naturaldyeing.R;
import mcl.jejunu.naturaldyeing.adapter.SearchResultAdapter;
import mcl.jejunu.naturaldyeing.model.Color;
import mcl.jejunu.naturaldyeing.model.Resource;

public class SearchResultsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, View.OnClickListener {

    private Realm realm;

    private RecyclerView recyclerView;
    private SearchResultAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        realm = Realm.getDefaultInstance();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SearchResultAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
        searchView.setIconifiedByDefault(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        RealmResults<Resource> resourceRealmResults = realm.where(Resource.class).contains("name", query).or().contains("scientificName", query, Case.INSENSITIVE).findAll();
        RealmResults<Color> colorRealmResults = realm.where(Color.class).contains("name", query, Case.INSENSITIVE).findAll();

        List<Resource> resources = realm.copyFromRealm(resourceRealmResults);
        List<Color> colors = realm.copyFromRealm(colorRealmResults);

        System.out.println(colors);

        adapter.replaceWith(resources, colors, query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onClick(View v) {

        if (v.getTag().getClass().equals(Resource.class)) {
            Resource resource = (Resource) v.getTag();
            Intent intent = new Intent(this, ResourceActivity.class);
            intent.putExtra("resourceId", resource.getId());
            startActivity(intent);
        } else {
            Color color = (Color) v.getTag();
            Intent intent = new Intent(this, ColorActivity.class);
            intent.putExtra("colorId", color.getId());
            startActivity(intent);
        }
    }
}
