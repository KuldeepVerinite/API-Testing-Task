package Method;

import org.testng.annotations.Test;

public class PTestClass2407 {

    PatchService ps = new PatchService();
    CreateTokenDeleteBooking ctdb = new CreateTokenDeleteBooking();

    @Test(priority = 1)
    public void PartialUpdateBooking(){
        ps.patchUpdateBooking("Sharad", 1230, 200,"Basic YWRtaW46cGFzc3dvcmQxMjM=");
    }

    @Test(priority = 2)
    public void partialBookingWithoutCookie(){
         String token =
        ctdb.createToken1("admin", "password123",200);
        ps.partialBookingCookie("Prajyot", 765, 403, "" );
    }

    @Test(priority = 3)
    public void partialBookingOldCookie(){
        String token1=
                ctdb.createToken1("admin","password123", 200);
        String token2=
                ctdb.createToken1("admin","password123", 200);
        ps.partialBookingCookie("Prajyot", 378, 200, token1);
        ps.partialBookingCookie("Me", 535, 403, token1);
    }


    @Test(priority = 4)
    public void partialBookingInvalidCookie(){
        String token=
                ctdb.createToken1("admin","password123", 200);
        ps.partialBookingCookie("Me", 535, 403, token+"sdfg" );
    }

    @Test(priority = 5)
    public void partialBookingValidAuth(){
        ps.patchUpdateBooking("You", 535, 200, "Basic YWRtaW46cGFzc3dvcmQxMjM=");
    }

}
