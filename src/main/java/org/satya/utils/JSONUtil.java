package org.satya.utils;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONUtil {

    public static String readJson(String file) throws Exception {

        return new String(
                Files.readAllBytes(
                        Paths.get("src/test/resources/body/" + file)
                )
        );
    }
}
