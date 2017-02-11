package core;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import java.lang.reflect.Method;
import org.testng.ITest;
import org.testng.annotations.*;

public class TMTest implements ITest {
	private String test_name = "";

	private void setTestName(String a) {
		test_name = a;
	}

	public String getTestName() {
		return test_name;
	}

	@BeforeMethod(alwaysRun = true)
    public void bm(Method method, Object[] parameters) {
           setTestName(method.getName());
           Override a = method.getAnnotation(Override.class);
if (!String.class.isAssignableFrom(parameters[a.id()].getClass())) {
throw new IllegalArgumentException("Incorrect parameter type - " + parameters[a.id()].getClass().getSimpleName() +  ", it must be String");} 
           String testCaseId = (String) parameters[a.id()];
           setTestName(testCaseId);}

	@DataProvider(name = "dp")
	public static Object[][] dp() {
		return new Object[][] { { "TC-01.01 (Summary of 1 and 2 equals 3)", "1", "2", "3" },
				{ "TC-01.02 (Summary of 3 and 4 equals 7)", "3", "4", "7" },
				{ "TC-01.03 (Summary of 5 and 6 equals 11)", "5", "6", "11" },
				{ "TC-01.04 (Summary of 7 and 8 equals 15)", "7", "8", "15" } }; // 0
																					// 1
																					// 2
																					// 3
	} // a b c d

	@Override // @Override(id = 0)
	@Test(dataProvider = "dp")
	public void test(String a, String b, String c, String d) {
		assertThat(Integer.parseInt(b) + Integer.parseInt(c), is(Integer.parseInt(d)));
	}
}
