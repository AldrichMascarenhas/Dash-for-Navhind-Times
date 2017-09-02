package com.dashfornavhindtimes.data.api;


import com.dashfornavhindtimes.data.model.Media.Media;
import com.dashfornavhindtimes.data.model.Post.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by Aldrich on 12-Jun-17.
 */

public interface NavhindTimesService {

    @GET("posts")
    Call<List<Post>> getPostsByCategory(@Query("categories") int categoriesID, @Query("page") int page);

    @GET("posts/{postId}")
    Call<Post> getPostById(@Path("postId") int postId);

    @GET("media/{mediaId}")
    Call<Media> getPostMediaById(@Path("mediaId") int mediaId);


}

