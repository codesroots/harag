package second.abdo.com.herag.Adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import second.abdo.com.herag.Items.Ads_items;
import second.abdo.com.herag.R;

/**
 * Created by tournedo2003 on 3/13/17.
 */

public class Ads_adapters extends BaseAdapter {

    private ArrayList<Ads_items> ads_list;
    private Context context;
    private LayoutInflater mInflater;
    private Typeface lightFace;
    private View view ;
    private ArrayList<Ads_items> arraylist;
    private HashMap<Integer, Boolean> fav_hashMap;
    public  boolean currentAdvertisement;

    boolean[] animationStates;

    public Ads_adapters(Context context, ArrayList<Ads_items> chalet_list_result) {
        this.context = context;
        Log.i("adsarray", String.valueOf(chalet_list_result));
        this.ads_list = chalet_list_result;
//        lightFace = Typeface.createFromAsset(context.getAssets(), "fonts/GE_SS_Two_Light.otf");
        mInflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<Ads_items>();
        this.arraylist.addAll(ads_list);
        animationStates = new boolean[ads_list.size()];
        notifyDataSetChanged();
    }
    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        if (observer != null) {
            super.unregisterDataSetObserver(observer);
        }
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return ads_list.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return ads_list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup  parent) {
        final ViewHolder holder;


        if(convertView == null){
            convertView = mInflater.inflate(R.layout.large_image_row, null);



            holder = new ViewHolder();
           holder.image = (ImageView) convertView.findViewById(R.id.imageView_logo);
            holder.progres = (ProgressBar) convertView.findViewById(R.id.progressSpinner);
            holder.title = (TextView) convertView.findViewById(R.id.textView_ad_title);
            holder.username = (TextView) convertView.findViewById(R.id.textView_authorName);
            holder.time = (TextView) convertView.findViewById(R.id.textView_time);

//            holder.notif_val = (TextView) convertView.findViewById(R.id.menu_notif);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Log.i("title", ads_list.get(position).getImage_one());
        holder.title.setText(ads_list.get(position).getTitle());

        try {
            holder.username.setText((CharSequence) ads_list.get(position).getUser().get(0).getName());

        }
        catch (Throwable e ){
            e.printStackTrace();
        }


        String toyBornTime = ads_list.get(position).getCreated_at();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");

        try {

            Date oldDate = dateFormat.parse(toyBornTime);
            System.out.println(oldDate);

            Date currentDate = new Date();

            long diff = currentDate.getTime() - oldDate.getTime();
            long seconds = diff / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;

            if (oldDate.before(currentDate)) {

                Log.e("oldDate", "is previous date");
                Log.e("Difference: ", " seconds: " + seconds + " minutes: " + minutes
                        + " hours: " + hours + " days: " + days);
                if (days >= 0){
                    holder.time.setText("قبل"+days+"يوما"+"و"+hours+"ساعة");


                }else  if (hours >= 0){

                    holder.time.setText("قبل"+hours+"ساعة");

                }else {

                    holder.time.setText("قبل"+minutes+"دقيقة");

                }


            }

            // Log.e("toyBornTime", "" + toyBornTime);

        } catch (ParseException e) {

            e.printStackTrace();
        }

//       Picasso.with(context).load(ads_list.get(position).getImage_one()).into(holder.image);
        if(!ads_list.get(position)

                .getImage_one().isEmpty()){
            Picasso.with(context).load(ads_list.get(position)

                    .getImage_one()).fit().centerCrop().into(holder.image, new Callback() {

                @Override
                public void onSuccess() {
                    holder.image.setVisibility(View.VISIBLE);
                    holder.progres.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onError() {
                    holder.progres.setVisibility(View.VISIBLE);
                    holder.image.setVisibility(View.INVISIBLE);
                }
            });

        }

//        holder.title.setTypeface(lightFace);
        return convertView;
    }

    static class ViewHolder{
        ImageView image ;
        TextView title ,username ,time;
        ProgressBar progres;
        TextView notif_val ;
    }

    public void printDifference(Date startDate, Date endDate){

        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays,
                elapsedHours, elapsedMinutes, elapsedSeconds);

    }
}
