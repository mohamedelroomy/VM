package Tests;

import Pages.VM;
import org.testng.annotations.Test;
public class AddService extends TestBase {


    VM vm;
    @Test(priority = 3)
    public void addVisitLabService() throws InterruptedException {
        vm.waitForLoader();
        vm.addLabService("cbc");
    }
}
