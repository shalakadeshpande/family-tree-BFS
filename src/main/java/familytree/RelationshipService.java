package familytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import model.Constants;
import model.Person;

public class RelationshipService {

	public String getParentName(String personName, String type, FamilyTree familyTree) {
		Person parent = null;
		Map<Person, List<Person>> adjecency = getAdjecency(familyTree);
		Set<Person> allPersonNodes = adjecency.keySet();
		for (Person person : allPersonNodes) {
			List<String> allChildrenNames = adjecency.get(person).stream().map(Person::getName)
					.collect(Collectors.toList());
			if (allChildrenNames.contains(personName)) {
				parent = person;
			}

		}
		if (parent != null) {
			return Constants.FATHER.equalsIgnoreCase(type) ? parent.getName() : parent.getSpouseName();

		}
		return null;
	}

	private Map<Person, List<Person>> getAdjecency(FamilyTree familyTree) {
		return familyTree.getAdjecency();
	}

	public Person searchPersonByName(String personName, FamilyTree familyTree) {
		Map<Person, List<Person>> adjecency = getAdjecency(familyTree);
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

	public List<String> listSiblingsByType(String personName, String siblingType, FamilyTree familyTree) {

		String gender = "sisters".equalsIgnoreCase(siblingType) ? "F" : "M";
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
		String gender = "sons".equalsIgnoreCase(childrenType) ? "M" : "F";
		Map<Person, List<Person>> adjecency = getAdjecency(familyTree);
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

	private List<Person> getAllSiblings(String personName, FamilyTree familyTree) {
		Map<Person, List<Person>> adjecency = getAdjecency(familyTree);
		String father = getParentName(personName, Constants.FATHER, familyTree);
		Person personObj = searchPersonByName(father, familyTree);
		return adjecency.get(personObj);
	}

	public List<String> listCousins(String personName, FamilyTree familytree) {
		Map<Person, List<Person>> adjecency = getAdjecency(familytree);
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
		List<Person> matchedParentList = new ArrayList<>();
		if ("aunt".equalsIgnoreCase(type)) {
			matchedParentList = getAllSiblings(father, familyTree).stream()
					.filter(sibling -> sibling.getGender().equalsIgnoreCase("F")).collect(Collectors.toList());
		} else {
			matchedParentList = getAllSiblings(father, familyTree).stream().filter(
					sibling -> sibling.getGender().equalsIgnoreCase("M") && !sibling.getName().equalsIgnoreCase(father))
					.collect(Collectors.toList());
		}

		return matchedParentList.stream().map(Person::getName).collect(Collectors.toList());
	}

	public String getGrandParents(String personName, String type, FamilyTree familyTree) {
		String father = getParentName(personName, Constants.FATHER, familyTree);
		if (father == null || father.isEmpty()) {
			return null;
		}
		return "grandmother".equalsIgnoreCase(type) ? getParentName(father, "Mother", familyTree)
				: getParentName(father, Constants.FATHER, familyTree);
	}

}
