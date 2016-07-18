package com.mpandg.mpandgbluetooth.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.mpandg.mpandgbluetooth.R;
import com.mpandg.mpandgbluetooth.model.BodyType;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import javax.sql.DataSource;

public class TypeDetailActivity extends AppCompatActivity {

    private static final String EXTRA_IMAGE = "com.mpandg.adnroid.fastfood.extraImage";

    private ImageView image;
    private CollapsingToolbarLayout collapsingToolbar;
    private FloatingActionButton fab;
    private BodyType bodyType;

    public static void navigate(AppCompatActivity activity, View transitionImage, BodyType bodyType) {
        Intent intent = new Intent(activity, TypeDetailActivity.class);
        intent.putExtra(BodyType.KEY, bodyType);

        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, EXTRA_IMAGE).toBundle();
        ActivityCompat.startActivity(activity, intent, bundle);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityTransitions();
        setContentView(R.layout.activity_type_detail);

        // prepare the activity to support the transition.
        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), EXTRA_IMAGE);
        supportPostponeEnterTransition();

        //get and handle the sent intent:
        Intent intent = getIntent();
        bodyType = intent.getParcelableExtra(BodyType.KEY);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        collapsingToolbar.setTitle(bodyType.getName());
        //collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(bodyType.getColor()));
        //collapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
//        collapsingToolbar.setCollapsedTitleTypeface(Tools.getTypeface(this));
//        collapsingToolbar.setExpandedTitleTypeface(Tools.getTypeface(this));
        collapsingToolbar.setExpandedTitleGravity(GravityCompat.START | Gravity.BOTTOM);
        collapsingToolbar.setCollapsedTitleGravity(GravityCompat.START);

        //initiating the image .
        image = (ImageView) findViewById(R.id.image);
        Picasso.with(this).load(bodyType.getPhoto()).into(image, new Callback() {
            @Override public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    public void onGenerated(Palette palette) {
                        applyPalette(palette);
                    }
                });
            }

            @Override public void onError() {

            }
        });

        TextView title = (TextView) findViewById(R.id.title);
        assert title != null;
        //title.setText(bodyType.getName());

        TextView price = (TextView) findViewById(R.id.comment);
        assert price != null;
        //price.setText(bodyType.getPrice());

        assert toolbar != null;
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //set the color of status bar:
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(bodyType.getColor()));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.biker));
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // fab has been pressed.

                // add the bodyType item to card
//                bodyType.addToCart(dataSource);

                // show a snack telling the user that the item is added to cart.
//                Tools.snack(DetailActivity.this, R.string.added_to_cart);
            }
        });

    }

    private void initActivityTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide slide = new Slide();
            //slide.addTarget(R.id.description);
            slide.setInterpolator(AnimationUtils.loadInterpolator(this,
                    android.R.interpolator.linear_out_slow_in));
            //slide.setDuration(0.3d);
            slide.excludeTarget(android.R.id.statusBarBackground, true);
            slide.excludeTarget(android.R.id.navigationBarBackground, true);
            slide.excludeTarget(R.id.fab, true);
            getWindow().setEnterTransition(slide);
            getWindow().setReturnTransition(slide);
        }
    }

    @SuppressWarnings("deprecation")
    private void applyPalette(Palette palette) {
        int primaryDark = getResources().getColor(R.color.primary);
        int primary = getResources().getColor(R.color.primary_dark);
        //palette.getMutedColor(primary)
        collapsingToolbar.setContentScrimColor(palette.getLightVibrantColor(primary));
        collapsingToolbar.setStatusBarScrimColor(palette.getLightVibrantColor(primaryDark));
        updateBackground(fab, palette);
        supportStartPostponedEnterTransition();
    }

    @SuppressWarnings("deprecation")
    private void updateBackground(FloatingActionButton fab, Palette palette) {
        int lightVibrantColor = palette.getLightVibrantColor(getResources().getColor(android.R.color.white));
        int vibrantColor = palette.getVibrantColor(getResources().getColor(R.color.accent));
        fab.setRippleColor(lightVibrantColor);
        fab.setBackgroundTintList(ColorStateList.valueOf(vibrantColor));
    }
}