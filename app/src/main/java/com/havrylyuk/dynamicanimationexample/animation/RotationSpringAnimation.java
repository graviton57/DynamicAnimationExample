package com.havrylyuk.dynamicanimationexample.animation;

import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import static com.havrylyuk.dynamicanimationexample.animation.SpringAnimationUtil.createSpringAnimation;

/**
 * Simple class showcase rotation spring animation
 * Created by Igor Havrylyuk on 27.03.2017.
 */

public class RotationSpringAnimation  {

    private static final float INITIAL_ROTATION = 0f;
    private SpringAnimation rotationAnimation;
    private float previousRotation;
    private float currentRotation ;
    private View animateView;
    private TextView infoView;

    public RotationSpringAnimation(ImageView animateView, TextView infoView) {
        // create a rotation SpringAnimation
        rotationAnimation = createSpringAnimation(animateView,
                SpringAnimation.ROTATION, INITIAL_ROTATION,
                SpringForce.STIFFNESS_MEDIUM, SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
        this.infoView = infoView;
        this.animateView = animateView;
        this.animateView.setOnTouchListener(touchListener);
        rotationAnimation.addUpdateListener(updateListener);
        updateInfoView();
    }

    public View.OnTouchListener getTouchListener() {
        return touchListener;
    }

    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        double centerX;
        double centerY;
        float x;
        float y;
        // angle calculation
        private void updateRotation(View view){
            currentRotation = view.getRotation() +
                    (float) Math.toDegrees(Math.atan2(x - centerX, centerY - y));
        }
        @Override
        public boolean onTouch(View view, MotionEvent event) {
             centerX = view.getWidth() / 2.0;
             centerY = view.getHeight() / 2.0;
             x = event.getX();
             y = event.getY();
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    // cancel so we can grab the view during previous animation
                    rotationAnimation.cancel();
                    updateRotation(view);
                    break;
                case MotionEvent.ACTION_MOVE:
                    // save current rotation
                    previousRotation = currentRotation;
                    updateRotation(view);
                    // rotate view by angle difference
                    float angle = currentRotation - previousRotation;
                    view.setRotation(view.getRotation() + angle);// += angle;
                    updateInfoView();
                    break;
                case MotionEvent.ACTION_UP:
                    rotationAnimation.start();
                    break;
            }
            return true;
        }
    };

    private DynamicAnimation.OnAnimationUpdateListener updateListener = new DynamicAnimation.OnAnimationUpdateListener() {
        @Override
        public void onAnimationUpdate(DynamicAnimation animation, float value, float velocity) {
            updateInfoView();
        }
    };

    public void updateInfoView(){
        if (animateView != null && infoView != null) {
            infoView.setText(String.format(Locale.getDefault(), "%.2f", animateView.getRotation()));
        }
    }
}
