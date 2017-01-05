package com.acertainsupplychain;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;










import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.acertainsupplychain.OrderManager.StepStatus;

public class OrderManagerImpl implements OrderManager{
	private AtomicInteger workFlowId = new AtomicInteger(0);
	private Map<Integer,WorkFlow> wfs = new ConcurrentHashMap<Integer,WorkFlow>();

	PrintWriter writer = null;
	
	
	public OrderManagerImpl (){
		try {
			writer = new PrintWriter("log.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Cannot create Log ", e);
		}
		
	}
	
	/**
	 * Registers an order workflow with the order manager.
	 * 
	 * @param steps
	 *            - the order steps to be executed.
	 * @return the ID of the order workflow.
	 * @throws OrderProcessingException
	 *             - an exception thrown if steps are malformed or another error
	 *             condition occurs (you may specialize exceptions deriving from
	 *             OrderProcessingException if you want).
	 */
	public int registerOrderWorkflow(List<OrderStep> steps)
			throws OrderProcessingException{
		



		int supplierId;
		List<ItemQuantity> items;
		List<WorkflowStep> workflowsteps = new ArrayList<WorkflowStep>();

		for (OrderStep orderStep:steps){
			supplierId = orderStep.getSupplierId();
			items = orderStep.getItems();			

			WorkflowStep as = new WorkflowStep(StepStatus.REGISTERED, supplierId, items);
			workflowsteps.add(as);
					
			
			String logLine = ""+supplierId;

			for(ItemQuantity item:items)
			{ logLine += " "+item.getItemId()+" "+ item.getQuantity();
			}
			log(logLine);
		}
		
		Integer id = workFlowId.incrementAndGet();
		WorkFlow wf = new WorkFlow (id, workflowsteps);
		wfs.put(id,wf);
		
	
		return id;
	}
	
		
	private synchronized void log(String line){
		
		writer.println(line);
		writer.flush(); 
	} 
// 
	/**
	 * Queries the current state of a given order workflow registered with the
	 * order manager.
	 * 
	 * @param orderWorkflowId
	 *            - the ID of the workflow being queried.
	 * @return the list of states of the multiple steps of the given workflow
	 *         (order matters).
	 * @throw InvalidWorkflowException - if the workflow ID given is not valid.
	 */
	public List<StepStatus> getOrderWorkflowStatus(int orderWorkflowId)
			throws InvalidWorkflowException{
		if (!wfs.containsKey(orderWorkflowId)){
			throw new InvalidWorkflowException("workflow ID is not valid");
		}
		
		
		List<StepStatus> list = new ArrayList(); 
		WorkFlow workflow = wfs.get(orderWorkflowId); 
		
		// sychronizing around the workflow ID, so all the reads and writes are thread safe
		synchronized(workflow.getId()){

			for(WorkflowStep step : workflow.workflowStep() ) {
				
				list.add(step.status());
			}
		} 
		return list;
	
	
	}

}
