package exceptions;

public class BrowsingNotSupportedException extends CostumException{

    public BrowsingNotSupportedException() {
        super(
                "BROWSING ERROR: Failed to browse to the lesson url. \n " +
                        "Sorry for the inconvenience but you can't access this web resource"
        );
    }
}
