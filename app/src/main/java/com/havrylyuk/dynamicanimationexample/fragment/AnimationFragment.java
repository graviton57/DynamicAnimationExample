package com.havrylyuk.dynamicanimationexample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.havrylyuk.dynamicanimationexample.animation.AnimationType;
import com.havrylyuk.dynamicanimationexample.R;
import com.havrylyuk.dynamicanimationexample.animation.PositionSpringAnimation;
import com.havrylyuk.dynamicanimationexample.animation.RotationSpringAnimation;
import com.havrylyuk.dynamicanimationexample.animation.ScaleSpringAnimation;


/**
 *
 * Created by Igor Havrylyuk on 27.03.2017.
 */

public class AnimationFragment extends Fragment {


    private static final String ARG_ITEM_ID = "ARG_ITEM_ID";
    private AnimationType animType;
    private ImageView animateView;
    private TextView infoView;


    public static AnimationFragment getInstance(int itemId) {
        Bundle args = new Bundle();
        args.putInt(ARG_ITEM_ID, itemId);
        AnimationFragment animationFragment = new AnimationFragment();
        animationFragment.setArguments(args);
        return animationFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_animation, container, false);
        if (getArguments() != null) {
            animType = AnimationType.values()[getArguments().getInt(ARG_ITEM_ID)];
        }
        initUI(rootView);
        prepareAnimations();
        return rootView;
    }

    private void initUI (View rootView) {
        infoView = (TextView) rootView.findViewById(R.id.info_text_view);
        animateView = ( ImageView) rootView.findViewById(R.id.animate_image);
    }

    private void prepareAnimations() {
        switch (animType) {
            case POSITION:
                animateView.setImageResource(R.drawable.heart);
                PositionSpringAnimation positionSpringAnimation =
                        new PositionSpringAnimation(animateView);
                setInfoTextVisible(false);
                break;
            case ROTATION:
                animateView.setImageResource(R.drawable.loading);
                RotationSpringAnimation rotationAnimation =
                        new RotationSpringAnimation(animateView, infoView);
                setInfoTextVisible(true);
                break;
            case SCALE:
                animateView.setImageResource(R.drawable.fingle);
                ScaleSpringAnimation scaleSpringAnimation =
                        new ScaleSpringAnimation(animateView, infoView);
                setInfoTextVisible(true);
                break;
        }
    }

    private void setInfoTextVisible(boolean isVisible){
        if (infoView != null) {
            if (isVisible){
                infoView.setVisibility(View.VISIBLE);
            } else {
                infoView.setVisibility(View.GONE);
            }
        }
    }

}
