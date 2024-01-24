package tests;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetAndPostRequest {
    @Test
    public  void GetAPI(){
        baseURI ="https://reqres.in";
        given().get("/api/users?page=2").
                then().
                statusCode(200).
                body("data[1].first_name",equalTo("Lindsay")).
                body("data.first_name",hasItems("Lindsay","Tobias"));
    }
    @Test
    public void PostAPI(){
//        Map<String, String> map = new HashMap<>();
//        map.put("name","Parvez");
//        map.put("job","Automation Engineer");
//        System.out.println(map);
//        JSONObject requestBody = new JSONObject(map);
//        System.out.println(requestBody);
//        JSONObject request = new JSONObject();
//        request.put("name","Parvez");
//        request.put("job","Automation Engineer");
//        System.out.println(request.toJSONString());
//        Read the content of the JSON file into a String
        String jsonFilePath = "classpath:bodyData.json";
        String requestBody = null;

        try {
            requestBody = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseURI ="https://reqres.in";
        if (requestBody != null) {
        given().header("Content-Type","application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(requestBody).
                when().
                post("api/users").
                then().
                statusCode(201)
                .log().all();
    }
    }
}
