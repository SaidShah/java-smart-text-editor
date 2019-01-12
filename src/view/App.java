package view;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Link;
import model.LinkList;

public class App extends Application {

	LinkList myList = new LinkList();
	Scene sceneOne;
	final String TEXT_STYLE = "-fx-font: 19 verdana; -fx-font-weight: bold";
	private String textCopied;
	final Clipboard myClipBoard = Clipboard.getSystemClipboard();
	final ClipboardContent myContent = new ClipboardContent();
	Alert alert = new Alert(AlertType.ERROR);
	int pos = 0;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		File warAndpeaceFile = new File("InputData/warAndpeace.dat");
		myList.loadDictionary();
		if (warAndpeaceFile.exists()) {
			load(warAndpeaceFile);
		}
		Image image = new Image("file:Images/BackgroundImage.jpg");
		BackgroundImage bgi = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

		Menu fileMenu = new Menu("_File");
		Menu editMenu = new Menu("_Edit");
		Menu viewMenu = new Menu("_View");
		Menu markovMenu = new Menu("_Markov");
		Menu chartsMenu = new Menu("_Charts");

		MenuItem saveText = new MenuItem("_Save");
		MenuItem closeWindow = new MenuItem("_Close");
		MenuItem newText = new MenuItem("_New");
		MenuItem clearText = new MenuItem("_Clear");
		MenuItem openText = new MenuItem("_Open");

		MenuItem wordCount = new MenuItem("_Word Count");
		MenuItem sentenceCount = new MenuItem("_Sentence Count");
		MenuItem fleschScore = new MenuItem("_Flesch Score");

		MenuItem copyText = new MenuItem("_Copy");
		MenuItem cutText = new MenuItem("_Cut");
		MenuItem pasteText = new MenuItem("_Paste");
		MenuItem deleteText = new MenuItem("_Delete");

		MenuItem useWarAndPeace = new MenuItem("_Use War and Peace");

		MenuItem hundredThousandWordChart = new MenuItem("100,000 Word Chart");

		fileMenu.getItems().addAll(newText, saveText, openText, clearText, closeWindow);
		editMenu.getItems().addAll(copyText, cutText, pasteText, deleteText);
		viewMenu.getItems().addAll(wordCount, sentenceCount, fleschScore);
		markovMenu.getItems().addAll(useWarAndPeace);
		chartsMenu.getItems().addAll(hundredThousandWordChart);

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu, editMenu, viewMenu, markovMenu, chartsMenu);

		TextArea textArea = new TextArea();
		textArea.setPrefSize(1100, 1200);
		textArea.setStyle(TEXT_STYLE);
		textArea.setPadding(new Insets(30));
		textArea.setWrapText(true);

		TextArea suggestionArea = new TextArea();
		suggestionArea.setPrefSize(300, 400);
		suggestionArea.setStyle(TEXT_STYLE);
		suggestionArea.setPadding(new Insets(30));
		suggestionArea.setEditable(false);
		suggestionArea.setText("SUGGESTIONS");

		TextArea missSpelledWords = new TextArea();
		missSpelledWords.setPrefSize(400, 400);
		missSpelledWords.setStyle(TEXT_STYLE);
		missSpelledWords.setPadding(new Insets(30));
		missSpelledWords.setEditable(false);
		missSpelledWords.setText("MISSPELLED WORDS");

		Label wordCountLabel = new Label("Total Word Count:");
		wordCountLabel.setStyle(TEXT_STYLE);
		wordCountLabel.setTextFill(Color.WHITE);
		TextField wordCountText = new TextField();
		wordCountText.setStyle(TEXT_STYLE);
		wordCountText.setMaxSize(100, 50);

		Label sentenceCountLabel = new Label("Sentence Count:");
		sentenceCountLabel.setStyle(TEXT_STYLE);
		sentenceCountLabel.setTextFill(Color.WHITE);
		TextField sentenceCountText = new TextField();
		sentenceCountText.setStyle(TEXT_STYLE);
		sentenceCountText.setMaxSize(100, 50);

		Label fleschScoreLabel = new Label("Flesch Score:");
		fleschScoreLabel.setStyle(TEXT_STYLE);
		fleschScoreLabel.setTextFill(Color.WHITE);
		TextField fleschScoreText = new TextField();
		fleschScoreText.setStyle(TEXT_STYLE);
		fleschScoreText.setMaxSize(100, 20);

		HBox textBox = new HBox(10);
		textBox.getChildren().add(textArea);
		textBox.setAlignment(Pos.CENTER);

		HBox statusBars = new HBox(15);
		statusBars.getChildren().addAll(wordCountLabel, wordCountText, sentenceCountLabel, sentenceCountText,
				fleschScoreLabel, fleschScoreText);
		statusBars.setAlignment(Pos.CENTER);
		statusBars.setPadding(new Insets(50));

		BorderPane layout = new BorderPane();
		layout.setTop(menuBar);
		layout.setMinHeight(50);

		HBox textAreas = new HBox();
		textAreas.setPadding(new Insets(20));
		textAreas.setSpacing(20);
		textAreas.getChildren().addAll(suggestionArea, textBox, missSpelledWords);
		textAreas.setAlignment(Pos.CENTER);

		VBox screen = new VBox();
		screen.getChildren().addAll(layout, textAreas, statusBars);
		screen.setAlignment(Pos.CENTER);
		screen.setBackground(new Background(bgi));

		sceneOne = new Scene(screen, 1650, 700);
		primaryStage.setScene(sceneOne);
		primaryStage.show();

		closeWindow.setOnAction(e -> {
			Platform.exit();
		});

		textArea.textProperty().addListener((textProperty, oldText, newTypedText) -> {
			final int MAX_WORD_SIZE = 20;
			if (!newTypedText.isEmpty()) {
				suggestionArea.setText("SUGGESTIONS\n");
				missSpelledWords.setText("MISSPELLED WORDS\n");
				pos = textArea.getCaretPosition();
				String tempText = newTypedText.substring(pos < MAX_WORD_SIZE ? 0 : pos - (MAX_WORD_SIZE - 1),
						pos + 1 > newTypedText.length() ? newTypedText.length() : pos + 1);
				String tempWords[] = tempText.split("[\\W]+");
				if (tempWords.length > 0) {
					String wordToSearch = tempWords[tempWords.length - 1];
					String[] words = newTypedText.split("[\\s]+");
					Link tempLink = myList.findWord(wordToSearch);
					for (String s : words) {
						boolean isThere = myList.getDictionary().containsValue(s.trim());
						if (isThere == false) {
							missSpelledWords.appendText(s.trim() + "\n");
						}
					}
					if (tempLink != null) {
						String[] allWords = tempLink.getMyList().getWordsFromBabyLink();
						for (int i = 0; i < 15 && i < allWords.length; i += 2) {
							suggestionArea.appendText(allWords[i].trim() + "\n");

						}

					}
				}
			}
		});

		suggestionArea.setOnMouseClicked(e -> {
			if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
				String word = suggestionArea.getSelectedText();
				textArea.insertText(pos == textArea.getText().length() ? pos : pos + 1, " " + word);
				pos = pos + word.length() + 1;
				textArea.insertText(pos++, " ");

			}
		});

		copyText.setOnAction(e -> {
			textCopied = textArea.getSelectedText();
			myContent.putString(textCopied);
			myClipBoard.setContent(myContent);
		});

		cutText.setOnAction(e -> {
			textCopied = textArea.getSelectedText();
			myContent.putString(textCopied);
			myClipBoard.setContent(myContent);
			textArea.replaceSelection("");

		});

		clearText.setOnAction(e -> {
			textArea.clear();
			suggestionArea.clear();
			wordCountText.clear();
			sentenceCountText.clear();
			fleschScoreText.clear();
			missSpelledWords.clear();
			suggestionArea.setText("SUGGESTIONS\n");
			missSpelledWords.setText("MISSPELLED WORDS\n");
		});

		pasteText.setOnAction(e -> {
			textArea.appendText(textCopied);
		});

		deleteText.setOnAction(e -> {
			textArea.replaceSelection("");
		});

		useWarAndPeace.setOnAction(e -> {
			GridPane markovPane = new GridPane();
			markovPane.setHgap(15);
			markovPane.setVgap(15);
			markovPane.setPadding(new Insets(20, 20, 20, 20));
			final Alert markovDialog = new Alert(AlertType.CONFIRMATION);
			markovDialog.setTitle("Markov Generator");
			Label wordLabel = new Label("Which word would you like to start with? ");
			wordLabel.setStyle(TEXT_STYLE);
			wordLabel.setTextFill(Color.BLUE);
			TextField pickWord = new TextField();
			pickWord.setStyle(TEXT_STYLE);
			pickWord.setMaxSize(100, 50);
			Label numLabel = new Label("How many words would you like? ");
			numLabel.setStyle(TEXT_STYLE);
			numLabel.setTextFill(Color.BLUE);
			TextField numEntered = new TextField();
			numEntered.setStyle(TEXT_STYLE);
			numEntered.setMaxSize(100, 50);
			Label warningLabel = new Label("Invalid Number or Word");
			warningLabel.setStyle(TEXT_STYLE);
			warningLabel.setTextFill(Color.RED);
			warningLabel.setVisible(false);
			markovPane.add(wordLabel, 0, 0);
			markovPane.add(pickWord, 1, 0);
			markovPane.add(numLabel, 0, 1);
			markovPane.add(numEntered, 1, 1);
			markovPane.add(warningLabel, 0, 2);
			markovDialog.initOwner(null);
			markovDialog.getDialogPane().setContent(markovPane);
			Optional<ButtonType> choice = markovDialog.showAndWait();
			while (choice.isPresent() && choice.get() == ButtonType.OK) {
				String word = pickWord.getText().trim();
				boolean isWord = word.matches("[a-zA-Z]+");
				String number = numEntered.getText().trim();
				boolean isNumber = number.matches("[0-9]+");
				if (word.isEmpty() || number.isEmpty() || isWord == false || isNumber == false) {
					warningLabel.setVisible(true);
					choice = markovDialog.showAndWait();
				} else {
					File dataFile = new File("InputData/warAndpeace.dat");
					if (!dataFile.exists()) {
						String text = myList.convertDocumentToString("InputData/WarAndPeace.txt");
						myList.readWords(text);
						save(dataFile);
					} else {
						load(dataFile);
					}
					int totalWords = Integer.parseInt(number);
					String randomWords[] = myList.randomSentence(word, totalWords);
					if (randomWords == null) {
						alert.setAlertType(AlertType.ERROR);
						alert.setContentText("Sorry, there are not enough words available to generate your sentence");
						alert.showAndWait();
						break;
					}
					textArea.clear();
					wordCountText.clear();
					sentenceCountText.clear();
					fleschScoreText.clear();
					for (int i = 0; i < randomWords.length - 1; i++) {
						textArea.appendText(randomWords[i] + " ");
					}
					break;
				}

			}
			suggestionArea.setText("SUGGESTIONS");
			missSpelledWords.clear();
			missSpelledWords.setText("MISSPELLED WORDS\n");
			textArea.appendText(" ");
			warningLabel.setVisible(false);

		});

		wordCount.setOnAction(e -> {
			String text = textArea.getText();
			wordCountText.clear();
			wordCountText.appendText(String.valueOf(myList.calculateWordCount(text)));
		});

		sentenceCount.setOnAction(e -> {
			String text = textArea.getText();
			sentenceCountText.clear();
			sentenceCountText.appendText(String.valueOf(myList.calculateNumberOfSentences(text)));
		});

		fleschScore.setOnAction(e -> {
			String text = textArea.getText();
			int score = myList.fleschScore(text);
			if (score == -1) {
				fleschScoreText.clear();
				alert.setAlertType(AlertType.ERROR);
				alert.setContentText("There are no complete sentences in your text");
				alert.showAndWait();
			} else {
				fleschScoreText.clear();
				fleschScoreText.appendText(String.valueOf(score));
			}
		});

		saveText.setOnAction(e -> {
			int wordCounter = 0;
			String text = textArea.getText();
			if (text.isEmpty()) {
				alert.setAlertType(AlertType.ERROR);
				alert.setTitle("Warning");
				alert.setContentText("Please Enter Text");
				alert.showAndWait();
			} else {

				FileChooser textSaver = new FileChooser();
				FileChooser.ExtensionFilter textSaverExtensionFilter = new FileChooser.ExtensionFilter(
						"TXT files (*.txt)", "*.txt");
				textSaver.getExtensionFilters().add(textSaverExtensionFilter);
				File textSaverFile = textSaver.showSaveDialog(primaryStage);
				if (textSaverFile != null) {

					try {

						FileWriter fw = new FileWriter(textSaverFile, false);
						BufferedWriter bw = new BufferedWriter(fw);
						String[] words = text.split(" ");
						for (String word : words) {
							bw.write(word + " ");
							wordCounter++;
							if (wordCounter == 20) {
								bw.newLine();
								wordCounter = 0;
							}
						}
						bw.close();
					} catch (IOException e1) {

					}
				}
			}
		});

		openText.setOnAction(e -> {

			String text;
			FileChooser textChooser = new FileChooser();
			File importText = textChooser.showOpenDialog(primaryStage);
			if (importText != null) {
				text = myList.convertDocumentToString(importText.getAbsolutePath());
				textArea.clear();
				wordCountText.clear();
				sentenceCountText.clear();
				fleschScoreText.clear();
				textArea.appendText(text);
			}

		});

		hundredThousandWordChart.setOnAction(e -> {
			HundredThousandWordTimeChart hundredThousandWordCharts = new HundredThousandWordTimeChart(primaryStage,
					sceneOne);
			primaryStage.setScene(hundredThousandWordCharts.getScene());
		});

	}

	public void save(File file) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(fileOutputStream));
			objectOutputStream.writeObject(myList);
			objectOutputStream.close();
		} catch (IOException e) {
			// e.printStackTrace();
		}
	}

	public void load(File file) {

		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(fis));
			myList = (LinkList) ois.readObject();
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public Scene getScene() {
		return sceneOne;
	}

}
