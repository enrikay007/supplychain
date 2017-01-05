package com.acertainsupplychain;

import java.util.List;

import com.acertainsupplychain.OrderManager.StepStatus;

public class WorkflowStep {
 private StepStatus status;
 private int supplierId;
 private List<ItemQuantity> itemQuantities;
 
 public WorkflowStep(StepStatus status, int supplierId, List<ItemQuantity> itemQuantities ){
	 this.status = status;
	 this.supplierId = supplierId;
	 this.itemQuantities = itemQuantities;
	}
 
 public StepStatus status() {
		return status;
	}

 
 
 public int getSupplierId() {
		return supplierId;
	}

	/**
	 * @return the items
	 */
	public List<ItemQuantity> itemQuantities () {
		return itemQuantities;
	}



}
