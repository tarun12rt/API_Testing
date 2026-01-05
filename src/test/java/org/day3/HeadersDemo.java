package org.day3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class HeadersDemo {

    @Test(priority=1)
    void testHeaders() {

        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .header("Content-Type","text/html; charset=ISO-8859-1")
                .and()
                .header("Content-Encoding","gzip")
                .and()
                .header("Server","gws");
    }

    @Test(priority=2)
    void getHeaders() {

        Response resp=
        given()
                .when()
                .get("https://www.google.com/");
//        String headerValue = resp.getHeader("Content-Type");
//        System.out.println("The value of Content-Type Header is: "+headerValue);
//        get all headers info
        Headers myHeaders = resp.getHeaders();
        for(Header hd:myHeaders){
            System.out.println(hd.getName()+"                                 "+hd.getValue());
        }
    }
}
