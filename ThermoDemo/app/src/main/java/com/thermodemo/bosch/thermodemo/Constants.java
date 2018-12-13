package com.thermodemo.bosch.thermodemo;

public class Constants {
    public static final String FIRST_BASE_PREFFIX = "http://192.168.192.40";

    public static final String SECOND_BASE_PREFFIX = "http://192.168.192.39";

    public static final String BASE_SUFFIX_GREEN_LED_ON =
            "/m2m/fim/items/da%3Aitem%3AGPIO%3AOUTPUT_DEFAULT_VALUE_LOW%3A2/operations/on";
    public static final String BASE_SUFFIX_RED_LED_ON =
            "/m2m/fim/items/da:item:GPIO:OUTPUT_DEFAULT_VALUE_LOW:13/operations/on";

    public static final String BASE_SUFFIX_GREEN_LED_OFF =
            "/m2m/fim/items/da%3Aitem%3AGPIO%3AOUTPUT_DEFAULT_VALUE_LOW%3A2/operations/off";

    public static final String BASE_SUFFIX_RED_LED_OFF =
            "/m2m/fim/items/da:item:GPIO:OUTPUT_DEFAULT_VALUE_LOW:13/operations/off";

    public static final String BASE_SUFFIX_GET_TEMPERATURE =
            "/m2m/fim/items/da%3Aitem%3AGPIO%3AW1_TMP_DS18B20%3A%5BDS18B20%5D%20Temperature%20Sensor/properties/value";

    public static final String TEMPERATURE_POST_URL =
            "http://192.168.192.38:8080/add";
}
