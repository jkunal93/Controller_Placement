import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Delay {

	private static final String FILENAME = "output.txt";

	public static void main(String[] args) {
		Random rand = new Random();
		int min = 50;
		int max = 100;
		int random=0;
		String temp="";
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
			for (int i = 0;i <100;i++){
				for (int j = 0;j<100;j++){
					random = rand.nextInt((max-min)+1)+min;
					temp = temp.concat(Integer.toString(random));
					temp = temp + ",";
				}
				temp = temp+"\n";
			}

			//String content = "This is the content to write into file\n";

			bw.write(temp);

			// no need to close it.
			//bw.close();

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}