package proitdevelopers.com.bloomberg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TopNewsAdapter extends BaseAdapter {

    private List<TopNewsModel> topNewsModelArrayList = new ArrayList<>();

    private final Context context;

    public TopNewsAdapter(Context context) {
        this.context = context;
    }

    public void updateList(List<TopNewsModel> topNewsModelList) {
        this.topNewsModelArrayList.addAll(topNewsModelList);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return topNewsModelArrayList.size();
    }

    @Override
    public TopNewsModel getItem(int position) {
        return topNewsModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        final ImageView img;
        TextView topic;
        TextView title;



        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);

            img = (ImageView) convertView.findViewById(R.id.imageView);
            topic = (TextView) convertView.findViewById(R.id.textView);
            title = (TextView) convertView.findViewById(R.id.textView2);


            convertView.setTag(new ViewHolder(img, topic, title));
        } else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            img = viewHolder.img;
            topic = viewHolder.topic;
            title = viewHolder.title;

        }

        final TopNewsModel topNewsModel = getItem(position);

        topic.setText(topNewsModel.getTopic());
        title.setText(topNewsModel.getTitle());


        Picasso.with(context)
                .load(topNewsModel.getImg())
                .placeholder(R.drawable.trump_news)
                .into(img);





        return convertView;
    }

    private static class ViewHolder {


        private final ImageView img;
        private final TextView topic;
        private final TextView title;





        private ViewHolder(ImageView img,TextView topic,
                           TextView title) {

            this.img = img;
            this.topic = topic;
            this.title = title;

        }
    }
}
