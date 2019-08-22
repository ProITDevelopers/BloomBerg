package proitdevelopers.com.bloomberg;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopNewsFragment extends Fragment {

    TopNewsModel topNewsModel;

    List<TopNewsModel> newsModelList;

    ListView listView;

    TopNewsAdapter topNewsAdapter;
    ImageView img;
    TextView topic,title;

    int position;


    View v;


    public TopNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_top_news, container, false);


        img = (ImageView) v.findViewById(R.id.imageView);
        topic = (TextView) v.findViewById(R.id.textView);
        title = (TextView) v.findViewById(R.id.textView2);
        listView = (ListView) v.findViewById(R.id.listviewNews);
        loadNews();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                topNewsModel = newsModelList.get(position);

                Toast.makeText(getContext(),
                        "" + topNewsModel.getTitle(),
                        Toast.LENGTH_SHORT).show();


                setRandomNews(position);

            }
        });

//        setRandomNews(newsModelList);

        return v;
    }

    private void loadNews() {
        newsModelList = new ArrayList<>();
        newsModelList.add(new TopNewsModel("https://imageresizer.static9.net.au/Vx5YraVfKfTpSba-3ypxkfTTr5s=/360x202/smart/http%3A%2F%2Fprod.static9.net.au%2Ffs%2Fb250d1ac-d28d-4216-a46c-63ff89f7fb2f","POLITICS","Sydney News - Latest breaking local news today"));
        newsModelList.add(new TopNewsModel("https://cdn-01.independent.ie/incoming/article38422134.ece/f34a9/AUTOCROP/w620h342/AFP_1JN32V.jpg","POLITICS","World News - International Headlines"));
        newsModelList.add(new TopNewsModel("https://media2.s-nbcnews.com/j/newscms/2019_29/2935996/190716-epoch-times-main-kh_c9ef1f3d4197658c3e8a791c408687d6.focal-758x379.jpg","POLITICS","World News | NBC News"));
        newsModelList.add(new TopNewsModel("https://e3.365dm.com/19/03/768x432/skynews-george-pell-pell-vatican_4606428.jpg?20190312235327","POLITICS","World News - Breaking international Sky News"));

        newsModelList.add(new TopNewsModel("https://cdn-01.independent.ie/incoming/article38422134.ece/f34a9/AUTOCROP/w620h342/AFP_1JN32V.jpg","MARKETS","World News - International Headlines"));
        newsModelList.add(new TopNewsModel("https://imageresizer.static9.net.au/Vx5YraVfKfTpSba-3ypxkfTTr5s=/360x202/smart/http%3A%2F%2Fprod.static9.net.au%2Ffs%2Fb250d1ac-d28d-4216-a46c-63ff89f7fb2f","MARKETS","Sydney News - Latest breaking local news today"));
        newsModelList.add(new TopNewsModel("https://media2.s-nbcnews.com/j/newscms/2019_29/2935996/190716-epoch-times-main-kh_c9ef1f3d4197658c3e8a791c408687d6.focal-758x379.jpg","MARKETS","World News | NBC News"));
        newsModelList.add(new TopNewsModel("https://e3.365dm.com/19/03/768x432/skynews-george-pell-pell-vatican_4606428.jpg?20190312235327","MARKETS","World News - Breaking international Sky News"));

        newsModelList.add(new TopNewsModel("https://media2.s-nbcnews.com/j/newscms/2019_29/2935996/190716-epoch-times-main-kh_c9ef1f3d4197658c3e8a791c408687d6.focal-758x379.jpg","MOVIES","World News | NBC News"));
        newsModelList.add(new TopNewsModel("https://imageresizer.static9.net.au/Vx5YraVfKfTpSba-3ypxkfTTr5s=/360x202/smart/http%3A%2F%2Fprod.static9.net.au%2Ffs%2Fb250d1ac-d28d-4216-a46c-63ff89f7fb2f","MOVIES","Sydney News - Latest breaking local news today"));
        newsModelList.add(new TopNewsModel("https://cdn-01.independent.ie/incoming/article38422134.ece/f34a9/AUTOCROP/w620h342/AFP_1JN32V.jpg","MOVIES","World News - International Headlines"));
        newsModelList.add(new TopNewsModel("https://e3.365dm.com/19/03/768x432/skynews-george-pell-pell-vatican_4606428.jpg?20190312235327","MOVIES","World News - Breaking international Sky News"));

        setAdapters(newsModelList);

    }

    private void setAdapters(final List<TopNewsModel> newsModelList) {
        topNewsAdapter = new TopNewsAdapter(getContext());
        topNewsAdapter.updateList(newsModelList);
        listView.setAdapter(topNewsAdapter);


        Random rand = new Random();
        position = rand.nextInt(newsModelList.size()-1);
        setRandomNews(position);
    }


    private void setRandomNews(int position){



        Picasso.with(getContext())
                .load(newsModelList.get(position).getImg())
                .placeholder(R.drawable.trump_news)
                .into(img);

        topic.setText(newsModelList.get(position).getTopic());
        title.setText(newsModelList.get(position).getTitle());

        topNewsAdapter.notifyDataSetChanged();


    }

}
