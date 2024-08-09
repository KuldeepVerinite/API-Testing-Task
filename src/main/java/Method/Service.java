package Method;
import API.RestfulBooker;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class Service {

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

        return resp;
    }

    public void createBookingXML() {
                given()
                .baseUri(RestfulBooker.Base.getUrl())
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .contentType("text/xml")
                // Use "application/xml" for XML content type
                .body(getBody2())  // Assuming getBody1 returns XML string
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

    private String getBody2() {
        String xmlBody = """
                        <booking>
                            <firstname>Jim</firstname>
                            <lastname>Brown</lastname>
                            <totalprice>111</totalprice>
                            <depositpaid>true</depositpaid>
                            <bookingdates>
                                <checkin>2018-01-01</checkin>
                                <checkout>2019-01-01</checkout>
                            </bookingdates>
                            <additionalneeds>Breakfast</additionalneeds>
                        </booking>
                        """;
        return xmlBody;
    }

    public void getCreatedBooking(Response resp){
        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        int bookingid = jsonObject.getInt("bookingid");
        System.out.println(bookingid);
        Service service= new Service();
        service.getBooking(bookingid,200);
    }


    public String getFirstOrLastnameOrAdditionalneeds(Response resp,String name){
        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        String getname = jsonObject.getJSONObject("booking").getString(name);
        return getname;
    }

    public int getPrice(Response resp,String name){
        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        int price = jsonObject.getJSONObject("booking").getInt(name);
        return price;
    }

    public boolean getDepositpaid(Response resp,String name){
        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        boolean depositpaid = jsonObject.getJSONObject("booking").getBoolean(name);
        return depositpaid;
    }

    public String getCheckInOrOut(Response resp,String name){
        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        String getname = jsonObject.getJSONObject("booking").getJSONObject("bookingdates").getString(name);
        return getname;
    }

    public int getBookingId(Response resp){
        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        int booking_id = jsonObject.getInt("bookingid");
        return booking_id;
    }

    public void createTokenDeleteBooking(String username, String password,int Scode,int id){

        Response resp= createToken(username,password,Scode);

        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        String token = jsonObject.getString("token");
        System.out.println(token);

        deleteBooking(id,token);
    }

    public void deleteBooking(int id,String token){
        given().baseUri(RestfulBooker.Base.getUrl())
                .header("Cookie","token="+token)
                .contentType("application/json")
                .log().all()
                .when().delete(RestfulBooker.Booking.getUrl()+"/"+id)
                .then().log().all().assertThat().statusCode(201);
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

//  PatchService

    public Response patchUpdateBooking(String firstname, String lastname,int id){
       Response resp = given().baseUri(RestfulBooker.Base.getUrl())
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .contentType("application/json")
                .body(getBodyForPatch(firstname, lastname)).log().all()
                .when().patch(RestfulBooker.Booking.getUrl()+"/"+id)
                .then().assertThat().statusCode(200).log().all().extract().response();
       return resp;
    }

    private String getBodyForPatch(String firstname, String lastname){
        return "{" +
                "\"firstname\":\"" + firstname + "\"" +
                ", \"lastname\":\"" + lastname + "\"" +
                "}";

    }

//    GetService

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


//    PutService

    public Response updateBooking(String firstname, String lastname,int id){
        Response resp=given().baseUri(RestfulBooker.Base.getUrl()).header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .contentType("application/json")
                .body(getBodyForPut(firstname, lastname)).log().all()
                .when().put(RestfulBooker.Booking.getUrl()+"/"+id)
                .then().assertThat().statusCode(200).log().all().extract().response();
        return resp;
    }

    private String getBodyForPut(String firstname, String lastname){
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

    public String getFirstOrLastnameOrAdditionalneedsForPutAndGet(Response resp,String name){
        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        String getname = jsonObject.getString(name);
        return getname;
    }

    public int getPriceForPutAndGet(Response resp,String name){
        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        int price = jsonObject.getInt(name);
        return price;
    }

    public boolean getDepositpaidForPutAndGet(Response resp,String name){
        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        boolean depositpaid = jsonObject.getBoolean(name);
        return depositpaid;
    }

    public String getCheckInOrOutForPutAndGet(Response resp,String name){
        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        String getname = jsonObject.getJSONObject("bookingdates").getString(name);
        return getname;
    }
}