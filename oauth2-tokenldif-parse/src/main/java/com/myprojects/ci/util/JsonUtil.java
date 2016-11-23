package com.myprojects.ci.util;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public final class JsonUtil {

    private static final ObjectMapper objectMapper = newObjectMapper();

    // creating custom mapper to handle cases when dto's with custom views are sent as not part of response

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * Creates a new ObjectMapper that includes support for date serialization in ISO date format and
     * org.joda.time data formats.
     * 
     * We use joda time rather than java.text.SimpleDateFormat because of its robust handling of different
     * date formats and, unlike SimpleDateFormatter, it is actually thread safe. This allows us to keep the
     * ObjectMapper thread safe as well.
     * (Credits for suggestion of using Joda time : Clemens Rossell)
     * 
     * @return a new ObjectMapper with ISO-8601 and org.joda.time date serialization support
     */
    // Initializes object mapper with base serializers and deserializers
    public static ObjectMapper newObjectMapper() {
        return newObjectMapper("Base", true);
    }

    private static ObjectMapper newObjectMapper(String name, boolean customViewSupported) {
        ObjectMapper mapper = new ObjectMapper();
        // Enable auto-detection of all fields (public, private, protected) by default.
        // Setter and getter detection is disabled by default (can be selectively re-enabled with annotations).
        mapper.setVisibilityChecker(mapper.getVisibilityChecker()
                .with(JsonAutoDetect.Visibility.NONE)
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);

        // Set up customer serializers / deserializers
        SimpleModule module = new SimpleModule(name, new Version(1, 0, 0, null));


        mapper.registerModule(module);
        return mapper;
    }

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to serialize object to json", e);
        }
    }

    public static <T> T fromJson(String json, Class<T> type) {
        try {
            return objectMapper.readValue(json, type);
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to de-serialize json: " + json, e);
        }
    }

    private JsonUtil() {
    }

    public static String toPrettyJson(Object object) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to serialize object to json", e);
        }
    }
    
}
