package seedu.travel.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.travel.MainApp;
import seedu.travel.model.place.Photo;
import seedu.travel.model.place.Place;

/**
 * An UI component that displays information of a {@code Place}.
 */
public class ExpandedPlacePanel extends UiPart<Region> {

    public static final String EMPTY_PHOTO_PATH = "pBSgcMnA";
    private static final String FXML = "ExpandedPlacePanel.fxml";
    private static final String[] TAG_COLOR_STYLES =
        { "teal", "red", "yellow", "blue", "orange", "brown", "green", "salmon", "black", "grey" };
    public static final String DEFAULT_IMAGE_FILEPATH = "/images/test-img.jpg";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Place place;

    @FXML
    private ImageView img;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label countryCode;
    @FXML
    private Label dateVisited;
    @FXML
    private Label rating;
    @FXML
    private Label address;
    @FXML
    private Label description;
    @FXML
    private FlowPane tags;

    public ExpandedPlacePanel(Place place) {
        super(FXML);
        this.place = place;
        id.setText("");
        name.setText(place.getName().fullName);
        countryCode.setText(place.getCountryCode().code);
        dateVisited.setText(place.getDateVisited().getDate());
        rating.setText(place.getRating().value);
        address.setText(place.getAddress().value);
        description.setText(place.getDescription().value);
        initTags(place);
        initImg(place.getPhoto());
    }

    /**
     * Returns the color style for {@code tagName}'s label.
     */
    private String getTagColorStyleFor(String tagName) {
        // Hash code of the tag name to generate a random color, so that the color remain consistent
        // between different runs of the program while still making it random enough between tags.
        return TAG_COLOR_STYLES[Math.abs(tagName.hashCode()) % TAG_COLOR_STYLES.length];
    }

    /**
     * Initialised and loads the image into ImageViewer for {@code place}.
     */
    private void initImg(Photo photo) {
        Image placeImage;

        try {
            if (photo.equals(new Photo(EMPTY_PHOTO_PATH))) {
                placeImage = new Image(MainApp.class.getResourceAsStream(DEFAULT_IMAGE_FILEPATH));
            } else {
                FileInputStream inputStream = new FileInputStream(photo.getFilePath());
                placeImage = new Image(inputStream);
            }
            img.setImage(placeImage);
            img.setFitWidth(800);
            img.setPreserveRatio(true);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the tag labels for {@code place}.
     */
    private void initTags(Place place) {
        place.getTags().forEach(tag -> {
            Label tagLabel = new Label(tag.tagName);
            tagLabel.getStyleClass().add(getTagColorStyleFor(tag.tagName));
            tags.getChildren().add(tagLabel);
        });
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ExpandedPlacePanel)) {
            return false;
        }

        // state check
        ExpandedPlacePanel card = (ExpandedPlacePanel) other;
        return id.getText().equals(card.id.getText())
                && place.equals(card.place);
    }
}
