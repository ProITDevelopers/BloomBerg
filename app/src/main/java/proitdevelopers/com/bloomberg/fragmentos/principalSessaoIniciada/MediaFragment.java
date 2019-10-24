package proitdevelopers.com.bloomberg.fragmentos.principalSessaoIniciada;

import android.os.Bundle;
import android.view.*;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import proitdevelopers.com.bloomberg.R;


public class MediaFragment extends Fragment {

    private int someStateValue;
    private final String SOME_VALUE_KEY = "someValueToSave";

    private Toolbar toolbar;
    private TextView mTitle;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private View v;

    RelativeLayout relativeLayout;

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
//        viewPager = (ViewPager) v.findViewById(R.id.viewPager);
//        relativeLayout = (RelativeLayout) v.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Video"),0);
        tabLayout.addTab(tabLayout.newTab().setText("Audio"));

//        viewPager.setAdapter(new PagerAdapter(getFragmentManager(), tabLayout.getTabCount()));
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());

               setCurrentTabFragment(tab.getPosition());

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
                /*Intent intentPesquisaNoticia = new Intent(getContext(), PesquisarNoticiaActivity.class);
                startActivity(intentPesquisaNoticia);*/
                break;

        }
        return true;

    }

    private void setCurrentTabFragment(int tabPosition)
    {
        switch (tabPosition)
        {
            case 0 :
                replaceFragment(new VideoFragment());
                break;
            case 1 :
                replaceFragment(new AudioFragment());
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.where_the_tab_contents_go, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
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
