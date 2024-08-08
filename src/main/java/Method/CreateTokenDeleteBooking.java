package Method;

import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class CreateTokenDeleteBooking {
    PostService postservice = new PostService();
    DeleteService deleteservice=new DeleteService();

    public void createToken1(String username, String password,int Scode,int id){

        Response resp= postservice.createToken(username,password,Scode);

        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        String token = jsonObject.getString("token");
        System.out.println(token);

        deleteservice.deleteBooking(id,token);
    }


}
