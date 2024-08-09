import Assertion.Assertion;
import Method.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class TestClass1507 {
    Service service =new Service();
    Assertion assertion =new Assertion();

//    @AfterMethod
//    public void afterMethod(){
//        System.out.println("---------------------------------Test Ended Here---------------------------------------");
//    }

    @Test(priority = 1)
    public void testTokenGeneration() {//testTokenGeneration
        service.createToken("admin","password123",200);
    }

    @Test(priority = 2)
    public void testTokenGenerationWithInvalidPassword() {
        Response resp= service.createToken("admin","password111",200);
        assertion.responseBodyValidationForError(resp);
    }

    @Test(priority = 3)
    public void testTokenGenerationWithInvalidUsername() {
        Response resp= service.createToken("subadmin","password123",200);
        assertion.responseBodyValidationForError(resp);
    }

    @Test(priority = 4)
    public void testTokenGenerationWithoutUsernamePassword() {
        Response resp= service.createToken("","",200);
        assertion.responseBodyValidationForError(resp);
    }

    @Test(priority = 5)
    public void testGetBookingIds() {
        service.getBookingIds();
    }

}
