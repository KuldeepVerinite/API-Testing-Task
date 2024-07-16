package Method;

import API.RestfulBooker;

import static io.restassured.RestAssured.given;

public class DeleteService {
    public void deleteBooking(int id){
        given().baseUri(RestfulBooker.Base.getUrl())
                .header("Cookie","token=")
                .contentType("application/json")
                .log().all()
                .when().delete(RestfulBooker.Booking.getUrl()+"/"+id)
                .then().log().all().assertThat().statusCode(201);
    }

}
