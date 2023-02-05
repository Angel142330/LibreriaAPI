package com.example.libreriaapi;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.libreriaapi.Data.Volume;
import java.util.ArrayList;
import java.util.List;

public class BookSearchResultsAdapter extends RecyclerView.Adapter<BookSearchResultsAdapter.BookSearchResultHolder> {
    private List<Volume> results = new ArrayList<>();


    public interface ItemClickListener {
        void onClick(View v, Volume volume);
    }

    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @NonNull
    @Override
    public BookSearchResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);

        return new BookSearchResultHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookSearchResultHolder holder, int position) {
        Volume volume = results.get(position);

        holder.titleTextView.setText(volume.getVolumeInfo().getTitle());
        holder.publishedDateTextView.setText(volume.getVolumeInfo().getPublishedDate());

        if (volume.getVolumeInfo().getImageLinks() != null) {
            String imageUrl = volume.getVolumeInfo().getImageLinks().getSmallThumbnail()
                    .replace("http://", "https://");

            Glide.with(holder.itemView)
                    .load(imageUrl)
                    .into(holder.smallThumbnailImageView);

        }

        if (volume.getVolumeInfo().getAuthors() != null) {
            String autores = "";
            for (String a :volume.getVolumeInfo().getAuthors() ){
                autores += a + ", ";
            }
            holder.authorsTextView.setText(autores);
        }

    }


    @Override
    public int getItemCount() {
        return results.size();
    }

    //notificar al recycler view de que hay nueva info
    public void setResults(List<Volume> results) {
        this.results.addAll(results);
        notifyDataSetChanged();
    }

    class BookSearchResultHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        private TextView titleTextView;
        private TextView authorsTextView;
        private TextView publishedDateTextView;
        private ImageView smallThumbnailImageView;

        @Override
        public void onClick(View v) {

            if (clickListener != null){
                clickListener.onClick(v, results.get(getAdapterPosition()));
            }

            Volume volume = results.get(getAdapterPosition());
            Intent intent = new Intent(v.getContext(), Descripcion.class);
            intent.putExtra("datos", volume.getId());
            v.getContext().startActivity(intent);
        }
        public BookSearchResultHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.book_item_title);
            authorsTextView = itemView.findViewById(R.id.book_item_authors);
            publishedDateTextView = itemView.findViewById(R.id.book_item_publishedDate);
            smallThumbnailImageView = itemView.findViewById(R.id.book_item_smallThumbnail);
            itemView.setOnClickListener(this);

        }

    }
}