package example.bm2105_cw2_source_g6;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.Gravity;
import android.view.View;

import androidx.annotation.StyleRes;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;

import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import example.bm2105_cw2_source_g6.Common.Common;
import example.bm2105_cw2_source_g6.adapter.ProductRecyclerAdapter;
import example.bm2105_cw2_source_g6.database.DatabaseHelper;
import example.bm2105_cw2_source_g6.database.model.Cart;
import example.bm2105_cw2_source_g6.database.model.Product;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView txtFullName;

    private ImageSwitcher foodImageSwitcher;
    private TextSwitcher foodNameSwitcher;
    private TextSwitcher priceSwitcher;
    private TextSwitcher descriptionsSwitcher;

    private RecyclerView recycler_menu;
    private CardSliderLayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;

    private TextView foodNameTextView1;
    private TextView foodNameTextView2;

    private long foodAnimDuration;
    private int foodNameOffset1;
    private int foodNameOffset2;

    private ArrayList<Product> productList;
    private int currentPosition;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.cart.addToCart(productList.get(currentPosition % productList.size()), 1);

                Snackbar.make(view, "Product added to cart", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // Set Name for user
        View headerView = navigationView.getHeaderView(0);
        txtFullName = (TextView) headerView.findViewById(R.id.txtFullName);
        txtFullName.setText("Hi, " + Common.currentUser.getUserName());

        // Load Menu
        loadMenu();
        initFoodText();
        initSwitchers();
    }

    // init the recycler view with food
    private void loadMenu(){
        recycler_menu = (RecyclerView) findViewById(R.id.recycler_menu);
        recycler_menu.setHasFixedSize(true);

        recycler_menu.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    onActiveCardChange();
                }
            }
        });

        layoutManager = new CardSliderLayoutManager (this);
        recycler_menu.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        databaseHelper = new DatabaseHelper(this);

        productList = databaseHelper.getProduct();
        mAdapter = new ProductRecyclerAdapter(productList);
        recycler_menu.setAdapter(mAdapter);

        new CardSnapHelper().attachToRecyclerView(recycler_menu);
    }

    private void setFoodName(String text, boolean left2right) {
        final TextView invisibleText;
        final TextView visibleText;
        if (foodNameTextView1.getAlpha() > foodNameTextView2.getAlpha()) {
            visibleText = foodNameTextView1;
            invisibleText = foodNameTextView2;
        } else {
            visibleText = foodNameTextView2;
            invisibleText = foodNameTextView1;
        }

        final int vOffset;
        if (left2right) {
            invisibleText.setX(0);
            vOffset = foodNameOffset2;
        } else {
            invisibleText.setX(foodNameOffset2);
            vOffset = 0;
        }

        invisibleText.setText(text);

        final ObjectAnimator iAlpha = ObjectAnimator.ofFloat(invisibleText, "alpha", 1f);
        final ObjectAnimator vAlpha = ObjectAnimator.ofFloat(visibleText, "alpha", 0f);
        final ObjectAnimator iX = ObjectAnimator.ofFloat(invisibleText, "x", foodNameOffset1);
        final ObjectAnimator vX = ObjectAnimator.ofFloat(visibleText, "x", vOffset);

        final AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(iAlpha, vAlpha, iX, vX);
        animSet.setDuration(foodAnimDuration);
        animSet.start();
    }

    private void onActiveCardChange() {
        final int pos = layoutManager.getActiveCardPosition();
        if (pos == RecyclerView.NO_POSITION || pos == currentPosition) {
            return;
        }

        onActiveCardChange(pos);
    }

    private void onActiveCardChange(int pos) {
        int animH[] = new int[] {R.anim.slide_in_right, R.anim.slide_out_left};
        int animV[] = new int[] {R.anim.slide_in_top, R.anim.slide_out_bottom};

        final boolean left2right = pos < currentPosition;
        if (left2right) {
            animH[0] = R.anim.slide_in_left;
            animH[1] = R.anim.slide_out_right;

            animV[0] = R.anim.slide_in_bottom;
            animV[1] = R.anim.slide_out_top;
        }

        setFoodName(productList.get(pos % productList.size()).getProduct_code(), left2right);


        foodNameSwitcher.setInAnimation(Home.this, animV[0]);
        foodNameSwitcher.setOutAnimation(Home.this, animV[1]);
        foodNameSwitcher.setText(productList.get(pos % productList.size()).getProduct_name());

        priceSwitcher.setInAnimation(Home.this, animV[0]);
        priceSwitcher.setOutAnimation(Home.this, animV[1]);
        priceSwitcher.setText("RM " + roundOffTo2DecPlaces(productList.get(pos % productList.size()).getProduct_price()));

        descriptionsSwitcher.setText(productList.get(pos % productList.size()).getProduct_desc());

        InputStream is;

        try {
            is = this.getAssets().open(productList.get(pos % productList.size()).getProduct_image());
        } catch (IOException e){
            is = null;
        }

        foodImageSwitcher.setImageDrawable(Drawable.createFromStream(is, ""));

        currentPosition = pos;
    }

    private void initSwitchers() {
        foodNameSwitcher = (TextSwitcher) findViewById(R.id.ts_food_name);
        foodNameSwitcher.setFactory(new TextViewFactory(R.style.foodNameTextView, false));
        foodNameSwitcher.setCurrentText(productList.get(0).getProduct_name());

        priceSwitcher = (TextSwitcher) findViewById(R.id.ts_price);
        priceSwitcher.setFactory(new TextViewFactory(R.style.priceTextView, false));
        priceSwitcher.setCurrentText("RM " + roundOffTo2DecPlaces(productList.get(0).getProduct_price()));

        descriptionsSwitcher = (TextSwitcher) findViewById(R.id.ts_description);
        descriptionsSwitcher.setInAnimation(this, android.R.anim.fade_in);
        descriptionsSwitcher.setOutAnimation(this, android.R.anim.fade_out);
        descriptionsSwitcher.setFactory(new TextViewFactory(R.style.DescriptionTextView, false));
        descriptionsSwitcher.setCurrentText(productList.get(0).getProduct_desc());

        foodImageSwitcher = (ImageSwitcher) findViewById(R.id.ts_food_image);
        foodImageSwitcher.setInAnimation(this, R.anim.fadein);
        foodImageSwitcher.setOutAnimation(this, R.anim.fade_out);
        foodImageSwitcher.setFactory(new ImageViewFactory());

        InputStream is;

        try {
            is = this.getAssets().open(productList.get(0).getProduct_image());
        } catch (IOException e){
            is = null;
        }

        foodImageSwitcher.setImageDrawable(Drawable.createFromStream(is, ""));
    }

    private String roundOffTo2DecPlaces(double val)
    {
        return String.format("%.2f", val);
    }

    private void initFoodText() {
        foodAnimDuration = getResources().getInteger(R.integer.labels_animation_duration);
        foodNameOffset1 = getResources().getDimensionPixelSize(R.dimen.left_offset);
        foodNameOffset2 = getResources().getDimensionPixelSize(R.dimen.card_width);
        foodNameTextView1 = (TextView) findViewById(R.id.food_name_1);
        foodNameTextView2 = (TextView) findViewById(R.id.food_name_2);

        foodNameTextView1.setX(foodNameOffset1);
        foodNameTextView2.setX(foodNameOffset2);
        foodNameTextView1.setText(productList.get(0).getProduct_code());
        foodNameTextView2.setAlpha(0f);

        foodNameTextView1.setTypeface(Typeface.createFromAsset(getAssets(), "open-sans-extrabold.ttf"));
        foodNameTextView2.setTypeface(Typeface.createFromAsset(getAssets(), "open-sans-extrabold.ttf"));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu) {

        } else if (id == R.id.nav_cart) {
            Intent intent = new Intent(this, CartView.class);
            startActivity(intent);
        } else if (id == R.id.nav_orders) {
            Intent intent = new Intent(this, Order.class);
            startActivity(intent);
        } else if(id == R.id.nav_edit_profile) {
            Intent intent = new Intent(this, EdittPage.class);
            startActivity(intent);
        } else if (id == R.id.nav_log_out) {
            logout();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Are you sure you want to log out?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Remove the session and log user out
                        SharedPreferences mPrefs = getSharedPreferences(Validation.SESSION_NAME, MODE_PRIVATE);
                        mPrefs.edit().remove(Validation.SESSION_NAME).apply();

                        Common.currentUser = null;

                        Intent intent = new Intent(Home.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private class TextViewFactory implements  ViewSwitcher.ViewFactory {

        @StyleRes
        final int styleId;
        final boolean center;

        TextViewFactory(@StyleRes int styleId, boolean center) {
            this.styleId = styleId;
            this.center = center;
        }

        @SuppressWarnings("deprecation")
        @Override
        public View makeView() {
            final TextView textView = new TextView(Home.this);

            if (center) {
                textView.setGravity(Gravity.CENTER);
            }

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                textView.setTextAppearance(Home.this, styleId);
            } else {
                textView.setTextAppearance(styleId);
            }

            return textView;
        }

    }

    private class ImageViewFactory implements ViewSwitcher.ViewFactory {
        @Override
        public View makeView() {
            final ImageView imageView = new ImageView(Home.this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            final ViewGroup.LayoutParams lp = new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(lp);

            return imageView;
        }
    }
}
