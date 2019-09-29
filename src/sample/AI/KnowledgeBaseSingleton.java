package sample.AI;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeBaseSingleton {
    private static KnowledgeBaseSingleton ourInstance = new KnowledgeBaseSingleton();
    private static List<String> knowledbaseSentence = new ArrayList<>();

    public static KnowledgeBaseSingleton getInstance() {
        return ourInstance;
    }

    public List<String> getKnowledgeBaseSentences(){
        return knowledbaseSentence;
    }

    public void addSentenceToKnowledgeBase(String sentence){
        knowledbaseSentence.add(sentence);
    }


    private KnowledgeBaseSingleton() {
    }
}
