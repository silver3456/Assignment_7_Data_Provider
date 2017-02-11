package core;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import java.io.*;
import java.util.*;
import org.testng.annotations.*;

public class DPCSVTest {
	String csvFile = "C:/Users/avsel/input.csv";

	@DataProvider(name = "dp")
	public Iterator<String[]> a2d() throws InterruptedException, IOException {
		String cvsLine = ""; // a,b,c,d
		String[] a = null; // ["a","b","c","d"]
		ArrayList<String[]> al = new ArrayList<>();// [["a","b","c","d"],["a","b","c","d"]]
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		while ((cvsLine = br.readLine()) != null) {
			a = cvsLine.split(",");
			al.add(a);
		}
		br.close();
		return al.iterator();
	}

	@Test(dataProvider = "dp")
	public void test(String a, String b, String c, String d) {
		System.out.println(a + ", " + b + ", " + c + ", " + d);
		assertThat(Integer.parseInt(b) + Integer.parseInt(c), is(Integer.parseInt(d)));
	}
}
