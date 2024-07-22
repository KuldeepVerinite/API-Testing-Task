import Assertion.Assertion;
import Method.*;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TestClass1507 {
    GetService gs=new GetService();
    PostService ps=new PostService();
    Assertion as=new Assertion();

    @AfterMethod
    public void afterMethod(){
        System.out.println("---------------------------------Test Ended Here---------------------------------------");
    }

    @Test(priority = 1)
    public void test1() {
            ps.createToken("admin","password123",200);
    }

    @Test(priority = 2)
    public void test2() {
        Response resp=ps.createToken("admin","password111",200);
        as.responseBodyValidation(resp);
    }

    @Test(priority = 3)
    public void test3() {
        Response resp=ps.createToken("subadmin","password123",200);
        as.responseBodyValidation(resp);
    }

    @Test(priority = 4)
    public void test4() {
        Response resp=ps.createToken("","",200);
        as.responseBodyValidation(resp);
    }

    @Test(priority = 5)
    public void test5() {
        gs.getBookingIds();
    }

}
