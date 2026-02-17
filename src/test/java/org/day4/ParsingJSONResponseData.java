package org.day4;

import io.restassured.response.Response;
import org.json.JSONObject;
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
                .get("http://localhost:8000/students2.json");
        Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertEquals(res.header("Content-Type"),"application/json; charset=UTF-8");
        String studentName = res.jsonPath().get("students[6].name").toString();
        Assert.assertEquals(studentName,"Tarun");
    

    }

    @Test
    void testJSONResponseBodyData(){
        Response res = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:8000/students2.json");
/*
        Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertEquals(res.header("Content-Type"),"application/json; charset=UTF-8");
        String studentName = res.jsonPath().get("students[6].name").toString();
        Assert.assertEquals(studentName,"Tarun");
*/



        JSONObject jo = new JSONObject(res.asString()); //converting response to json obj type

   /*     for(int i=0; i<jo.getJSONArray("students").length(); i++){
            String location = jo.getJSONArray("students").getJSONObject(i).get("location").toString();
            System.out.println(location);
        }*/

//        Search for location in json - validation 1
        boolean status = false;

        for(int i=0; i<jo.getJSONArray("students").length(); i++){
            String location = jo.getJSONArray("students").getJSONObject(i).get("location").toString();
            System.out.println(location);

            if(location.equalsIgnoreCase("patna")){
                status = true;
                break;
            }
        }
        Assert.assertTrue(status);

//        Valid

    }
}
