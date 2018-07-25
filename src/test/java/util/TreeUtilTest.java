package util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.FamilyTree;
import model.Person;
import service.ChildTreeService;

public class TreeUtilTest {

	TreeUtil treeutil;
	FamilyTree tree;
	ChildServiceMock childServiceMock;

	@Before
	public void setup() {
		treeutil = new TreeUtil();
		tree = new FamilyTree();
		childServiceMock = new ChildServiceMock();
		TreeUtil.init(tree);
	}

	@Test
	public void shouldTestGetFatherName() {
		String actual = this.treeutil.getParentName("Alex", "Father", tree);
		assertEquals("Evan", actual);
	}

	@Test
	public void shouldTestGetFatherNameForOldestPersonInTree() {
		String actual = this.treeutil.getParentName("Evan", "Father", tree);
		assertEquals(null, actual);
	}

	@Test
	public void shouldTestGetFatherNameWhenInvalidNameGiven() {
		String actual = this.treeutil.getParentName("Evan123", "Father", tree);
		assertNull(actual);
	}

	@Test
	public void shouldTestGetMotherName() {
		String actual = this.treeutil.getParentName("Alex", "Mother", tree);
		assertEquals("Diana", actual);
	}

	@Test
	public void shouldTestGetMotherNameForOldestPersonInTree() {
		String actual = this.treeutil.getParentName("Evan", "Mother", tree);
		assertNull(actual);
	}

	@Test
	public void shouldTestGetMotherNameWhenInvalidNameGiven() {
		String actual = this.treeutil.getParentName("Evan123", "Mother", tree);
		assertNull(actual);
	}

	@Test
	public void shouldTestSearchPerosnByName() {
		Person actual = this.treeutil.searchPersonByName("Evan", tree);
		assertEquals("Evan", actual.getName());
	}

	@Test
	public void shouldTestListBrothers() {
		List<String> actual = this.treeutil.listSiblingsByType("Alex", "brothers", tree);
		assertEquals(2, actual.size());
		List<String> expected = new ArrayList<>();
		expected.add("John");
		expected.add("Joe");

		assertTrue(actual.containsAll(expected));
	}

	@Test
	public void shouldTestListBrothersWhenNobrothersExist() {
		List<String> actual = this.treeutil.listSiblingsByType("Evan", "brothers", tree);

		assertTrue(actual.isEmpty());
	}

	@Test
	public void shouldTestListSisters() {
		List<String> actual = this.treeutil.listSiblingsByType("Alex", "sisters", tree);
		assertEquals(1, actual.size());
		List<String> expected = new ArrayList<>();
		expected.add("Nisha");

		assertTrue(actual.containsAll(expected));
	}

	@Test
	public void shouldTestListSistersWhenNoSistersExist() {
		List<String> actual = this.treeutil.listSiblingsByType("Evan", "sisters", tree);

		assertTrue(actual.isEmpty());
	}

	@Test
	public void shouldTestListSons() {
		List<String> actual = this.treeutil.listChildren("Evan", "sons", tree);
		assertEquals(3, actual.size());
		List<String> expected = new ArrayList<>();
		expected.add("John");
		expected.add("Joe");
		expected.add("Alex");

		assertTrue(actual.containsAll(expected));
	}

	@Test
	public void shouldTestlistDaughters() {
		List<String> actual = this.treeutil.listChildren("Evan", "daughters", tree);
		assertEquals(1, actual.size());
		List<String> expected = new ArrayList<>();
		expected.add("Nisha");

		assertTrue(actual.containsAll(expected));
	}

	@Test
	public void shouldTestListCousins() {
		childServiceMock.addChild(tree, "Nancy", "Joy", "M");
		childServiceMock.addChild(tree, "Niki", "smilie", "F");
		List<String> actual = this.treeutil.listCousins("Joy", tree);
		assertEquals(1, actual.size());
		List<String> expected = new ArrayList<>();
		expected.add("smilie");

		assertTrue(actual.containsAll(expected));
	}

	@Test
	public void shouldTestGetAunt() {

		childServiceMock.addChild(tree, "Nancy", "Joy", "M");
		childServiceMock.addChild(tree, "Niki", "smilie", "F");
		List<String> actual = this.treeutil.getCousinParent("Joy", "aunt", tree);
		assertEquals(1, actual.size());
		List<String> expected = new ArrayList<>();
		expected.add("Nisha");

		assertTrue(actual.containsAll(expected));
	}

	@Test
	public void shouldTestGetUncle() {
		childServiceMock.addChild(tree, "Nancy", "Joy", "M");
		childServiceMock.addChild(tree, "Niki", "smilie", "F");
		List<String> actual = this.treeutil.getCousinParent("Joy", "uncle", tree);
		assertEquals(2, actual.size());
		List<String> expected = new ArrayList<>();
		expected.add("John");
		expected.add("Joe");

		assertTrue(actual.containsAll(expected));
	}

	@Test
	public void shouldTestGetGrandFather() {
		childServiceMock.addChild(tree, "Nancy", "Joy", "M");
		childServiceMock.addChild(tree, "Niki", "smilie", "F");
		String actual = this.treeutil.getGrandParents("Joy", "grandfather", tree);
		assertEquals("Evan", actual);
	}

	@Test
	public void shouldTestgetGrandMother() {
		childServiceMock.addChild(tree, "Nancy", "Joy", "M");
		childServiceMock.addChild(tree, "Niki", "smilie", "F");
		String actual = this.treeutil.getGrandParents("Joy", "grandmother", tree);
		assertEquals("Diana", actual);
	}

}

class ChildServiceMock extends ChildTreeService {
	@Override
	protected boolean addChild(FamilyTree tree, String personName, String childName, String gender) {
		return super.addChild(tree, personName, childName, gender);
	}
}
