package org.day1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class HTTPRequests{

    private static final Logger log = LoggerFactory.getLogger(HTTPRequests.class);

    @Test
    void getUsers()
    {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page",equalTo(2))
                .log().all();

    }
//    @Test
    void createUser()
    {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("name", "Tarun");
        hm.put("job", "Engineer");

        given()
                .contentType("application/json")
                .body(hm)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log().all();
    }


}
