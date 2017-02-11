package core;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import org.testng.ITest;
import org.testng.annotations.*;

public class DPCSVTMTest implements ITest { // String csvFile = "a.csv";
	String csvFile = System.getProperty("testcases"); // mvn site
														// -Dtestcases="./input.csv"
	private String test_name = "";

	public String getTestName() {
		return test_name;
	}

	private void setTestName(String a) {
		test_name = a;
	}

	@BeforeMethod(alwaysRun = true)
	public void bm(Method method, Object[] parameters) {
		setTestName(method.getName());
		Override a = method.getAnnotation(Override.class);
		String testCaseId = (String) parameters[a.id()];
		setTestName(testCaseId);
	}

	@DataProvider(name = "dp")
	public Iterator<String[]> a2d() throws InterruptedException, IOException {
		String cvsLine = "";
		String[] a = null;
		ArrayList<String[]> al = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		while ((cvsLine = br.readLine()) != null) {
			a = cvsLine.split(",");
			al.add(a);
		}
		br.close();
		return al.iterator();
	}

	@Override // @Override(id = 0)
	@Test(dataProvider = "dp")
	public void test(String a, String b, String c, String d) {
		assertThat(Integer.parseInt(b) + Integer.parseInt(c), is(Integer.parseInt(d)));
	}
}
