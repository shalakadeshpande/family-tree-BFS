package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.FamilyTree;
import model.Person;
import util.TreeUtil;

public class ChildTreeService implements ITreeService {
	TreeUtil treeUtil;

	public ChildTreeService() {
		this.treeUtil = new TreeUtil();
	}

	protected boolean addChild(FamilyTree tree, String personName, String childName, String gender) {
		Person matchedPerson = treeUtil.searchPersonByName(personName, tree);
		if (matchedPerson == null) {
			System.out.println(personName + " needs to be added to family tree first!");
			return false;
		}
		List<Person> matchedPersonChildren = tree.getParentChildMap().get(matchedPerson);
		if (matchedPersonChildren == null) {
			matchedPersonChildren = new ArrayList<>();
		}
		matchedPersonChildren.add(new Person(childName, gender));
		tree.getParentChildMap().put(matchedPerson, matchedPersonChildren);

		System.out.println("Welcome " + personName + "'s child to the family - " + childName);

		System.out.println("updated tree: ");
		TreeUtil.printTree(tree);
		return true;
	}

	@Override
	public boolean process(FamilyTree tree) {
		Scanner scanner = new Scanner(System.in);

		String childName;
		String gender;
		String motherName;
		System.out.println("Enter child name");
		childName = scanner.nextLine();
		System.out.println("Enter child gender");
		gender = scanner.nextLine();
		System.out.println("Enter Mother name");
		motherName = scanner.nextLine();
		return this.addChild(tree, motherName, childName, gender);

	}

}
