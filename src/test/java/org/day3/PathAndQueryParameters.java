package org.day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class PathAndQueryParameters {

//    https://reqres.in/api/users/2?delay=3

    @Test
    void testPathAndQueryParameters() {

        given()
                .pathParam("id", 2)
                .queryParam("delay", 3)


                .when()
                .get("https://reqres.in/api/users/{id}")

                .then()
                .statusCode(200)
                .log().all();
    }


}
