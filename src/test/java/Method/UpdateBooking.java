package Method;

import org.testng.annotations.Test;

public class UpdateBooking {

    PostService ps = new PostService();

    @Test(priority = 1)
    public void createInvalidBody(){
        ps.createBooking("Prajyot", "Sidwadkar", 500);
    }

    PutService ps1 = new PutService();

    @Test(priority = 2)
    public void updateInvalidBody(){
        ps1.updateBooking("Me", "You", 1332, 400, "Basic YWRtaW46cGFzc3dvcmQxMjM=");
    }

    @Test(priority = 3)
    public void updateBookingWithoutAuthHeader(){
        ps1.updateBooking("You", "Me", 955, 403, "");
    }

    @Test(priority = 4)
    public void updateBookingInvalidAuthHeader(){
        ps1.updateBooking("You", "Me", 955, 403, "wehfksbfjwejfnwejfwef");
    }

    CreateTokenDeleteBooking ctdb = new CreateTokenDeleteBooking();

    @Test(priority = 5)
    public void updateBookingCookieheader(){
        String token =
        ctdb.createToken1("admin", "password123", 200);
        ps1.updateBookingCookie("I", "Me", 12, 403, "" );
    }

    @Test(priority = 6)
    public void updateBookingInvalidCookieheader(){
        String token =
                ctdb.createToken1("admin", "password123", 200);
        ps1.updateBookingCookie("I", "Me", 12, 403, token+"dfgdfg" );
    }

}
