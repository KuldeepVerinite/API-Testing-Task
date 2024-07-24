package Method;

import org.testng.annotations.Test;

public class DeleteBooking {

    DeleteService ds = new DeleteService();
    GetService gs = new GetService();
    PutService ps = new PutService();
    CreateTokenDeleteBooking ctdb = new CreateTokenDeleteBooking();

    @Test(priority = 1)
    public void deleteBookingCookie(){
        String token =
                ctdb.createToken1("admin","password123",200);
        ds.deleteBooking(1672, token+"qwety", 403);
    }

    @Test(priority = 2)
    public void deleteBookingWithoutCookie(){
        String token =
                ctdb.createToken1("admin", "password123", 200);
        ds.deleteBooking(3071, "", 403);
    }

    @Test(priority = 3)
    public void deleteBookingValidCookie(){
        String token =
                ctdb.createToken1("admin", "password123", 200);
        ds.deleteBooking(702, token, 201);
    }

    @Test(priority = 4)
    public void getId(){
        gs.getBooking(702,404);
    }

    @Test(priority = 5)
    public void updateDeleted(){
        ps.updateBooking("ps", "sp", 702, 405, "Basic YWRtaW46cGFzc3dvcmQxMjM=");
    }



}
