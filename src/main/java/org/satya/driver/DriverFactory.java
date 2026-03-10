package org.satya.driver;

public class DriverFactory {

    private static ThreadLocal<RestDriver> driver =
            new ThreadLocal<>();

    public static void initDriver() {
        driver.set(new RestDriver());
    }

    public static RestDriver getDriver() {
        return driver.get();
    }

    public static void unload() {
        driver.remove();
    }
}