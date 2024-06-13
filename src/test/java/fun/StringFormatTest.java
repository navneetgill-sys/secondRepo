package fun;

import org.testng.annotations.Test;

public class StringFormatTest {



	String xpath = "//tbody/tr[" + 2 + "]/tf";


	@Test
	public void t1() {
		System.out.println(xpath);
	}

	@Test
	public void t2() {
		String newxPath = "//tbody/tr[%d]/td";
		System.out.println(newxPath);
		String tempxpath = String.format(newxPath, 90);

		System.out.println("merge  me tempxpath");
		


		for (int i = 1; i < 30; i++) {
		 String realXpath = String.format(newxPath, i);
		System.out.println(realXpath);
		}
	}
	@Test
	public void t3() {
		String oldString = "myname is %s ";
		String name = String.format(oldString, "navneet");
		System.out.println(name);
	}

}
