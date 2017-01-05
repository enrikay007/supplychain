package com.acertainsupplychain;

import java.util.List;


public class WorkFlow {

private final Integer id;
private final List<WorkflowStep> workflowStep;

public WorkFlow(Integer id, List<WorkflowStep> workflowStep) {
	this.id = id;
	this.workflowStep = workflowStep;
}


/**
 * @return the getId
 */
public Integer getId() {
	return id;
}

/**
 * @return the workflowStep
 */
public List<WorkflowStep> workflowStep() {
	return workflowStep;
}




}
