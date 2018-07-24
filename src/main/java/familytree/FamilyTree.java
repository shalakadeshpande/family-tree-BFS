package familytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import model.Person;

public class FamilyTree {
	RelationshipService relationshipService;

	public FamilyTree() {
		this.relationshipService = new RelationshipService();
	}

	public void setRelationshilService(RelationshipService relationshilService) {
		this.relationshipService = relationshilService;
	}

	public void init() {

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
		this.setAdjecency(rootAdjList);

		System.out.println("Initial tree - ");
		this.printTree();
	}

	public void printTree() {
		Set<Person> keys = adjecency.keySet();
		for (Person key : keys) {
			System.out.println(key + " : " + adjecency.get(key));
		}
	}

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

	public boolean addSpouse(String personName, String spouseName) {

		Person matchedPerson = relationshipService.searchPerosnByName(personName, this);
		if (matchedPerson == null) {
			matchedPerson = relationshipService.searchPerosnByName(spouseName, this);
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

	public boolean addChild(String personName, String childName, String gender) {
		Person matchedPerson = relationshipService.searchPerosnByName(personName, this);
		if (matchedPerson == null) {
			matchedPerson = relationshipService.searchPerosnByWifeName(personName, this);
		}
		if (matchedPerson == null) {
			System.out.println(personName + " needs to be added to family tree first!");
			return false;
		}
		List<Person> matchedPersonChildren = this.adjecency.get(matchedPerson);
		if (matchedPersonChildren == null) {
			matchedPersonChildren = new ArrayList<>();
		}
		matchedPersonChildren.add(new Person(childName, gender));
		this.adjecency.put(matchedPerson, matchedPersonChildren);

		System.out.println("Welcome " + personName + "'s child to the family - " + childName);
		return true;
	}

	public String getFatherName(String personName) {
		return relationshipService.getFatherName(personName, this);
	}

	public String getMotherName(String personName) {
		return relationshipService.getMotherName(personName, this);
	}

	public List<String> listBrothers(String personName) {
		return relationshipService.listBrothers(personName, this);
	}

	public List<String> listSisters(String personName) {
		return relationshipService.listSisters(personName, this);
	}

	public List<String> listSons(String personName) {
		return relationshipService.listSons(personName, this);
	}

	public List<String> listDaughters(String personName) {
		return relationshipService.listDaughters(personName, this);
	}

	public List<String> listCousins(String personName) {
		return relationshipService.listCousins(personName, this);
	}

	public List<String> getAunt(String personName) {
		return relationshipService.getAunt(personName, this);
	}

	public List<String> getUncle(String personName) {
		return relationshipService.getUncle(personName, this);
	}

	public String getGrandFather(String personName) {
		return relationshipService.getGrandFather(personName, this);
	}

	public String getGrandMother(String personName) {
		return relationshipService.getGrandMother(personName, this);
	}

}
