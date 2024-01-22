package tests;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

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
        Map<String, String> map = new HashMap<>();
        map.put("name","Parvez");
        map.put("job","Automation Engineer");
        System.out.println(map);
        JSONObject requestBody = new JSONObject(map);
        System.out.println(requestBody);
        JSONObject request = new JSONObject();
        request.put("name","Parvez");
        request.put("job","Automation Engineer");
        System.out.println(request.toJSONString());
        baseURI ="https://reqres.in";
        given().header("Content-Type","application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                post("api/users").
                then().
                statusCode(201)
                .log().all();
    }
}
