package service;

import java.util.Scanner;

import model.FamilyTree;
import model.Person;
import util.TreeUtil;

public class AddSpouseTreeService implements ITreeService {
	TreeUtil treeUtil;

	public AddSpouseTreeService() {
		this.treeUtil = new TreeUtil();
	}

	private boolean addSpouse(FamilyTree tree, String personName, String spouseName) {

		Person matchedPerson = treeUtil.searchPersonByName(personName, tree);
		if (matchedPerson == null) {
			matchedPerson = treeUtil.searchPersonByName(spouseName, tree);
			if (matchedPerson != null) {
				matchedPerson.setSpouseName(personName);
				System.out.println("Welcome " + spouseName + "'s spouse to the family!" + personName);
				return true;
			} else {
				System.out.println(
						"Person not found! " + personName + " or " + spouseName + " needs to be added as child first.");
				return false;
			}
		} else {
			matchedPerson.setSpouseName(spouseName);
			System.out.println("Welcome " + personName + "'s wife to the family!" + spouseName);
			return true;
		}

	}

	@Override
	public void process(FamilyTree tree) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter husband name");
		String husbandName = scanner.nextLine();
		System.out.println("Enter wife name");
		String wifeName = scanner.nextLine();
		this.addSpouse(tree, husbandName, wifeName);
		System.out.println("updated tree: ");
		treeUtil.printTree(tree);

	}

}
