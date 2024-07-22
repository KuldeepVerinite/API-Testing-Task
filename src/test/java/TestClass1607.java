import Assertion.Assertion;
import Method.GetService;
import Method.PostService;
import org.testng.annotations.Test;

public class TestClass1607 {

    GetService gs=new GetService();
    PostService ps=new PostService();
    Assertion as=new Assertion();

    @Test(priority = 6)
    public void test6() {
        gs.getBookingIdsByFirstName("John");
    }

    @Test(priority = 7)
    public void test7() {
        gs.getBookingIdsBylastName("Brown");
    }

    @Test(priority = 8)
    public void test8() {
        gs.getBookingIdsByCheckin("2024-01-01");
    }

    @Test(priority = 9)
    public void test9() {
        gs.getBookingIdsByCheckout("2099-07-01");
    }

    @Test(priority = 10)
    public void test10() {
        gs.getBooking(85,200);
    }
}
