package hsl.devspace.app.coreserver.resources;

import hsl.devspace.app.corelogic.domain.Package;
import hsl.devspace.app.corelogic.repository.Package.PackageRepositoryImpl;
import hsl.devspace.app.coreserver.common.Context;
import hsl.devspace.app.coreserver.common.PropertyReader;
import hsl.devspace.app.coreserver.model.SuccessMessage;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Created by Kasun Dinesh on 9/29/16.
 * This class handles the requests related to the packages.
 */
@Path("/packages")
public class PackageService {
    private static final Logger log = LoggerFactory.getLogger(PackageService.class);
    ApplicationContext context = Context.appContext;
    PackageRepositoryImpl packageRepository = (PackageRepositoryImpl) context.getBean("packageRepoImpl");
    PropertyReader propertyReader = new PropertyReader("header.properties");

    // Retrieve all the packages
    @GET
    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPackages(@javax.ws.rs.core.Context UriInfo uriInfo) {
        List packageList = packageRepository.selectAll();
        Response response;
        Package pack=new Package();
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setCode(Response.Status.OK.getStatusCode());
        successMessage.setStatus("success");
        String url = uriInfo.getAbsolutePath().toString();
        successMessage.addLink(url, "self");
        if (packageList.size() != 0) {
            successMessage.setMessage("packages retrieved");
            for (int i = 0; i < packageList.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                pack = (Package) packageList.get(i);
                jsonObject.put("PackageName", pack.getPackName());
//                jsonObject.put("content", pack.getContent());
                jsonObject.put("price", pack.getPrice());
                jsonObject.put("imagePath", pack.getImage());
                successMessage.addData(jsonObject);
            }
        } else {
            successMessage.setMessage("no packages to retrieve");
        }
        return Response.status(Response.Status.OK).entity(successMessage)
                .header("Access-Control-Allow-Origin", propertyReader.readProperty("Access-Control-Allow-Origin"))
                .build();
    }
}
