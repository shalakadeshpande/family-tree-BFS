package familytree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.FamilyTree;
import model.Person;

public class FamilyTreeTest {
	FamilyTree familyTree;

	@Before
	public void setUp() {
		familyTree = new FamilyTree();
	}

	@Test
	public void shouldTestInit() {
		familyTree.init();
		String outputString = familyTree.toString();
		assertNotNull(familyTree.getParentChildMap());
		assertEquals(outputString, "FamilyTree traversed as per BFS algorithm");
	}

	@Test
	public void shouldTestAddSpouse() {
		familyTree.init();
		RelationshipService mockService = new RelationShipServiceMock();
		familyTree.setRelationshilService(mockService);

		boolean actual = familyTree.addSpouse("Alex", "Second_Nancy");
		assertTrue(actual);

		actual = familyTree.addSpouse("Nancy", "Second_Alex");
		assertTrue(actual);

	}
	@Test
	public void shouldTestAddSpouseForNull() {
		familyTree.init();
		RelationshipService mockService = new RelationShipServiceNullResponseMock();
		familyTree.setRelationshilService(mockService);

		boolean actual = familyTree.addSpouse("Alex11", "Second_Nancy");
		assertFalse(actual);

	}

	@Test
	public void shouldTestAddChildByFathersName() {
		familyTree.init();
		RelationshipService mockService = new RelationShipServiceMock();
		familyTree.setRelationshilService(mockService);

		boolean actual = familyTree.addChild("Joy", "Alex", "M");
		assertTrue(actual);

	}
	
	@Test
	public void shouldTestAddChildByMothersName() {
		familyTree.init();
		RelationshipService mockService = new RelationShipServiceNullResponseMock();
		familyTree.setRelationshilService(mockService);

		boolean actual = familyTree.addChild("Joy", "Nancy", "M");
		assertTrue(actual);

	}

}

class RelationShipServiceMock extends RelationshipService {
	public Person searchPersonByName(String personName, FamilyTree familyTree) {
		return new Person("dummy", "M");

	}
}

class RelationShipServiceNullResponseMock extends RelationshipService {
	public Person searchPersonByName(String personName, FamilyTree familyTree) {
		return null;

	}

	public Person searchPerosnByWifeName(String personName, FamilyTree familyTree) {
		return new Person("Nancy", "F");
	}
}
