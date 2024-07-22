package Assertion;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;

import java.lang.reflect.Method;

public class Assertion {

    public void responseBodyValidation(Response resp){
        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        String respBody = jsonObject.toString();
        Assert.assertEquals(respBody,responseBodyForError());
        if(respBody.equals(responseBodyForError())){
            System.out.println("Expected body and Actual body is same");
        }
        else{
            System.out.println("Not same");
        }
    }

    public String responseBodyForError(){
     String respBody= "{" +
                "\"reason\":\"Bad credentials\"" +
                "}";
        return respBody;
    }

    public String responseBody(){
        String responseBody= "{\"firstname\":\"Eric\",\"bookingdates\":{\"checkin\":\"2015-07-06\",\"checkout\":\"2019-05-23\"},\"totalprice\":639,\"depositpaid\":true,\"lastname\":\"Brown\"}";
        return responseBody;
    }
}
