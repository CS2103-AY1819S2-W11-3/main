package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BEDOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_CLEMENTI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_DG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BEDOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CLEMENTI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BEDOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CLEMENTI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING_AMK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING_BEDOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING_CLEMENTI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING_DG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_EWL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MRT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.place.Place;

/**
 * A utility class containing a list of {@code Place} objects to be used in tests.
 */
public class TypicalPlaces {

    public static final Place ALICE = new PlaceBuilder()
            .withName("VivoCity")
            .withAddress("1 Harbourfront Walk, Singapore 098585")
            .withDescription("Expansive, modern shopping center hosting a wide range of retailers, "
                    + "restaurants & theaters.")
            .withRating("1")
            .withTags("shoppingMall")
            .build();
    public static final Place BENSON = new PlaceBuilder()
            .withName("Changi Airport Singapore")
            .withAddress("Airport Boulevard")
            .withDescription("Passenger & cargo hub with 4 modern terminals plus buses & trains to the city center.")
            .withRating("5")
            .withTags("airport", "shoppingMall")
            .build();
    public static final Place CARL = new PlaceBuilder()
            .withName("Universal Studios Singapore")
            .withRating("4")
            .withDescription("Movie amusement centre with sets & rides on themes from "
                    + "Hollywood to sci-fi, plus live entertainment.")
            .withAddress("8 Sentosa Gateway, 0982693")
            .withTags("amusementPark", "recreation")
            .build();
    public static final Place DANIEL = new PlaceBuilder()
            .withName("Japanese Cemetery Park")
            .withRating("3")
            .withDescription("The Japanese Cemetery Park serves as the burial ground for "
                    + "Japanese soldiers and civilians who lived in the early 20th century.")
            .withAddress("22 Chuan Hoe Ave, Singapore 549854")
            .withTags("cemetery", "placeOfInterest")
            .build();
    public static final Place ELLE = new PlaceBuilder()
            .withName("National University of Singapore")
            .withRating("4")
            .withDescription("The National University of Singapore is an autonomous research university in Singapore.")
            .withAddress("21 Lower Kent Ridge Rd, Singapore 119077")
            .withTags("school")
            .build();
    public static final Place FIONA = new PlaceBuilder()
            .withName("Singapore Zoo")
            .withRating("3")
            .withDescription("Rainforest zoo with tram rides, trails & viewing platforms "
                    + "to see wildlife habitats & exhibits.")
            .withAddress("80 Mandai Lake Rd, Singapore 729826")
            .withTags("zoo", "animals")
            .build();
    public static final Place GEORGE = new PlaceBuilder()
            .withName("Buddha Tooth Relic Temple")
            .withRating("4")
            .withDescription("Tang dynasty–style temple housing religious relics, "
                    + "with ornate rooms & a tranquil rooftop garden.")
            .withAddress("288 South Bridge Rd, Singapore 058840")
            .withTags("temple", "heritageSite")
            .build();

    // Manually added
    public static final Place HOON = new PlaceBuilder()
            .withName("Changi General Hospital")
            .withRating("2")
            .withDescription("Singapore's first purpose-built general hospital to"
                    + " serve communities in the east and north-east regions.")
            .withAddress("2 Simei Street 3, Singapore 529889")
            .build();

    public static final Place IDA = new PlaceBuilder()
            .withName("Singapore Botanic Gardens")
            .withRating("5")
            .withDescription("Botanical gardens with sculptures, a swan lake"
                    + " & significant collection of tropical trees.")
            .withAddress("1 Cluny Rd, Singapore 259569")
            .build();

    // Manually added - Place's details found in {@code CommandTestUtil}
    public static final Place AMK = new PlaceBuilder()
            .withName(VALID_NAME_AMK)
            .withRating(VALID_RATING_AMK)
            .withDescription(VALID_DESCRIPTION_AMK)
            .withAddress(VALID_ADDRESS_AMK)
            .withTags(VALID_TAG_MRT)
            .build();
    public static final Place BEDOK = new PlaceBuilder()
            .withName(VALID_NAME_BEDOK)
            .withRating(VALID_RATING_BEDOK)
            .withDescription(VALID_DESCRIPTION_BEDOK)
            .withAddress(VALID_ADDRESS_BEDOK)
            .withTags(VALID_TAG_EWL, VALID_TAG_MRT)
            .build();
    public static final Place CLEMENTI = new PlaceBuilder()
            .withName(VALID_NAME_CLEMENTI)
            .withRating(VALID_RATING_CLEMENTI)
            .withDescription(VALID_DESCRIPTION_CLEMENTI)
            .withAddress(VALID_ADDRESS_CLEMENTI)
            .withTags(VALID_TAG_EWL)
            .build();
    public static final Place DG = new PlaceBuilder()
            .withName(VALID_NAME_DG)
            .withRating(VALID_RATING_DG)
            .withDescription(VALID_DESCRIPTION_DG)
            .withAddress(VALID_ADDRESS_DG)
            .build();

    public static final String KEYWORD_MATCHING_SINGAPORE = "Singapore"; // A keyword that matches SINGAPORE

    public static final String KEYWORD_MATCHING_SHOPPING_MALL = "shoppingMall"; // A keyword that matches shoppingMall

    public static final String KEYWORD_MATCHING_FOUR = "4"; // A keyword that matches 4

    private TypicalPlaces() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical places.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Place place : getTypicalPlaces()) {
            ab.addPlace(place);
        }
        return ab;
    }

    public static List<Place> getTypicalPlaces() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
