package task3_online_courses.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Test {
    private final String testId;
    private String title;
    private final List<Question> questions;

    public Test(String title) {
        this.testId = UUID.randomUUID().toString().substring(0, 8);
        this.title = title;
        this.questions = new ArrayList<>();
    }

    public String getTestId() {
        return testId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return new ArrayList<>(questions);
    }

    public int evaluate() {
        if (questions.isEmpty()) {
            return 100;
        }
        Random random = new Random();
        int correctAnswers = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (random.nextBoolean()) {
                correctAnswers++;
            }
        }
        return (correctAnswers * 100) / questions.size();
    }

    @Override
    public String toString() {
        return "Test{id='" + testId + "', title='" + title + "', questions=" + questions.size() + "}";
    }

    public static class Question {
        private final String questionId;
        private String text;
        private List<String> options;
        private int correctOptionIndex;

        public Question(String text, List<String> options, int correctOptionIndex) {
            this.questionId = UUID.randomUUID().toString().substring(0, 8);
            this.text = text;
            this.options = new ArrayList<>(options);
            this.correctOptionIndex = correctOptionIndex;
        }

        public String getQuestionId() {
            return questionId;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<String> getOptions() {
            return new ArrayList<>(options);
        }

        public void setOptions(List<String> options) {
            this.options = new ArrayList<>(options);
        }

        public int getCorrectOptionIndex() {
            return correctOptionIndex;
        }

        public void setCorrectOptionIndex(int correctOptionIndex) {
            this.correctOptionIndex = correctOptionIndex;
        }

        public boolean checkAnswer(int selectedOption) {
            return selectedOption == correctOptionIndex;
        }
    }
}
