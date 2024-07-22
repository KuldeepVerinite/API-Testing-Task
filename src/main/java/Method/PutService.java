package Method;

import API.RestfulBooker;

import static io.restassured.RestAssured.given;

public class PutService {

    public void updateBooking(String firstname, String lastname,int id){
        given().baseUri(RestfulBooker.Base.getUrl()).header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .contentType("application/json")
                .body(getBody(firstname, lastname)).log().all()
                .when().put(RestfulBooker.Booking.getUrl()+"/"+id)
                .then().assertThat().statusCode(200).log().all();
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
