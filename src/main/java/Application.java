import java.io.IOException;
import java.util.Scanner;

import model.FamilyTree;
import service.ITreeService;
import service.TreeServiceFactory;
import util.TreeUtil;

public class Application {
	public static void main(String[] args) throws IOException {
		runFamilyTreeApp();
	}

	private static void runFamilyTreeApp() {
		FamilyTree tree = new FamilyTree();
		TreeUtil.init(tree);
		Scanner scanner = new Scanner(System.in);

		String userChoice = "0";
		while (!"4".equals(userChoice)) {
			System.out.println("---------------------");
			System.out.println("Select operation : ->");
			System.out.println("1 Add Child ");
			System.out.println("2 Add Spouse ");
			System.out.println("3 Get Relations ");
			System.out.println("4 Exit");
			System.out.println("Please enter your choice: ");

			userChoice = scanner.nextLine();

			if (!"4".equals(userChoice)) {

				ITreeService service = TreeServiceFactory.getServiceInstance(userChoice);

				service.process(tree);
			}

		}
		System.out.println("Terminated.");
		// scanner.close();
	}

}
