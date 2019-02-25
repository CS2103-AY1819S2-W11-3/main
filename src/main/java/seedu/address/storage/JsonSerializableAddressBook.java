package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.place.Place;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Places list contains duplicate place(s).";

    private final List<JsonAdaptedPlace> places = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given places.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("places") List<JsonAdaptedPlace> places) {
        this.places.addAll(places);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        places.addAll(source.getPlaceList().stream().map(JsonAdaptedPlace::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPlace jsonAdaptedPlace : places) {
            Place place = jsonAdaptedPlace.toModelType();
            if (addressBook.hasPlace(place)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPlace(place);
        }
        return addressBook;
    }

}
