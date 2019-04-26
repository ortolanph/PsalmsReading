package org.psalms.services;


import org.psalms.beans.FullPsalm;
import org.psalms.external.BibleAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.logging.Logger;

@Service
public class BibleAPIService {

    private static final String BASE_TRANSLATION = "bible.translation.default";
    private static final String BASE_PSALMS_TRANSLATION = "bible.translation.psalms";

    private static final Logger LOGGER = Logger.getLogger(BibleAPIService.class.getName());

    @Autowired
    private Retrofit retrofit;

    @Autowired
    private BibleAPI bibleAPI;

    @Autowired
    private Environment environment;

    public BibleAPIService() { }

    public FullPsalm getPsalm(int psalmNumber) throws Exception {
        String psalm = String.format("%s+%d", environment.getProperty(BASE_PSALMS_TRANSLATION), psalmNumber);

        Call<FullPsalm> retrofitCall = bibleAPI.getPsalm(psalm, environment.getProperty(BASE_TRANSLATION));

        Response<FullPsalm> response = retrofitCall.execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null
                    ? response.errorBody().string() : "Unknown error");
        }

        return response.body();
    }

}
