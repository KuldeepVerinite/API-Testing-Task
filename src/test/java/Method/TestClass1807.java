package Method;

import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TestClass1807 {

Service service =new Service();


@AfterMethod
public void afterMethod(){
    System.out.println("---------------------------------Test Ended Here---------------------------------------");
}

@Test(priority = 1)       //  UB_001, UB_004
public void updateBooking(){
    Response resp = service.createBooking("Kuldeep","Pawar");
    service.getBooking(service.getBookingId(resp),200);
    service.updateBooking("","", service.getBookingId(resp));
    service.getBooking(service.getBookingId(resp),200);
}

@Test(priority = 2)       //  PU_001, PU_002, PU_010
public void partialUpdateBooking(){
    Response resp = service.createBooking("Navdeep","Pawar");
    service.getBooking(service.getBookingId(resp),200);
    service.patchUpdateBooking("Kuldeep","Pawar", service.getBookingId(resp));
    service.getBooking(service.getBookingId(resp),200);
    }

}
