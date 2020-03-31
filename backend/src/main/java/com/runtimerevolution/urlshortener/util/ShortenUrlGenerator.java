package com.runtimerevolution.urlshortener.util;

import org.springframework.stereotype.Component;

@Component
public class ShortenUrlGenerator {

    private static final char[] BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static final int BASE_LENGTH = BASE62.length;

    /**
     * Base62 converter,
     * receives database sequence next id
     * returns the representation of the id in Base 62
     **/
    public String idToBase62Url(int n) {
        if (n > 0) {
            StringBuilder shorturl = new StringBuilder();
            while (n > 0) {
                shorturl.append(BASE62[n % BASE_LENGTH]);
                n = n / 62;
            }
            //reverse because string builder adds chars sequentially, and we want the number from the first char to the last one
            return shorturl.toString();
        }else {
            return null;
        }
    }
}
