package Method;
import API.RestfulBooker;

import static io.restassured.RestAssured.given;

public class NewPostService {

    private String firstname;
    private String lastname;
    private int id;

    public void createBooking(){
        given().baseUri(RestfulBooker.Base.getUrl())
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .contentType("application/json")
                .body(getBody()).log().all()
                .when().post(RestfulBooker.Booking.getUrl())
                .then().assertThat().statusCode(200).log().all();
    }

    private String getBody() {
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

    // Getters and Setters for private fields

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

