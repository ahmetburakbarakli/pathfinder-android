package com.siipilas.pathfinder;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.app.Activity;
import android.widget.Toast;

@SuppressLint("Registered")
public class MainActivity extends Activity {
    private static final int TIME_DELAY = 2000;
    int back_pressed_counter = 0;
    private static long back_pressed;
    WebView mPathWebViewer;
    SwipeRefreshLayout mPathSwipeRefreshLayout;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPathWebViewer = findViewById(R.id.mPathWebViewer);
        mPathWebViewer.setWebViewClient(new WebViewClient());
        mPathWebViewer.getSettings().setJavaScriptEnabled(true);
        mPathWebViewer.loadUrl("http://google.com");

        mPathSwipeRefreshLayout = findViewById(R.id.mPathSwipeRefreshLayout);
        mPathSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPathWebViewer.reload();
                mPathSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mPathWebViewer.canGoBack())
            mPathWebViewer.goBack();
        else {
            if (back_pressed + TIME_DELAY > System.currentTimeMillis())
                super.onBackPressed();
            else {
                back_pressed_counter++;
                switch (back_pressed_counter) {
                    case 1:
                        Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getBaseContext(), "You need to double-click to back button.", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getBaseContext(), "Seriously, just click twice to it.", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(getBaseContext(), "CAN YOU JUST EXIT FROM ME!", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(getBaseContext(), "GTFO!", Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        final Toast toast = Toast.makeText(getBaseContext(), "Omae wa mou shindeiru!", Toast.LENGTH_SHORT);
                        toast.show();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                toast.cancel();
                                finish();
                            }
                        }, 777);
                        Toast.makeText(getBaseContext(), "NANI!", Toast.LENGTH_LONG).show();
                        break;
                }
            }
            back_pressed = System.currentTimeMillis();
        }
    }
}