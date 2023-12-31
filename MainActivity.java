package com.yahoshuah.messiah.teaching.jutit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.yahoshuah.messiah.teaching.jutit.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    float dx_swiper, dx_divider;
    ViewGroup.LayoutParams flBeforeParms;
    int iv_after_width, cv_swiper_width;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.getRoot().post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "(Imageview After) width: "+binding.ivAfter.getWidth());

                iv_after_width = binding.ivAfter.getWidth();

                ViewGroup.LayoutParams ivBeforeParms = binding.ivBefore.getLayoutParams();
                ivBeforeParms.width = binding.ivAfter.getWidth();
                flBeforeParms = binding.flParentBefore.getLayoutParams();
                flBeforeParms.width = binding.ivAfter.getWidth()/2;
                binding.flParentBefore.setLayoutParams(flBeforeParms);
                cv_swiper_width = binding.flTouchAreaSwiper.getWidth();
                binding.flTouchAreaSwiper.setOnTouchListener(new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {

                        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                            Log.d(TAG, "onTouch: (Down - x)"+motionEvent.getX());
                            Log.d(TAG, "onTouch: (Down - y)"+motionEvent.getY());
                            Log.d(TAG, "onTouch: (view_down - y)"+view.getX());
                            Log.d(TAG, "onTouch: (view_down - y)"+view.getY());
                            Log.d(TAG, "onTouch: (Down - x) raw"+motionEvent.getRawX());
                            Log.d(TAG, "onTouch: (Down - y) raw"+motionEvent.getRawY());
                            dx_swiper = view.getX() - motionEvent.getRawX();
                            dx_divider = binding.cvDivider.getX() - motionEvent.getRawX();
                        } else
                        if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                            if(((motionEvent.getRawX() + dx_swiper) + 100) > 100 && (motionEvent.getRawX() + dx_swiper) < (iv_after_width - 100) ){
                                view.animate()
                                        .x(motionEvent.getRawX() + dx_swiper)
                                        .setDuration(0)
                                        .start();
                                binding.cvDivider.animate()
                                        .x(motionEvent.getRawX() + dx_divider)
                                        .setDuration(0)
                                        .start();
                                flBeforeParms.width = (int) (motionEvent.getRawX() + dx_swiper + cv_swiper_width/2);
                                binding.flParentBefore.setLayoutParams(flBeforeParms);
                            }


                            Log.d(TAG, "onTouch: (MOVE - x)"+motionEvent.getX());
                            Log.d(TAG, "onTouch: (MOVE - y)"+motionEvent.getY());
                            Log.d(TAG, "onTouch: (MOVE - x) raw"+motionEvent.getRawX());
                            Log.d(TAG, "onTouch: (MOVE - y) raw"+motionEvent.getRawY());

                        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                        }

                        return true;
                    }
                });

            }
        });




    }
}