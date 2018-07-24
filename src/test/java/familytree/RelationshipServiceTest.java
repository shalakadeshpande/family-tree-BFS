package familytree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Person;

class RelationshipServiceTest {
	FamilyTree familyTree;
	RelationshipService service;

	@BeforeEach
	public void setUp() {
		this.service = new RelationshipService();
		this.familyTree = new FamilyTree();
		this.familyTree.init();
	}

	@Test
	void shouldTestGetFatherName() {
		String actual = this.service.getFatherName("Alex", this.familyTree);
		assertEquals("Evan", actual);
	}

	@Test
	void shouldTestGetMotherName() {
		String actual = this.service.getMotherName("Alex", this.familyTree);
		assertEquals("Diana", actual);
	}

	@Test
	void shouldTestSearchPerosnByWifeName() {
		Person actual = this.service.searchPerosnByWifeName("Diana", this.familyTree);
		assertEquals("Diana", actual.getSpouseName());
		assertEquals("Evan", actual.getName());
	}

	@Test
	void shouldTestSearchPerosnByName() {
		Person actual = this.service.searchPerosnByName("Evan", this.familyTree);
		assertEquals("Evan", actual.getName());
	}

	@Test
	void shouldTestListBrothers() {
		List<String> actual = this.service.listBrothers("Alex", this.familyTree);
		assertEquals(2, actual.size());
		List<String> expected = new ArrayList<>();
		expected.add("John");
		expected.add("Joe");

		assertTrue(actual.containsAll(expected));
	}

	@Test
	void shouldTestListSisters() {
		List<String> actual = this.service.listSisters("Alex", this.familyTree);
		assertEquals(1, actual.size());
		List<String> expected = new ArrayList<>();
		expected.add("Nisha");

		assertTrue(actual.containsAll(expected));
	}

	@Test
	void shouldTestListSons() {
		List<String> actual = this.service.listSons("Evan", this.familyTree);
		assertEquals(3, actual.size());
		List<String> expected = new ArrayList<>();
		expected.add("John");
		expected.add("Joe");
		expected.add("Alex");

		assertTrue(actual.containsAll(expected));
	}

	@Test
	void shouldTestlistDaughters() {
		List<String> actual = this.service.listDaughters("Evan", this.familyTree);
		assertEquals(1, actual.size());
		List<String> expected = new ArrayList<>();
		expected.add("Nisha");

		assertTrue(actual.containsAll(expected));
	}

	@Test
	void shouldTestListCousins() {
		this.familyTree.addChild("Nancy", "Joy", "M");
		this.familyTree.addChild("Niki", "smilie", "F");
		List<String> actual = this.service.listCousins("Joy", this.familyTree);
		assertEquals(1, actual.size());
		List<String> expected = new ArrayList<>();
		expected.add("smilie");

		assertTrue(actual.containsAll(expected));
	}

	@Test
	void shouldTestGetAunt() {
		this.familyTree.addChild("Nancy", "Joy", "M");
		this.familyTree.addChild("Niki", "smilie", "F");
		List<String> actual = this.service.getAunt("Joy", this.familyTree);
		assertEquals(1, actual.size());
		List<String> expected = new ArrayList<>();
		expected.add("Nisha");

		assertTrue(actual.containsAll(expected));
	}

	@Test
	void shouldTestGetUncle() {
		this.familyTree.addChild("Nancy", "Joy", "M");
		this.familyTree.addChild("Niki", "smilie", "F");
		List<String> actual = this.service.getUncle("Joy", this.familyTree);
		assertEquals(2, actual.size());
		List<String> expected = new ArrayList<>();
		expected.add("John");
		expected.add("Joe");

		assertTrue(actual.containsAll(expected));
	}

	@Test
	void shouldTestGetGrandFather() {
		this.familyTree.addChild("Nancy", "Joy", "M");
		this.familyTree.addChild("Niki", "smilie", "F");
		String actual = this.service.getGrandFather("Joy", this.familyTree);
		assertEquals("Evan", actual);
	}
	
	@Test
	void shouldTestgetGrandMother() {
		this.familyTree.addChild("Nancy", "Joy", "M");
		this.familyTree.addChild("Niki", "smilie", "F");
		String actual = this.service.getGrandMother("Joy", this.familyTree);
		assertEquals("Diana", actual);
	}

}
