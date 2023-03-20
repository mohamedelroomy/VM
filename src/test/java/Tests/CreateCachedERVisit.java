package Tests;

import Pages.CompletePatientInfo;
import Pages.Dashboard;
import Pages.Login;
import Pages.VM;
import org.testng.Assert;
import org.testng.annotations.Test;
@Test(groups = "createER")
public class CreateCachedERVisit extends TestBase {
    Login login;
    Dashboard dashboard;
    VM vm;
    String username = "romy";
    String pass = "2579";
    String expectedDashboardURL = "http://dev-testing.andalusiagroup.net:7090/dashboard";
    CompletePatientInfo cp;
    String arabicFirstName = "حافظ";
    String arabicSecondName = "سعد";
    String arabicThirdName = "سعيد";
    String arabicFourthName = "رامي";
    String regionCode = "427";
    String phone = "01010133582";

    @Test(priority = 0)
    public void userCanLoginSuccessfully () {
        login =new Login(driver);
        login.insertUserNameAndPass(username,pass);
        dashboard = new Dashboard(driver);
        Assert.assertEquals(dashboard.getDashboardURL(), expectedDashboardURL);
    }
    @Test(dependsOnMethods = "userCanLoginSuccessfully",priority = 1)
    public void userNavigateToVM () throws InterruptedException {
        dashboard.navigateToVM();
        vm = new VM(vmFrame());
        vm.waitForLoader();
        Assert.assertTrue(vm.getManagePatientVisitsHeader().contains("Manage Patient Visits"));
    }
    @Test(priority = 2)
    public void userCanCreateCashedERVisit() throws InterruptedException {
        vm.selectVisitClass(4);
        vm.addNewVirtualVisit();
        vm.clickOnCompletePatientInfo();
        vm.waitForLoader();
        cp = new CompletePatientInfo(mpiFrame());
        cp.insertArabicPatientName(arabicFirstName,arabicSecondName,arabicThirdName,arabicFourthName);
        cp.setAge("55","4");
        Thread.sleep(2000);
        cp.selectGender('m');
        cp.selectMaritalStatus('m');
        cp.selectRegionCode(regionCode);
        cp.insertPhoneNumber(phone);
        cp.saveAndClose();
        mpiFrame.switchTo().parentFrame();
        vm.waitForLoader();
        vm.createVisitAndConfirmArrival();
    }

}
