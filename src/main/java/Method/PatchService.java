package Method;

import API.RestfulBooker;

import static io.restassured.RestAssured.given;

public class PatchService {

    public void patchUpdateBooking(String firstname, int id,int sCode, String Auth){
        given().baseUri(RestfulBooker.Base.getUrl())
                .header("Authorization",Auth)
                .contentType("application/json")
                .body(getBody(firstname)).log().all()
                .when().patch(RestfulBooker.Booking.getUrl()+"/"+id)
                .then().assertThat().statusCode(sCode).log().all();
    }

    public void partialBookingCookie(String firstname, int id, int sCode, String token ){
        given().baseUri(RestfulBooker.Base.getUrl()).header("Cookie","token="+token)
                .contentType("application/json")
                .body(getBody(firstname)).log().all()
                .when().patch(RestfulBooker.Booking.getUrl()+"/"+id)
                .then().assertThat().statusCode(sCode).log().all();
    }

    private String getBody(String firstname){
        return "{" +
                "\"firstname\":\"" + firstname + "\"" +
                "}";

    }

}
