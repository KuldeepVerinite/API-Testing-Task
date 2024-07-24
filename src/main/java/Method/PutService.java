package Method;

import API.RestfulBooker;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class PutService {

    public void updateBooking(String firstname, String lastname,int id, int sCode, String Auth ){
        given().baseUri(RestfulBooker.Base.getUrl()).header("Authorization",Auth)
                .contentType("application/json")
                .body(getBody(firstname, lastname)).log().all()
                .when().put(RestfulBooker.Booking.getUrl()+"/"+id)
                .then().assertThat().statusCode(sCode).log().all();
    }

    public void updateBookingCookie(String firstname, String lastname,int id, int sCode, String token ){
        given().baseUri(RestfulBooker.Base.getUrl()).header("Cookie","token="+token)
                .contentType("application/json")
                .body(getBody(firstname, lastname)).log().all()
                .when().put(RestfulBooker.Booking.getUrl()+"/"+id)
                .then().assertThat().statusCode(sCode).log().all();
    }

    private String getBody(String firstname, String lastname){
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
