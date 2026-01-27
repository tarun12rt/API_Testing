package org.day4;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ParsingJSONResponseData {

    @Test
    void testJSONResponse(){
//        Approach 1
        /*given()
                .when()
                .get("http://localhost:3000/students2.json")
                .then()
                .statusCode(200)
                .header("Content-Type","application/json; charset=UTF-8")
                .body("students[6].name",equalTo("Tarun"));*/

        //        Approach 2

        Response res = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/students2.json");
        Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertEquals(res.header("Content-Type"),"application/json; charset=UTF-8");
        String studentName = res.jsonPath().get("students[6].name").toString();
        Assert.assertEquals(studentName,"Tarun");
    

    }
}
