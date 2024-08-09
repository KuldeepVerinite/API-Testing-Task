package Method;

import org.testng.annotations.Test;

public class DeleteBooking {

    APIService apiservice = new APIService();
//    DeleteService apiservice = new DeleteService();
//    GetService apiservice = new GetService();
//    PutService apiservice = new PutService();
//    CreateTokenDeleteBooking apiservice = new CreateTokenDeleteBooking();

    @Test(priority = 1)
    public void deleteBookingCookie(){
        String token =
                apiservice.createToken1("admin","password123",200);
        apiservice.deleteBooking(1672, token+"qwety", 403);
    }

    @Test(priority = 2)
    public void deleteBookingWithoutCookie(){
        String token =
                apiservice.createToken1("admin", "password123", 200);
        apiservice.deleteBooking(3071, "", 403);
    }

    @Test(priority = 3)
    public void deleteBookingValidCookie(){
        String token =
                apiservice.createToken1("admin", "password123", 200);
        apiservice.deleteBooking(702, token, 201);
    }

    @Test(priority = 4)
    public void getId(){
        apiservice.getBooking(702,404);
    }

    @Test(priority = 5)
    public void updateDeleted(){
        apiservice.updateBooking("apiservice", "sp", 702, 405, "Basic YWRtaW46cGFzc3dvcmQxMjM=");
    }

    @Test(priority = 6)
    public void deleteMultipleId(){
        String token =
                apiservice.createToken1("admin", "password123", 200);
        apiservice.deleteBookingIds("176,85", token, 201);
    }

    @Test(priority = 7)
    public void getDeleteMultipleId(){
        String token =
                apiservice.createToken1("admin", "password123", 200);
        apiservice.getMulipleBookingIds("176,85", 404);
    }

    @Test(priority = 8)
    public void updateDeleteMultiple(){
        apiservice.updateMultipleBooking("as","qw","176,85", 405,
                "Basic YWRtaW46cGFzc3dvcmQxMjM=");
    }

}
