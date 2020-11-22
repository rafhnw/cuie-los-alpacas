package cuie.module08.timecontrol_manufactory;

import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.converter.LocalTimeStringConverter;

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
    private Text captionLabel;

    MyTimeSkin(MyTimeControl control) {
        super(control);
        initializeSelf();
        initializeParts();
        layoutParts();
        setupValueChangeListeners();
        setupBindings();
    }

    private void initializeSelf() {
        // getSkinnable().loadFonts("/fonts/Lato/Lato-Reg.ttf", "/fonts/Lato/Lato-Lig.ttf");
        getSkinnable().addStylesheetFiles("style.css");
    }

    private void initializeParts() {
        editableTimeField = new TextField();
        editableTimeField.getStyleClass().add("editable-time-field");

        captionLabel = new Text();
        captionLabel.getStyleClass().add("caption-label");
    }

    private void layoutParts() {
        getChildren().addAll(new VBox(captionLabel, editableTimeField));
    }

    private void setupValueChangeListeners() {
    }

    private void setupBindings() {
        editableTimeField.textProperty().bindBidirectional(getSkinnable().timeValueProperty(),
                                                           new LocalTimeStringConverter());

        captionLabel.textProperty().bind(getSkinnable().captionProperty());
    }
}
