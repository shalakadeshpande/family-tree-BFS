import java.io.IOException;
import java.util.Scanner;

import familytree.FamilyTree;
import model.Relations;

public class Application {
	public static void main(String[] args) throws IOException {
		runFamilyTreeApp();
	}
	
	private static void runFamilyTreeApp() {
		FamilyTree familyTree = new FamilyTree();
		familyTree.init();

		String userChoice = "0";
		while (!userChoice.equals("4")) {
			System.out.println("---------------------");
			System.out.println("Select operation : ->");
			System.out.println("1 Add Child ");
			System.out.println("2 Add Spouse ");
			System.out.println("3 Get Relations ");
			System.out.println("4 Exit");
			System.out.println("Please enter your choice: ");
			Scanner scanner = new Scanner(System.in);
			userChoice = scanner.nextLine();

			if (userChoice.equals("1")) {
				System.out.println("Enter child name");
				String childName = scanner.nextLine();
				System.out.println("Enter child gender");
				String gender = scanner.nextLine();
				System.out.println("Enter Mother name");
				String motherName = scanner.nextLine();
				familyTree.addChild(motherName, childName, gender);
				System.out.println("updated tree: ");
				familyTree.printTree();
			} else if (userChoice.equals("2")) {
				System.out.println("Enter husband name");
				String husbandName = scanner.nextLine();
				System.out.println("Enter wife name");
				String wifeName = scanner.nextLine();
				familyTree.addSpouse(husbandName, wifeName);
				System.out.println("updated tree: ");
				familyTree.printTree();
			} else if (userChoice.equals("3")) {
				System.out.println("Enter person name");
				String personName = scanner.nextLine();
				System.out.println("Enter relation");
				String relation = scanner.nextLine();
				System.out.println("Person=" + personName + " relation=" + relation);
				switch (relation) {

				case "father":
					System.out.println(relation + "=" + familyTree.getFatherName(personName));
					break;
				case "mother":
					System.out.println(relation + "=" + familyTree.getMotherName(personName));
					break;
				case "brothers":
					System.out.println(relation + "=" + familyTree.listBrothers(personName));
					break;
				case "sisters":
					System.out.println(relation + "=" + familyTree.listSisters(personName));
					break;
				case "sons":
					System.out.println(relation + "=" + familyTree.listSons(personName));
					break;
				case "daughters":
					System.out.println(relation + "=" + familyTree.listDaughters(personName));
					break;
				case "cousins":
					System.out.println(relation + "=" + familyTree.listCousins(personName));
					break;
				case "aunt":
					System.out.println(relation + "=" + familyTree.getAunt(personName));
					break;
				case "uncle":
					System.out.println(relation + "=" + familyTree.getUncle(personName));
					break;
				case "grandfather":
					System.out.println(relation + "=" + familyTree.getGrandFather(personName));
					break;
				case "grandmother":
					System.out.println(relation + "=" + familyTree.getGrandMother(personName));
					break;
				default:
					System.out.println(relation + " is not known.");
					System.out.println("Allowed relations are : ");
					for (Relations eachRel : Relations.values()) {
						System.out.println(eachRel);
					}
					break;
				}
			}

		}
		System.out.println(familyTree);
		System.out.println("Terminated.");
	}

}
