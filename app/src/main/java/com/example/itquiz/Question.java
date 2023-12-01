package com.example.itquiz;

public class Question {
    private String IDquestion;
    private String question;
    private String answer;
    private String option1;
    private String option2;
    private String option3;
    private String selectAnswer;
    public Question() {
    }

    public Question(String IDquestion,String question, String answer, String option1, String option2, String option3, String selectAnswer) {
        this.IDquestion = IDquestion;
        this.question = question;
        this.answer = answer;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.selectAnswer = selectAnswer;
    }

    public String getIDquestion() {
        return IDquestion;
    }

    public void setIDquestion(String IDquestion) {
        this.IDquestion = IDquestion;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getSelectAnswer() {
        return selectAnswer;
    }

    public void setSelectAnswer(String selectAnswer) {
        this.selectAnswer = selectAnswer;
    }


    @Override
    public String toString() {
        return question + "\n a. " +
                answer + "\n b. " +
                option1 + "\n c. " +
                option2 + "\n d. " +
                option3 ;
    }
}
