package com.acertainsupplychain;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ItemSupplierImpl implements ItemSupplier {
Map <Integer,Integer> orderedQuantity = new ConcurrentHashMap();
Integer supplierId;	
private PrintWriter writer;

public ItemSupplierImpl (int supplierId){
	this.supplierId = supplierId;
	
	try {
			writer = new PrintWriter("log.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Cannot create Log ", e);
		}
		
	}	
	
private synchronized void log(String line){
		
		writer.println(line);
		writer.flush(); 
	} 
	/**
	 * Executes an order step with the item supplier, adding the quantity
	 * ordered to the given items.
	 * 
	 * @param step
	 *            - the order step to be executed by this item supplier.
	 * @throws OrderProcessingException
	 *             - if the step is malformed or another exception occurs (you
	 *             may specialize exceptions deriving from
	 *             OrderProcessingException if you want).
	 */
	public void executeStep(OrderStep step) throws OrderProcessingException
	{   
		if (supplierId != step.getSupplierId())
		{
			throw new OrderProcessingException();
		}
		
		String logLine = ""+supplierId;

		for(ItemQuantity item:step.getItems())
		{ logLine += " "+item.getItemId()+" "+ item.getQuantity();
		if (orderedQuantity.containsKey(item.getItemId())){
			orderedQuantity.put(item.getItemId(), orderedQuantity.get(item.getItemId())+1);
		}
		else {
			orderedQuantity.put(item.getItemId(),1);
				
		}
		}
		log(logLine);
		
		

	}
	/**
	 * Returns the total quantity ordered per item at this item supplier.
	 * 
	 * @param itemIds
	 *            - the IDs of the items queried.
	 * @return the position of the items.
	 * @throws InvalidItemException
	 *             - if any of the item IDs is unknown to this item supplier.
	 */
	public List<ItemQuantity> getOrdersPerItem(Set<Integer> itemIds)
			throws InvalidItemException
			{
		List<ItemQuantity> itemQuantities = new ArrayList();
		
		for (Integer id:itemIds){
		if (!orderedQuantity.containsKey(id)){
			throw new InvalidItemException();
		}
		
			itemQuantities.add(new ItemQuantity(id,orderedQuantity.get(id)));	
		}
		
		
		return itemQuantities;
			}


}
