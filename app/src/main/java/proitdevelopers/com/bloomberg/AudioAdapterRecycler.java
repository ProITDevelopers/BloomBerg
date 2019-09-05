package proitdevelopers.com.bloomberg;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AudioAdapterRecycler extends RecyclerView.Adapter<AudioAdapterRecycler.MyViewHolder> {

    private Context mContext;
    private List<AudioModel> albumList;
    private ItemClickListener clickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;



        TextView time;


        public MyViewHolder(View view) {
            super(view);


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

    }



    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

}
