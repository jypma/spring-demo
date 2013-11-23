package nl.ypmania.demo.controller;

public class HomeForm {
    public static HomeForm empty() { 
        return new HomeForm(null); 
    }
    
    private String messageFromUser;
    
    public String getMessageFromUser() {
        return messageFromUser;
    }

    public HomeForm(String messageFromUser) {
        this.messageFromUser = messageFromUser;
    }
}
