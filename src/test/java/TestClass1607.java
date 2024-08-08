import Assertion.Assertion;
import Method.GetService;
import Method.PostService;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class TestClass1607 {

    PostService postservice=new PostService();
    Assertion as=new Assertion();

    @Test(priority = 1)
    public void testGetBookingIdsByFirstName() {
       Response resp = postservice.createBooking("Kuldeep","Pawar");
        postservice.getBookingIdsByFirstName(postservice.getFirstOrLastnameOrAdditionalneeds(resp,"firstname"));
        int id = postservice.getBookingId(resp);
        postservice.getBooking(id,200);
    }

    @Test(priority = 2)
    public void testGetBookingIdsByLastName() {
        Response resp = postservice.createBooking("Virat","Kohli");
        postservice.getBookingIdsByFirstName(postservice.getFirstOrLastnameOrAdditionalneeds(resp,"lastname"));
        int id = postservice.getBookingId(resp);
        postservice.getBooking(id,200);
    }

    @Test(priority = 3)
    public void testGetBookingIdsByCheckIn() {
        Response resp = postservice.createBooking("Rohit","Sharma");
        postservice.getBookingIdsByCheckin(postservice.getCheckInOrOut(resp,"checkin"));
    }

    @Test(priority = 4)
    public void testGetBookingIdsByCheckOut() {
        Response resp = postservice.createBooking("Jasprit","Bumrah");
        postservice.getBookingIdsByCheckin(postservice.getCheckInOrOut(resp,"checkout"));
    }

    @Test(priority = 5)
    public void testGetBookingById() {
        Response resp = postservice.createBooking("Yuvraj","Singh");
        postservice.getBooking(postservice.getBookingId(resp),200);
    }
}
