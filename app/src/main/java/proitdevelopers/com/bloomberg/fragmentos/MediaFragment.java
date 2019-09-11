package proitdevelopers.com.bloomberg.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.TextView;
import proitdevelopers.com.bloomberg.R;
import proitdevelopers.com.bloomberg.activitys.PesquisarNoticiaActivity;


public class MediaFragment extends Fragment {

    private int someStateValue;
    private final String SOME_VALUE_KEY = "someValueToSave";

    private Toolbar toolbar;
    private TextView mTitle;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private View v;

    public MediaFragment() {
        // Required empty public constructor
    }


    // Fires when a configuration change occurs and fragment needs to save state

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(SOME_VALUE_KEY, someStateValue);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_media, container, false);

        if (savedInstanceState != null) {
            someStateValue = savedInstanceState.getInt(SOME_VALUE_KEY);
            // Do something with value if needed
        }
        toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);


        if (getActivity()!=null){
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        }

        // Set up the ViewPager with the sections adapter.
        viewPager = (ViewPager) v.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Video"));
        tabLayout.addTab(tabLayout.newTab().setText("Audio"));

        viewPager.setAdapter(new PagerAdapter(getFragmentManager(), tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        inflater.inflate(R.menu.mediafrag_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_pesquisar:
                Intent intentPesquisaNoticia = new Intent(getContext(), PesquisarNoticiaActivity.class);
                startActivity(intentPesquisaNoticia);
                break;

        }
        return true;

    }




    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }


        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new VideoFragment();
                case 1:
                    return new AudioFragment();

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Video";
                case 1:
                    return "Audio";

            }
            return null;
        }
    }
}
