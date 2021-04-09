package com.example.genericfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.bookmarkViewHolder> {
    ArrayList<BookmarkData> bookmarkData = new ArrayList<>();
    LayoutInflater mInflater;

    public BookmarkAdapter (Context context) {mInflater = LayoutInflater.from(context);}

    @NonNull
    @Override
    public BookmarkAdapter.bookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.list_bookmark, viewGroup, false);
        return new bookmarkViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkAdapter.bookmarkViewHolder holder, int position) {
        holder.onBind(bookmarkData.get(position));

        holder.bookmarkImg.setImageResource(bookmarkData.get(position).getBookmarkImg());
        holder.bookmarkName.setText(bookmarkData.get(position).getBookmarkName());
    }

    @Override
    public int getItemCount() {return bookmarkData.size();}

    void addItem(BookmarkData data) {bookmarkData.add(data);}

    class bookmarkViewHolder extends RecyclerView.ViewHolder {
        public final TextView bookmarkName;
        public final ImageView bookmarkImg;
        public final Button bm_priceBtn, bm_infoBtn;
        final BookmarkAdapter mAdapter;

        public bookmarkViewHolder(final View itemView, BookmarkAdapter adapter) {
            super(itemView);

            bookmarkName = itemView.findViewById(R.id.bookmarkName);
            bookmarkImg = itemView.findViewById(R.id.bookmarkImg);
            bm_priceBtn = itemView.findViewById(R.id.bm_priceBtn);
            bm_infoBtn = itemView.findViewById(R.id.bm_infoBtn);

            bm_priceBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                }
            });

            bm_infoBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                }
            });

            this.mAdapter = adapter;
        }

        void onBind(BookmarkData data) {
            bookmarkImg.setImageResource(data.getBookmarkImg());
            bookmarkName.setText(data.getBookmarkName());
        }
    }
}
