package rest.ressources;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;

import javax.ws.rs.core.Response.Status;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import rest.entities.Employe;

@Path("employes")
public class EmployeRessource 
{
	//ArrayList<Employe>  employes = new ArrayList<Employe>();

	public static java.util.List<Employe> employes = new ArrayList<Employe>(); //il faut utiliser statique sinon la liste va perdre ces valeur a chaque request
	
	@Path("/add")
	@POST //server
	@Consumes(MediaType.APPLICATION_XML)
	public Response addEmploye(Employe employe)
	{
		employes.add(employe);
		//ArrayList<Employe> listEmploye = new ArrayList<Employe>();
		
		return Response.status(Status.OK).entity("add successful").build();  //Status.OK/FOUND/NOT_FOUND
	}
	
	@Path("/afficher")
	@GET
	@Produces("application/xml")
	public java.util.List<Employe> getAll() 
	{
		if(employes==null) {
			return null;
		}
		return employes;
	}
	
	
	@Path("/modifier")
	@PUT//update
	@Consumes("application/xml")
	public String modifier(Employe emp) 
	{	
		for(Employe item : employes) 
		{
			if(item.getCin().equals(emp.getCin())) 
			{
				employes.remove(item);
				employes.add(emp);
			}
		}
		
		
		
		/*int index=employes.indexOf(emp);
		if(index != -1) 
		{
		  employes.set(index, emp);
		  return index;
		}*/
			return employes.toString();	
	}
	

	
	

	@GET
	@Path("/chercher/{cin}")
	@Produces("application/xml")
	public Employe chercherEmp(@PathParam("cin") String cin) 
	{
 		Employe employe;
		for(Employe e : employes) 
		{
			if(e.getCin().equals(cin)) 
			{
				employe=e;
				return employe; 
			}
		}
		return null;
	}
	
	
	@DELETE
	@Path("/delete/{cin}") //with query param we don't add {cin}
	public String suppEmp(@PathParam("cin") String cin) 
	{
		Iterator<Employe> item = employes.iterator();
		while(item.hasNext()) 
		{
			if(item.next().getCin().equals(cin)) 
			{
				item.remove();
				return("employe supprimé");
			}
		}

		return("employe non supprimé");
	}	
	
	
	//http://localhost:8383/JAXRS-CRUD-0.0.1-SNAPSHOT/rest/employes/delete1?cin=123
	@DELETE
	@Path("/delete1")
	public String suppEmp1(@QueryParam("cin") String cin) 
	{
		Iterator<Employe> it=employes.iterator();
		while(it.hasNext()) 
		{
			if(it.next().getCin().equals(cin)) 
			{
				it.remove();
				return("employe supprimé");
			}
		}
		return("employe non supprimé");
	}	
	 
}