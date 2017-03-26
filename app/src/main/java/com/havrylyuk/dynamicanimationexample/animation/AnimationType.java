package com.havrylyuk.dynamicanimationexample.animation;

/**
 * Created by Igor Havrylyuk on 26.03.2017.
 */

public enum AnimationType {

    POSITION("Position"),
    ROTATION("Rotation"),
    SCALE("Scale");

    private String name;

    AnimationType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
