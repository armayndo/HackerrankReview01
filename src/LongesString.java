
public class LongesString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getLongesString("Time to write great code"));
	}

	public static String getLongesString(String string) {
		String[] strArr = string.split(" ");

		String curWOrd = "";

		for (int i = 0; i < strArr.length; i++) {
			if (strArr[i].length() % 2 == 0)
				if (strArr[i].length() > curWOrd.length()) {
					curWOrd = strArr[i];

				}
		}

		if (curWOrd.length() == 0) {
			return "00";
		} else {

			return curWOrd;
		}

	}

}
