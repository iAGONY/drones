package com.musala.drones.utility;

import java.util.Base64;

public class StringUtil {

    public static byte[] decode(String image) {
        if(image != null && image != "") {
            return Base64.getDecoder().decode(image);
        }
        return null;
    }
}
