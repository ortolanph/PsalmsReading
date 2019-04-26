package org.psalms.external;

import org.psalms.beans.FullPsalm;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BibleAPI {

    @GET("/{psalmWithNumber}")
    public Call<FullPsalm> getPsalm(@Path("psalmWithNumber") String psalmWithNumber,
                                    @Query("translation") String translation);

}
