import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import familytree.FamilyTree;
import model.Person;
import model.Relations;

public class Application {
	public static void main(String[] args) throws IOException {
		familyTreeApp();
	}
	
	private static void familyTreeApp() {
		FamilyTree familyTree = new FamilyTree();
		init(familyTree);

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
					System.out.println(relation + "=" + familyTree.getBrothers(personName));
					break;
				case "sisters":
					System.out.println(relation + "=" + familyTree.getSisters(personName));
					break;
				case "sons":
					System.out.println(relation + "=" + familyTree.getSons(personName));
					break;
				case "daughters":
					System.out.println(relation + "=" + familyTree.getDaughters(personName));
					break;
				case "cousins":
					System.out.println(relation + "=" + familyTree.getCousins(personName));
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

	private static void init(FamilyTree familyTree) {

		Person root = new Person("Evan", "M");
		root.setSpouseName("Diana");

		Map<Person, List<Person>> rootAdjList = new HashMap<>();
		rootAdjList.put(null, Arrays.asList(root));

		List<Person> children = new ArrayList<>();
		Person child1 = new Person("Alex", "M");
		child1.setSpouseName("Nancy");
		Person child2 = new Person("John", "M");
		child2.setSpouseName("Niki");
		Person child3 = new Person("Joe", "M");
		Person child4 = new Person("Nisha", "F");

		children.add(child1);
		children.add(child2);
		children.add(child3);
		children.add(child4);

		rootAdjList.put(root, children);
		familyTree.setAdjecency(rootAdjList);

		System.out.println("Initial tree - ");
		familyTree.printTree();
	}
}
