package Method;
import API.RestfulBooker;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.sql.SQLOutput;

import static io.restassured.RestAssured.given;

public class PostService {

    public Response createToken(String username, String password,int Scode){
        Response resp=given().baseUri(RestfulBooker.Base.getUrl()).contentType("application/json")
                        .body(getBody(username, password)).log().all()
                        .when().post(RestfulBooker.Create_token.getUrl())
                        .then().assertThat().statusCode(Scode).log().all().extract().response();

//        String jsonResponse = resp.asString();
//        JSONObject jsonObject = new JSONObject(jsonResponse);
//        String token = jsonObject.getString("token");
//        System.out.println(token);
//        String RespBody = jsonObject.toString();
//        System.out.println(RespBody);

        return resp;
    }

    public String getBody(String username, String password){
        return "{" +
                "\"username\":\"" + username + '\"' +
                ", \"password\":\"" + password + '\"' +
                '}';
    }

    public void createBooking(String firstname, String lastname){
        given().baseUri(RestfulBooker.Base.getUrl()).header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .contentType("application/json")
                .body(getBody1(firstname, lastname)).log().all()
                .when().post(RestfulBooker.Booking.getUrl())
                .then().assertThat().statusCode(200).log().all();
    }

    public String getBody1(String firstname, String lastname){
        return "{" +
                "\"firstname\":\"" + firstname + "\"" +
                ", \"lastname\":\"" + lastname + "\"" +
                ", \"totalprice\":" + 18 +
                ", \"depositpaid\":" + true +
                ", \"bookingdates\": {" +
                "\"checkin\":\"2018-01-01\"" +
                ", \"checkout\":\"2019-01-01\"" +
                "}" +
                ", \"additionalneeds\":\"Breakfast\"" +
                "}";
    }

}
