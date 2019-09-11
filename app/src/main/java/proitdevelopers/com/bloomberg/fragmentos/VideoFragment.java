package proitdevelopers.com.bloomberg.fragmentos;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.squareup.picasso.Picasso;
import proitdevelopers.com.bloomberg.audio_video.MediaPlayerActivity;
import proitdevelopers.com.bloomberg.interfaces.ItemClickListener;
import proitdevelopers.com.bloomberg.R;
import proitdevelopers.com.bloomberg.modelo.VideoModel;
import proitdevelopers.com.bloomberg.adapter.VideoAdapterRecycler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {

    String videoUri="https://www.radiantmediaplayer.com/media/bbb-360p.mp4";

    VideoModel videoModel;


    List<VideoModel> videoModelList;

    ListView listView;
    RecyclerView recyclerView;

    VideoAdapterRecycler adapter;
    ImageView img;
    TextView title;

    int position;

    private View v;

    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         v = inflater.inflate(R.layout.fragment_videos, container, false);

        img = (ImageView) v.findViewById(R.id.imageView21);
        title = (TextView) v.findViewById(R.id.destaque_noticia_tv);
        recyclerView = v.findViewById(R.id.recyclerTendencias);
        loadNews();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);



        return v;
    }

    private void loadNews() {
        videoModelList = new ArrayList<>();
        videoModelList.add(new VideoModel("https://imageresizer.static9.net.au/Vx5YraVfKfTpSba-3ypxkfTTr5s=/360x202/smart/http%3A%2F%2Fprod.static9.net.au%2Ffs%2Fb250d1ac-d28d-4216-a46c-63ff89f7fb2f","August 29","Sydney News - Latest breaking local news today",videoUri));
        videoModelList.add(new VideoModel("https://cdn-01.independent.ie/incoming/article38422134.ece/f34a9/AUTOCROP/w620h342/AFP_1JN32V.jpg","August 29","World News - International Headlines",videoUri));
        videoModelList.add(new VideoModel("https://media2.s-nbcnews.com/j/newscms/2019_29/2935996/190716-epoch-times-main-kh_c9ef1f3d4197658c3e8a791c408687d6.focal-758x379.jpg","August 29","World News | NBC News",videoUri));
        videoModelList.add(new VideoModel("https://e3.365dm.com/19/03/768x432/skynews-george-pell-pell-vatican_4606428.jpg?20190312235327","August 29","World News - Breaking international Sky News",videoUri));

        videoModelList.add(new VideoModel("https://cdn-01.independent.ie/incoming/article38422134.ece/f34a9/AUTOCROP/w620h342/AFP_1JN32V.jpg","August 28","World News - International Headlines",videoUri));
        videoModelList.add(new VideoModel("https://imageresizer.static9.net.au/Vx5YraVfKfTpSba-3ypxkfTTr5s=/360x202/smart/http%3A%2F%2Fprod.static9.net.au%2Ffs%2Fb250d1ac-d28d-4216-a46c-63ff89f7fb2f","August 28","Sydney News - Latest breaking local news today",videoUri));
        videoModelList.add(new VideoModel("https://media2.s-nbcnews.com/j/newscms/2019_29/2935996/190716-epoch-times-main-kh_c9ef1f3d4197658c3e8a791c408687d6.focal-758x379.jpg","August 28","World News | NBC News",videoUri));
        videoModelList.add(new VideoModel("https://e3.365dm.com/19/03/768x432/skynews-george-pell-pell-vatican_4606428.jpg?20190312235327","August 28","World News - Breaking international Sky News",videoUri));

        videoModelList.add(new VideoModel("https://media2.s-nbcnews.com/j/newscms/2019_29/2935996/190716-epoch-times-main-kh_c9ef1f3d4197658c3e8a791c408687d6.focal-758x379.jpg","August 26","World News | NBC News",videoUri));
        videoModelList.add(new VideoModel("https://imageresizer.static9.net.au/Vx5YraVfKfTpSba-3ypxkfTTr5s=/360x202/smart/http%3A%2F%2Fprod.static9.net.au%2Ffs%2Fb250d1ac-d28d-4216-a46c-63ff89f7fb2f","August 25","Sydney News - Latest breaking local news today",videoUri));
        videoModelList.add(new VideoModel("https://cdn-01.independent.ie/incoming/article38422134.ece/f34a9/AUTOCROP/w620h342/AFP_1JN32V.jpg","August 24","World News - International Headlines",videoUri));
        videoModelList.add(new VideoModel("https://e3.365dm.com/19/03/768x432/skynews-george-pell-pell-vatican_4606428.jpg?20190312235327","August 23","World News - Breaking international Sky News",videoUri));


        setVideoAdapter(videoModelList);

    }


    private void setVideoAdapter(List<VideoModel> videoModelList){
        adapter = new VideoAdapterRecycler(getContext(), videoModelList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        Random rand = new Random();
        position = rand.nextInt(videoModelList.size()-1);
        setRandomNews(position);

        adapter.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                setRandomNews(position);
            }
        });
    }

    private void setRandomNews(int position){



        Picasso.with(getContext())
                .load(videoModelList.get(position).getImg())
                .placeholder(R.drawable.trump_news)
                .into(img);


        title.setText(videoModelList.get(position).getTitle());





    }


}
