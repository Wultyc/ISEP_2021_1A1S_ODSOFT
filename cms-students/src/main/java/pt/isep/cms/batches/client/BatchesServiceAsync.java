package pt.isep.cms.batches.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import pt.isep.cms.batches.shared.Batche;
import pt.isep.cms.batches.shared.BatcheDetails;

import java.util.ArrayList;

public interface BatchesServiceAsync {

  public void addBatche(Batche batche, AsyncCallback<Batche> callback);
  public void deleteBatche(String id, AsyncCallback<Boolean> callback);
  public void deleteBatches(ArrayList<String> ids, AsyncCallback<ArrayList<BatcheDetails>> callback);
  public void getBatcheDetails(AsyncCallback<ArrayList<BatcheDetails>> callback);
  public void getBatche(String id, AsyncCallback<Batche> callback);
  public void updateBatche(Batche batche, AsyncCallback<Batche> callback);
}

