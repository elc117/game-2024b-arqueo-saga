package io.github.t2paradigmas;

import java.util.ArrayList;

public class Question {
    private int id;
    private String text;
    private int answer;
    private ArrayList<String> options;
    private int level;
    private boolean answered;

    // Construtor usado na serialização do arquivo JSON na classe Quiz.
    private Question() {
        id = -1;
        text = null;
        options = null;
        answer = -1;
    }

    public Question(String text, Integer answer, ArrayList<String> options, Integer level) {
        this.text = text;
        this.answer = answer;
        this.options = options;
        this.level = level;
        this.answered = false;
    }

    public String getText() {
        return text;
    }

    public Integer getAnswer() {
        return answer;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public boolean isAnswered() {
        return answered;
    }
    public void setAnswered(boolean answered) {
        this.answered = answered;
    }
}
