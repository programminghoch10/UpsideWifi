package com.programminghoch10.UpsideWifi;

import android.widget.ImageView;
import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

public class UpsideWifi implements IXposedHookInitPackageResources {
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
	            wifiIndicator.setRotation(180);
	        }
	    });
	}

}
