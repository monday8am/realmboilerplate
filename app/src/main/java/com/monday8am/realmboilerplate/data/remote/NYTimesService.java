package com.monday8am.realmboilerplate.data.remote;

import com.monday8am.realmboilerplate.data.model.NYTimesStory;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Retrofit interface for the New York Times WebService
 */

public interface NYTimesService {

    @GET("svc/topstories/v1/{section}.json")
    Observable<NYTimesResponse<List<NYTimesStory>>> topStories(
        @Path("section") String section,
        @Query(value = "api-key", encoded = true) String apiKey);
}
