package main.java.ws.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import java.rmi.RemoteException;
import java.text.ParseException;

import co.com.sc.nexura.superfinanciera.action.generic.services.trm.client.TCRMClient;

/**
 * @author Jefferson Johannes Roth Filho (jjrothfilho@gmail.com)
 *
 */
@Path("/queryTCRM")
public class queryTCRM {
	
	/**
	 * TCRM date to query
	 */
	private String _DATE_TO_QUERY = null;
	
	@GET
	@Produces("application/json")
    public Response tcrm() {
		
		String tcrmResponseValue = null;
		String[] tcrmQueryAssociatedDateArray = new String[] {};
		JSONObject responseJSON = new JSONObject();
    	
    	try {
			tcrmResponseValue = TCRMClient.main(tcrmQueryAssociatedDateArray);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
		responseJSON.put("value", tcrmResponseValue);
    	
    	return Response.status(200).entity(responseJSON.toString()).build();
    }

    @GET
    @Path("/{tcrmQueryAssociatedDate}")
    @Produces("application/json")
    public Response getMsg(@PathParam("tcrmQueryAssociatedDate") String tcrmQueryAssociatedDate) {
    	
    	_DATE_TO_QUERY = tcrmQueryAssociatedDate;
    	String[] tcrmQueryAssociatedDateArray = new String[] {_DATE_TO_QUERY};
    	String tcrmResponseValue = null;
		JSONObject responseJSON = new JSONObject();
    	
    	try {
			tcrmResponseValue = TCRMClient.main(tcrmQueryAssociatedDateArray);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
		responseJSON.put("value", tcrmResponseValue);
    	
    	//String output = "tcrmQueryAssociatedDate   : " + tcrmQueryAssociatedDate;
  
        return Response.status(200).entity(responseJSON.toString()).build();
  
    }

}
