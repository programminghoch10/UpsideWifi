package com.programminghoch10.UpsideWifi;

import android.os.Build;
import android.widget.ImageView;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

public class UpsideWifi implements IXposedHookInitPackageResources {
    ImageView wifiIndicator;

    @Override
    public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
        if (!resparam.packageName.equals("com.android.systemui")) {
            return;
        }
        String layoutName = android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ? "status_bar_wifi_group" : "signal_cluster_view";
        resparam.res.hookLayout("com.android.systemui", "layout", layoutName, new XC_LayoutInflated() {
            @Override
            public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                wifiIndicator = liparam.view.findViewById(liparam.res.getIdentifier("wifi_signal", "id", "com.android.systemui"));
                wifiIndicator.setRotation(180);
            }
        });
    }
}
