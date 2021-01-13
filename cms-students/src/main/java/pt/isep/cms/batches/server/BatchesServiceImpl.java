package pt.isep.cms.batches.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import pt.isep.cms.batches.client.BatchesService;
import pt.isep.cms.batches.shared.Batche;
import pt.isep.cms.batches.shared.BatcheDetails;

@SuppressWarnings("serial")
public class BatchesServiceImpl extends RemoteServiceServlet implements
    BatchesService {

  private static final String[] batchesNameData = new String[] {
      "Batche X", "Batche Y", "Batche Z"};
  
  private final String[] batchesDescripData = new String[] {
      "abc", "2abc", "3abc"};
  
  private final String[] batchesManDateData = new String[] {
      "2-10-2020", "5-7-2019", "23-1-2020"};
      
  private final HashMap<String, Batche> batches = new HashMap<String, Batche>();

  public BatchesServiceImpl() {
    initBatches();
  }
  
  private void initBatches() {
    // TODO: Create a real UID for each batche
    //
    for (int i = 0; i < batchesNameData.length && i < batchesDescripData.length && i < batchesManDateData.length; ++i) {
      Batche batche = new Batche(String.valueOf(i), batchesNameData[i], batchesDescripData[i], batchesManDateData[i]);
      batches.put(batche.getId(), batche); 
    }
  }
  
  public Batche addBatche(Batche batche) {
    batche.setId(String.valueOf(batches.size()));
    batches.put(batche.getId(), batche); 
    return batche;
  }

  public Batche updateBatche(Batche batche) {
	  String lid=batche.getId();
    batches.remove(batche.getId());
    batches.put(batche.getId(), batche); 
    return batche;
  }

  public Boolean deleteBatche(String id) {
    batches.remove(id);
    return true;
  }
  
  public ArrayList<BatcheDetails> deleteBatches(ArrayList<String> ids) {

    for (int i = 0; i < ids.size(); ++i) {
      deleteBatche(ids.get(i));
    }
    
    return getBatcheDetails();
  }
  
  public ArrayList<BatcheDetails> getBatcheDetails() {
    ArrayList<BatcheDetails> batcheDetails = new ArrayList<BatcheDetails>();
    
    Iterator<String> it = batches.keySet().iterator();
    while(it.hasNext()) { 
      Batche batche = batches.get(it.next());          
      batcheDetails.add(batche.getLightWeightBatche());
    }
    
    return batcheDetails;
  }

  public Batche getBatche(String id) {
    return batches.get(id);
  }
}
