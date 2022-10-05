package BasicRestAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class restAssuredBasic {

        String baseURI=RestAssured.baseURI ="http://hrm.syntaxtechs.net/syntaxapi/api";
        String token= "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjQ4NDA4NzksImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY2NDg4NDA3OSwidXNlcklkIjoiNDQ5OSJ9.qhVoKLi-55kUcmqmzxYiPHF1sbM5xS3Pa01UfKv25a0";
//create a request for creation of an employee
    @Test
    public void CreateAnEmployee(){
//        as we are giving the specifications of prep of a request so the
//        return type of our variable will be RequestSpecification
        RequestSpecification request = given().header("Content-Type", "application/json").header("Authorization", token)
                .body(jsonObject.createEmployeePayload());
//send the request to the server and get the response back
        Response response = request.when().post("/createEmployee.php");

//        print the response on console
        response.prettyPrint();

//        assert that the response contains the message Employee created
         String  ExpectedValue="Employee Created";
         String actualValue=response.jsonPath().getString("Message");
        System.out.println("the value of key Message is :   "+ actualValue);
        Assert.assertEquals(actualValue,ExpectedValue);

        response.then().assertThat().statusCode(201);
    }

    @Test
    public void JsonTest(){

        String re = jsonObject.createEmployeePayload();
        System.out.println(re);

    }

}
