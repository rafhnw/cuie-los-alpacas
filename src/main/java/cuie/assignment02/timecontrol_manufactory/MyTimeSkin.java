package cuie.assignment02.timecontrol_manufactory;

import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

class MyTimeSkin extends SkinBase<MyTimeControl> {
    // wird spaeter gebraucht
    private static final int ICON_SIZE  = 12;
    private static final int IMG_OFFSET = 4;

    private static ImageView invalidIcon = new ImageView(new Image(MyTimeSkin.class.getResource("icons/invalid.png").toExternalForm(),
                                                                   ICON_SIZE, ICON_SIZE,
                                                                   true, false));

    private static ImageView validIcon = new ImageView(new Image(MyTimeSkin.class.getResource("icons/valid.png").toExternalForm(),
                                                                   ICON_SIZE, ICON_SIZE,
                                                                   true, false));

    private TextField editableTimeField;
    private Label readOnlyField; //todo was ist für uns das richtige control?
    private Text captionLabel;

    MyTimeSkin(MyTimeControl control) {
        super(control);
        initializeSelf();
        initializeParts();
        layoutParts();
        setupValueChangeListeners();
        setupBindings();
        setupEventHandlers();
    }

    private void initializeSelf() {
        // getSkinnable().loadFonts("/fonts/Lato/Lato-Reg.ttf", "/fonts/Lato/Lato-Lig.ttf");
        getSkinnable().addStylesheetFiles("style.css");
    }

    private void initializeParts() {
        editableTimeField = new TextField();
        editableTimeField.getStyleClass().add("editable-time-field");

        // set visible if editable
        editableTimeField.setVisible(getSkinnable().isEditable());

        captionLabel = new Text();
        captionLabel.getStyleClass().add("caption-label");

        readOnlyField = new Label();
        readOnlyField.getStyleClass().add("read-only-field");

        // only visible if not editable
        readOnlyField.setVisible(!getSkinnable().isEditable());
    }

    // alle parts müssen hier hinzugefügt werden
    private void layoutParts() {
        getChildren().addAll(new VBox(captionLabel, editableTimeField, readOnlyField));
    }

    private void setupValueChangeListeners() {
        // react on property change
        getSkinnable().editableProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                editableTimeField.setVisible(true);
                readOnlyField.setVisible(false);
            } else {
                editableTimeField.setVisible(false);
                readOnlyField.setVisible(true);
            }
        });
    }
    private void setupEventHandlers() {
        //getSkinable accesses control
        editableTimeField.setOnAction(event -> getSkinnable().convert());
    }

    private void setupBindings() {
        editableTimeField.textProperty().bindBidirectional(getSkinnable().timeAsStringProperty());

        captionLabel.textProperty().bind(getSkinnable().captionProperty());

        // should have same time
        readOnlyField.textProperty().bind(getSkinnable().timeValueProperty().asString());
    }
}
