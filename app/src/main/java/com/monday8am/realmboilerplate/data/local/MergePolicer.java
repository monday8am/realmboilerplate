package com.monday8am.realmboilerplate.data.local;

import com.monday8am.realmboilerplate.data.model.NYTimesStory;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Merger utility.
 */

public class MergePolicer {

    private static SimpleDateFormat mInputDateFormat =
            new SimpleDateFormat("yyyy-MM-d'T'HH:mm:ssZZZZZ", Locale.US);
    private static SimpleDateFormat mOutputDateFormat =
            new SimpleDateFormat("MM-dd-yyyy", Locale.US);

    public static boolean mergeNYTimeStory (NYTimesStory persistedStory, NYTimesStory story) {

        Date parsedPublishedDate = mInputDateFormat.parse(story.getPublishedDate(),
                new ParsePosition(0));
        story.setSortTimeStamp(parsedPublishedDate.getTime());
        story.setPublishedDate(mOutputDateFormat.format(parsedPublishedDate));

        if (persistedStory != null) {
            // Only local state is the `read` boolean.
            story.setRead(persistedStory.isRead());
        }

        return  (persistedStory == null ||
                !persistedStory.getUpdatedDate().equals(story.getUpdatedDate()));
    }

}
