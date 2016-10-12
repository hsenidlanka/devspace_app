package hsl.devspace.app.coreserver.model;

/**
 * Created by Kasun Dinesh on 7/22/16.
 * This is the model/template of the HATEOAS links to pass as a response.
 */
public class Link {
    private String link; // HATEOAS link (URL)
    private String rel; // Description

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }
}
