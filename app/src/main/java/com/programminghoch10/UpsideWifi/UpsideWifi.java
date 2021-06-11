package com.programminghoch10.UpsideWifi;

import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

public class UpsideWifi implements IXposedHookInitPackageResources {
    static final RotateAnimation rotate;

    static {
        rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(5000);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setInterpolator(new LinearInterpolator());
    }

    ImageView wifiIndicator;

    @Override
    public void handleInitPackageResources(InitPackageResourcesParam resparam)
            throws Throwable {
        if (!resparam.packageName.equals("com.android.systemui")) {
            return;
        }
        resparam.res.hookLayout("com.android.systemui", "layout", "status_bar_wifi_group", new XC_LayoutInflated() {
            @Override
            public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                wifiIndicator = (ImageView) liparam.view.findViewById(liparam.res.getIdentifier("wifi_signal", "id", "com.android.systemui"));
                wifiIndicator.startAnimation(rotate);
            }
        });
    }

}
