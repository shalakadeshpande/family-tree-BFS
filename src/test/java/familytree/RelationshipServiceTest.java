package familytree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.FamilyTree;
import model.Person;

public class RelationshipServiceTest {/*
	FamilyTree familyTree;
	RelationshipService service;

	@Before
	public void setUp() {
		this.service = new RelationshipService();
		this.familyTree = new FamilyTree();
		this.familyTree.init();
	}

	@Test
	public void shouldTestGetFatherName() {
		String actual = this.service.getParentName("Alex", "Father", this.familyTree);
		assertEquals("Evan", actual);
	}

	@Test
	public void shouldTestGetFatherNameForOldestPersonInTree() {
		String actual = this.service.getParentName("Evan", "Father", this.familyTree);
		assertEquals(null, actual);
	}

	@Test
	public void shouldTestGetFatherNameWhenInvalidNameGiven() {
		String actual = this.service.getParentName("Evan123", "Father", this.familyTree);
		assertNull(actual);
	}

	@Test
	public void shouldTestGetMotherName() {
		String actual = this.service.getParentName("Alex", "Mother", this.familyTree);
		assertEquals("Diana", actual);
	}

	@Test
	public void shouldTestGetMotherNameForOldestPersonInTree() {
		String actual = this.service.getParentName("Evan", "Mother", this.familyTree);
		assertNull(actual);
	}

	@Test
	public void shouldTestGetMotherNameWhenInvalidNameGiven() {
		String actual = this.service.getParentName("Evan123", "Mother", this.familyTree);
		assertNull(actual);
	}

	@Test
	public void shouldTestSearchPerosnByName() {
		Person actual = this.service.searchPersonByName("Evan", this.familyTree);
		assertEquals("Evan", actual.getName());
	}

	@Test
	public void shouldTestListBrothers() {
		List<String> actual = this.service.listSiblingsByType("Alex", "brothers", this.familyTree);
		assertEquals(2, actual.size());
		List<String> expected = new ArrayList<>();
		expected.add("John");
		expected.add("Joe");

		assertTrue(actual.containsAll(expected));
	}

	@Test
	public void shouldTestListBrothersWhenNobrothersExist() {
		this.familyTree.addChild("Nancy", "Joy", "M");
		List<String> actual = this.service.listSiblingsByType("Joy", "brothers", this.familyTree);

		assertTrue(actual.isEmpty());
	}

	@Test
	public void shouldTestListSisters() {
		List<String> actual = this.service.listSiblingsByType("Alex", "sisters", this.familyTree);
		assertEquals(1, actual.size());
		List<String> expected = new ArrayList<>();
		expected.add("Nisha");

		assertTrue(actual.containsAll(expected));
	}

	@Test
	public void shouldTestListSistersWhenNoSistersExist() {
		this.familyTree.addChild("Nancy", "Joy", "M");
		List<String> actual = this.service.listSiblingsByType("Joy", "sisters", this.familyTree);

		assertTrue(actual.isEmpty());
	}

	@Test
	public void shouldTestListSons() {
		List<String> actual = this.service.listChildren("Evan", "sons", this.familyTree);
		assertEquals(3, actual.size());
		List<String> expected = new ArrayList<>();
		expected.add("John");
		expected.add("Joe");
		expected.add("Alex");

		assertTrue(actual.containsAll(expected));
	}

	@Test
	public void shouldTestlistDaughters() {
		List<String> actual = this.service.listChildren("Evan", "daughters", this.familyTree);
		assertEquals(1, actual.size());
		List<String> expected = new ArrayList<>();
		expected.add("Nisha");

		assertTrue(actual.containsAll(expected));
	}

	@Test
	public void shouldTestListCousins() {
		this.familyTree.addChild("Nancy", "Joy", "M");
		this.familyTree.addChild("Niki", "smilie", "F");
		List<String> actual = this.service.listCousins("Joy", this.familyTree);
		assertEquals(1, actual.size());
		List<String> expected = new ArrayList<>();
		expected.add("smilie");

		assertTrue(actual.containsAll(expected));
	}

	@Test
	public void shouldTestGetAunt() {
		this.familyTree.addChild("Nancy", "Joy", "M");
		this.familyTree.addChild("Niki", "smilie", "F");
		List<String> actual = this.service.getCousinParent("Joy", "aunt", this.familyTree);
		assertEquals(1, actual.size());
		List<String> expected = new ArrayList<>();
		expected.add("Nisha");

		assertTrue(actual.containsAll(expected));
	}

	@Test
	public void shouldTestGetUncle() {
		this.familyTree.addChild("Nancy", "Joy", "M");
		this.familyTree.addChild("Niki", "smilie", "F");
		List<String> actual = this.service.getCousinParent("Joy", "uncle", this.familyTree);
		assertEquals(2, actual.size());
		List<String> expected = new ArrayList<>();
		expected.add("John");
		expected.add("Joe");

		assertTrue(actual.containsAll(expected));
	}

	@Test
	public void shouldTestGetGrandFather() {
		this.familyTree.addChild("Nancy", "Joy", "M");
		this.familyTree.addChild("Niki", "smilie", "F");
		String actual = this.service.getGrandParents("Joy", "grandfather", this.familyTree);
		assertEquals("Evan", actual);
	}

	@Test
	public void shouldTestgetGrandMother() {
		this.familyTree.addChild("Nancy", "Joy", "M");
		this.familyTree.addChild("Niki", "smilie", "F");
		String actual = this.service.getGrandParents("Joy", "grandmother", this.familyTree);
		assertEquals("Diana", actual);
	}

*/}
