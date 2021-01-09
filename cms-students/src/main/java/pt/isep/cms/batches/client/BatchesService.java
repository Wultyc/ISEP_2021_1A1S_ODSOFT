package pt.isep.cms.batches.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import pt.isep.cms.batches.shared.Batche;
import pt.isep.cms.batches.shared.BatcheDetails;

import java.util.ArrayList;

@RemoteServiceRelativePath("batchesService")
public interface BatchesService extends RemoteService {
	
  Batche addBatche(Batche batche);
  Boolean deleteBatche(String id); 
  ArrayList<BatcheDetails> deleteBatches(ArrayList<String> ids);
  ArrayList<BatcheDetails> getBatcheDetails();
  Batche getBatche(String id);
  Batche updateBatche(Batche batche);
}
