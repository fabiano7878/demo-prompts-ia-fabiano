package br.com.openai.prompt.resource;

import br.com.openai.prompt.service.MyAiService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

@Path("/email-me-a-poem")
public class EmailMeAPoemResource {

    private final MyAiService service;

    public EmailMeAPoemResource(MyAiService service) {
        this.service = service;
    }

    @GET
    public String emailMeAPoem(@QueryParam("topiPoem") String topiPoem,
                               @QueryParam("qtdLine") int qtdLine) {
        return service.writeAPoem(topiPoem, qtdLine);
    }

}
