package Method;

import API.RestfulBooker;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class CreateTokenDeleteBooking {
    PostService ps= new PostService();
    DeleteService ds=new DeleteService();

    public String createToken1(String username, String password,int Scode){

        Response resp=ps.createToken(username,password,Scode);

        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        String token = jsonObject.getString("token");
        return token;


    }


}
