package helpers;

public class Environment {
    public final static String
        urlYandexMarket = System.getProperty("urlYandexMarket","https://market.yandex.ru"),
        itemYandexMarket = System.getProperty("itemYandexMarket","Iphone 7"),

        urlIndeed = System.getProperty("urlIndeed","https://de.indeed.com/"),
        emailIndeed = System.getProperty("emailIndeed","cherrycat+22@mail.ru"),
        passwordIndeed = System.getProperty("passwordIndeed","Polyris123!123"),
        jobIndeed = System.getProperty("jobIndeed","software tester"),
        whereJobIndeed = System.getProperty("jobRegionIndeed","Deutschland"),

        urlIScout = System.getProperty("urlIScout","https://www.immobilienscout24.de/"),
        emailIScout = System.getProperty("emailIScout","cherrycat+22@mail.ru"),
        passwordIScout = System.getProperty("passwordIScout","Polyris123!123"),


        remoteDriverUrl = System.getProperty("remote_driver_url"), // https://username:password@selenoid.autotests.cloud:4444/wd/hub/
        videoStorageUrl = System.getProperty("video_storage_url"); // https://selenoid.autotests.cloud/video/
    ;

}
