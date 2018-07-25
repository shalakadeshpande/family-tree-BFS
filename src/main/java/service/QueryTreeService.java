package service;

import java.util.Scanner;

import model.Constants;
import model.FamilyTree;
import model.Relations;
import util.TreeUtil;

public class QueryTreeService implements ITreeService {

	private TreeUtil treeUtil;

	public QueryTreeService() {
		this.treeUtil = new TreeUtil();
	}

	@Override
	public boolean process(FamilyTree tree) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter person name");
		String personName = scanner.nextLine();
		System.out.println("Enter relation");
		String relation = scanner.nextLine();
		System.out.println("Person=" + personName + " relation=" + relation);
		boolean status = true;
		switch (relation) {

		case "father":
			System.out.println(relation + "=" + treeUtil.getParentName(personName, Constants.FATHER, tree));
			break;
		case "mother":
			System.out.println(relation + "=" + treeUtil.getParentName(personName, Constants.MOTHER, tree));
			break;
		case "brothers":
			System.out.println(relation + "=" + treeUtil.listSiblingsByType(personName, Constants.BROTHERS, tree));
			break;
		case "sisters":
			System.out.println(relation + "=" + treeUtil.listSiblingsByType(personName, Constants.SISTERS, tree));
			break;
		case "sons":
			System.out.println(relation + "=" + treeUtil.listChildren(personName, Constants.SONS, tree));
			break;
		case "daughters":
			System.out.println(relation + "=" + treeUtil.listChildren(personName, Constants.DAUGHTERS, tree));
			break;
		case "cousins":
			System.out.println(relation + "=" + treeUtil.listCousins(personName, tree));
			break;
		case "aunt":
			System.out.println(relation + "=" + treeUtil.getCousinParent(personName, Constants.AUNT, tree));
			break;
		case "uncle":
			System.out.println(relation + "=" + treeUtil.getCousinParent(personName, Constants.UNCLE, tree));
			break;
		case "grandfather":
			System.out.println(relation + "=" + treeUtil.getGrandParents(personName, Constants.GRANDFATHER, tree));
			break;
		case "grandmother":
			System.out.println(relation + "=" + treeUtil.getGrandParents(personName, Constants.GRANDMOTHER, tree));
			break;
		default:
			status = false;
			System.out.println(relation + " is not known.");
			System.out.println("Allowed relations are : ");
			for (Relations eachRel : Relations.values()) {
				System.out.println(eachRel);
			}
			break;
		}
		return status;
	}

}
