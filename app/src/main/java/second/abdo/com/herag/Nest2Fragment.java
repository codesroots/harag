package second.abdo.com.herag;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import second.abdo.com.herag.Items.Cat_items;
import second.abdo.com.herag.Items.SUBSubCategories_item;


/**
 * A simple {@link Fragment} subclass.
 */
public class Nest2Fragment extends Fragment {
    public TabLayout tabLayout;
    public ViewPager mViewPager;

    public static Nest2Fragment newInstance(int position,String subcat) {
        Nest2Fragment fragment = new Nest2Fragment();
        Bundle args = new Bundle();
        args.putInt("key3", position);
        args.putString("key3string", subcat);
        fragment.setArguments(args);

        return fragment;
    }

    public Nest2Fragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onResume() {
        super.onResume();

        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        if (tab.getPosition() != 0) {
                            TabLayout vp=(TabLayout) getActivity().findViewById(R.id.tabs2);

                          vp.setVisibility(View.GONE);
                            Bundle bundle = getArguments();




                        }else {

                            TabLayout vp=(TabLayout) getActivity().findViewById(R.id.tabs2);

                            vp.setVisibility(View.VISIBLE);
                        }


                    }
                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);


                    }


                });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.container_maintab3);
    Nest2Fragment.SectionsPagerAdapter mSectionsPagerAdapter = new Nest2Fragment.SectionsPagerAdapter(getChildFragmentManager());
       mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs3);


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
                        SUB_arrayListt.add(0, new SUBSubCategories_item("0", "الكل", "1"));

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
//            Log.i("id", String.valueOf(args.getInt("key5")));
            String catid = bundle.getString("key3string");
            Log.i("catid", catid);
            Log.i("posi", String.valueOf(myInt));
            if (position == 0) {
                return ADS.newInstance(Integer.parseInt(catid));



            }else {
                return ADS.newInstance(Integer.parseInt(SUB_arrayListt.get(position).getId()));
            }

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
