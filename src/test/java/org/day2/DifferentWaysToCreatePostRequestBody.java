package org.day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DifferentWaysToCreatePostRequestBody {
    String studentId;

//    Post request body using hashMap

    //    @Test(priority = 1)
    public void testPostUsingHashMap() {

        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "Tarun");
        data.put("location", "Patna");
        data.put("phone", "9472606686");

        String[] courseArr = {"Rest API", "Java"};
        data.put("courses", courseArr);

        studentId =
                given()
                        .contentType("application/json")
                        .body(data)
                        .when()
                        .post("http://localhost:3000/students")
                        .then()
                        .statusCode(201)
                        .log().all()
                        .extract()
                        .path("id");

        System.out.println("Created student ID: " + studentId);
    }


    //Deleting student record
    @Test(priority = 2)
    public void testDeleteStudent() {

        given()
                .when()
                .delete("http://localhost:3000/students/"+studentId)
                .then()
                .statusCode(200)
                .log().all();
    }


    //    Post request body using org.jason Library
//@Test(priority = 1)
    public void testPostUsingJsonLibrary() {

        JSONObject data = new JSONObject();
        data.put("name", "Tarun");
        data.put("location", "Patna");
        data.put("phone", "9472606686");

        String[] courseArr = {"Rest API", "Java"};
        data.put("courses", courseArr);

        studentId =
                given()
                        .contentType("application/json")
                        .body(data.toString())
                        .when()
                        .post("http://localhost:3000/students")
                        .then()
                        .statusCode(201)
                        .body("name",equalTo("Tarun"))
                        .log().all()
                        .extract()
                        .path("id");

        System.out.println("Created student ID: " + studentId);
    }

    //    Post request body using POJO Class
//    @Test(priority = 1)
    public void testPostUsingPOJO() {
        Pojo_PostRequest data = new Pojo_PostRequest();
        data.setName("Tarun");
        data.setLocation("Hyderabad");
        data.setPhone("9472606688");
        String[] coursesArr ={"C","C++"};
        data.setCourses(coursesArr);

        studentId =
                given()
                        .contentType("application/json")
                        .body(data)
                        .when()
                        .post("http://localhost:3000/students")
                        .then()
                        .statusCode(201)
                        .body("name",equalTo("Tarun"))
                        .log().all()
                        .extract()
                        .path("id");

        System.out.println("Created student ID: " + studentId);
    }

    //    Post request body using External JSON File
    @Test(priority = 1)
    public void testPostUsingExternalJSONFile() throws FileNotFoundException {

        File f = new File("src/test/resources/body.json");
        FileReader fr = new FileReader(f);

        JSONTokener jt = new JSONTokener(fr);

        JSONObject data = new JSONObject(jt);

        studentId =
                given()
                        .contentType("application/json")
                        .body(data.toString())
                        .when()
                        .post("http://localhost:3000/students")
                        .then()
                        .statusCode(201)
                        .body("name",equalTo("Tarun"))
                        .log().all()
                        .extract()
                        .path("id");

        System.out.println("Created student ID: " + studentId);
    }
}
