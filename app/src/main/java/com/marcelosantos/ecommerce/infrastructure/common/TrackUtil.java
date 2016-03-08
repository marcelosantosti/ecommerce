package com.marcelosantos.ecommerce.infrastructure.common;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;

/**
 * Created by Marcelo on 08/03/2016.
 */
public class TrackUtil {

    public static void trackContentView(String contentName) {

        TrackUtil.trackContentView(contentName, null);
    }

    public static void trackContentView(String contentName, String customMessage) {

        ContentViewEvent contentViewEvent = new ContentViewEvent();
        contentViewEvent.putContentName(contentName);

        if (!StringUtil.isNullOrEmpty(customMessage))
            contentViewEvent.putCustomAttribute("message", customMessage);

        Answers.getInstance().logContentView(contentViewEvent);
    }
}
