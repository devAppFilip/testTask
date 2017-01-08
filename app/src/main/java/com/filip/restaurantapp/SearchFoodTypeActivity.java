package com.filip.restaurantapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

/*
Used to provide search options for user. Tapping item on list takes user back to MainActivity and
sets EditText text to chosen type of food. List can be easily modified.
 */
public class SearchFoodTypeActivity extends AppCompatActivity {

    ArrayList<String> foodTypeArrayList;
    ListView foodTypeListView;
    ArrayAdapter arrayAdapter;
    String chosenFoodType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food_type);

        foodTypeArrayList = new ArrayList<>();
        foodTypeListView = (ListView) findViewById(R.id.foodTypeListView);
        chosenFoodType = "";
        SharedPreferences sharedPreferences = getSharedPreferences("com.filip.restaurantapp", Context.MODE_PRIVATE);

        //was used to import list of food
        //importFood();

        //get foodTypeArrayList from sharedPreferences
        try {
            foodTypeArrayList = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("foodType",ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //save foodTypeArrayList in sharedPreferences
        try {
            sharedPreferences.edit().putString("foodType", ObjectSerializer.serialize(foodTypeArrayList)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }




        arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, foodTypeArrayList);
        foodTypeListView.setAdapter(arrayAdapter);
        foodTypeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                chosenFoodType = foodTypeArrayList.get(position);
                Intent intent = new Intent();
                intent.putExtra("chosenFoodType", chosenFoodType);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    //manipulates strings and adds them to array list
    public void importFood(){
        String foodString = "* [[American Chinese cuisine]]\n" +
                "* [[Biscuit (bread)|Biscuit]] (and [[Biscuits and gravy]])\n" +
                "* [[Bread]]\n" +
                "* [[Ammonia cookie]]\n" +
                "* [[Cuisine of Antebellum America]]\n" +
                "* [[Apple butter]]\n" +
                "* [[Apple sauce]]\n" +
                "* [[Baked potato]]\n" +
                "* [[Barbecue]] (see below for specific types)\n" +
                "* [[Bear claw (pastry)|Bear claw]]\n" +
                "* [[Beef Manhattan]]\n" +
                "* [[Blue cheese dressing]]\n" +
                "* [[Blue-plate special]]\n" +
                "* [[Bookbinder soup]]\n" +
                "* [[Breakfast burrito]]\n" +
                "* [[Brunswick stew]]\n" +
                "* [[Buffalo burger]]\n" +
                "* [[Buffalo wing]]\n" +
                "* [[Bull roast]]\n" +
                "* [[Burnt ends]]\n" +
                "* [[Butter cookie]]\n" +
                "* [[Cajun cuisine]]\n" +
                "* [[Calf's liver and bacon]]\n" +
                "* [[Carne pizzaiola]]\n" +
                "* [[Carolina style]]\n" +
                "* [[Celery Victor]]\n" +
                "* [[Cheese dog]]\n" +
                "* [[Cheese fries]]\n" +
                "* [[Cheese steak]]\n" +
                "* [[Chicken à la King]]\n" +
                "* [[Chicken and waffles]]\n" +
                "* [[Chicken Divan]]\n" +
                "* [[Chicken fingers]]\n" +
                "* [[Chicken French]]\n" +
                "* [[Chicken fried bacon]]\n" +
                "* [[Chicken fried steak]]\n" +
                "* [[Chicken nugget]]\n" +
                "* [[Chicken parmigiana]]\n" +
                "* [[Chicken sandwich]]\n" +
                "* [[Chili con carne]]\n" +
                "* [[Chili dog]]\n" +
                "* [[Chimichanga]]\n" +
                "* [[Chinese food]]\n" +
                "* [[Chips and dip]]\n" +
                "* [[Chocolate chip cookies]]\n" +
                "* [[Choco pie]]\n" +
                "* [[Chowder]]\n" +
                "* [[City chicken]]\n" +
                "* [[Clam cake]]\n" +
                "* [[Clam chowder]]\n" +
                "* [[Coleslaw]]\n" +
                "* [[Cordon bleu (dish)|Cordon bleu]]\n" +
                "* [[Corn chowder]]\n" +
                "* [[Corn dog]]\n" +
                "* [[Corn flakes]]\n" +
                "* [[Corn relish]]\n" +
                "* [[Corned beef]]\n" +
                "* [[Cornish game hen]]\n" +
                "* [[Cowboy beans]]\n" +
                "* [[Crab cake]]\n" +
                "* [[Creamed corn]]\n" +
                "* [[Creamed eggs on toast]]\n" +
                "* [[Deviled crab]]\n" +
                "* [[Deviled egg]]\n" +
                "* [[Domesticated turkey]]\n" +
                "* [[Doughnut]]\n" +
                "* [[Drunken chicken]]\n" +
                "* [[Eggo]]\n" +
                "* [[Eggs Benedict]]\n" +
                "* [[Eggs Neptune]]\n" +
                "* [[Energy bar]]\n" +
                "* [[Engastration]] (i.e., [[Turducken]])\n" +
                "* [[Fajita]]\n" +
                "* [[Fortune cookie]]\n" +
                "* [[French dip]]\n" +
                "* [[Fried chicken]]\n" +
                "* [[Fried fish]]\n" +
                "* [[Fry sauce]]\n" +
                "* [[Frybread]]\n" +
                "* [[Garden salad]]\n" +
                "* [[German chocolate cake]]\n" +
                "* [[Goulash]]\n" +
                "* [[Greek-American cuisine]]\n" +
                "* [[Green bean casserole]]\n" +
                "* [[Grits]]\n" +
                "* [[Hamburger]]\n" +
                "* [[Hangtown fry]]\n" +
                "* [[Haystack (food)|Haystack]]\n" +
                "* [[Hog fry]]\n" +
                "* [[Home fries]]\n" +
                "* [[Hot chicken]]\n" +
                "* [[Hot chicken sandwich]]\n" +
                "* [[Ice cream cake]]\n" +
                "* [[Italian-American cuisine]]\n" +
                "* [[Italian dressing]]\n" +
                "* [[Jell-O]]\n" +
                "* [[Jerky]]\n" +
                "* [[Juba (food)|Juba]]\n" +
                "* [[Liver and onions]]\n" +
                "* [[Lobster Newberg]]\n" +
                "* [[Lobster roll]]\n" +
                "* [[London broil]]\n" +
                "* [[Lorna Doone (cookie)|Lorna Doone]]\n" +
                "* [[Macaroni and cheese]]\n" +
                "* [[Macaroni salad]]\n" +
                "* [[Maple bacon donut]]\n" +
                "* [[Maraca pie]]\n" +
                "* [[Mashed potato]]\n" +
                "* [[Mashed pumpkin]]\n" +
                "* [[Meatcake]]\n" +
                "* [[Meatloaf]]\n" +
                "* [[Milk toast]]\n" +
                "* [[Milkshake]]\n" +
                "* [[Mission burrito]]\n" +
                "* [[Mozzarella sticks]]\n" +
                "* [[Muffuletta]]\n" +
                "* [[Mulligan stew (food)|Mulligan stew]]\n" +
                "* [[Onion ring]]\n" +
                "* [[Oreo]]\n" +
                "* [[Oysters Rockefeller]]\n" +
                "* [[Pancake]]s\n" +
                "* [[Pasta salad]]\n" +
                "* [[Pastrami]]\n" +
                "* [[Patty]]\n" +
                "* [[Peanut butter]]\n" +
                "* [[Pemmican]]\n" +
                "* [[Pepperoni]]\n" +
                "* [[Pickled cucumber]]\n" +
                "* [[Pigs in blankets]]\n" +
                "* [[Pizza strips]]\n" +
                "* [[Ploye]]\n" +
                "* [[Pop-Tarts]]\n" +
                "* [[Popover]]\n" +
                "* [[Poppyseed muffin]]\n" +
                "* [[Pork and beans]]\n" +
                "* [[Potato salad]]\n" +
                "* [[Potato skins]]\n" +
                "* [[Potato wedges]]\n" +
                "* [[Potatoes O'Brien]]\n" +
                "* [[Protein bar]]\n" +
                "* [[Pulled pork]]\n" +
                "* [[Pumpkin pie]]\n" +
                "* [[Rabbit pie]]\n" +
                "* [[Ranch dressing]]\n" +
                "* [[Reuben sandwich]]\n" +
                "* [[Ribs (food)|Ribs]]\n" +
                "* [[Rolled oyster]]\n" +
                "* [[Russian dressing]]\n" +
                "* [[Russian tea cake]]\n" +
                "* [[Salisbury steak]]\n" +
                "* [[Sandwich]]\n" +
                "* [[Sausage gravy]]\n" +
                "* [[Scampi]]\n" +
                "* [[Scrapple]]\n" +
                "* [[Seafood cocktail]]\n" +
                "* [[Senate bean soup]]\n" +
                "* [[Slinger]]\n" +
                "* [[Sloppy joe]]\n" +
                "* [[Smelt (fish)|Smelt]]\n" +
                "* [[Sonofabitch stew]]\n" +
                "* [[Soul food]]\n" +
                "* [[Sour cream]]\n" +
                "* [[Spanish rice]]\n" +
                "* [[Squab (food)|Squab]]\n" +
                "* [[Steak]]\n" +
                "* [[Steak sandwich]]\n" +
                "* [[Steak sauce]]\n" +
                "* [[Steamed clams]]\n" +
                "* [[Stuffed ham]]\n" +
                "* [[Stuffed peppers]]\n" +
                "* [[Stuffed zucchini]]\n" +
                "* [[Succotash]]\n" +
                "* [[Surf and turf]]\n" +
                "* [[Swiss steak]]\n" +
                "* [[Tetrazzini]]\n" +
                "* [[Cuisine of the Thirteen Colonies]]\n" +
                "* [[Thousand Island dressing]]\n" +
                "* [[Toaster Strudel]]\n" +
                "* [[Tomato compote]]\n" +
                "* [[Tuna casserole]]\n" +
                "* [[Turducken]]\n" +
                "* [[Vichyssoise]]\n" +
                "* [[Waffle]]";
        String[] splitString = foodString.split("\n");
        Log.i("Info", splitString[0].toString());
        for(int i = 0; i < splitString.length; i++){
            splitString[i] = splitString[i].substring(4, splitString[i].indexOf("]"));
        }
        foodTypeArrayList.clear();
        for(String food: splitString){
            foodTypeArrayList.add(food);
        }
    }
}
