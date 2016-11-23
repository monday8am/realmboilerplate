package com.monday8am.realmboilerplate.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Store class for handling the business rules of the app.
 */

public class Store {

    /**
     * Map between section titles and their NYTimes API keys
     */
    private static final Map<String, String> sections;
    static {
        sections = new HashMap<>();
        sections.put("home", "Home");
        sections.put("world", "World");
        sections.put("national", "National");
        sections.put("politics", "Politics");
        sections.put("nyregion", "NY Region");
        sections.put("business", "Business");
        sections.put("opinion", "Opinion");
        sections.put("technology", "Technology");
        sections.put("science", "Science");
        sections.put("health", "Health");
        sections.put("sports", "Sports");
        sections.put("arts", "Arts");
        sections.put("fashion", "Fashion");
        sections.put("dining", "Dining");
        sections.put("travel", "Travel");
        sections.put("magazine", "Magazine");
        sections.put("realestate", "Real Estate");
    }

}
