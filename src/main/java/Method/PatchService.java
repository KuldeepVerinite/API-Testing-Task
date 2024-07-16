package Method;

import API.RestfulBooker;

import static io.restassured.RestAssured.given;

public class PatchService {

    public void patchUpdateBooking(int totalprice, String lastname,int id){
        given().baseUri(RestfulBooker.Base.getUrl())
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .contentType("application/json")
                .body(getBody(totalprice, lastname)).log().all()
                .when().patch(RestfulBooker.Booking.getUrl()+"/"+id)
                .then().assertThat().statusCode(200).log().all();
    }

    public String getBody(int totalprice, String lastname){
        return "{" +
                "\"totalprice\":\"" + totalprice + "\"" +
                ", \"lastname\":\"" + lastname + "\"" +
                "}";

    }

}
