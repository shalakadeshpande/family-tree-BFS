package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import model.Constants;
import model.FamilyTree;
import model.Person;

public class TreeUtil {
	public static void printTree(FamilyTree familyTree) {
		Set<Person> keys = familyTree.getParentChildMap().keySet();
		for (Person key : keys) {
			System.out.println(key + " : " + familyTree.getParentChildMap().get(key));
		}
	}

	public Person searchPersonByName(String personName, FamilyTree familyTree) {
		Map<Person, List<Person>> adjecency = familyTree.getParentChildMap();
		Set<Person> allPersonNodes = adjecency.keySet();
		for (Person person : allPersonNodes) {
			List<Person> allChildren = adjecency.get(person);
			List<Person> matched = allChildren.stream().filter(each -> personName.equalsIgnoreCase(each.getName())
					|| personName.equalsIgnoreCase(each.getSpouseName())).collect(Collectors.toList());
			if (null != matched && !matched.isEmpty())
				return matched.get(0);
		}

		return null;
	}

	public String getParentName(String personName, String type, FamilyTree familyTree) {
		Person parent = null;
		Map<Person, List<Person>> adjecency = familyTree.getParentChildMap();
		Set<Person> allPersonNodes = adjecency.keySet();
		for (Person person : allPersonNodes) {
			List<String> allChildrenNames = adjecency.get(person).stream().map(Person::getName)
					.collect(Collectors.toList());
			if (allChildrenNames.contains(personName)) {
				parent = person;
				break;
			}

		}
		if (parent != null) {
			return Constants.FATHER.equalsIgnoreCase(type) ? parent.getName() : parent.getSpouseName();

		}
		return null;
	}

	public List<String> listSiblingsByType(String personName, String siblingType, FamilyTree familyTree) {

		String gender = Constants.SISTERS.equalsIgnoreCase(siblingType) ? Constants.FEMALE : Constants.MALE;
		List<Person> siblings = getAllSiblings(personName, familyTree);
		if (siblings == null || siblings.isEmpty()) {
			return Collections.emptyList();
		}

		List<Person> matchedSiblings = siblings.stream()
				.filter(sibling -> !sibling.getName().equalsIgnoreCase(personName)
						&& sibling.getGender().equalsIgnoreCase(gender))
				.collect(Collectors.toList());
		return matchedSiblings.stream().map(Person::getName).collect(Collectors.toList());

	}

	public List<String> listChildren(String personName, String childrenType, FamilyTree familyTree) {
		String gender = Constants.SONS.equalsIgnoreCase(childrenType) ? Constants.MALE : Constants.FEMALE;
		Map<Person, List<Person>> adjecency = familyTree.getParentChildMap();
		Person personObj = searchPersonByName(personName, familyTree);
		if (personObj == null) {
			System.out.println(personName + " Not found! first add as child to family tree.");
			return Collections.emptyList();
		}
		List<Person> children = adjecency.get(personObj);
		if (children != null && !children.isEmpty()) {
			return children.stream().filter(child -> gender.equalsIgnoreCase(child.getGender())).map(Person::getName)
					.collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	public List<String> listCousins(String personName, FamilyTree familytree) {
		Map<Person, List<Person>> adjecency = familytree.getParentChildMap();
		List<String> cousins = new ArrayList<>();
		String father = getParentName(personName, Constants.FATHER, familytree);
		List<Person> fathersSiblings = getAllSiblings(father, familytree).stream()
				.filter(sibling -> !sibling.getName().equals(father)).collect(Collectors.toList());
		for (Person eachSibling : fathersSiblings) {
			List<Person> childList = adjecency.get(eachSibling);
			if (childList != null && !childList.isEmpty()) {
				cousins.addAll(childList.stream().map(Person::getName).collect(Collectors.toList()));
			}

		}

		return cousins;
	}

	public List<String> getCousinParent(String personName, String type, FamilyTree familyTree) {
		String father = getParentName(personName, Constants.FATHER, familyTree);
		List<Person> matchedParentList;
		if (Constants.AUNT.equalsIgnoreCase(type)) {
			matchedParentList = getAllSiblings(father, familyTree).stream()
					.filter(sibling -> sibling.getGender().equalsIgnoreCase(Constants.FEMALE))
					.collect(Collectors.toList());
		} else {
			matchedParentList = getAllSiblings(father, familyTree).stream()
					.filter(sibling -> sibling.getGender().equalsIgnoreCase(Constants.MALE)
							&& !sibling.getName().equalsIgnoreCase(father))
					.collect(Collectors.toList());
		}

		return matchedParentList.stream().map(Person::getName).collect(Collectors.toList());
	}

	public String getGrandParents(String personName, String type, FamilyTree familyTree) {
		String father = getParentName(personName, Constants.FATHER, familyTree);
		if (father == null || father.isEmpty()) {
			return null;
		}
		return Constants.GRANDMOTHER.equalsIgnoreCase(type) ? getParentName(father, Constants.MOTHER, familyTree)
				: getParentName(father, Constants.FATHER, familyTree);
	}

	private List<Person> getAllSiblings(String personName, FamilyTree familyTree) {
		Map<Person, List<Person>> adjecency = familyTree.getParentChildMap();
		String father = getParentName(personName, Constants.FATHER, familyTree);
		Person personObj = searchPersonByName(father, familyTree);
		return adjecency.get(personObj);
	}

	public static void init(FamilyTree familytree) {
		Person root = new Person("Evan", Constants.MALE);
		root.setSpouseName("Diana");

		Map<Person, List<Person>> rootAdjList = new HashMap<>();
		rootAdjList.put(null, Arrays.asList(root));

		List<Person> children = new ArrayList<>();
		Person child1 = new Person("Alex", Constants.MALE);
		child1.setSpouseName("Nancy");
		Person child2 = new Person("John", Constants.MALE);
		child2.setSpouseName("Niki");
		Person child3 = new Person("Joe", Constants.MALE);
		Person child4 = new Person("Nisha", Constants.FEMALE);

		children.add(child1);
		children.add(child2);
		children.add(child3);
		children.add(child4);

		rootAdjList.put(root, children);
		familytree.setParentChildMap(rootAdjList);

		System.out.println("Initial tree - ");
		printTree(familytree);
	}
}
