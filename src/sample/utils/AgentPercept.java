package sample.utils;

import sample.AI.KnowledgeBaseSingleton;

import java.util.ArrayList;
import java.util.Arrays;

public class AgentPercept {
    private int currCol = 0;
    private int currRow = 0;
    private int currDirection = 0;
    private boolean hasArrow = true;

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
            knowledgeBaseSingleton.addSentenceToKnowledgeBase(makeSentenceLiteral(type1, currCol, currRow));
            knowledgeBaseSingleton.addSentenceToKnowledgeBase(
                    "~"+makeSentenceLiteral(type1, currCol, currRow) + getEnvironmentAssumptions(type2)
            );
        }
        else {
            knowledgeBaseSingleton.addSentenceToKnowledgeBase(makeSentenceLiteral("~"+type1, currCol, currRow));

            if(currCol -1>-1)
                knowledgeBaseSingleton.addSentenceToKnowledgeBase(makeSentenceLiteral("~"+type2, currCol -1, currRow));
            if(currCol +1<10)
                knowledgeBaseSingleton.addSentenceToKnowledgeBase(makeSentenceLiteral("~"+type2, currCol +1, currRow));
            if(currRow -1>-1)
                knowledgeBaseSingleton.addSentenceToKnowledgeBase(makeSentenceLiteral("~"+type2, currCol, currRow -1));
            if(currRow +1<10)
                knowledgeBaseSingleton.addSentenceToKnowledgeBase(makeSentenceLiteral("~"+type2, currCol, currRow +1));
        }
    }


    private String makeSentenceLiteral(String str, int x, int y){
        return str + x+y;
    }

    private String getEnvironmentAssumptions(String type){
        return  ((currCol -1>-1)? "|"+makeSentenceLiteral(type, currCol -1, currRow):"")
                + ((currRow -1>-1)? "|"+makeSentenceLiteral(type, currCol, currRow -1):"")
                + ((currCol +1<10)? "|"+makeSentenceLiteral(type, currCol +1, currRow):"")
                + ((currRow +1<10)? "|"+makeSentenceLiteral(type, currCol, currRow +1):"");
    }

    public int getCurrCol() {
        return currCol;
    }

    public void setCurrCol(int currCol) {
        this.currCol = currCol;
    }

    public int getCurrRow() {
        return currRow;
    }

    public void setCurrRow(int currRow) {
        this.currRow = currRow;
    }

    public int getCurrDirection() {

        return currDirection;
    }

    public void setCurrDirection(int currDirection) {
        this.currDirection = currDirection;
    }

    public boolean isHasArrow() {
        return hasArrow;
    }

    public boolean isVisited(int i, int j){
        return knowledgeBaseSingleton.visitedCell[i][j];
    }

    public void addToVisitedList(int i, int j){
        knowledgeBaseSingleton.visitedCell[i][j] = true;
    }


    public void setHasArrow(boolean hasArrow) {
        this.hasArrow = hasArrow;
    }

    public KnowledgeBaseSingleton getKnowledgeBaseSingleton() {
        return knowledgeBaseSingleton;
    }
}
