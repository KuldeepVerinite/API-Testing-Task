package Method;
import API.RestfulBooker;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class PostService {

    public Response createToken(String username, String password, int Scode) {
        Response resp = given().baseUri(RestfulBooker.Base.getUrl()).contentType("application/json")
                .body(getBody(username, password)).log().all()
                .when().post(RestfulBooker.Create_token.getUrl())
                .then().assertThat().statusCode(Scode).log().all().extract().response();
        return resp;
    }

    private String getBody(String username, String password) {
        return "{" +
                "\"username\":\"" + username + '\"' +
                ", \"password\":\"" + password + '\"' +
                '}';
    }

    public Response createBooking(String firstname, String lastname) {
        Response resp = given().baseUri(RestfulBooker.Base.getUrl())
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .contentType("application/json")
                .body(getBody1(firstname, lastname)).log().all()
                .when().post(RestfulBooker.Booking.getUrl())
                .then().assertThat().statusCode(200).log().all().extract().response();

//        String jsonResponse = resp.asString();
//        JSONObject jsonObject = new JSONObject(jsonResponse);
//        int bookingid = jsonObject.getInt("bookingid");
//        System.out.println(bookingid);
//
//        GetService g= new GetService();
//        g.getBooking(bookingid,200);
        return resp;
    }

    public void createBookingXML() {
        given()
                .baseUri(RestfulBooker.Base.getUrl())
                .contentType("text/xml")
                // Use "application/xml" for XML content type
                .body(getBody1())  // Assuming getBody1 returns XML string
                .log().all()
                .when()
                .post(RestfulBooker.Booking.getUrl())
                .then()
                .log().all()
                .assertThat().statusCode(200);

    }

    private String getBody1(String firstname, String lastname) {
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

    private String getBody1() {
        String xmlBody = "<booking>\n" +
                "    <firstname>John</firstname>\n" +
                "    <lastname>Pawar</lastname>\n" +
                "    <totalprice>111</totalprice>\n" +
                "    <depositpaid>true</depositpaid>\n" +
                "    <bookingdates>\n" +
                "        <checkin>2018-01-01</checkin>\n" +
                "        <checkout>2019-01-01</checkout>\n" +
                "    </bookingdates>\n" +
                "    <additionalneeds>Breakfast</additionalneeds>\n" +
                "</booking>";
        return xmlBody;
    }

    public void getCreatedBooking(Response resp){
        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        int bookingid = jsonObject.getInt("bookingid");
        System.out.println(bookingid);
        GetService g= new GetService();
        g.getBooking(bookingid,200);
    }

    public String getCreatedBooking1(Response resp){
        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        String firstname = jsonObject.getJSONObject("booking").getString("firstname");
        return firstname;
    }

    public String getCreatedBooking2(Response resp,String name){
        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        String getname = jsonObject.getJSONObject("booking").getString(name);
        return getname;
    }

    public int getCreatedBooking3(Response resp,String name){
        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        int price = jsonObject.getJSONObject("booking").getInt(name);
        return price;
    }

    public boolean getCreatedBooking4(Response resp,String name){
        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        boolean depositpaid = jsonObject.getJSONObject("booking").getBoolean(name);
        return depositpaid;
    }

    public String getCreatedBooking5(Response resp,String name){
        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        String getname = jsonObject.getJSONObject("booking").getJSONObject("bookingdates").getString(name);
        return getname;
    }

    public int getCreatedBookingId(Response resp){
        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        int booking_id = jsonObject.getInt("bookingid");
        return booking_id;
    }
}