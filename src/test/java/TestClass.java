import Assertion.Assertion;
import Method.*;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestClass {
    GetService gs=new GetService();
    PostService ps=new PostService();
    Assertion as=new Assertion();

    @Test(priority = 1)
    public void test1() {
            ps.createToken("admin","password123",200);
    }

    @Test(priority = 2)
    public void test2() {
        Response resp=ps.createToken("admin","password111",200);
        as.errorCodeValidation(resp);
    }

    @Test(priority = 3)
    public void test3() {
        Response resp=ps.createToken("subadmin","password123",200);
        as.errorCodeValidation(resp);
    }

    @Test(priority = 4)
    public void test4() {
        Response resp=ps.createToken("","",200);
        as.errorCodeValidation(resp);
    }

    @Test(priority = 5)
    public void test5() {
        gs.getBookingIds();
    }

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
        gs.getBookingIdsByCheckin("2024-01-01");
    }

    @Test(priority = 10)
    public void test10() {
        gs.getBooking(85);
    }
}
