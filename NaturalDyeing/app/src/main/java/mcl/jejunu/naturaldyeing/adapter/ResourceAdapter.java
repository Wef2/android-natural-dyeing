package mcl.jejunu.naturaldyeing.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.RealmResults;
import mcl.jejunu.naturaldyeing.R;
import mcl.jejunu.naturaldyeing.model.Resource;

/**
 * Created by neo-202 on 2016-08-11.
 */
public class ResourceAdapter extends RecyclerView.Adapter<ResourceAdapter.ViewHolder> {

    private RealmResults<Resource> resources;

    public ResourceAdapter(RealmResults<Resource> resources) {
        this.resources = resources;
    }

    @Override
    public ResourceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_resource, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.cardView.setTag(resources.get(position));
        holder.nameText.setText(resources.get(position).getName());
        holder.scientificNameText.setText(resources.get(position).getScientificName());
    }

    @Override
    public int getItemCount() {
        return resources.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public TextView nameText;
        public TextView scientificNameText;

        public ViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.card_view);
            nameText = (TextView) v.findViewById(R.id.name_text);
            scientificNameText = (TextView) v.findViewById(R.id.scientific_name_text);
        }
    }

}