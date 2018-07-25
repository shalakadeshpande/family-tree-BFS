package service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.FamilyTree;
import util.TreeUtil;

public class AddChildTreeServiceTest {

	private AddChildTreeService service;
	private FamilyTree tree;

	@Before
	public void setup() {
		service = new AddChildTreeService();
		tree = new FamilyTree();
	}

	@Test
	public void shouldTestAddChild() {
		TreeUtil.init(tree);

		boolean actual = service.addChild(tree, "Alex", "Joy", "M");
		assertTrue(actual);

	}
	
	@Test
	public void shouldTestAddChildInvalidCase() {
		TreeUtil.init(tree);

		boolean actual = service.addChild(tree, "Alex33", "Joy", "M");
		assertFalse(actual);

	}

}
