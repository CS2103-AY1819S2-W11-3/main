package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.place.Address;
import seedu.address.model.place.Description;
import seedu.address.model.place.Name;
import seedu.address.model.place.Place;
import seedu.address.model.place.Rating;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Place objects.
 */
public class PlaceBuilder {

    public static final String DEFAULT_NAME = "Alice James";
    public static final String DEFAULT_RATING = "4";
    public static final String DEFAULT_DESCRIPTION = "I love this place";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Rating rating;
    private Description description;
    private Address address;
    private Set<Tag> tags;

    public PlaceBuilder() {
        name = new Name(DEFAULT_NAME);
        rating = new Rating(DEFAULT_RATING);
        description = new Description(DEFAULT_DESCRIPTION);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PlaceBuilder with the data of {@code placeToCopy}.
     */
    public PlaceBuilder(Place placeToCopy) {
        name = placeToCopy.getName();
        rating = placeToCopy.getRating();
        description = placeToCopy.getDescription();
        address = placeToCopy.getAddress();
        tags = new HashSet<>(placeToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Place} that we are building.
     */
    public PlaceBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Place} that we are building.
     */
    public PlaceBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Place} that we are building.
     */
    public PlaceBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Place} that we are building.
     */
    public PlaceBuilder withRating(String phone) {
        this.rating = new Rating(phone);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Place} that we are building.
     */
    public PlaceBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    public Place build() {
        return new Place(name, rating, description, address, tags);
    }

}
