import Assertion.Assertion;
import Method.Service;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class TestClass1607 {

    Service service =new Service();

    @Test(priority = 1)
    public void testGetBookingIdsByFirstName() {
       Response resp = service.createBooking("Kuldeep","Pawar");
        service.getBookingIdsByFirstName(service.getFirstOrLastnameOrAdditionalneeds(resp,"firstname"));
        int id = service.getBookingId(resp);
        service.getBooking(id,200);
    }

    @Test(priority = 2)
    public void testGetBookingIdsByLastName() {
        Response resp = service.createBooking("Virat","Kohli");
        service.getBookingIdsByFirstName(service.getFirstOrLastnameOrAdditionalneeds(resp,"lastname"));
        int id = service.getBookingId(resp);
        service.getBooking(id,200);
    }

    @Test(priority = 3)
    public void testGetBookingIdsByCheckIn() {
        Response resp = service.createBooking("Rohit","Sharma");
        service.getBookingIdsByCheckin(service.getCheckInOrOut(resp,"checkin"));
    }

    @Test(priority = 4)
    public void testGetBookingIdsByCheckOut() {
        Response resp = service.createBooking("Jasprit","Bumrah");
        service.getBookingIdsByCheckin(service.getCheckInOrOut(resp,"checkout"));
    }

    @Test(priority = 5)
    public void testGetBookingById() {
        Response resp = service.createBooking("Yuvraj","Singh");
        service.getBooking(service.getBookingId(resp),200);
    }
}
