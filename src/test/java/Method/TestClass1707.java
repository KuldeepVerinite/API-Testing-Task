package Method;

import Assertion.Assertion;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class TestClass1707 {
    Service service =new Service();
    Assertion assertion =new Assertion();

    @Test(priority = 1) //GB_002
    public void responseBodyValidation(){
        //Need to change the response body because of Dynamic nature of Restful Booker API
        Response resp= service.getBooking(4,200);
        assertion.responseBodyValidation(resp);
    }

    @Test(priority = 2)   //GB_003
    public void bookingNotFound(){
        service.getBooking(10000,404);
    }

    @Test(priority = 3)   //CB_001,   CB_004
    public void createBookingSuccessful(){
        Response resp= service.createBooking("Kuldeep","Pawar");
        service.getCreatedBooking(resp);
    }

    @Test(priority = 4)       //CB_003    failed case
    public void createBookingUsingXML(){
        service.createBookingXML();
    }

//    @Test(priority = 5)    //Needs to change JSON request body in 'Service' class
    public void createBookingWithInvalidBody(){
//        service.createBooking();
    }
}
