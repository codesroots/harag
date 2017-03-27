package second.abdo.com.herag;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import second.abdo.com.herag.Items.Cat_items;
import second.abdo.com.herag.Items.SUBSubCategories_item;


/**
 * A simple {@link Fragment} subclass.
 */
public class Ads_test extends Fragment {
    public TabLayout tabLayout;
    public ViewPager mViewPager;

    public static Nest2Fragment newInstance(int position) {
        Nest2Fragment fragment = new Nest2Fragment();
        Bundle args = new Bundle();
        args.putInt("key3", position);
        fragment.setArguments(args);

        return fragment;
    }

    public Ads_test() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.container_maintab3);
        Ads_test.SectionsPagerAdapter mSectionsPagerAdapter = new Ads_test.SectionsPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
//        tabLayout = (TabLayout) view.findViewById(R.id.tabs3);
        mViewPager.setOffscreenPageLimit(12);


        Bundle bundle = getArguments();

        int myInt = bundle.getInt("key3");
        if (myInt != 0) {
            tabLayout.setVisibility(View.VISIBLE);


        }
        return view;


    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Cat_items> chalet_list;
        private Context context;
        private ArrayList<Cat_items> arraylist;
        private LayoutInflater mInflater;
        private ViewPager mViewPager;
        public    ArrayList<SUBSubCategories_item> SUB_arrayListt;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);


            if (All_Herag_Fragment.SUBsub_arrayList != null) {

                Bundle bundle = getArguments();

                int myInt = bundle.getInt("key3");
                int myInt2 = bundle.getInt("key");

                SUB_arrayListt = new ArrayList<SUBSubCategories_item>();

                if (myInt != 0) {
                    if (All_Herag_Fragment.SUBsub_arrayList.get(myInt).getSubCategoriess().size() != 0) {
                        for (int photosIndex = 0; photosIndex < All_Herag_Fragment.SUBsub_arrayList.get(myInt).getSubCategoriess().size(); photosIndex++) {


                            String names = All_Herag_Fragment.SUBsub_arrayList.get(myInt).getSubCategoriess().get(photosIndex).getName();
                            SUB_arrayListt.add(All_Herag_Fragment.SUBsub_arrayList.get(myInt).getSubCategoriess().get(photosIndex));

                        }
                    }else{
                        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();

                    }
                }


            }
        }


        @Override
        public Fragment getItem(int position) {
            Bundle bundle = getArguments();

            int myInt = bundle.getInt("key3");

            return ADS.newInstance(myInt);


        }

        @Override
        public int getCount() {
            // Show 4 total pages.

            if (All_Herag_Fragment.SUBsub_arrayList != null) {

                Bundle bundle = getArguments();  int myInt = bundle.getInt("key3");
                int myInt2 = bundle.getInt("key");

                return SUB_arrayListt.size();


            }


            else {
                return  0;

            }
        }

        @Override
        public CharSequence getPageTitle(int position) {

            Bundle bundle = getArguments();
            if (All_Herag_Fragment.SUBsub_arrayList != null) {
                int myInt = bundle.getInt("key3");
                int myInt2 = bundle.getInt("key");


                return SUB_arrayListt.get(position).getName();
            }


            else {
                return  null;
            }





        }

    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////ر




    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
