import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image;


/**
 * To do list application.
 * @author  Jonathan Le
 * @version 1.00
 */
public class ToDoList extends Application {
    private int numberOfTasks = 0;
    private int completedTasks = 0;

    /**
     * Getter for numbersOfTask.
     * @return int numbersOfTask for this object.
     */
    public int getNumberOfTasks() {
        return this.numberOfTasks;
    }

    /**
     * Getter for completedTasks.
     * @return int completedTasks for this object.
     */
    public int getCompletedTasks() {
        return this.completedTasks;
    }

    /**
     * Main method.
     * @param args args.
     */
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();
        Scene scene = new Scene(root, 1200, 800, Color.LIGHTBLUE);
        primaryStage.setTitle("Mr Incredible's TODOLIST");
        Image icon = new Image("img.jpg");
        primaryStage.getIcons().add(icon);
        primaryStage.setResizable(false);

        Text subtext = new Text();
        subtext.setText("Enter your to do items here!");
        subtext.setX(10);
        subtext.setY(720);
        subtext.setFont(Font.font("Verdana", 20));
        root.getChildren().add(subtext);

        Text textCompletedTasks = new Text();
        textCompletedTasks.setText("Amount of tasks completed: " + completedTasks);
        textCompletedTasks.setX(105);
        textCompletedTasks.setY(190);
        textCompletedTasks.setFont(Font.font("Verdana", 10));
        root.getChildren().add(textCompletedTasks);

        Text textNumberOfTasks = new Text();
        textNumberOfTasks.setText("Amount of tasks remaining: " + numberOfTasks);
        textNumberOfTasks.setX(445);
        textNumberOfTasks.setY(190);
        textNumberOfTasks.setFont(Font.font("Verdana", 10));
        root.getChildren().add(textNumberOfTasks);

        //Sets name of task
        TextField taskName = new TextField();
        taskName.setPromptText("Enter your task name");
        taskName.setFocusTraversable(false);
        taskName.setLayoutX(5);
        taskName.setLayoutY(770);
        root.getChildren().add(taskName);

        ImageView imageView = new ImageView();
        imageView.setImage(new Image("happy.png"));
        imageView.setY(200);
        imageView.setX(700);
        root.getChildren().add(imageView);


        //Sets type of task
        ChoiceBox<String> typeChoiceBox = new ChoiceBox<>();
        typeChoiceBox.getItems().addAll("Study", "Shop", "Cook", "Sleep");
        typeChoiceBox.setLayoutX(165);
        typeChoiceBox.setLayoutY(770);
        root.getChildren().add(typeChoiceBox);

        //Sets hours of task
        ChoiceBox<String> timeChoiceBox = new ChoiceBox<>();
        timeChoiceBox.getItems().addAll("1", "2", "3", "4", "5");
        timeChoiceBox.setLayoutX(245);
        timeChoiceBox.setLayoutY(770);
        root.getChildren().add(timeChoiceBox);

        //ListView
        ListView<ToDoItem> listView = new ListView<>();
        listView.setPrefWidth(500);
        listView.setLayoutX(100);
        listView.setLayoutY(200);
        root.getChildren().add(listView);

        ListView<String> finishedItems = new ListView<>();
        finishedItems.setPrefWidth(500);
        finishedItems.setPrefHeight(65);
        finishedItems.setLayoutX(100);
        Text finished = new Text("Finished items: ");
        finished.setX(100);
        finished.setY(612);
        root.getChildren().add(finished);
        finishedItems.setLayoutY(620);
        root.getChildren().add(finishedItems);

        //Queue button
        Button queueButton = new Button();
        queueButton.setLayoutX(10);
        queueButton.setLayoutY(735);
        queueButton.setText("Queue");
        queueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (emptySelections(taskName.getText(), typeChoiceBox.getValue(), timeChoiceBox.getValue()) != 0) {
                    switch (emptySelections(taskName.getText(), typeChoiceBox.getValue(), timeChoiceBox.getValue())) {
                        case 1:
                            alert("No name found", "Error! Please enter a task name!",
                                    "Okay, will do!");
                            break;
                        case 2:
                            alert("No task type found", "Error! Please select a task type!",
                                    "Okay, will do!");
                            break;
                        case 3:
                            alert("No time requirement found", "Error! Please select a time requirement!",
                                    "Okay, will do!");
                            break;
                        default:
                            System.out.println("Should never print.");
                    }
                } else {
                    ToDoItem temp = new ToDoItem(taskName.getText(), typeChoiceBox.getValue(),
                            timeChoiceBox.getValue());
                    boolean found = false;
                    for (int i = 0; i < listView.getItems().size(); i++) {
                        if (temp.equals(listView.getItems().get(i))) {
                            alert("To do list already contains this item",
                                    "You have already added this item!", "Oh, okay!");
                            found = true;
                        }
                    }
                    if (!found) {
                        listView.getItems().add(temp);
                        numberOfTasks++;
                        textNumberOfTasks.setText("Amount of tasks remaining: " + numberOfTasks);
                        if (numberOfTasks == 2) {
                            imageView.setImage(new Image("img.png"));
                        }
                        if (numberOfTasks == 4) {
                            imageView.setImage(new Image("img2.png"));
                        }
                        if (numberOfTasks == 6) {
                            imageView.setImage(new Image("img3.png"));
                        }
                        if (numberOfTasks == 8) {
                            imageView.setImage(new Image("img4.png"));
                        }
                        if (numberOfTasks == 10) {
                            imageView.setImage(new Image("img5.png"));
                        }
                    }
                }
            }
        });
        root.getChildren().add(queueButton);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ToDoItem>() {

            @Override
            public void changed(ObservableValue<? extends ToDoItem> observable, ToDoItem oldValue, ToDoItem newValue) {
                Button dequeueButton = new Button();
                dequeueButton.setText("Dequeue Task");
                dequeueButton.setLayoutX(75);
                dequeueButton.setLayoutY(735);
                root.getChildren().add(dequeueButton);
                dequeueButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            dequeueButton.setVisible(false);
                            String theName =
                                    listView.getItems().get(listView.getSelectionModel().getSelectedIndex()).getName();
                            finishedItems.getItems().add(theName);
                            listView.getItems().remove(listView.getSelectionModel().getSelectedIndex());
                            numberOfTasks--;
                            completedTasks++;
                            if (numberOfTasks == 0) {
                                imageView.setImage(new Image("happy.png"));
                            }
                            if (numberOfTasks == 2) {
                                imageView.setImage(new Image("img.png"));
                            }
                            if (numberOfTasks == 4) {
                                imageView.setImage(new Image("img2.png"));
                            }
                            if (numberOfTasks == 6) {
                                imageView.setImage(new Image("img3.png"));
                            }
                            if (numberOfTasks == 8) {
                                imageView.setImage(new Image("img4.png"));
                            }
                            if (numberOfTasks == 10) {
                                imageView.setImage(new Image("img5.png"));
                            }
                        } catch (IndexOutOfBoundsException ioobe) {
                            alert("No tasks left", "There are no tasks! Good job!", "I'll add a new task then!");
                        }
                        textNumberOfTasks.setText("Amount of tasks remaining: " + numberOfTasks);
                        textCompletedTasks.setText("Amount of tasks completed: " + completedTasks);
                    }
                });
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Checks if user has any empty selections.
     * @param taskName Inputted task name.
     * @param type Inputted task type.
     * @param hours Inputted task hours.
     * @return 1 if name is null, 2 if type is null, 3 if hours is null.
     */
    public static int emptySelections(String taskName, String type, String hours) {
        int i = 0;
        if (taskName == null || taskName.trim().isEmpty()) {
            i = 1;
        } else {
            if (type == null || type.trim().isEmpty()) {
                i = 2;
            } else {
                if (hours == null || hours.trim().isEmpty()) {
                    i = 3;
                }
            }
        }
        return i;
    }

    /**
     * Creates an alert window.
     * @param title Title of the alert.
     * @param message Message of the alert.
     * @param buttonMessage Button message of the alert.
     */
    public static void alert(String title, String message, String buttonMessage) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(200);
        window.setMaxWidth(250);
        window.setMaxHeight(200);
        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button(buttonMessage);
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
