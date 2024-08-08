package Method;

import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TestClass1807 {

PostService postservice=new PostService();


@AfterMethod
public void afterMethod(){
    System.out.println("---------------------------------Test Ended Here---------------------------------------");
}

@Test       //  UB_001, UB_004
public void updateBooking(){
    Response resp = postservice.createBooking("Kuldeep","Pawar");
    postservice.getBooking(postservice.getBookingId(resp),200);
    postservice.updateBooking("","",postservice.getBookingId(resp));
    postservice.getBooking(postservice.getBookingId(resp),200);
}

@Test       //  PU_001, PU_002, PU_010
public void partialUpdateBooking(){
    Response resp = postservice.createBooking("Navdeep","Pawar");
    postservice.getBooking(postservice.getBookingId(resp),200);
    postservice.patchUpdateBooking("Kuldeep","Pawar",postservice.getBookingId(resp));
    postservice.getBooking(postservice.getBookingId(resp),200);
    }

}
