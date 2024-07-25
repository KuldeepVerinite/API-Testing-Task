package Method;

import API.RestfulBooker;

import static io.restassured.RestAssured.given;

public class DeleteService {
    public void deleteBooking(int id,String token,int sCode){
        given().baseUri(RestfulBooker.Base.getUrl())
                .header("Cookie","token="+token)
                .contentType("application/json")
                .log().all()
                .when().delete(RestfulBooker.Booking.getUrl()+"/"+id)
                .then().log().all().assertThat().statusCode(sCode);
    }

    public void deleteBookingIds(String bookingIds,String token,int sCode){
        given().baseUri(RestfulBooker.Base.getUrl())
                .header("Cookie","token="+token)
                .contentType("application/json")
                .log().all()
                .when().delete(RestfulBooker.Booking.getUrl()+"/"+bookingIds)
                .then().log().all().assertThat().statusCode(sCode);
    }

    public void deleteBookingUsingAuthorization(int id,String auth,int sCode){
        given().baseUri(RestfulBooker.Base.getUrl())
                .header("Authorization",auth)
                .contentType("application/json")
                .log().all()
                .when().delete(RestfulBooker.Booking.getUrl()+"/"+id)
                .then().log().all().assertThat().statusCode(sCode);
    }

    public void deleteBookingWithoutHeaders(int id,int sCode){
        given().baseUri(RestfulBooker.Base.getUrl())
                .contentType("application/json")
                .log().all()
                .when().delete(RestfulBooker.Booking.getUrl()+"/"+id)
                .then().log().all().assertThat().statusCode(sCode);
    }

}
