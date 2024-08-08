package Method;

import io.restassured.response.Response;
import org.testng.annotations.Test;

public class TestClass2207 {
    PostService postservice=new PostService();
    @Test
    public void deleteBooking(){
        Response resp = postservice.createBooking("Kuldeep","Pawar");
        postservice.getBooking(postservice.getBookingId(resp),200);
        postservice.createTokenDeleteBooking("admin","password123",200,postservice.getBookingId(resp));
        postservice.getBooking(postservice.getBookingId(resp),404);
    }

    @Test
    public void deleteBookingWithAuth(){
        Response resp = postservice.createBooking("Kuldeep","Pawar");
        postservice.getBooking(postservice.getBookingId(resp),200);
        postservice.deleteBookingUsingAuthorization(postservice.getBookingId(resp),"Basic YWRtaW46cGFzc3dvcmQxMjM=",201);
    }

    @Test
    public void deleteBookingWithInvalidAuth(){
        Response resp = postservice.createBooking("Kuldeep","Pawar");
        postservice.getBooking(postservice.getBookingId(resp),200);
        postservice.deleteBookingUsingAuthorization(postservice.getBookingId(resp),"Basic YWRtaW46cGFzc3dvcmQ",403);
    }

    @Test
    public void deleteBookingWithoutHeaders(){
        Response resp = postservice.createBooking("Kuldeep","Pawar");
        postservice.getBooking(postservice.getBookingId(resp),200);
        postservice.deleteBookingWithoutHeaders(postservice.getBookingId(resp),403);
    }

}
