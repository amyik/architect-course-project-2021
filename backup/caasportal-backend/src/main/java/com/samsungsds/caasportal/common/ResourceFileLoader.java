package com.samsungsds.caasportal.common;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;


public class ResourceFileLoader {
    public static String readTemplate(String scriptPath) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource(scriptPath);
        String templateString = "";

        try {
            templateString = IOUtils.toString(url, Charset.forName(CommonConst.FILE_ENCODING_UTF_8));
        } catch (IOException ioe) {
            throw new RuntimeException("readTemplate error : " + ioe.getMessage(), ioe);
        }

        return templateString;
    }
}

