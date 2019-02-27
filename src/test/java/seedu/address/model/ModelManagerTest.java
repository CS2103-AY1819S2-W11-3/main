package seedu.address.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.TypicalPlaces.ALICE;
import static seedu.address.testutil.TypicalPlaces.BENSON;
import static seedu.address.testutil.TypicalPlaces.BOB;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.place.NameContainsKeywordsPredicate;
import seedu.address.model.place.Place;
import seedu.address.model.place.exceptions.PlaceNotFoundException;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.PlaceBuilder;

public class ModelManagerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
        assertEquals(null, modelManager.getSelectedPlace());
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.setUserPrefs(null);
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.setGuiSettings(null);
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.setAddressBookFilePath(null);
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void hasPlace_nullPlace_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.hasPlace(null);
    }

    @Test
    public void hasPlace_placeNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPlace(ALICE));
    }

    @Test
    public void hasPlace_placeInAddressBook_returnsTrue() {
        modelManager.addPlace(ALICE);
        assertTrue(modelManager.hasPlace(ALICE));
    }

    @Test
    public void deletePlace_placeIsSelectedAndFirstPlaceInFilteredPlaceList_selectionCleared() {
        modelManager.addPlace(ALICE);
        modelManager.setSelectedPlace(ALICE);
        modelManager.deletePlace(ALICE);
        assertEquals(null, modelManager.getSelectedPlace());
    }

    @Test
    public void deletePlace_placeIsSelectedAndSecondPlaceInFilteredPlaceList_firstPlaceSelected() {
        modelManager.addPlace(ALICE);
        modelManager.addPlace(BOB);
        assertEquals(Arrays.asList(ALICE, BOB), modelManager.getFilteredPlaceList());
        modelManager.setSelectedPlace(BOB);
        modelManager.deletePlace(BOB);
        assertEquals(ALICE, modelManager.getSelectedPlace());
    }

    @Test
    public void setPlace_placeIsSelected_selectedPlaceUpdated() {
        modelManager.addPlace(ALICE);
        modelManager.setSelectedPlace(ALICE);
        Place updatedAlice = new PlaceBuilder(ALICE).withDescription(VALID_DESCRIPTION_BOB).build();
        modelManager.setPlace(ALICE, updatedAlice);
        assertEquals(updatedAlice, modelManager.getSelectedPlace());
    }

    @Test
    public void getFilteredPlaceList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        modelManager.getFilteredPlaceList().remove(0);
    }

    @Test
    public void setSelectedPlace_placeNotInFilteredPlaceList_throwsPlaceNotFoundException() {
        thrown.expect(PlaceNotFoundException.class);
        modelManager.setSelectedPlace(ALICE);
    }

    @Test
    public void setSelectedPlace_placeInFilteredPlaceList_setsSelectedPlace() {
        modelManager.addPlace(ALICE);
        assertEquals(Collections.singletonList(ALICE), modelManager.getFilteredPlaceList());
        modelManager.setSelectedPlace(ALICE);
        assertEquals(ALICE, modelManager.getSelectedPlace());
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPlace(ALICE).withPlace(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPlaceList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPlaceList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }
}
