package sample.utils;

public class AgentPercept {
    int currX = 0;
    int currY = 0;

    public AgentPercept(){

    }

    public void addKnowledgeFromPercept(String perceptSpace){
        System.out.println(perceptSpace);
    }



    public int getCurrX() {
        return currX;
    }

    public void setCurrX(int currX) {
        this.currX = currX;
    }

    public int getCurrY() {
        return currY;
    }

    public void setCurrY(int currY) {
        this.currY = currY;
    }
}
