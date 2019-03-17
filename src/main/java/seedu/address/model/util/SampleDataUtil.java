package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.place.Address;
import seedu.address.model.place.CountryCode;
import seedu.address.model.place.Description;
import seedu.address.model.place.Name;
import seedu.address.model.place.Place;
import seedu.address.model.place.Rating;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Place[] getSamplePlaces() {
        return new Place[] {
            new Place(new Name("VivoCity"), new CountryCode("SGP"), new Rating("1"),
                    new Description("Expansive, modern shopping center "
                            + "hosting a wide range of retailers, restaurants & theaters."),
                    new Address("1 Harbourfront Walk, Singapore 098585"),
                    getTagSet("shoppingMall")),
            new Place(new Name("Changi Airport Singapore"), new CountryCode("SGP"), new Rating("5"),
                    new Description("Passenger & cargo hub with 4 modern terminals "
                            + "plus buses & trains to the city center."),
                    new Address("Airport Boulevard"),
                    getTagSet("airport", "shoppingMall")),
            new Place(new Name("Japanese Cemetery Park"), new CountryCode("SGP"), new Rating("3"),
                    new Description("The Japanese Cemetery Park serves as the burial ground for Japanese soldiers "
                            + "and civilians who lived in the early 20th century."),
                    new Address("22 Chuan Hoe Ave, Singapore 549854"),
                    getTagSet("cemetery", "placeOfInterest")),
            new Place(new Name("Universal Studios Singapore"), new CountryCode("SGP"), new Rating("4"),
                    new Description("Movie amusement park with sets & rides on themes from Hollywood to sci-fi,"
                            + " plus live entertainment."),
                    new Address("8 Sentosa Gateway, 0982693"),
                    getTagSet("amusementPark", "recreation")),
            new Place(new Name("National University of Singapore"), new CountryCode("SGP"), new Rating("4"),
                    new Description("The National University of Singapore is an "
                            + "autonomous research university in Singapore."),
                    new Address("21 Lower Kent Ridge Rd, Singapore 119077"),
                    getTagSet("school")),
            new Place(new Name("Singapore Zoo"), new CountryCode("SGP"), new Rating("3"),
                    new Description("Rainforest zoo with tram rides, trails & viewing platforms "
                            + "to see wildlife habitats & exhibits."),
                    new Address("80 Mandai Lake Rd, Singapore 729826"),
                    getTagSet("zoo", "animals"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Place samplePlace : getSamplePlaces()) {
            sampleAb.addPlace(samplePlace);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
