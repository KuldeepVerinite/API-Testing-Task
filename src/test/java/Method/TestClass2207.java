package Method;

import io.restassured.response.Response;
import org.testng.annotations.Test;

public class TestClass2207 {
    Service service =new Service();
    @Test(priority = 1)
    public void deleteBooking(){
        Response resp = service.createBooking("Kuldeep","Pawar");
        service.getBooking(service.getBookingId(resp),200);
        service.createTokenDeleteBooking("admin","password123",200, service.getBookingId(resp));
        service.getBooking(service.getBookingId(resp),404);
    }

    @Test(priority = 2)
    public void deleteBookingWithAuth(){
        Response resp = service.createBooking("Kuldeep","Pawar");
        service.getBooking(service.getBookingId(resp),200);
        service.deleteBookingUsingAuthorization(service.getBookingId(resp),"Basic YWRtaW46cGFzc3dvcmQxMjM=",201);
    }

    @Test(priority = 3)
    public void deleteBookingWithInvalidAuth(){
        Response resp = service.createBooking("Kuldeep","Pawar");
        service.getBooking(service.getBookingId(resp),200);
        service.deleteBookingUsingAuthorization(service.getBookingId(resp),"Basic YWRtaW46cGFzc3dvcmQ",403);
    }

    @Test(priority = 4)
    public void deleteBookingWithoutHeaders(){
        Response resp = service.createBooking("Kuldeep","Pawar");
        service.getBooking(service.getBookingId(resp),200);
        service.deleteBookingWithoutHeaders(service.getBookingId(resp),403);
    }

}
