package Method;
import API.RestfulBooker;
import io.restassured.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class GetService {

    public void getBookingIds(){
        given().baseUri(RestfulBooker.Base.getUrl())
                .when().get(RestfulBooker.Booking.getUrl())
                .then().assertThat().statusCode(200).log().all();
    }

    public void getBookingIdsByFirstName(String firstname){
        given().baseUri(RestfulBooker.Base.getUrl())
                .queryParam("firstname",firstname)
                .contentType("application/json")
                .when().get(RestfulBooker.Booking.getUrl())
                .then().assertThat().statusCode(200).log().all();
    }

    public void getBookingIdsBylastName(String lastname){
        given().baseUri(RestfulBooker.Base.getUrl())
                .queryParam("lastname",lastname)
                .when().get(RestfulBooker.Booking.getUrl())
                .then().assertThat().statusCode(200).log().all();
    }

    public void getBookingIdsByCheckin(String checkin){
        given().baseUri(RestfulBooker.Base.getUrl())
                .queryParam("checkin",checkin).
                when().get(RestfulBooker.Booking.getUrl())
                .then().assertThat().statusCode(200).log().all();
    }

    public void getBookingIdsByCheckout(String checkout){
        given().baseUri(RestfulBooker.Base.getUrl())
                .queryParam("checkout",checkout).
                when().get(RestfulBooker.Booking.getUrl())
                .then().assertThat().statusCode(200).log().all();
    }

    public Response getBooking(int id, int statusCode){
        Response resp=given().baseUri(RestfulBooker.Base.getUrl())
                .when().get(RestfulBooker.Booking.getUrl()+"/"+id)
                .then().assertThat().statusCode(statusCode).log().all().extract().response();
        return resp;
    }
}
