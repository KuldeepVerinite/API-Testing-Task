package Method;

import API.RestfulBooker;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class PutService {

    public Response updateBooking(String firstname, String lastname,int id){
        Response resp=given().baseUri(RestfulBooker.Base.getUrl()).header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .contentType("application/json")
                .body(getBody(firstname, lastname)).log().all()
                .when().put(RestfulBooker.Booking.getUrl()+"/"+id)
                .then().assertThat().statusCode(200).log().all().extract().response();
        return resp;
    }

    private String getBody(String firstname, String lastname){
        Random random = new Random();
        return "{" +
                "\"firstname\":\"" + firstname + "\"" +
                ", \"lastname\":\"" + lastname + "\"" +
                ", \"totalprice\":" + random.nextInt(1000) +
                ", \"depositpaid\":" + random.nextBoolean() +
                ", \"bookingdates\": {" +
                "\"checkin\":\"2018-01-01\"" +
                ", \"checkout\":\"2019-01-01\"" +
                "}" +
                ", \"additionalneeds\":\"Breakfast\"" +
                "}";
    }

    public String getCreatedBooking1(Response resp){
        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        String firstname = jsonObject.getString("firstname");
        return firstname;
    }

    public String getCreatedBooking2(Response resp,String name){
        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        String getname = jsonObject.getString(name);
        return getname;
    }

    public int getCreatedBooking3(Response resp,String name){
        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        int price = jsonObject.getInt(name);
        return price;
    }

    public boolean getCreatedBooking4(Response resp,String name){
        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        boolean depositpaid = jsonObject.getBoolean(name);
        return depositpaid;
    }

    public String getCreatedBooking5(Response resp,String name){
        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        String getname = jsonObject.getJSONObject("bookingdates").getString(name);
        return getname;
    }

    public int getCreatedBookingId(Response resp){
        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        int booking_id = jsonObject.getInt("bookingid");
        return booking_id;
    }
}
