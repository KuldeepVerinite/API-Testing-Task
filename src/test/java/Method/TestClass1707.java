package Method;

import Assertion.Assertion;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class TestClass1707 {
    PostService postservice=new PostService();
    Assertion as=new Assertion();

    @Test   //GB_002
    public void responseBodyValidation(){
        Response resp=postservice.getBooking(4,200);
        as.responseBodyValidation(resp);
    }

    @Test   //GB_003
    public void bookingNotFound(){
        postservice.getBooking(10000,404);
    }

    @Test   //CB_001,   CB_004
    public void createBookingSuccessful(){
        Response resp=postservice.createBooking("Kuldeep","Pawar");
        postservice.getCreatedBooking(resp);
    }

    @Test       //CB_003    fail
    public void createBookingUsingXML(){
        postservice.createBookingXML();
    }

//    @Test
    public void createBookingWithInvalidBody(){
//        postservice.createBooking();
    }
}
