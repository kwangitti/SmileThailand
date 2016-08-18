package com.example.kwangitti.smilethailand.utils;

import com.example.kwangitti.smilethailand.api.model.ServerTimeGson;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

/**
 * Created by softbaked on 8/19/16 AD.
 */
public class ApiHeaderUtils {

    public static String parseToMd5(ServerTimeGson data) {
        String serverTime = data.getServerTime();
        return md5Hashing(serverTime);
    }

    private static String md5Hashing(String serverTime) {

        String test = "elims" + serverTime;

        MessageDigest m = null;

        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        if (m != null) {
            m.update(test.getBytes(), 0, test.length());
            return new BigInteger(1, m.digest()).toString(16);
        }

        return null;
    }
}
