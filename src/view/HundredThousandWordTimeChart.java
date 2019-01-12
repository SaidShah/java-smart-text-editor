package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.TimeCalculations;

public class HundredThousandWordTimeChart {

	TimeCalculations timeCalcs = new TimeCalculations();
	Scene hundredThousandWordScene;

	@SuppressWarnings("unchecked")
	public HundredThousandWordTimeChart(Stage stage, Scene scene) {
		Image image = new Image("file:Images/BackgroundImage.jpg");
		BackgroundImage bgi = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		BorderStroke borderStroke = new BorderStroke(Color.DARKORANGE, BorderStrokeStyle.DASHED, null,
				new BorderWidths(5));
		final String TEXT_FONT = "-fx-font: 20 verdana; -fx-font-weight: bold;";
		final int BUTTON_WIDTH = 100;
		final int BUTTON_HEIGHT = 60;

		Button backButton = new Button("Back");
		backButton.setStyle(TEXT_FONT);
		backButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		backButton.setTextFill(Color.BLUE);
		Label oneLoopBigO = new Label("One Loop Time Complexity is O( n )");
		oneLoopBigO.setStyle(TEXT_FONT);
		oneLoopBigO.setTextFill(Color.DARKBLUE);
		Label threeLoopBigO = new Label("Three Loop Time Complexity is O(Log n )");
		threeLoopBigO.setStyle(TEXT_FONT);
		threeLoopBigO.setTextFill(Color.DARKBLUE);

		timeCalcs.loadHundredThousandWordOneLoopArray();
		timeCalcs.loadHundredThousandWordThreeLoopArray();

		long[] oneLoopArray = timeCalcs.getHundredThousandWordOneLoopArray();
		long[] threeLoopArray = timeCalcs.getHundredThousandWordThreeLoopArray();

		NumberAxis nanoSecondsY = new NumberAxis(0, 14000, 2500);
		NumberAxis wordsX = new NumberAxis(0, 100000, 5000);
		nanoSecondsY.setTickLabelFill(Color.WHITE);
		nanoSecondsY.isAutoRanging();
		wordsX.setTickLabelFill(Color.WHITE);
		wordsX.isAutoRanging();

		AreaChart<Number, Number> areaChart = new AreaChart<Number, Number>(wordsX, nanoSecondsY);
		areaChart.setTitle("One Loop vs. Three Loops\n     	  100,000 Words");
		areaChart.setMinSize(1300, 700);
		areaChart.setBorder(new Border(borderStroke));

		XYChart.Series<Number, Number> oneLoopSeries = new XYChart.Series<Number, Number>();
		oneLoopSeries.setName("One Loop");
		areaChart.setStyle(TEXT_FONT);
		oneLoopSeries.getData().add(new XYChart.Data<Number, Number>(100, oneLoopArray[99]));
		oneLoopSeries.getData().add(new XYChart.Data<Number, Number>(1000, oneLoopArray[999]));
		oneLoopSeries.getData().add(new XYChart.Data<Number, Number>(10000, oneLoopArray[9999]));
		oneLoopSeries.getData().add(new XYChart.Data<Number, Number>(100000, oneLoopArray[99999]));

		XYChart.Series<Number, Number> threeLoopSeries = new XYChart.Series<Number, Number>();
		threeLoopSeries.setName("Three Loops");
		threeLoopSeries.getData().add(new XYChart.Data<Number, Number>(100, threeLoopArray[99]));
		threeLoopSeries.getData().add(new XYChart.Data<Number, Number>(1000, threeLoopArray[999]));
		threeLoopSeries.getData().add(new XYChart.Data<Number, Number>(10000, threeLoopArray[9999]));
		threeLoopSeries.getData().add(new XYChart.Data<Number, Number>(100000, threeLoopArray[99999]));

		VBox screen = new VBox(20);
		screen.setAlignment(Pos.CENTER);
		screen.getChildren().addAll(backButton, areaChart, oneLoopBigO, threeLoopBigO);
		screen.setBackground(new Background(bgi));

		hundredThousandWordScene = new Scene(screen, 1650, 950);

		areaChart.getData().addAll(oneLoopSeries, threeLoopSeries);

		backButton.setOnAction(e -> {
			stage.setScene(scene);
		});

	}

	public Scene getScene() {
		return hundredThousandWordScene;
	}
}
