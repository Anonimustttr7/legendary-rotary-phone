package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class Main extends Application {
    int n = 25;
    int m = 25;
    private int[][] board = new int[n][m];
    private Button[][] buttons = new Button[n][m];

    @Override
    public void start(Stage primaryStage) throws Exception {
        generateBoard();

        GridPane gridPane = new GridPane();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Button button = new Button();
                button.setPrefSize(30, 30);
                button.setStyle("-fx-font-size: 12");
                int x = i;
                int y = j;
                button.setOnAction(e -> revealCell(x, y));
                buttons[i][j] = button;
                gridPane.add(button, i, j);
            }
        }
        Scene scene = new Scene(gridPane,300,300);
        primaryStage.setTitle("сапер");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
        private void generateBoard(){
            int b = 6;
            Random random = new Random();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    board[i][j] = random.nextInt(b);
                }
            }
        }

        private void revealCell(int x,int y){
            if (board[x][y] == 0) {
                int minesAround = countMinesAround(x,y);
                buttons[x][y].setText(minesAround > 0? String.valueOf(minesAround):"бах");
            } else {
                buttons[x][y].setText("0");
            }
        }

        private int countMinesAround(int x,int y) {
            int count = 0;
            for (int i = Math.max(0, x - 1); i <= Math.min(9, x + 1); i++) {
                for (int j = Math.max(0, y - 1); j <= Math.min(9, y + 1); j++) {
                    if (board[i][j]==1) {
                        count++;
                    }
                }
            }
            return count;
        }

//        private void gameover(int x,int y) {
//            for(int i=0;i<x;i++){
//                for(int j=0;j<y;j++){
//                    Button button = buttons[i][j];
//                    if(button[i][j]){
//                        button.setText("x");
//                    }
//                }
//            }
//    }


    public static void main(String[] args) {
        launch(args);
    }
}
