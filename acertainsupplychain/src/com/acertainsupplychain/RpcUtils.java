package com.acertainsupplychain;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RpcUtils {
Map <Integer,ItemSupplier> itemSuppliers = new ConcurrentHashMap(); 	


public RpcUtils(){
	for (int i=0; i<10;i++){
		itemSuppliers.put(Integer.valueOf(i),new ItemSupplierImpl(i));
	}
	
}

public void executeAtSupplier(OrderStep step) throws OrderProcessingException{
		// rpc is supposed to use xstream to transform order step into XML
		//and then use Jetty/http to send the orderstep xml to the right item supplier 
	
	itemSuppliers.get(step.getSupplierId()).executeStep(step);
	}

}
