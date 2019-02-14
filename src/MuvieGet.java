import java.util.ArrayList;
import java.util.List;

public class MuvieGet {

	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		SendGet http = new SendGet();

		System.out.println("Testing 1 - Send Http GET request");
		List<String> listMov = new ArrayList<String>();
		listMov = http.getMovieTitles("rom");
		
		for (String string : listMov) {
			System.out.println(string);
		}
	}
	
	

}
