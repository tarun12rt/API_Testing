package org.day4;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ParsingJSONResponseData {

    @Test
    void testJSONResponse(){
        given()
                .when()
                .get("http://localhost:3000/students")
                .then()
                .statusCode(200)
                .header("Content-Type","application/json")
                .body("x.students[6].name",equalTo("Tarun"));

    

    }
}
