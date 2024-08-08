import Assertion.Assertion;
import Method.*;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TestClass1507 {
    PostService postservice=new PostService();
    Assertion as=new Assertion();

//    @AfterMethod
//    public void afterMethod(){
//        System.out.println("---------------------------------Test Ended Here---------------------------------------");
//    }

    @Test(priority = 1)
    public void testTokenGeneration() {//testTokenGeneration
        postservice.createToken("admin","password123",200);
    }

    @Test(priority = 2)
    public void testTokenGenerationWithInvalidPassword() {
        Response resp=postservice.createToken("admin","password111",200);
        as.responseBodyValidation(resp);
    }

    @Test(priority = 3)
    public void testTokenGenerationWithInvalidUsername() {
        Response resp=postservice.createToken("subadmin","password123",200);
        as.responseBodyValidation(resp);
    }

    @Test(priority = 4)
    public void testTokenGenerationWithoutUsernamePassword() {
        Response resp=postservice.createToken("","",200);
        as.responseBodyValidation(resp);
    }

    @Test(priority = 5)
    public void testGetBookingIds() {
        postservice.getBookingIds();
    }

}
