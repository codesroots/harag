package second.abdo.com.herag.Items;

import java.util.ArrayList;

/**
 * Created by tournedo2003 on 3/13/17.
 */

public class Ads_items {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_one() {
        return image_one;
    }

    public void setImage_one(String image_one) {
        this.image_one = image_one;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }


    private String title;
    private String content;
    private String image_one;
    private String user_id;
    private String city;
private String mobile;
    private String created_at;

    public ArrayList<User_items> getUser() {
        return user;
    }

    public void setUser(ArrayList<User_items> user) {
        this.user = user;
    }

    private ArrayList<User_items> user;

    public Ads_items(String id, String title,String image_one ,String content, String user_id , String city,String mobile,String created_at, ArrayList<User_items> user) {

         this.id = id ;
        this.title = title;
        this.content = content;
        this.image_one = image_one;
        this.user_id = user_id;
        this.city = city;
        this.mobile = mobile;
        this.created_at = created_at;
        this.user = user;
    }

    }
