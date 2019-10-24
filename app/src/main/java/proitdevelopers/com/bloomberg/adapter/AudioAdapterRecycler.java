package proitdevelopers.com.bloomberg.adapter;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import proitdevelopers.com.bloomberg.modelo.AudioModel;
import proitdevelopers.com.bloomberg.interfaces.ItemClickListener;
import proitdevelopers.com.bloomberg.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class AudioAdapterRecycler extends RecyclerView.Adapter<AudioAdapterRecycler.MyViewHolder> {

    private Context mContext;
    private List<AudioModel> albumList;
    private ItemClickListener clickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;



        TextView time,textView11;


        public MyViewHolder(View view) {
            super(view);


            title = (TextView) view.findViewById(R.id.textView20);
            time = (TextView) view.findViewById(R.id.textView21);
            textView11 = (TextView) view.findViewById(R.id.textView11);

            itemView.setTag(itemView);
            itemView.setOnClickListener(this);



        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }


    public AudioAdapterRecycler(Context mContext, List<AudioModel> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_media_audio, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        AudioModel audioModel = albumList.get(position);



        holder.time.setText(audioModel.getTime());
        holder.title.setText(audioModel.getTitle());


//        Uri uri = Uri.parse(audioModel.getFileLink());
//        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
//        mmr.setDataSource(uri.getPath());
//
//
////        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
////        mmr.setDataSource(mediaPath);
//        String duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
//        mmr.release();
//
//        long dur = Long.parseLong(duration);
//        String minutos = String.valueOf(dur / 60000);
//        String seconds = String.valueOf((dur % 60000) / 1000);
//        String out = minutos + ":"+seconds;
//        holder.textView11.setText(out);





    }



    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

}
