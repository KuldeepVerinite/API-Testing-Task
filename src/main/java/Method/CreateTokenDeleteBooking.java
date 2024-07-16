package Method;

import API.RestfulBooker;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class CreateTokenDeleteBooking {
    PostService ps= new PostService();

    public void createToken1(String username, String password,int Scode,int id){

        Response resp=ps.createToken(username,password,Scode);

        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        String token = jsonObject.getString("token");
        System.out.println(token);

        given().baseUri(RestfulBooker.Base.getUrl())
                .header("Cookie","token="+token)
                .contentType("application/json")
                .log().all()
                .when().delete(RestfulBooker.Booking.getUrl()+"/"+id)
                .then().log().all().assertThat().statusCode(201);
    }

}
