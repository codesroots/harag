package second.abdo.com.herag.Items;

import java.util.ArrayList;

/**
 * Created by tournedo2003 on 3/9/17.
 */

public class Cat_items {
    private String id;

    public String getCategories() {
        return Categories;
    }

    public void setCategories(String categories) {
        Categories = categories;
    }

    private String Categories ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name ;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    private String order ;




    public void setSubCategories(ArrayList<SubCategories_item> subCategories) {
        SubCategories = subCategories;
    }

    public ArrayList<SubCategories_item> getSubCategories() {
        return SubCategories;
    }

    private ArrayList<SubCategories_item> SubCategories;

    public Cat_items(String id, String name, String order, ArrayList<SubCategories_item> subCategories) {
        this.id = id;
        this.name = name;
this.SubCategories = subCategories;
        this.order = order;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
