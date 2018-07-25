package service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.FamilyTree;
import util.TreeUtil;

public class AddSpouseTreeServiceTest {
	AddSpouseTreeService service;
	FamilyTree tree;

	@Before
	public void setUp() {
		service = new AddSpouseTreeService();
		tree = new FamilyTree();
		TreeUtil.init(tree);
	}

	@Test
	public void shouldTestAddSpouse() {
		boolean actual = service.addSpouse(tree, "Alex", "test");
		assertTrue(actual);
	}
	
	@Test
	public void shouldTestAddSpouseForInvalid() {
		boolean actual = service.addSpouse(tree, "Joe44", "test");
		assertFalse(actual);
	}

}
