package second.abdo.com.herag;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.dision.android.rtlviewpager.RTLViewPager;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.IoniconsModule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import second.abdo.com.herag.Adapters.ImageAdapter;
import second.abdo.com.herag.Connect_TO_Server.Json_Controller;
import second.abdo.com.herag.Connect_TO_Server.VolleyCallback;
import second.abdo.com.herag.Items.Cat_items;
import second.abdo.com.herag.Items.Menu_Items;
import second.abdo.com.herag.Items.SUBSubCategories_item;
import second.abdo.com.herag.Items.SubCategories_item;

public class MainActivity extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private ArrayList<Menu_Items> menu_list;
    public static ArrayList<Cat_items> Cat_arrayList;
    public static ArrayList<SubCategories_item> SUB_arrayList;
    public static ArrayList<SUBSubCategories_item> SUBSub_arrayList;
    public  int pos;
    public Button button;

    public static TabLayout tabLayout;
    RTLViewPager vp;
private ImageView menupop;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Iconify
                .with(new FontAwesomeModule())

                .with(new IoniconsModule());
        trans();

        menupop = (ImageView) findViewById(R.id.menupop);
        menupop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(MainActivity.this, menupop);
                //Inflating the Popup using xml file
               

                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(
                                MainActivity.this,
                                "You Clicked : " + item.getTitle(),
                                Toast.LENGTH_SHORT
                        ).show();
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        menu_list = new ArrayList<Menu_Items>();

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        boolean appear_notif[] = {false, false, false, false, false, false, false, false};

        String menu_notif[] = {"", "", "", "", "", "", "", ""};
        String[] menu_titles = getResources().getStringArray(R.array.menu_titles);
        int[] menu_images = {};
        for (int i = 0; i < menu_titles.length; i++) {
            menu_list.add(new Menu_Items(menu_titles[i], menu_notif[i], appear_notif[i]));
        }
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(getApplicationContext()));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                ViewPager vp2=(ViewPager) findViewById(R.id.container_main);
                mViewPager.setCurrentItem(1);
vp2.setCurrentItem(position);
                Toast.makeText(MainActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();

                drawer.closeDrawers();
            }
        });

    }
    public  void showTabLayout() {
        tabLayout.setVisibility(View.VISIBLE);

    }

    public  void hideTabLayout() {
        tabLayout.setVisibility(View.GONE);

    }



    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(final int position) {


if (position == 5 || position == 8 || position == 0 ){

    return ADS.newInstance(Integer.parseInt(Cat_arrayList.get(position).getId()));

}

            return  All_Herag_Fragment.newInstance(position,Cat_arrayList.get(position).getId());


        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return Cat_arrayList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return Cat_arrayList.get(position).getName();
        }
    }


    private  void trans() {



        new Json_Controller().GetDataFromServer(new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                if (!result.equals("")) {
                    Cat_arrayList = new ArrayList<Cat_items>();
                    try {

                        JSONObject main_obj = new JSONObject(result);
                        JSONArray prices = main_obj.getJSONArray("data");

                       Cat_arrayList.add(0,new Cat_items("0","الكل","1",SUB_arrayList));

                        for (int index = 0 ;index< prices.length();index++){

                            JSONObject catgrey = prices.getJSONObject(index);
                            JSONObject catgreys = catgrey.getJSONObject("Categories");

                            Log.i("name2", String.valueOf(catgrey));
                           String catname = catgreys.getString("name");
                            String catid = catgreys.getString("id");
                            String catorder = catgreys.getString("order");





                                if (catgreys.has("SubCategories")) {

                                    JSONArray catsub = catgreys.getJSONArray("SubCategories");
                                    SUB_arrayList = new ArrayList<SubCategories_item>();

                                    for (int photosIndex = 0; photosIndex < catsub.length(); photosIndex++) {

                                        JSONObject dateObj = catsub.getJSONObject(photosIndex);
                                        String subname = dateObj.getString("name");
                                        String subid = dateObj.getString("id");
                                        String suborder = dateObj.getString("order");
                                        if (dateObj.has("SubCategories")) {
                                            JSONArray catsubsub = dateObj.getJSONArray("SubCategories");

                                            SUBSub_arrayList = new ArrayList<SUBSubCategories_item>();

                                            for (int photosIndexs = 0; photosIndexs < catsubsub.length(); photosIndexs++) {

                                                JSONObject dateObjs = catsubsub.getJSONObject(photosIndexs);
                                                String subnames = dateObjs.getString("name");
                                                String subids = dateObjs.getString("id");
                                                String suborders = dateObjs.getString("order");
                                                SUBSub_arrayList.add(new SUBSubCategories_item(subids, subnames, suborders));


                                            }
                                        }

                                        SUB_arrayList.add(new SubCategories_item(subid, subname, suborder,SUBSub_arrayList));
                                    }
                                }



                                Cat_arrayList.add(new Cat_items(catid, catname, catorder, SUB_arrayList));

                        }

                        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
                        mViewPager = (ViewPager) findViewById(R.id.container);
                        mViewPager.setAdapter(mSectionsPagerAdapter);

//                       mViewPager.setCurrentItem(Cat_arrayList.size()-1, false);
              //          mViewPager.setAllowedSwipeDirection(SwipeDirection.left);

                        tabLayout = (TabLayout) findViewById(R.id.tabs);
                        tabLayout.setupWithViewPager(mViewPager);
                        tabLayout.setOnTabSelectedListener(
                                new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {
                                    @Override
                                    public void onTabSelected(TabLayout.Tab tab) {
                                        super.onTabSelected(tab);
//

                                    }
                                });
//                   Log.i("Tag",String.valueOf(Integer.parseInt(ratearray.getString("Bid")) * Integer.parseInt(nums)));
                        if (1 != 0) {
//                            for (int i = 0; i < ratearray.length(); i++)
//                            {
//                                JSONObject Jasonobject = ratearray.getJSONObject(i);
//
//
//                                Log.d("Tag", Jasonobject.toString());
//                            }
//                            for (int index = 0; index < jArray.length(); index++) {
//                                JSONObject currentObj = jArray.getJSONObject(index);
//                                JSONArray datesArray = currentObj.getJSONArray("results");
//
//
////                                String city_name = currentObj.getString("Rate");
////                                String city_id = "" + currentObj.getInt("id");
////                                get_cityID_map.put(city_name, city_id);
////                                city_array.add(city_name);
//                            }
                        } else {
                            Toast.makeText(MainActivity.this, "no Currency found!!", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Error while connecting to server event !!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Error while connecting to server event !!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(MainActivity.this, "" + error, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "Error while connecting to server event !!", Toast.LENGTH_SHORT).show();
            }
        }, MainActivity.this, "http://tojr.net/api/v1/cat/json", false);
        // SelectedSpinnerItem(city_array, city_spinner);,%22\"+Cur+\"AUD%22,%22\"


    }
}
