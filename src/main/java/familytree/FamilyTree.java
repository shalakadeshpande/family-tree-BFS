package familytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import model.Person;

public class FamilyTree {

	private Map<Person, List<Person>> adjecency = new HashMap<>();

	public Map<Person, List<Person>> getAdjecency() {
		return adjecency;
	}

	public void setAdjecency(Map<Person, List<Person>> adjecency) {
		this.adjecency = adjecency;
	}

	@Override
	public String toString() {
		Queue<Person> q = new LinkedList<>();
		Person rootNode = adjecency.get(null).get(0);
		q.add(rootNode);
		while (!q.isEmpty()) {
			rootNode = q.poll();
			System.out.print(rootNode + " ");
			List<Person> childrenNodes = adjecency.get(rootNode);
			if (!(childrenNodes == null || childrenNodes.isEmpty())) {
				childrenNodes.forEach(eachChild -> {
					q.add(eachChild);
				});
			}

		}
		return "FamilyTree traversed as per BFS algorithm";
	}

	public void addSpouse(String personName, String spouseName) {

		Person matchedPerson = searchPerosnByName(personName);
		if (matchedPerson == null) {
			matchedPerson = searchPerosnByName(spouseName);
			if (matchedPerson != null) {
				matchedPerson.setSpouseName(personName);
				System.out.println("Welcome " + spouseName + "'s spouse to the family!" + personName);
			} else {
				System.out.println(
						"Person not found! " + personName + " or " + spouseName + " needs to be added as child first.");
			}
		} else {
			matchedPerson.setSpouseName(spouseName);
			System.out.println("Welcome " + personName + "'s wife to the family!" + spouseName);
		}

	}

	public void addChild(String personName, String childName, String gender) {
		Person matchedPerson = searchPerosnByName(personName);
		if (matchedPerson == null) {
			matchedPerson = searchPerosnByWifeName(personName);
		}
		if (matchedPerson == null) {
			System.out.println(personName + " needs to be added to family tree first!");
			return;
		}
		List<Person> matchedPersonChildren = this.adjecency.get(matchedPerson);
		if (matchedPersonChildren == null) {
			matchedPersonChildren = new ArrayList<>();
		}
		matchedPersonChildren.add(new Person(childName, gender));
		this.adjecency.put(matchedPerson, matchedPersonChildren);

		System.out.println("Welcome " + personName + "'s child to the family - " + childName);
	}

	private Person searchPerosnByWifeName(String personName) {
		Person matchedPerson = null;
		Set<Person> allPersonNodes = this.adjecency.keySet();
		for (Person person : allPersonNodes) {
			List<Person> allChildren = this.adjecency.get(person);
			List<Person> matched = allChildren.stream()
					.filter(each -> each.getSpouseName() != null && each.getSpouseName().equalsIgnoreCase(personName))
					.collect(Collectors.toList());
			if (!(matched == null || matched.isEmpty())) {
				return matched.get(0);
			}
		}

		return matchedPerson;
	}

	private Person searchPerosnByName(String personName) {

		Person matchedPerson = null;
		Set<Person> allPersonNodes = this.adjecency.keySet();
		for (Person person : allPersonNodes) {
			List<Person> allChildren = this.adjecency.get(person);
			List<Person> matched = allChildren.stream().filter(each -> each.getName().equalsIgnoreCase(personName))
					.collect(Collectors.toList());
			if (null != matched && !matched.isEmpty())
				return matched.get(0);
		}

		return matchedPerson;
	}

	public List<Person> listChildren(String personName) {
		Person matchedPerson = searchPerosnByName(personName);
		if (matchedPerson == null) {
			matchedPerson = searchPerosnByWifeName(personName);
		}
		return (matchedPerson == null) ? null : this.adjecency.get(matchedPerson);
	}

	public String listSpouse(String personName) {
		Person matchedPerson = searchPerosnByName(personName);
		if (matchedPerson == null) {
			matchedPerson = searchPerosnByWifeName(personName);
			return (matchedPerson == null) ? null : matchedPerson.getName();
		}
		return matchedPerson.getSpouseName();
	}

	public List<Person> listSons(String personName) {
		List<Person> children = this.listChildren(personName);
		return children.stream().filter(eachChild -> eachChild.getGender().equalsIgnoreCase("M"))
				.collect(Collectors.toList());
	}

	public List<Person> listDaughters(String personName) {
		List<Person> children = this.listChildren(personName);
		return children.stream().filter(eachChild -> eachChild.getGender().equalsIgnoreCase("F"))
				.collect(Collectors.toList());
	}

	public String getFatherName(String personName) {
		Set<Person> allPersonNodes = this.adjecency.keySet();
		for (Person person : allPersonNodes) {
			List<String> allChildrenNames = this.adjecency.get(person).stream().map(eachPerson -> eachPerson.getName())
					.collect(Collectors.toList());
			if (allChildrenNames.contains(personName)) {
				return (person != null) ? person.getName() : personName + " is oldest Person known in family tree";
			}
		}
		return null;
	}

	public String getMotherName(String personName) {
		Set<Person> allPersonNodes = this.adjecency.keySet();
		for (Person person : allPersonNodes) {
			List<String> allChildrenNames = this.adjecency.get(person).stream().map(eachPerson -> eachPerson.getName())
					.collect(Collectors.toList());
			if (allChildrenNames.contains(personName)) {
				return (person != null) ? person.getSpouseName()
						: personName + " is oldest Person known in family tree";
			}
		}
		return null;
	}

	public List<String> getBrothers(String personName) {
		List<Person> siblings = getSiblings(personName);
		if (siblings != null && !siblings.isEmpty()) {
			List<Person> brothers = siblings.stream().filter(sibling -> !sibling.getName().equalsIgnoreCase(personName)
					&& sibling.getGender().equalsIgnoreCase("M")).collect(Collectors.toList());
			return brothers.stream().map(brother -> brother.getName()).collect(Collectors.toList());
		}
		return Collections.EMPTY_LIST;
	}

	private List<Person> getSiblings(String personName) {
		String father = getFatherName(personName);
		Person personObj = searchPerosnByName(father);
		List<Person> siblings = this.adjecency.get(personObj);
		return siblings;
	}

	public List<String> getSisters(String personName) {
		List<Person> siblings = getSiblings(personName);
		if (siblings != null && !siblings.isEmpty()) {
			List<Person> sisters = siblings.stream().filter(sibling -> !sibling.getName().equalsIgnoreCase(personName)
					&& sibling.getGender().equalsIgnoreCase("F")).collect(Collectors.toList());
			return sisters.stream().map(brother -> brother.getName()).collect(Collectors.toList());
		}
		return Collections.EMPTY_LIST;
	}

	public List<String> getSons(String personName) {
		Person personObj = searchPerosnByName(personName);
		if (personObj == null) {
			System.out.println(personName + " Not found! first add as child to family tree.");
			return Collections.EMPTY_LIST;
		}
		List<Person> children = adjecency.get(personObj);
		if (children != null && !children.isEmpty()) {
			return children.stream().filter(child -> child.getGender().equalsIgnoreCase("M"))
					.map(child -> child.getName()).collect(Collectors.toList());
		}
		return Collections.EMPTY_LIST;
	}

	public List<String> getDaughters(String personName) {
		Person personObj = searchPerosnByName(personName);
		if (personObj == null) {
			System.out.println(personName + " Not found! first add as child to family tree.");
			return Collections.EMPTY_LIST;
		}
		List<Person> children = adjecency.get(personObj);
		if (children != null && !children.isEmpty()) {
			return children.stream().filter(child -> child.getGender().equalsIgnoreCase("F"))
					.map(child -> child.getName()).collect(Collectors.toList());
		}
		return Collections.EMPTY_LIST;
	}

	public List<String> getCousins(String personName) {
		List<String> cousins = new ArrayList<>();
		String father = getFatherName(personName);
		List<Person> fathersSiblings = getSiblings(father).stream().filter(sibling -> !sibling.getName().equals(father))
				.collect(Collectors.toList());
		for (Person eachSibling : fathersSiblings) {
			List<Person> childList = adjecency.get(eachSibling);
			if (childList != null && !childList.isEmpty()) {
				cousins.addAll(childList.stream().map(each -> each.getName()).collect(Collectors.toList()));
			}

		}

		return cousins;
	}

	public void printTree() {
		Set<Person> keys = adjecency.keySet();
		for (Person key : keys) {
			System.out.println(key + " : " + adjecency.get(key));
		}
	}

	public List<String> getAunt(String personName) {
		String father = getFatherName(personName);
		List<Person> auntList = getSiblings(father).stream()
				.filter(sibling -> sibling.getGender().equalsIgnoreCase("F")).collect(Collectors.toList());
		return auntList.stream().map(each -> each.getName()).collect(Collectors.toList());
	}

	public List<String> getUncle(String personName) {
		String father = getFatherName(personName);
		List<Person> uncleList = getSiblings(father).stream().filter(
				sibling -> sibling.getGender().equalsIgnoreCase("M") && !sibling.getName().equalsIgnoreCase(father))
				.collect(Collectors.toList());
		return uncleList.stream().map(each -> each.getName()).collect(Collectors.toList());
	}

	public String getGrandFather(String personName) {

		String father = getFatherName(personName);
		if (father != null && !father.isEmpty()) {
			return getFatherName(father);
		}
		return null;
	}

	public String getGrandMother(String personName) {
		String father = getFatherName(personName);
		if (father != null && !father.isEmpty()) {
			return getMotherName(father);
		}
		return null;
	}

}
