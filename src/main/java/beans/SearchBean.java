package beans;

import exceptions.EmptySearchException;

public class SearchBean {
    private String name;
    public SearchBean(String name) throws EmptySearchException {
        if(name==null){
            throw new EmptySearchException();
        }
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
