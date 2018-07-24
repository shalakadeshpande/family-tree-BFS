package familytree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Person;

class FamilyTreeTest {
	FamilyTree familyTree;

	@BeforeEach
	public void setUp() {
		familyTree = new FamilyTree();
	}

	@Test
	void shouldTestInit() {
		familyTree.init();
		String outputString = familyTree.toString();
		assertNotNull(familyTree.getAdjecency());
		assertEquals(outputString, "FamilyTree traversed as per BFS algorithm");
	}

	@Test
	void shouldTestAddSpouse() {
		familyTree.init();
		RelationshipService mockService = new RelationShipServiceMock();
		familyTree.setRelationshilService(mockService);

		boolean actual = familyTree.addSpouse("Alex", "Second_Nancy");
		assertTrue(actual);

		actual = familyTree.addSpouse("Nancy", "Second_Alex");
		assertTrue(actual);

	}
	@Test
	void shouldTestAddSpouseForNull() {
		familyTree.init();
		RelationshipService mockService = new RelationShipServiceNullResponseMock();
		familyTree.setRelationshilService(mockService);

		boolean actual = familyTree.addSpouse("Alex11", "Second_Nancy");
		assertFalse(actual);

	}

	@Test
	void shouldTestAddChildByFathersName() {
		familyTree.init();
		RelationshipService mockService = new RelationShipServiceMock();
		familyTree.setRelationshilService(mockService);

		boolean actual = familyTree.addChild("Joy", "Alex", "M");
		assertTrue(actual);

	}
	
	@Test
	void shouldTestAddChildByMothersName() {
		familyTree.init();
		RelationshipService mockService = new RelationShipServiceNullResponseMock();
		familyTree.setRelationshilService(mockService);

		boolean actual = familyTree.addChild("Joy", "Nancy", "M");
		assertTrue(actual);

	}

}

class RelationShipServiceMock extends RelationshipService {
	public Person searchPerosnByName(String personName, FamilyTree familyTree) {
		return new Person("dummy", "M");

	}
}

class RelationShipServiceNullResponseMock extends RelationshipService {
	public Person searchPerosnByName(String personName, FamilyTree familyTree) {
		return null;

	}

	public Person searchPerosnByWifeName(String personName, FamilyTree familyTree) {
		return new Person("Nancy", "F");
	}
}
