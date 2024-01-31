package beans;

import exceptions.EmailFormException;

public class EmailBean {
    private final UserBean sender;
    private final UserBean receiver;
    private String object;
    private String body;


    public EmailBean(UserBean sender, UserBean receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }
    public void setObject(String object) throws EmailFormException {
        if(object.isEmpty()){
                throw new EmailFormException("Email object field empty");
        } else {
            this.object = object;
        }
    }
    public void setBody(String body) throws EmailFormException {
        if(body.isEmpty()){
            throw new EmailFormException("Email content field empty");
        } else {
            this.body = body;
        }
    }


    public UserBean getSender() {
        return sender;
    }

    public UserBean getReceiver() {
        return receiver;
    }

    public String getObject() {
        return object;
    }

    public String getBody() {
        return body;
    }
}
