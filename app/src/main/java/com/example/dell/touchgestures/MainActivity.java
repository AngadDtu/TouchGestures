package com.example.dell.touchgestures;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
HashMap<Integer,View> map=new HashMap<Integer,View>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RelativeLayout rl=(RelativeLayout)findViewById(R.id.rel);
        rl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getActionMasked()== MotionEvent.ACTION_DOWN){
                    View newView=new View(MainActivity.this);
                    float x=event.getX();
                    float y=event.getY();
                    newView.setBackgroundColor(Color.BLACK);
                    newView.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
                    newView.setX(x - 100);
                    newView.setY(y - 100);
                    rl.addView(newView);
                    map.put(event.getPointerId(0),newView);
                }
                else if(event.getActionMasked()==MotionEvent.ACTION_POINTER_DOWN){
                    int newPointerIndex=event.getActionIndex();
                    int newPointerId=event.getPointerId(newPointerIndex);
                    View newView=new View(MainActivity.this);
                    float x=event.getX(newPointerIndex);
                    float y=event.getY(newPointerIndex);
                    newView.setBackgroundColor(Color.BLACK);
                    newView.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
                    newView.setX(x - 100);
                    newView.setY(y - 100);
                    rl.addView(newView);
                    map.put(newPointerId,newView);
                }
                else if((event.getActionMasked()==MotionEvent.ACTION_POINTER_UP) || (event.getActionMasked()==MotionEvent.ACTION_UP)) {
                    int pointerIndex=event.getActionIndex();
                    int pointerId=event.getPointerId(pointerIndex);
                    View V=map.get(pointerId);
                   rl.removeView(V);
                }
                return true;
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
}
