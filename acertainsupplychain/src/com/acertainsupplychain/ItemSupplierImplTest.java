package com.acertainsupplychain;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

public class ItemSupplierImplTest {

	ItemSupplierImpl itemSupplier =  new ItemSupplierImpl(1);
	@Test
	public void test() {
		try {
			
			Set<Integer> itemIds = new HashSet();
			itemIds.add(1);
			itemSupplier.getOrdersPerItem(itemIds);
			
			fail("Should throw exception");
			}
			catch(Exception e) 
			{
				//expected exception 
			}



}
}