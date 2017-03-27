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

import java.util.ArrayList;

import second.abdo.com.herag.Items.Cat_items;
import second.abdo.com.herag.Items.SubCategories_item;

/**
 * Created by AG on 3/7/2017.
 */

public class All_Herag_Fragment extends Fragment {

    ArrayList<String> mylist = new ArrayList<String>();
    public  static   ArrayList<SubCategories_item> SUBsub_arrayList;

    public  TabLayout tabLayout;
public  ViewPager mViewPager;



    public static All_Herag_Fragment newInstance(int position,String catid) {

        Log.i("sssssssss", String.valueOf(position));
        All_Herag_Fragment fragment = new All_Herag_Fragment();
        Bundle args = new Bundle();
        args.putInt("key", position);
        args.putString("keystring", catid);
        fragment.setArguments(args);

        return fragment;
    }

    public All_Herag_Fragment() {



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
                            MainActivity   app = new MainActivity();

                            app.hideTabLayout();
                            Bundle bundle = getArguments();

                            int myInt = bundle.getInt("key");
                            Log.i("ssssaawww", String.valueOf(myInt));


                        }else {

                            MainActivity   app = new MainActivity();

                            app.showTabLayout();
                        }

                        Log.i("numTab", String.valueOf(tab.getPosition()));

                    }
                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);


                    }


                });

    }
    public  void showTabLayout() {
        tabLayout.setVisibility(View.VISIBLE);

    }

    public  void hideTabLayout() {
        tabLayout.setVisibility(View.GONE);

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); Bundle bundle = getArguments();
        int myInt = bundle.getInt("key");
        Log.i("ssssaawww", String.valueOf(myInt));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();

        int myInt = bundle.getInt("key");


        View view = inflater.inflate(R.layout.fragment_tab2, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.container_main);
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager(),MainActivity.Cat_arrayList);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs2);


        tabLayout.setVisibility(View.GONE);

        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        if (tab.getPosition() != 0) {

                            Bundle bundle = getArguments();

                            int myInt = bundle.getInt("key");
                            Log.i("ssssaawww", String.valueOf(myInt));


                        }else {


                        }

                        Log.i("numTab", String.valueOf(tab.getPosition()));

                    }
                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);


                    }


                });
        if (myInt != 0 ){
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
        public    ArrayList<SubCategories_item> SUB_arrayList;
        public SectionsPagerAdapter(FragmentManager fm, ArrayList<Cat_items> Cat_items_result) {
            super(fm);
            Bundle bundle = getArguments();
            int myInt = bundle.getInt("key");

            if (MainActivity.Cat_arrayList != null) {
                if (myInt != 0) {
                    SUB_arrayList = new ArrayList<SubCategories_item>();
                    SUB_arrayList.addAll(MainActivity.Cat_arrayList.get(myInt).getSubCategories());
                    SUB_arrayList.add(0, new SubCategories_item("0", "الكل", "1", null));
                    Log.i("SUBsub_arrayListakey١", String.valueOf(myInt));


if (SUB_arrayList.get(myInt).getSubCategoriess() != null) {
    if (myInt == 1 || myInt == 3) {
        SUBsub_arrayList = new ArrayList<SubCategories_item>();
        for (int photosIndex = 0; photosIndex < SUB_arrayList.size(); photosIndex++) {
            String names = SUB_arrayList.get(photosIndex).getName();
            SUBsub_arrayList.add(SUB_arrayList.get(photosIndex));

        }
    }
}else {
    SUBsub_arrayList = new ArrayList<SubCategories_item>();
    SUBsub_arrayList.add(new SubCategories_item("تم","تم","تم",null));


}
                }

            }
        }



        @Override
        public Fragment getItem(int position) {
            Bundle bundle = getArguments();
            int myInt = bundle.getInt("key");
            String catid = bundle.getString("keystring");
            Log.i("catid", catid);
            Log.i("posi", String.valueOf(myInt));
            if (position == 0) {
                return ADS.newInstance(Integer.parseInt(catid));



            }
            if (myInt == 1 || myInt == 3) {
                return Nest2Fragment.newInstance(position,SUB_arrayList.get(position).getId());
            }else{

            return ADS.newInstance(Integer.parseInt(SUB_arrayList.get(position).getId()));


            }

        }

        @Override
        public int getCount() {
            // Show 4 total pages.

           Bundle bundle = getArguments();
if (SUB_arrayList != null){

    return SUB_arrayList.size();


}






else {
  return  0;

            }
        }

        @Override
        public CharSequence getPageTitle(int position) {

                                 Bundle bundle = getArguments();
                                 if (SUB_arrayList != null) {
                                                         int myInt = bundle.getInt("key");
                                                                            return SUB_arrayList.get(position).getName();

                                 }

                                                                  else {
                                                                             return  null;
                                 }





        }

    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////ر




    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
