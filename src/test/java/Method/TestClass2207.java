package Method;

import org.testng.annotations.Test;

public class TestClass2207 {
    GetService gs = new GetService();
    CreateTokenDeleteBooking ctdb = new CreateTokenDeleteBooking();
    DeleteService ds = new DeleteService();
    @Test
    public void deleteBooking(){
        gs.getBooking(638,200);
        ctdb.createToken1("admin","password123",200,100);
        gs.getBooking(200,404);
    }

    @Test
    public void deleteBookingWithAuth(){
        gs.getBooking(837,200);
        ds.deleteBookingUsingAuthorization(837,"Basic YWRtaW46cGFzc3dvcmQxMjM=",201);
    }

    @Test
    public void deleteBookingWithInvalidAuth(){
        gs.getBooking(265,200);
        ds.deleteBookingUsingAuthorization(265,"Basic YWRtaW46cGFzc3dvcmQ",403);
    }

    @Test
    public void deleteBookingWithoutHeaders(){
        gs.getBooking(1025,200);
        ds.deleteBookingWithoutHeaders(1025,403);
    }

}
