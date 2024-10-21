package br.com.openai.prompt.resource;

import br.com.openai.prompt.config.RequestSplitter;
import br.com.openai.prompt.config.WebCrawler;
import br.com.openai.prompt.service.BlogReaderService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Path("/")
public class BlogReaderResource {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(BlogReaderResource.class);

    private final BlogReaderService blogReaderService;

    private final WebCrawler webCrawler;

    private final RequestSplitter requestSplitter;

    @Inject
    public BlogReaderResource(BlogReaderService blogReaderService, WebCrawler webCrawler, RequestSplitter requestSplitter) {
        this.blogReaderService = blogReaderService;
        this.webCrawler = webCrawler;
        this.requestSplitter = requestSplitter;
    }

    @Path("/read")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String read(String url) {
        // Read the HTML from the specified URL
        url = url.replace("=","");
        String content = webCrawler.crawl(URLDecoder.decode(url, StandardCharsets.UTF_8));

        LOGGER.info("\uD83D\uDD1C Preparing analysis of {}, content{}", url, content);

        // Prepare the model
        blogReaderService.prepare();

        // Split the HTML into small pieces
        List<String> split = requestSplitter.split(content);

        StringBuffer articleCompletedText = new StringBuffer();

        // Send each piece of HTML to the LLM
        for (int i = 0; i < split.size(); i++) {
           blogReaderService.sendBody(split.get(i));
            articleCompletedText.append(split.get(i));
            LOGGER.info("\uD83E\uDDD0 Analyzing article... Part {} out of {}.", (i + 1), split.size());
        }

        //LOGGER.info("\uD83D\uDCDD Preparing response... texto completo do Artigo: {}", articleCompletedText);

        // Ask the model to sum up the article
        String sumUp = blogReaderService.sumUp(new String(articleCompletedText));

        LOGGER.info("✅ Response for {} ready", url);

        // Return the result to the user
        return sumUp;
    }
}