package Method;

import org.testng.annotations.Test;

public class UpdateBooking {

    APIService apiservice = new APIService();

    @Test(priority = 1)
    public void createInvalidBody(){
        apiservice.createBooking("Prajyot", "Sidwadkar", 500);
    }

//    PutService apiservice = new PutService();

    @Test(priority = 2)
    public void updateInvalidBody(){
        apiservice.updateBookingForInvalidBody("Me", "You", 1332, 400, "Basic YWRtaW46cGFzc3dvcmQxMjM=");
    }

    @Test(priority = 3)
    public void updateBookingWithoutAuthHeader(){
        apiservice.updateBooking("You", "Me", 955, 403, "");
    }

    @Test(priority = 4)
    public void updateBookingInvalidAuthHeader(){
        apiservice.updateBooking("You", "Me", 955, 403, "wehfksbfjwejfnwejfwef");
    }
    

    @Test(priority = 5)
    public void updateBookingCookieheader(){
        String token =
        apiservice.createToken1("admin", "password123", 200);
        apiservice.updateBookingCookie("I", "Me", 12, 403, "" );
    }

    @Test(priority = 6)
    public void updateBookingInvalidCookieheader(){
        String token =
                apiservice.createToken1("admin", "password123", 200);
        apiservice.updateBookingCookie("I", "Me", 12, 403, token+"dfgdfg" );
    }



}
