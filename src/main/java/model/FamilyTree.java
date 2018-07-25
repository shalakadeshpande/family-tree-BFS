package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class FamilyTree {
	private Map<Person, List<Person>> parentchildMap = new HashMap<>();

	public Map<Person, List<Person>> getParentChildMap() {
		return parentchildMap;
	}

	public void setParentChildMap(Map<Person, List<Person>> adjecency) {
		this.parentchildMap = adjecency;
	}

	@Override
	public String toString() {
		Queue<Person> q = new LinkedList<>();
		Person rootNode = parentchildMap.get(null).get(0);
		q.add(rootNode);
		while (!q.isEmpty()) {
			rootNode = q.poll();
			System.out.print(rootNode + " ");
			List<Person> childrenNodes = parentchildMap.get(rootNode);
			if (!(childrenNodes == null || childrenNodes.isEmpty())) {
				childrenNodes.forEach(eachChild -> {
					q.add(eachChild);
				});
			}

		}
		return "FamilyTree traversed as per BFS algorithm";
	}

}
