package proitdevelopers.com.bloomberg;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class AudioFragment extends Fragment {



    List<AudioModel> audioModelList;


    RecyclerView recyclerView;

    AudioAdapterRecycler adapter;

    TextView title;

    int position;

    private View v;

    public AudioFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_audio, container, false);


        title = (TextView) v.findViewById(R.id.destaque_noticia_tv);
        recyclerView = v.findViewById(R.id.recyclerMercado);
        loadNews();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        return v;
    }

    private void loadNews() {
        audioModelList = new ArrayList<>();
        audioModelList.add(new AudioModel("August 29","Sydney News - Latest breaking local news today"));
        audioModelList.add(new AudioModel("August 29","World News - International Headlines"));
        audioModelList.add(new AudioModel("August 29","World News | NBC News"));
        audioModelList.add(new AudioModel("August 29","World News - Breaking international Sky News"));

        audioModelList.add(new AudioModel("August 28","World News - International Headlines"));
        audioModelList.add(new AudioModel("August 28","Sydney News - Latest breaking local news today"));
        audioModelList.add(new AudioModel("August 28","World News | NBC News"));
        audioModelList.add(new AudioModel("August 28","World News - Breaking international Sky News"));

        audioModelList.add(new AudioModel("August 26","World News | NBC News"));
        audioModelList.add(new AudioModel("August 25","Sydney News - Latest breaking local news today"));
        audioModelList.add(new AudioModel("August 24","World News - International Headlines"));
        audioModelList.add(new AudioModel("August 23","World News - Breaking international Sky News"));


        setVideoAdapter(audioModelList);

    }


    private void setVideoAdapter(List<AudioModel> audioModelList){
        adapter = new AudioAdapterRecycler(getContext(), audioModelList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        Random rand = new Random();
        position = rand.nextInt(audioModelList.size()-1);
        setRandomNews(position);

        adapter.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                setRandomNews(position);
            }
        });
    }

    private void setRandomNews(int position){




        title.setText(audioModelList.get(position).getTitle());



    }


}
