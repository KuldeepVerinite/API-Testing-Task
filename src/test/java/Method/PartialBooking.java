package Method;

import org.testng.annotations.Test;

public class PartialBooking {
    
    APIService apiservice = new APIService();
//    PatchService apiservice = new PatchService();
//    CreateTokenDeleteBooking apiservice = new CreateTokenDeleteBooking();

    @Test(priority = 1)
    public void PartialUpdateBooking(){
        apiservice.patchUpdateBooking("Sharad", 1230, 200,"Basic YWRtaW46cGFzc3dvcmQxMjM=");
    }

    @Test(priority = 2)
    public void partialBookingWithoutCookie(){
         String token =
        apiservice.createToken1("admin", "password123",200);
        apiservice.partialBookingCookie("Prajyot", 765, 403, "" );
    }

    @Test(priority = 3) //failed case-same token should not reused but its taking same token 2nd time also
    public void partialBookingOldCookie(){
        String token1=
                apiservice.createToken1("admin","password123", 200);
        String token2=
                apiservice.createToken1("admin","password123", 200);
        apiservice.partialBookingCookie("Prajyot", 364, 200, token1);
        apiservice.partialBookingCookie("Me", 233, 403, token1);
    }


    @Test(priority = 4)
    public void partialBookingInvalidCookie(){
        String token=
                apiservice.createToken1("admin","password123", 200);
        apiservice.partialBookingCookie("Me", 535, 403, token+"sdfg" );
    }

    @Test(priority = 5)
    public void partialBookingValidAuth(){
        apiservice.patchUpdateBooking("You", 535, 200, "Basic YWRtaW46cGFzc3dvcmQxMjM=");
    }

    @Test(priority = 6)
    public void partialBookingInvalidAuth(){
        apiservice.patchUpdateBooking("You", 535, 403, "asdf");
    }

    @Test(priority = 7)
    public void partialBookingWithoutAuth(){
        apiservice.patchUpdateBooking("You", 535, 403, "");
    }

}
