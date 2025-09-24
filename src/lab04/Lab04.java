//https://github.com/Alex2492595/Lab04

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab04;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Alexander Nikopoulos
 * Lab 04
 * 19/09/2025
 */
public class Lab04 extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GridPane root = new GridPane();
        
        Button calculate = new Button("Calculate");
        
        Label dayslbl = new Label("Days Spent: ");
        Label airfarelbl = new Label("Airfare: ");
        Label carFeeslbl = new Label("Car Rental Fees: ");
        Label mileslbl = new Label("Miles Driven (Private Vehicle Only): ");
        Label parkinglbl = new Label("Parking Fees: ");
        Label taxilbl = new Label("Taxi Charges: ");
        Label registerFeeslbl = new Label("Conference/Seminar Registration Fees: ");
        Label lodginglbl = new Label("Lodging Charges (Per Night): ");
        Label totalExpenseslbl = new Label("");
        Label allowablelbl = new Label("");
        Label excesslbl = new Label("");
        Label savingslbl = new Label("");
        
        Text errorText = new Text();
        
        TextField daysTF = new TextField();
        TextField airfareTF = new TextField();
        TextField carFeesTF = new TextField();
        TextField milesTF = new TextField();
        TextField parkingTF = new TextField();
        TextField taxiTF = new TextField();
        TextField registerFeesTF = new TextField();
        TextField lodgingTF = new TextField();
        
        root.add(dayslbl, 0, 0);
        root.add(daysTF, 1, 0);
        root.add(airfarelbl, 0, 1);
        root.add(airfareTF, 1, 1);
        root.add(carFeeslbl, 0, 2);
        root.add(carFeesTF, 1, 2);
        root.add(mileslbl, 0, 3);
        root.add(milesTF, 1, 3);
        root.add(parkinglbl, 0, 4);
        root.add(parkingTF, 1, 4);
        root.add(taxilbl, 0, 5);
        root.add(taxiTF, 1, 5);
        root.add(registerFeeslbl, 0, 6);
        root.add(registerFeesTF, 1, 6);
        root.add(lodginglbl, 0, 7);
        root.add(lodgingTF, 1, 7);
        root.add(calculate, 0, 8);
        root.add(totalExpenseslbl, 0, 9);
        root.add(allowablelbl, 0, 10);
        root.add(excesslbl, 0, 11);
        root.add(savingslbl, 0, 12);
        root.add(errorText, 0, 9);
        
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        
        totalExpenseslbl.getStyleClass().add("label-results");
        allowablelbl.getStyleClass().add("label-results");
        excesslbl.getStyleClass().add("label-results");
        savingslbl.getStyleClass().add("label-results");
        
        calculate.setOnAction(e -> {
            try {
                int days = Integer.parseInt(daysTF.getText());
                double airfare = Integer.parseInt(airfareTF.getText());
                double carFees = Integer.parseInt(carFeesTF.getText());
                double miles = Integer.parseInt(milesTF.getText());
                double parking = Integer.parseInt(parkingTF.getText());
                double taxi = Integer.parseInt(taxiTF.getText());
                double registerFees = Integer.parseInt(registerFeesTF.getText());
                double lodging = Integer.parseInt(lodgingTF.getText());

                double parkingMax = days * 10;
                double taxiMax = days * 20;
                double lodgingMax = days * 95;

                double totalExpenses = airfare + carFees + (miles * 0.27) + parking + taxi + registerFees + (lodging * days) + (days * 37);
                double totalAllowable = airfare + carFees + (miles * 0.27) + parkingMax + taxiMax + registerFees + lodgingMax + (days * 37);

                double excess = 0;
                double savings = 0;

                if (parking > parkingMax) {
                    excess += (parking - parkingMax);
                } else {
                    savings += (parkingMax - parking);
                }

                if (taxi > taxiMax) {
                    excess += (taxi - taxiMax);
                } else {
                    savings += (taxiMax - taxi);
                }

                if (lodging > (lodgingMax / days)) {
                    excess += ((lodging * days) - lodgingMax);
                } else {
                    savings += (lodgingMax - (lodging * days));
                }

                errorText.setText("");
                totalExpenseslbl.setText("Total Expenses: $" + String.format("%.2f", totalExpenses));
                allowablelbl.setText("Total Allowable Expenses: $" + String.format("%.2f", totalAllowable));
                excesslbl.setText("Excess to be Paid: $" + String.format("%.2f", excess));
                savingslbl.setText("Amount Saved: $" + String.format("%.2f", savings));
                
            } catch (NumberFormatException ex) {
                errorText.setText("Please enter missing information.");
                errorText.setFill(Color.FIREBRICK);
                totalExpenseslbl.setText("");
                allowablelbl.setText("");
                excesslbl.setText("");
                savingslbl.setText("");
            }
        });
        
        Scene scene = new Scene(root, 600, 500);
        
        scene.getStylesheets().add("stylesheet1.css");
        stage.setScene(scene);
        stage.show();
    }
}
