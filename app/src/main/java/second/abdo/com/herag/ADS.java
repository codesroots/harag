package second.abdo.com.herag;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import second.abdo.com.herag.Adapters.Ads_adapters;
import second.abdo.com.herag.Connect_TO_Server.Json_Controller;
import second.abdo.com.herag.Connect_TO_Server.VolleyCallback;
import second.abdo.com.herag.Items.Ads_items;
import second.abdo.com.herag.Items.User_items;


/**
 * A simple {@link Fragment} subclass.
 */
public class ADS extends Fragment{

    private ListView ads_listView;
    public static Ads_adapters adapter ;
    public static ArrayList<Ads_items> ads_arrayList;
    public static ArrayList<User_items> users_arrayList;
    public Button button;

        public static ADS newInstance(int position) {
            ADS fragment = new ADS();
            Bundle args = new Bundle();
            args.putInt("key5", position);
            fragment.setArguments(args);

            return fragment;
        }

        public ADS() {

        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);



        }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
        public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.ads_list_layout, container, false);
        View layout1 = rootView.findViewById(R.id.ads_listView);
//        View layout2 = rootView.findViewById(R.id.ads_listView2);
            ads_listView = (ListView) rootView.findViewById(R.id.ads_listView);
//        button = (Button) rootView.findViewById(R.id.button_list);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                ads_listView = (ListView) rootView.findViewById(R.id.ads_listView);
//                        }
//        });

            Bundle bundle = getArguments();
            Log.i("id", String.valueOf(bundle.getInt("key5")));
            trans(String.valueOf(bundle.getInt("key5")));
            ads_listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                                                 private int currentVisibleItemCount;
                                                 private int currentScrollState;
                                                 private int currentFirstVisibleItem;
                                                 private int totalItem;
                                                 private LinearLayout lBelow;


                                                 @Override
                                                 public void onScrollStateChanged(AbsListView view, int scrollState) {
                                                     // TODO Auto-generated method stub
                                                     this.currentScrollState = scrollState;



                                                         this.isScrollCompleted();
                                                 }
                private int mLastFirstVisibleItem;
                                                 @Override
                                                 public void onScroll(AbsListView view, int firstVisibleItem,
                                                                      int visibleItemCount, int totalItemCount) {
                                                     // TODO Auto-generated method stub
                                                     this.currentFirstVisibleItem = firstVisibleItem;
                                                     this.currentVisibleItemCount = visibleItemCount;
                                                     this.totalItem = totalItemCount;
int num = 0 ;
                                                     TabLayout vp=(TabLayout) getActivity().findViewById(R.id.tabs);
                                                     TabLayout vp2=(TabLayout) getActivity().findViewById(R.id.tabs2);
                                                     TabLayout vp3=(TabLayout) getActivity().findViewById(R.id.tabs3);

                                                         if(mLastFirstVisibleItem<firstVisibleItem)
                                                         {
                                                             if(vp.isShown())
                                                             {
                                                                 vp.setVisibility(View.GONE);
                                                                 vp2.setVisibility(View.GONE);
                                                             }
                                                             if(vp3.isShown())
                                                             {
                                                                 num = 1;
//                                                                 vp3.setVisibility(View.GONE);
                                                                 vp.setVisibility(View.GONE);
                                                                 vp2.setVisibility(View.GONE);
                                                             }
                                                             Log.i("up", "up");
                                                         }
                                                         if(mLastFirstVisibleItem>firstVisibleItem)
                                                         {
                                                             if(!vp2.isShown())
                                                             {
                                                                 vp.setVisibility(View.VISIBLE);

                                                                 vp2.setVisibility(View.VISIBLE);

                                                             }

                                                             if(vp3.isShown() && vp.isShown())
                                                             {

                                                                vp.setVisibility(View.GONE);
                                                                 vp3.setVisibility(View.VISIBLE);

                                                             }else{
                                                                 vp.setVisibility(View.VISIBLE);


                                                             }
                                                             Bundle bundle = getArguments();
                                                         }
                                                         mLastFirstVisibleItem=firstVisibleItem;



                                                 }

                                                 private void isScrollCompleted() {
                                                     if (totalItem - currentFirstVisibleItem == currentVisibleItemCount
                                                             && this.currentScrollState == SCROLL_STATE_IDLE) {
                                                         /** To do code here*/


                                                     }
                                                 }

                                                 ;
                                             });
            ads_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
//                    intent.putExtra("ads_id", ads_arrayList.get(position).getId());


                    Bundle bundle = getArguments();
                    intent.putExtra("chalet_address", String.valueOf(bundle.getInt("key5")));

                    startActivity(intent);
                }
            });
            return rootView;
        }


    private  void trans(String id) {



        new Json_Controller().GetDataFromServer(new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                if (!result.equals("")) {
                    ads_arrayList = new ArrayList<Ads_items>();
                    try {

                        JSONObject main_obj = new JSONObject(result);
                        JSONArray ads = main_obj.getJSONArray("data");


                        for (int index = 0 ;index< ads.length();index++){

                            JSONObject catgrey = ads.getJSONObject(index);

                            Log.i("name2", String.valueOf(catgrey));
                            String title = catgrey.getString("title");
                            String photo = catgrey.getString("image_one");
                            String city = catgrey.getString("city");
                            String user_id = catgrey.getString("user_id");
                            String created = catgrey.getString("created_at");
                            String mobile = catgrey.getString("mobile");
                            String id = catgrey.getString("id");

                            String content = catgrey.getString("content");




                            users_arrayList = new ArrayList<User_items>();

                            if (catgrey.has("user") ) {
                                try {
                                    JSONObject catsub = catgrey.getJSONObject("user");


                                    String subname = catsub.getString("name");
                                    String subid = catsub.getString("id");

                                    String suborder = catsub.getString("mobile");
                                    users_arrayList.add(new User_items(subid, subname, suborder));
                                }catch (Exception e){};
                            }



                            ads_arrayList.add(new Ads_items(id, title, photo,content,user_id,city,mobile,created, users_arrayList));

                        }


                        Ads_adapters adapter = new Ads_adapters(getActivity(), ads_arrayList);;
                        ads_listView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "Error while connecting to server event !!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Error while connecting to server event !!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Error while connecting to server event !!", Toast.LENGTH_SHORT).show();
            }
        }, getActivity(), "http://tojr.net/api/v1/posts/?cat="+id+"&image=0&city=0&year=0", false);
        // SelectedSpinnerItem(city_array, city_spinner);,%22\"+Cur+\"AUD%22,%22\"


    }

    }

