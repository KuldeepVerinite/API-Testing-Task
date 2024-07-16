package Assertion;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;

public class Assertion {

    public void errorCodeValidation(Response resp){
        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        String respBody = jsonObject.toString();
        Assert.assertEquals(respBody,"{" +
                "\"reason\":\"Bad credentials\"" +
                "}");
        System.out.println("Expected body and Actual body is same");
    }
}
