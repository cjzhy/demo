package com.cnbdty.device.util;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SuppressWarnings("deprecation")
public class JsonUtils {
    public  static final Logger logger = LoggerFactory.getLogger(JsonUtils.class); 

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.getDeserializationConfig().set(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * object transform json string
     *
     * @param o object
     * @return json string
     */
    public static String objectToJson(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (IOException e) {
            logger.error("object:{} to json error:{}." ,e);
            return null;
        }
    }

    /**
     * json string transform object
     *
     * @param json
     * @param className
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String json, Class<T> className) {
        if (StringUtils.isEmpty(json)) {
            return null;
        } else {
            try {
                return objectMapper.readValue(json, className);
            } catch (IOException e) {
                logger.error("json:{} to object error:{}." ,e);
                return null;
            }
        }
    }

    @SuppressWarnings("unchecked")
	public static <T> T jsonToObject(String json, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(json)) {
            return null;
        } else {
            try {
                return (T)objectMapper.readValue(json, typeReference);
            } catch (IOException e) {
                logger.error("json:{} to object error:{}.", e);
                return null;
            }
        }
    }
}
