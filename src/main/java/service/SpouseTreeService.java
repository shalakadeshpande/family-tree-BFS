package service;

import java.util.Scanner;

import model.FamilyTree;
import model.Person;
import util.TreeUtil;

public class SpouseTreeService implements ITreeService {
	TreeUtil treeUtil;

	public SpouseTreeService() {
		this.treeUtil = new TreeUtil();
	}

	protected boolean addSpouse(FamilyTree tree, String personName, String spouseName) {

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
	public boolean process(FamilyTree tree) {
		Scanner scanner = new Scanner(System.in);
		String husbandName;
		String wifeName;
		try {
			System.out.println("Enter husband name");
			husbandName = scanner.nextLine();
			System.out.println("Enter wife name");
			wifeName = scanner.nextLine();
		} finally {
			scanner.close();
		}
		return this.addSpouse(tree, husbandName, wifeName);

	}

}
