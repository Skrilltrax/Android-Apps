package me.skrilltrax.gestures;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnDoubleTapListener,
        GestureDetector.OnGestureListener {

    private TextView textView;
    private GestureDetectorCompat gestureDetectorCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textview);
        gestureDetectorCompat = new GestureDetectorCompat(this, this);

    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        textView.setText("onSingleTapConfirmed");
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        textView.setText("onDoubleTap");
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        textView.setText("onDoubleTapEvent");
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        textView.setText("onDown");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        textView.setText("onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        textView.setText("onSingleTapUp");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        textView.setText("onScroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        textView.setText("onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        textView.setText("onFling");
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

}
