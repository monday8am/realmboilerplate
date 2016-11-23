package com.monday8am.realmboilerplate.data;

import java.util.HashMap;
import java.util.Map;

/**
 * DataManager class for handling the business rules of the app.
 */

public class DataManager {

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


    private static DataManager instance = null;
    private final Repository repository;
    private String selectedSection;

    // This could be replaced by Dependency Injection for easier testing
    public static synchronized Model getInstance() {
        if (instance == null) {
            Repository repository = new Repository();
            instance = new Model(repository);
        }
        return instance;
    }

}
