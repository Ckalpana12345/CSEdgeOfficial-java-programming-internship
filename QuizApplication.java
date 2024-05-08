import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApplication extends JFrame {
    private JButton startButton;
    private JTextArea questionArea;
    private JRadioButton option1, option2, option3, option4;
    private ButtonGroup optionGroup;
    private JButton nextButton;

    private String[] questions = {"What is the capital of France?",
                                  "Who painted the Mona Lisa?",
                                  "What is the largest planet in our solar system?"};
    private String[][] options = {{"Paris", "Berlin", "London", "Rome"},
                                  {"Leonardo da Vinci", "Vincent van Gogh", "Pablo Picasso", "Claude Monet"},
                                  {"Jupiter", "Mars", "Saturn", "Neptune"}};
                                  
   
    private int[] answers = {0, 0, 0};
    private int currentQuestionIndex;

    public QuizApplication() {
        setTitle("Quiz Application");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        startButton = new JButton("Start Quiz");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startQuiz();
            }
        });
        add(startButton, BorderLayout.NORTH);

        JPanel quizPanel = new JPanel(new GridLayout(5, 1));
        questionArea = new JTextArea();
        questionArea.setEditable(true);
        quizPanel.add(questionArea);

        optionGroup = new ButtonGroup();
        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();
        optionGroup.add(option1);
        optionGroup.add(option2);
        optionGroup.add(option3);
        optionGroup.add(option4);
        quizPanel.add(option1);
        quizPanel.add(option2);
        quizPanel.add(option3);
        quizPanel.add(option4);

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processAnswer();
            }
        });
        nextButton.setEnabled(false);
        quizPanel.add(nextButton);

        add(quizPanel, BorderLayout.CENTER);

        setVisible(true);
    }
    private void startQuiz() {
        startButton.setEnabled(false);
        nextButton.setEnabled(true);
        currentQuestionIndex = 0;
        loadQuestion();
    }

    private void loadQuestion() {
        questionArea.setText(questions[currentQuestionIndex]);
        option1.setText(options[currentQuestionIndex][0]);
        option2.setText(options[currentQuestionIndex][1]);
        option3.setText(options[currentQuestionIndex][2]);
        option4.setText(options[currentQuestionIndex][3]);
        optionGroup.clearSelection();
    }

    private void processAnswer() {
        if (option1.isSelected()) {
            answers[currentQuestionIndex] = 0;
        } else if (option2.isSelected()) {
            answers[currentQuestionIndex] = 1;
        } else if (option3.isSelected()) {
            answers[currentQuestionIndex] = 2;
        } else if (option4.isSelected()) {
            answers[currentQuestionIndex] = 3;
        }

        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            loadQuestion();
        } else {
            showResult();
        }
    }

    private void showResult() {
        int correctAnswers = 0;
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == getCorrectAnswerIndex(i)) {
                correctAnswers++;
            }
        }
        JOptionPane.showMessageDialog(this, "You got " + correctAnswers + " out of " + questions.length + " questions correct!");
    }

    private int getCorrectAnswerIndex(int questionIndex) {
        // This method would ideally retrieve correct answers from a database or predefined list
        return 0; // For simplicity, always return the first option as the correct answer
    }
    public static void main(String[] args) {
        new QuizApplication();
    }
    
}



