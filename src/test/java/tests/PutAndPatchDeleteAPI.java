package tests;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;

public class PutAndPatchDeleteAPI {

    @Test
    public void PutApi(){
        JSONObject request = new JSONObject();
        request.put("name","Parvez");
        request.put("job","Automation Engineer");

        baseURI ="https://reqres.in";
        given().header("Content-Type","application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                put("/api/users/2").
                then().
                statusCode(200)
                .log().all();
    }
    @Test
    public void PatchApi(){
        JSONObject request = new JSONObject();
        request.put("name","Parvez");
        request.put("job","Automation Engineer");

        baseURI ="https://reqres.in";
        given().header("Content-Type","application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                patch("/api/users/2").
                then().
                statusCode(200)
                .log().all();
    }
    @Test
    public void DeleteApi(){
        baseURI ="https://reqres.in";
                when().
                delete("/api/users/2").
                then().
                statusCode(204)
                .log().all();
    }
    }

