package sample.proyectoloteria.views;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.proyectoloteria.classes.Card;
import sample.proyectoloteria.events.ButtonPlayClicked;
import sample.proyectoloteria.models.LoteriaImages;

public class Loteria extends Stage {
    private VBox v_box;
    private HBox h_box1, h_box2, h_box3;
    private Button btn_prev, btn_next, btn_play;
    private Label lbl_time;
    private GridPane gdp_board, gdp_card;
    private Image img_card;
    private Scene scene;
    private Image img_card_boad;
    private ImageView image_view;

    // Establece la plantilla actual que es mostrada.
    // Así mismo, su valor cambia dependiendo la plantilla que esté en uso.
    private int current_board = 0;

    private final Card[][] BOARDS = LoteriaImages.BOARDS;
    private Button[][] BUTTONS = new Button[4][4];

    public Loteria() {
        createUI();
        this.setTitle("Loteria");
        this.setScene(scene);
        this.show();
    }

    private void createUI() {
        // Área de selección de plantilla (botones).
        // ...
        // Manejar la plantilla anterior.
        btn_prev = new Button("Atrás");
        btn_prev.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                prevBoard();

                renderBoard();
            }
        });
        btn_prev.setPrefWidth(100);
        // Manejar la plantilla siguiente.
        btn_next = new Button("Siguiente");
        btn_next.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nextBoard();

                renderBoard();
            }
        });
        btn_next.setPrefWidth(100);

        // Marcador de tiempo para cambiar de carta.
        lbl_time = new Label("00:00");

        // Contenedor de botones controladores.
        h_box1 = new HBox();
        h_box1.setSpacing(5);
        h_box1.setAlignment(Pos.CENTER);
        h_box1.getChildren().addAll(btn_prev, btn_next, lbl_time);

        // Grid Pane para las plantillas.
        gdp_board = new GridPane();

        // Grid Pane para las cartas.
        gdp_card = new GridPane();

        // Mostrar la primer plantilla (0).
        renderBoard();

        // Mostrar las cartas de las plantillas.
        renderCard();

        // Contenedor de las plantillas y las cartas.
        h_box2 = new HBox();
        h_box2.setAlignment(Pos.CENTER);
        h_box2.getChildren().addAll(gdp_board, gdp_card);

        // Botón jugar.
        btn_play = new Button("Jugar");
        btn_play.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonPlayClicked("Message"));
        btn_play.setPrefWidth(250);

        // Contenedor del botón jugar.
        h_box3 = new HBox();
        h_box3.setSpacing(5);
        h_box3.setAlignment(Pos.CENTER);
        h_box3.getChildren().addAll(btn_play);

        // Layout principal.
        // Contiene los botones, plantillas y cartas.
        v_box = new VBox();
        v_box.getChildren().addAll(h_box1, h_box2, h_box3);

        // Vista principal.
        scene = new Scene(v_box, 800, 600);
    }

    private void renderCard() {
        Button card = new Button();
        image_view = new ImageView();
        image_view.setFitWidth(280);
        image_view.setFitHeight(480);
        image_view.setImage(BOARDS[0][0].getImage());
        card.setGraphic(image_view);
        gdp_card.add(card, 0, 0);
    }

    private void renderBoard() {
        int image_index = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                image_view = new ImageView(BOARDS[current_board][image_index].getImage());
                image_view.setFitWidth(70);
                image_view.setFitHeight(120);
                gdp_board.add(image_view, i, j);

                image_index++;
            }
        }
    }

    private void prevBoard() {
        current_board--;

        if (current_board < 0) current_board = 4;
    }

    private void nextBoard() {
        current_board++;

        if (current_board > 4) current_board = 0;
    }
}
