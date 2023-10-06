import org.testng.annotations.DataProvider;
public class DataProviderClass {
    @org.testng.annotations.DataProvider(name = "loginParameters")
    public static Object[][] provideCredentials() {
        return new Object[][] {
                {"anna.suvorova@testpro.io","te$t$tudent"}
        };
    }
}
