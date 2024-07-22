package Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TestClass1807 {
GetService gs=new GetService();
PutService ps=new PutService();
PatchService patchs=new PatchService();


@AfterMethod
public void afterMethod(){
    System.out.println("---------------------------------Test Ended Here---------------------------------------");
}

@Test       //  UB_001, UB_004
public void updateBooking(){

    gs.getBooking(150,200);
    ps.updateBooking("","",150);
    gs.getBooking(150,200);
}

@Test       //  PU_001, PU_002, PU_010
public void partialUpdateBooking(){
    gs.getBooking(100,200);
    patchs.patchUpdateBooking("Kuldeep","Pawar",100);
    gs.getBooking(100,200);
    }

}
