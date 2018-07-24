package familytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import model.Person;

public class RelationshipService {

	public String getFatherName(String personName, FamilyTree familyTree) {
		Map<Person, List<Person>> adjecency = getAdjecency(familyTree);
		Set<Person> allPersonNodes = adjecency.keySet();
		for (Person person : allPersonNodes) {
			List<String> allChildrenNames = adjecency.get(person).stream().map(eachPerson -> eachPerson.getName())
					.collect(Collectors.toList());
			if (allChildrenNames.contains(personName)) {
				return (person != null) ? person.getName() : personName + " is oldest Person known in family tree";
			}
		}
		return null;
	}

	private Map<Person, List<Person>> getAdjecency(FamilyTree familyTree) {
		Map<Person, List<Person>> adjecency = familyTree.getAdjecency();
		return adjecency;
	}

	public String getMotherName(String personName, FamilyTree familyTree) {
		Map<Person, List<Person>> adjecency = getAdjecency(familyTree);
		Set<Person> allPersonNodes = adjecency.keySet();
		for (Person person : allPersonNodes) {
			List<String> allChildrenNames = adjecency.get(person).stream().map(eachPerson -> eachPerson.getName())
					.collect(Collectors.toList());
			if (allChildrenNames.contains(personName)) {
				return (person != null) ? person.getSpouseName()
						: personName + " is oldest Person known in family tree";
			}
		}
		return null;
	}

	public Person searchPerosnByWifeName(String personName, FamilyTree familyTree) {

		Map<Person, List<Person>> adjecency = getAdjecency(familyTree);
		Person matchedPerson = null;
		Set<Person> allPersonNodes = adjecency.keySet();
		for (Person person : allPersonNodes) {
			List<Person> allChildren = adjecency.get(person);
			List<Person> matched = allChildren.stream()
					.filter(each -> each.getSpouseName() != null && each.getSpouseName().equalsIgnoreCase(personName))
					.collect(Collectors.toList());
			if (!(matched == null || matched.isEmpty())) {
				return matched.get(0);
			}
		}

		return matchedPerson;
	}

	public Person searchPerosnByName(String personName, FamilyTree familyTree) {
		Map<Person, List<Person>> adjecency = getAdjecency(familyTree);
		Person matchedPerson = null;
		Set<Person> allPersonNodes = adjecency.keySet();
		for (Person person : allPersonNodes) {
			List<Person> allChildren = adjecency.get(person);
			List<Person> matched = allChildren.stream().filter(each -> each.getName().equalsIgnoreCase(personName))
					.collect(Collectors.toList());
			if (null != matched && !matched.isEmpty())
				return matched.get(0);
		}

		return matchedPerson;
	}

	public List<String> listBrothers(String personName, FamilyTree familyTree) {
		List<Person> siblings = listSiblings(personName, familyTree);
		if (siblings != null && !siblings.isEmpty()) {
			List<Person> brothers = siblings.stream().filter(sibling -> !sibling.getName().equalsIgnoreCase(personName)
					&& sibling.getGender().equalsIgnoreCase("M")).collect(Collectors.toList());
			return brothers.stream().map(brother -> brother.getName()).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	public List<String> listSisters(String personName, FamilyTree familyTree) {
		List<Person> siblings = listSiblings(personName, familyTree);
		if (siblings != null && !siblings.isEmpty()) {
			List<Person> sisters = siblings.stream().filter(sibling -> !sibling.getName().equalsIgnoreCase(personName)
					&& sibling.getGender().equalsIgnoreCase("F")).collect(Collectors.toList());
			return sisters.stream().map(brother -> brother.getName()).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	public List<String> listSons(String personName, FamilyTree familyTree) {
		Map<Person, List<Person>> adjecency = getAdjecency(familyTree);
		Person personObj = searchPerosnByName(personName, familyTree);
		if (personObj == null) {
			System.out.println(personName + " Not found! first add as child to family tree.");
			return Collections.emptyList();
		}
		List<Person> children = adjecency.get(personObj);
		if (children != null && !children.isEmpty()) {
			return children.stream().filter(child -> child.getGender().equalsIgnoreCase("M"))
					.map(child -> child.getName()).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	public List<String> listDaughters(String personName, FamilyTree familyTree) {
		Map<Person, List<Person>> adjecency = getAdjecency(familyTree);
		Person personObj = searchPerosnByName(personName, familyTree);
		if (personObj == null) {
			System.out.println(personName + " Not found! first add as child to family tree.");
			return Collections.emptyList();
		}
		List<Person> children = adjecency.get(personObj);
		if (children != null && !children.isEmpty()) {
			return children.stream().filter(child -> child.getGender().equalsIgnoreCase("F"))
					.map(child -> child.getName()).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	private List<Person> listSiblings(String personName, FamilyTree familyTree) {
		Map<Person, List<Person>> adjecency = getAdjecency(familyTree);
		String father = getFatherName(personName, familyTree);
		Person personObj = searchPerosnByName(father, familyTree);
		return adjecency.get(personObj);
	}

	public List<String> listCousins(String personName, FamilyTree familytree) {
		Map<Person, List<Person>> adjecency = getAdjecency(familytree);
		List<String> cousins = new ArrayList<>();
		String father = getFatherName(personName, familytree);
		List<Person> fathersSiblings = listSiblings(father, familytree).stream()
				.filter(sibling -> !sibling.getName().equals(father)).collect(Collectors.toList());
		for (Person eachSibling : fathersSiblings) {
			List<Person> childList = adjecency.get(eachSibling);
			if (childList != null && !childList.isEmpty()) {
				cousins.addAll(childList.stream().map(each -> each.getName()).collect(Collectors.toList()));
			}

		}

		return cousins;
	}

	public List<String> getAunt(String personName, FamilyTree familyTree) {
		String father = getFatherName(personName, familyTree);
		List<Person> auntList = listSiblings(father, familyTree).stream()
				.filter(sibling -> sibling.getGender().equalsIgnoreCase("F")).collect(Collectors.toList());
		return auntList.stream().map(each -> each.getName()).collect(Collectors.toList());
	}

	public List<String> getUncle(String personName, FamilyTree familyTree) {
		String father = getFatherName(personName, familyTree);
		List<Person> uncleList = listSiblings(father, familyTree).stream().filter(
				sibling -> sibling.getGender().equalsIgnoreCase("M") && !sibling.getName().equalsIgnoreCase(father))
				.collect(Collectors.toList());
		return uncleList.stream().map(each -> each.getName()).collect(Collectors.toList());
	}

	public String getGrandFather(String personName, FamilyTree familyTree) {

		String father = getFatherName(personName, familyTree);
		if (father != null && !father.isEmpty()) {
			return getFatherName(father, familyTree);
		}
		return null;
	}

	public String getGrandMother(String personName, FamilyTree familyTree) {
		String father = getFatherName(personName, familyTree);
		if (father != null && !father.isEmpty()) {
			return getMotherName(father, familyTree);
		}
		return null;
	}

}
