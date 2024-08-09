package Method;
import API.RestfulBooker;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class APIService {

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

    public Response createBooking(String firstname, String lastname,int sCode) {
        Response resp = given().baseUri(RestfulBooker.Base.getUrl())
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .contentType("application/json")
                .body(getBody1(firstname, lastname)).log().all()
                .when().post(RestfulBooker.Booking.getUrl())
                .then().assertThat().statusCode(sCode).log().all().extract().response();

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
        return "{" +
                "\"firstnam\":\"" + firstname + "\"" +
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
        APIService apiservice= new APIService();
        apiservice.getBooking(bookingid,200);
    }

    public String createToken1(String username, String password,int Scode){
        APIService apiservice = new APIService();
        Response resp=apiservice.createToken(username,password,Scode);

        String jsonResponse = resp.asString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        String token = jsonObject.getString("token");
        return token;
    }

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

    public void getBookingIds(int sCode){
        given().baseUri(RestfulBooker.Base.getUrl())
                .when().get(RestfulBooker.Booking.getUrl())
                .then().assertThat().statusCode(sCode).log().all();
    }

    public void getBookingIdsByFirstName(String firstname, int sCode){
        given().baseUri(RestfulBooker.Base.getUrl())
                .queryParam("firstname",firstname)
                .contentType("application/json")
                .when().get(RestfulBooker.Booking.getUrl())
                .then().assertThat().statusCode(sCode).log().all();
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

    public Response getBooking(int id, int sCode){
        Response resp=given().baseUri(RestfulBooker.Base.getUrl())
                .when().get(RestfulBooker.Booking.getUrl()+"/"+id)
                .then().assertThat().statusCode(sCode).log().all().extract().response();
        return resp;
    }

    public Response getMulipleBookingIds(String bookingIds, int sCode){
        Response resp=given().baseUri(RestfulBooker.Base.getUrl())
                .when().get(RestfulBooker.Booking.getUrl()+"/"+bookingIds)
                .then().assertThat().statusCode(sCode).log().all().extract().response();
        return resp;
    }

    public void patchUpdateBooking(String firstname, int id,int sCode, String Auth){
        given().baseUri(RestfulBooker.Base.getUrl())
                .header("Authorization",Auth)
                .contentType("application/json")
                .body(getBodyPatch(firstname)).log().all()
                .when().patch(RestfulBooker.Booking.getUrl()+"/"+id)
                .then().assertThat().statusCode(sCode).log().all();
    }

    public void partialBookingCookie(String firstname, int id, int sCode, String token ){
        given().baseUri(RestfulBooker.Base.getUrl()).header("Cookie","token="+token)
                .contentType("application/json")
                .body(getBodyPatch(firstname)).log().all()
                .when().patch(RestfulBooker.Booking.getUrl()+"/"+id)
                .then().assertThat().statusCode(sCode).log().all();
    }

    private String getBodyPatch(String firstname){
        return "{" +
                "\"firstname\":\"" + firstname + "\"" +
                "}";

    }

    public void updateBooking(String firstname, String lastname,int id, int sCode, String Auth ){
        given().baseUri(RestfulBooker.Base.getUrl()).header("Authorization",Auth)
                .contentType("application/json")
                .body(getBodyPut(firstname, lastname)).log().all()
                .when().put(RestfulBooker.Booking.getUrl()+"/"+id)
                .then().assertThat().statusCode(sCode).log().all();
    }

    public void updateBookingForInvalidBody(String firstname, String lastname,int id, int sCode, String Auth ){
        given().baseUri(RestfulBooker.Base.getUrl()).header("Authorization",Auth)
                .contentType("application/json")
                .body(getBodyPutForInvalid(firstname, lastname)).log().all()
                .when().put(RestfulBooker.Booking.getUrl()+"/"+id)
                .then().assertThat().statusCode(sCode).log().all();
    }

    public void updateMultipleBooking(String firstname, String lastname,String bookingIds, int sCode, String Auth ){
        given().baseUri(RestfulBooker.Base.getUrl()).header("Authorization",Auth)
                .contentType("application/json")
                .body(getBodyPut(firstname, lastname)).log().all()
                .when().put(RestfulBooker.Booking.getUrl()+"/"+bookingIds)
                .then().assertThat().statusCode(sCode).log().all();
    }

    public void updateBookingCookie(String firstname, String lastname,int id, int sCode, String token ){
        given().baseUri(RestfulBooker.Base.getUrl()).header("Cookie","token="+token)
                .contentType("application/json")
                .body(getBodyPut(firstname, lastname)).log().all()
                .when().put(RestfulBooker.Booking.getUrl()+"/"+id)
                .then().assertThat().statusCode(sCode).log().all();
    }

    private String getBodyPut(String firstname, String lastname){
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

    private String getBodyPutForInvalid(String firstname, String lastname){
        return "{" +
                "\"firstnam\":\"" + firstname + "\"" +
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