package info.infiniteloops.discuss.chat.model;

import java.util.ArrayList;


public class Consersation {
    public ArrayList<Message> listMessageData;
    public Consersation(){
        listMessageData = new ArrayList<>();
    }

    public ArrayList<Message> getListMessageData() {
        return listMessageData;
    }
}
