package org.day3;

import org.testng.annotations.Test;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.given;

public class LoggingDemo {

    @Test
    void testLogs(){
        given()
                .when()
                .get("https://www.google.com/")
                .then()
//                .log().cookies();
                .log().all();
//                .log().headers();
    }

}
