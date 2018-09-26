package com.udacity.gradle.builditbigger;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.udacity.jokeactivity.JokeActivity;

import static com.udacity.jokeactivity.JokeActivity.JOKE_KEY;


public class MainActivity extends AppCompatActivity implements EndPointAsyncTask.AsyncResponse {


    private ProgressBar mProgressBar;
    private InterstitialAd mInterstitialAd;

    private float initialX;
    private ImageView slidingButton;


    private Bitmap myBitmap;
    private ImageView drawingImageView;
    private float smileGradient;
    private final String SMILE_GRADIENT = "smila_gradient";
    private final String BUTTON_POSITION = "button_position";

    private final float SLIDING_BUTTON_POSITION = 45;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.GONE);


        initInterstitialAds();


        drawingImageView = findViewById(R.id.drawingImageView);
        BitmapDrawable drawable = (BitmapDrawable) drawingImageView.getDrawable();
        myBitmap = drawable.getBitmap();

        slidingButton = findViewById(R.id.imageView);
        initialX = slidingButton.getX();


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            smileGradient = savedInstanceState.getFloat(SMILE_GRADIENT);
            initialX = savedInstanceState.getFloat(BUTTON_POSITION);

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putFloat(SMILE_GRADIENT, smileGradient);
        outState.putFloat(BUTTON_POSITION, initialX);
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView tv = findViewById(R.id.information_tv);
        tv.setText(getResources().getString(R.string.default_rl_textview));
        tv.setTextSize(20);


        initSwipe();

        slidingButton.setX(SLIDING_BUTTON_POSITION);
        moveButtonBack();


    }

    private void doMagic(int curveRadius) {

        curveRadius = curveRadius * -1;

        Bitmap tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(), myBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas tempCanvas = new Canvas(tempBitmap);
        tempCanvas.drawBitmap(myBitmap, 0, 0, null);

        Paint paint = getPaint();


        int startx = Math.round(myBitmap.getWidth() / 3);
        int starty = Math.round(myBitmap.getHeight() - (myBitmap.getHeight() / 3));
        int endx = Math.round(myBitmap.getWidth() - (myBitmap.getWidth() / 3));
        int endy = Math.round(myBitmap.getHeight() - (myBitmap.getHeight() / 3));

        final Path path = new Path();
        int midX = startx + ((endx - startx) / 2);
        int midY = starty + ((endy - starty) / 2);
        float xDiff = midX - startx;
        float yDiff = midY - starty;
        double angle = (Math.atan2(yDiff, xDiff) * (180 / Math.PI)) - 90;
        double angleRadians = Math.toRadians(angle);
        float pointX = (float) (midX + curveRadius * Math.cos(angleRadians));
        float pointY = (float) (midY + curveRadius * Math.sin(angleRadians));

        path.moveTo(startx, starty);
        path.cubicTo(startx, starty, pointX, pointY, endx, endy);
        tempCanvas.drawPath(path, paint);

        drawingImageView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
    }

    @NonNull
    private Paint getPaint() {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        return paint;
    }



    @SuppressLint("ClickableViewAccessibility")
    private void initSwipe() {


        final RelativeLayout rl = findViewById(R.id.rl);


        rl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        //Movement logic here

                        if (initialX == 0) {
                            initialX = slidingButton.getX();
                        }


                        smileGradient = (event.getX() - slidingButton.getWidth()) / 40;

                        if (event.getX() > initialX + slidingButton.getWidth() / 2 &&
                                event.getX() + slidingButton.getWidth() / 2 < v.getWidth()) {
                            slidingButton.setX(event.getX() - slidingButton.getWidth() / 2);
                        }
                        if (event.getX() + slidingButton.getWidth() / 2 > rl.getWidth() &&
                                slidingButton.getX() + slidingButton.getWidth() / 2 < rl.getWidth()) {

                            slidingButton.setX(rl.getWidth() - slidingButton.getWidth());

                        }

                        doMagic(Math.round(smileGradient));
                        return true;


                    case MotionEvent.ACTION_UP:
                        //Release logic here


                        if (slidingButton.getX() + slidingButton.getWidth() > rl.getWidth() * 0.90) {

                            TextView tv = findViewById(R.id.information_tv);
                            tv.setText(getResources().getString(R.string.getting_joke));
                            tv.setTextSize(17);
                            tellJoke();

                        } else {
                            moveButtonBack();
                        }


                        return true;

                }
                return false;
            }
        });
    }

    private void moveButtonBack() {

        final ValueAnimator objectAnimator =
                ValueAnimator.ofFloat(smileGradient, 0);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float x = (Float) objectAnimator.getAnimatedValue();

                doMagic(Math.round(x));
            }
        });

        objectAnimator.setDuration(200);

        Log.d("MainAct", "MoveButtonBack " + initialX);

        final ValueAnimator positionAnimator =
                ValueAnimator.ofFloat(slidingButton.getX(), SLIDING_BUTTON_POSITION);
        positionAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        positionAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float x = (Float) positionAnimator.getAnimatedValue();
                slidingButton.setX(x);
            }
        });

        positionAnimator.setDuration(200);


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator, positionAnimator);
        animatorSet.start();


    }

    private void initInterstitialAds() {
        MobileAds.initialize(this,
                "ca-app-pub-3940256099942544~3347511713");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                goAsync();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void tellJoke() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            goAsync();
        }

    }

    private void goAsync() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
        //noinspection unchecked
        new EndPointAsyncTask(this).execute();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mInterstitialAd != null) {
            mInterstitialAd = null;
        }
    }

    @Override
    public void processFinish(String output) {
        startJokeDisplayActivity(output);
    }

    private void startJokeDisplayActivity(String mResult) {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }

//        moveButtonBack();


        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JOKE_KEY, mResult);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);


    }
}
