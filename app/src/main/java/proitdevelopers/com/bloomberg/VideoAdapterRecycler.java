package proitdevelopers.com.bloomberg;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoAdapterRecycler extends RecyclerView.Adapter<VideoAdapterRecycler.MyViewHolder> {

    private Context mContext;
    private List<VideoModel> albumList;
    private ItemClickListener clickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;


        final ImageView img;
        TextView time;


        public MyViewHolder(View view) {
            super(view);

//            img = (ImageView) view.findViewById(R.id.imageView);
//            title = (TextView) view.findViewById(R.id.textView2);
//            time = (TextView) view.findViewById(R.id.textView6);
            img = (ImageView) view.findViewById(R.id.imageView23);
            title = (TextView) view.findViewById(R.id.textView20);
            time = (TextView) view.findViewById(R.id.textView21);

            itemView.setTag(itemView);
            itemView.setOnClickListener(this);



        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }


    public VideoAdapterRecycler(Context mContext, List<VideoModel> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_media_video, parent, false);
//                .inflate(R.layout.item_media, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        VideoModel videoModel = albumList.get(position);



        holder.time.setText(videoModel.getTime());
        holder.title.setText(videoModel.getTitle());


        Picasso.with(mContext)
                .load(videoModel.getImg())
                .placeholder(R.drawable.trump_news)
                .into(holder.img);

    }



    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

}
