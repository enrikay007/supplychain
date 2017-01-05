package com.acertainsupplychain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class OrderManagerImplTest {


	@Test
	public void test() {
		OrderManagerImpl orderManager =  new OrderManagerImpl();

		try {
		orderManager.getOrderWorkflowStatus(-1);
		fail("Should throw exception");
		}
		catch(Exception e) 
		{
			//expected exception 
		}
		//Test 2
		List<OrderStep> steps = new ArrayList();
		try {
		Integer id = orderManager.registerOrderWorkflow(steps);
		List status = orderManager.getOrderWorkflowStatus(id);
		assertNotNull(status);
		}
		catch(Exception e) 
		{
		fail("Should not throw exception");
		}
		//Test 3
		List<ItemQuantity> items = new ArrayList();
		items.add(new ItemQuantity(9,11));
		steps.add(new OrderStep(102,items));
		
		try {
		Integer id = orderManager.registerOrderWorkflow(steps);
		List status = orderManager.getOrderWorkflowStatus(id);
		assertNotNull(status);
		assertEquals(1,status.size());
		}
		catch(Exception e) 
		{
		fail("Should not throw exception");
		}
		
		//Test 4
		List<ItemQuantity> items3 = new ArrayList();
		items3.add(new ItemQuantity(9,11));
		steps.add(new OrderStep(102,items3));
		
		List<ItemQuantity> items2 = new ArrayList();
		items2.add(new ItemQuantity(9,11));
		steps.add(new OrderStep(102,items2));
		
		try {
		Integer id = orderManager.registerOrderWorkflow(steps);
		List status = orderManager.getOrderWorkflowStatus(id);
		assertNotNull(status);
		assertEquals(3,status.size());
		}
		catch(Exception e) 
		{
		fail("Should not throw exception");
		}
	}
}