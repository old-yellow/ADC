package com.example.administrator.adcpt;

import android.app.Activity;
import android.drm.DrmStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/5/8.
 */

public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
