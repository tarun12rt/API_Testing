package org.day3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
//import static io.restassured.RestAssured.matcher.RestAssuredMatchers;
import static org.hamcrest.Matchers.*;

public class CookiesDemo {

//    @Test
    void testCookies() {

        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .cookie("AEC", "AaJma5sARAIMGgYEG2OqVtY1yvW1UXXktXy-w1d_zm8euJ_Ei0Tny_DaZSA")
                .log().all();
    }

    @Test
    void getCookies() {

        Response resp = given()
                .when()
                .get("https://www.google.com/");

//        get single cookie info
//        String cookie_value = resp.getCookie("AEC");
//        System.out.println("Value of cookie is "+ cookie_value);

//        get all cookies info
        Map<String, String> cookies_values = resp.getCookies();
        System.out.println(cookies_values.keySet());

        for(String k:cookies_values.keySet()){
            String cookies_value = resp.getCookie(k);
            System.out.println(k+"     "+cookies_value);
        }
    }
}
