package mcl.jejunu.naturaldyeing.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mcl.jejunu.naturaldyeing.R;
import mcl.jejunu.naturaldyeing.model.Color;
import mcl.jejunu.naturaldyeing.model.Resource;

/**
 * Created by Kim on 2016-08-20.
 */
public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    private List<Resource> resources;
    private List<Color> colors;
    private View.OnClickListener listener;
    private String keyword;

    public SearchResultAdapter(View.OnClickListener listener) {
        keyword = "";
        resources = new ArrayList<>();
        colors = new ArrayList<>();
        this.listener = listener;
    }

    public void replaceWith(List<Resource> resources, List<Color> colors, String keyword) {
        this.resources.clear();
        this.colors.clear();
        this.resources.addAll(resources);
        this.colors.addAll(colors);
        this.keyword = keyword;
        notifyDataSetChanged();
    }

    @Override
    public SearchResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_search_result, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.cardView.setOnClickListener(listener);
        if (position < resources.size()) {
            holder.cardView.setTag(resources.get(position));
            holder.typeText.setText("천연염색 자원");
            holder.nameText.setText(resources.get(position).getName());
            holder.scientificNameText.setText(resources.get(position).getScientificName());
        } else {
            int colorPosition = position - resources.size() ;
            holder.cardView.setTag(colors.get(colorPosition));
            holder.typeText.setText("색채");
            holder.nameText.setText(colors.get(colorPosition).getName());
        }
    }

    @Override
    public int getItemCount() {
        return resources.size() + colors.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public TextView typeText, nameText, scientificNameText;

        public ViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.card_view);
            typeText = (TextView) v.findViewById(R.id.type_text);
            nameText = (TextView) v.findViewById(R.id.name_text);
            scientificNameText = (TextView) v.findViewById(R.id.scientific_name_text);

        }
    }

}
