package org.example.learnspark.util;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {
    Gson gson = new Gson();

    Logger logger = LoggerFactory.getLogger(JsonTransformer.class);

    @Override
    public String render(Object model) throws Exception {
        String jsonOutput = gson.toJson(model);
        logger.info("JSON OUTPUT TO BE SENT = " + jsonOutput);
        return jsonOutput;
    }
}
