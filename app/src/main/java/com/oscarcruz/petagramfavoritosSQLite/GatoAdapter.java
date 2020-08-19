package com.oscarcruz.petagramfavoritosSQLite;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GatoAdapter extends RecyclerView.Adapter<GatoAdapter.ViewHolder> {

    private ArrayList<GatoItem> gatoItems;
    private Context context;
    private FavDB favDB;

    public GatoAdapter(ArrayList<GatoItem> gatoItems, Context context) {
        this.gatoItems = gatoItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        favDB = new FavDB(context);
        //create table on first
        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if (firstStart) {
            createTableOnFirstStart();
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview,
                parent,false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull GatoAdapter.ViewHolder holder, int position) {
        final GatoItem gatoItem = gatoItems.get(position);

        readCursorData(gatoItem, holder);
        holder.imageView.setImageResource(gatoItem.getImageResourse());
        holder.titleTextView.setText(gatoItem.getTitle());
    }



    @Override
    public int getItemCount() {
        return gatoItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView titleTextView, likeCountTextView;
        Button favBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            favBtn = itemView.findViewById(R.id.favBtn);
            likeCountTextView = itemView.findViewById(R.id.likeCountTextView);

            //add to fav btn
            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    GatoItem gatoItem = gatoItems.get(position);

                    if (gatoItem.getFavStatus().equals("0")) {

                        gatoItem.setFavStatus("1");
                        favDB.insertIntoTheDatabase(gatoItem.getTitle(), gatoItem.getImageResourse(),
                                gatoItem.getKey_id(), gatoItem.getFavStatus());
                        favBtn.setBackgroundResource(R.drawable.asset_yellow_bone);
                    } else {
                        gatoItem.setFavStatus("0");
                        favDB.remove_fav(gatoItem.getKey_id());
                        favBtn.setBackgroundResource(R.drawable.asset_white_bone);
                    }
                }
            });

            }
        }



    private void createTableOnFirstStart() {
        favDB.insertEmpty();

        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    private void readCursorData(GatoItem gatoItem, ViewHolder viewHolder) {
        Cursor cursor = favDB.read_all_data(gatoItem.getKey_id());
        SQLiteDatabase db = favDB.getReadableDatabase();
        try {
            while (cursor.moveToNext()) {
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAVORITE_STATUS));
                gatoItem.setFavStatus(item_fav_status);

                //check fav status
                if (item_fav_status != null && item_fav_status.equals("1")) {
                    viewHolder.favBtn.setBackgroundResource(R.drawable.asset_yellow_bone);
                } else if (item_fav_status != null && item_fav_status.equals("0")) {
                    viewHolder.favBtn.setBackgroundResource(R.drawable.asset_white_bone);
                }
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }

    }


}
