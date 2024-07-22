package Method;

import Assertion.Assertion;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class TestClass1707 {
    GetService gs=new GetService();
    PostService ps=new PostService();
    Assertion as=new Assertion();

    @Test   //GB_002
    public void responseBodyValidation(){
        Response resp=gs.getBooking(4,200);
        as.responseBodyValidation(resp);
    }

    @Test   //GB_003
    public void bookingNotFound(){
       gs.getBooking(10000,404);
    }

    @Test   //CB_001,   CB_004
    public void createBookingSuccessful(){
        Response resp=ps.createBooking("Kuldeep","Pawar");
        ps.getCreatedBooking(resp);
    }

//    @Test
    public void createBookingUsingXML(){
        ps.createBookingXML();
    }

//    @Test
    public void createBookingWithInvalidBody(){
//        ps.createBooking();
    }
}
