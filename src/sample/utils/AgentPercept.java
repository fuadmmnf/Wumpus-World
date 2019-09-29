package sample.utils;

import sample.AI.KnowledgeBaseSingleton;

import java.util.ArrayList;
import java.util.Arrays;

public class AgentPercept {
    private int currX = 0;
    private int currY = 0;
    KnowledgeBaseSingleton knowledgeBaseSingleton = KnowledgeBaseSingleton.getInstance();


    public void addKnowledgeFromPercept(String perceptSpace){
        ArrayList<String> percept = new ArrayList<String>(Arrays.asList(perceptSpace));
        extractKnowledge(percept, "B", "P");
        extractKnowledge(percept, "Gl", "G");
        extractKnowledge(percept, "S", "W");
    }


    private void extractKnowledge(ArrayList<String> percept, String type1, String type2){
        if(percept.contains(type1))
        {
            knowledgeBaseSingleton.addSentenceToKnowledgeBase(makeSentenceLiteral(type1, currX, currY));
            knowledgeBaseSingleton.addSentenceToKnowledgeBase(
                    "~"+makeSentenceLiteral(type1, currX, currY) + getEnvironmentAssumptions(type2)
            );
        }
        else {
            knowledgeBaseSingleton.addSentenceToKnowledgeBase(makeSentenceLiteral("~"+type1, currX, currY));

            if(currX-1>-1)
                knowledgeBaseSingleton.addSentenceToKnowledgeBase(makeSentenceLiteral("~"+type2, currX-1, currY));
            if(currX+1<10)
                knowledgeBaseSingleton.addSentenceToKnowledgeBase(makeSentenceLiteral("~"+type2, currX+1, currY));
            if(currY-1>-1)
                knowledgeBaseSingleton.addSentenceToKnowledgeBase(makeSentenceLiteral("~"+type2, currX, currY-1));
            if(currY+1<10)
                knowledgeBaseSingleton.addSentenceToKnowledgeBase(makeSentenceLiteral("~"+type2, currX, currY+1));
        }
    }


    private String makeSentenceLiteral(String str, int x, int y){
        return str + x+y;
    }

    private String getEnvironmentAssumptions(String type){
        return  ((currX-1>-1)? "|"+makeSentenceLiteral(type, currX-1, currY):"")
                + ((currY-1>-1)? "|"+makeSentenceLiteral(type, currX, currY-1):"")
                + ((currX+1<10)? "|"+makeSentenceLiteral(type, currX+1, currY):"")
                + ((currY+1<10)? "|"+makeSentenceLiteral(type, currX, currY+1):"");
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
