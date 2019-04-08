package seedu.travel.model.place;

import static seedu.travel.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.travel.model.tag.Tag;

/**
 * Represents a Place in TravelBuddy.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Place {

    // Identity fields
    private final Name name;
    private final CountryCode countryCode;
    private final DateVisited dateVisited;
    private final Rating rating;
    private final Description description;

    // Data fields
    private final Address address;
    private final Photo photo;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Place(Name name, CountryCode countryCode, DateVisited dateVisited, Rating rating, Description description,
        Address address, Photo photo, Set<Tag> tags) {
        requireAllNonNull(name, countryCode, dateVisited, rating, description, address, photo, tags);
        this.name = name;
        this.countryCode = countryCode;
        this.dateVisited = dateVisited;
        this.rating = rating;
        this.description = description;
        this.address = address;
        this.photo = photo;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public CountryCode getCountryCode() {
        return countryCode;
    }

    public DateVisited getDateVisited() {
        return dateVisited;
    }

    public Rating getRating() {
        return rating;
    }

    public Description getDescription() {
        return description;
    }

    public Address getAddress() {
        return address;
    }

    public Photo getPhoto() { return photo; }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both places of the same name also have the same phone number.
     * This defines a weaker notion of equality between two places.
     */
    public boolean isSamePlace(Place otherPlace) {
        if (otherPlace == this) {
            return true;
        }

        return otherPlace != null && otherPlace.getName().equals(getName());
    }

    /**
     * Returns true if both places have the same identity and data fields.
     * This defines a stronger notion of equality between two places.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Place)) {
            return false;
        }

        Place otherPlace = (Place) other;
        return otherPlace.getName().equals(getName())
                && otherPlace.getCountryCode().equals(getCountryCode())
                && otherPlace.getDateVisited().equals(getDateVisited())
                && otherPlace.getRating().equals(getRating())
                && otherPlace.getDescription().equals(getDescription())
                && otherPlace.getAddress().equals(getAddress())
                && otherPlace.getPhoto().equals(getPhoto())
                && otherPlace.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, countryCode, dateVisited, rating, description, address, photo, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Country Code: ")
                .append(getCountryCode())
                .append(" Date Visited: ")
                .append(getDateVisited())
                .append(" Rating: ")
                .append(getRating())
                .append(" Description: ")
                .append(getDescription())
                .append(" Address: ")
                .append(getAddress())
                .append(" Photo: ")
                .append(getPhoto())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
