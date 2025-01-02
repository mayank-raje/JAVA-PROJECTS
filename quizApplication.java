import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

// Class representing a Quiz Question
class Question {
    String questionText;
    String[] options;
    String correctAnswer;

    public Question(String questionText, String[] options, String correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public boolean checkAnswer(String answer) {
        return correctAnswer.equals(answer);
    }
}

public class quizApplication {
    private static final int TIME_LIMIT = 10; 
    private static int score = 0;
    private static ArrayList<Question> questions;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
       
        initializeQuestions();
        startQuiz();
        displayResult();
    }

    private static void initializeQuestions() {
        questions = new ArrayList<>();
        questions.add(new Question("What does HTML stand for?", new String[]{"A. Hyper Text Markup Language", "B. Hyper Text Management Language", "C. High Text Markup Language", "D. Hyperlinks Text Markup Language"}, "A"));
        questions.add(new Question("Which HTML element is used to display a picture?", new String[]{"A. <image>", "B. <pic>", "C. <img>", "D. <image-tag>"}, "C"));
        questions.add(new Question("What is the correct syntax for linking an external CSS file?", new String[]{"A. <link src='style.css'>", "B. <link href='style.css'>", "C. <style src='style.css'>", "D. <stylesheet href='style.css'>"}, "B"));
        questions.add(new Question("Which HTML tag is used for the largest heading?", new String[]{"A. <h6>", "B. <h1>", "C. <h2>", "D. <header>"}, "B"));
        questions.add(new Question("What is the default value of the position property in CSS?", new String[]{"A. relative", "B. absolute", "C. static", "D. fixed"}, "C"));
    }

    private static void startQuiz() {
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + question.questionText);
            for (String option : question.options) {
                System.out.println(option);
            }
        
            String userAnswer = getAnswerWithTimer();

            if (question.checkAnswer(userAnswer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect! The correct answer was: " + question.correctAnswer);
            }
        }
    }

    private static void displayResult() {
        System.out.println("\nQuiz Completed!");
        System.out.println("Your final score is: " + score + "/" + questions.size());
    }
  
    private static String getAnswerWithTimer() {
        final String[] userAnswer = new String[1];
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (userAnswer[0] == null) {
                    System.out.println("\nTime is up!");
                    sc.nextLine();
                }
            }
        };
        
        timer.schedule(task, TIME_LIMIT * 1000);
    
        System.out.print("Your answer (A/B/C/D): ");
        userAnswer[0] = sc.nextLine().toUpperCase();
        task.cancel();
        return userAnswer[0];
    }
}
