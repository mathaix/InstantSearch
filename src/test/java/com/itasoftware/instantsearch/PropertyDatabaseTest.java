package com.itasoftware.instantsearch;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class PropertyDatabaseTest {
	
	PropertyDatabase db = new PropertyDatabase();
	NrhpProperty a;
	NrhpProperty b;
	NrhpProperty c;
	NrhpProperty d;
	
	@Before
	public void initialize() {
		
		a = new NrhpProperty("4");
		a.addName("Jeffrey");
		a.setAddress("3 Point Street");
		a.setCity("Boston");
		a.setState("MA");
		db.add(a);
		
		b = new NrhpProperty("5");
		b.addName("Jermine");
		b.setAddress("3 FreeWay Street");
		b.setCity("Boston");
		b.setState("MA");
		db.add(b);
		
		c = new NrhpProperty("6");
		c.addName("Lewis");
		c.setAddress("6 Nice Place");
		c.setCity("Mansfield");
		c.setState("MA");
		db.add(c);
		
		d = new NrhpProperty("7");
		d.addName("Grace");
		d.setAddress("3 Grace Ave");
		d.setCity("Atteleboro");
		d.setState("MA");
		db.add(d);
		
	}
	
	@Test
	public void findPrefixProperties() {
		List<NrhpProperty> list = db.search("Atte");
		Assert.assertTrue (list.size() == 1);
	}
	
	@Test
	public void completeStringMatch() {
		List<NrhpProperty> result = db.search("Boston");
		printList(result);
		Assert.assertTrue (result.size() == 2);
		Assert.assertTrue(contains(result, a));
		Assert.assertTrue(contains(result, b));
	}

	@Test
	public void findSubstringProperties() {
		List<NrhpProperty> result = db.search("rac");
		Assert.assertTrue (result.size() == 1);
		Assert.assertTrue(contains(result, d));
	}
	
	@Test
	public void stringswithSpaces() {
		List<NrhpProperty> result = db.search("ce Ave");
		Assert.assertTrue (result.size() == 1);
		Assert.assertTrue(contains(result, d));
	}
	
	private 
	
	boolean contains(List<NrhpProperty> result, NrhpProperty p) {
		for (NrhpProperty item : result) {
			if (item.hashCode() == p.hashCode())
				return true;
		}
		return false;
	}
	
	void printList(List<NrhpProperty> list) {
	   for(NrhpProperty p: list) { 
		   System.out.println(p);
	   }
	}
}
