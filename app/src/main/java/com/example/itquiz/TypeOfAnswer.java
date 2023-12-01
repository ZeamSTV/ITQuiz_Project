package com.example.itquiz;

public class TypeOfAnswer {
    int rightAnswer, skipAnswer, wrongAnswer, timeoutAnswer, score;

    public TypeOfAnswer(int rightAnswer, int skipAnswer, int wrongAnswer, int timeoutAnswer, int score) {
        this.rightAnswer = rightAnswer;
        this.skipAnswer = skipAnswer;
        this.wrongAnswer = wrongAnswer;
        this.timeoutAnswer = timeoutAnswer;
        this.score = score;
    }

    public TypeOfAnswer() {
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public int getSkipAnswer() {
        return skipAnswer;
    }

    public void setSkipAnswer(int skipAnswer) {
        this.skipAnswer = skipAnswer;
    }

    public int getWrongAnswer() {
        return wrongAnswer;
    }

    public void setWrongAnswer(int wrongAnswer) {
        this.wrongAnswer = wrongAnswer;
    }

    public int getTimeoutAnswer() {
        return timeoutAnswer;
    }

    public void setTimeoutAnswer(int timeoutAnswer) {
        this.timeoutAnswer = timeoutAnswer;
    }

    public void clearResult(){
        score = 0;
        rightAnswer = 0;
        skipAnswer = 0;
        wrongAnswer = 0;
        timeoutAnswer = 0;
    }
}
