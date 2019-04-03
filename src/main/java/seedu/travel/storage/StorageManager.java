package seedu.travel.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.travel.commons.core.LogsCenter;
import seedu.travel.commons.exceptions.DataConversionException;
import seedu.travel.model.ReadOnlyCountryChart;
import seedu.travel.model.ReadOnlyRatingChart;
import seedu.travel.model.ReadOnlyTravelBuddy;
import seedu.travel.model.ReadOnlyUserPrefs;
import seedu.travel.model.ReadOnlyYearChart;
import seedu.travel.model.UserPrefs;

/**
 * Manages storage of TravelBuddy data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TravelBuddyStorage travelBuddyStorage;
    private UserPrefsStorage userPrefsStorage;
    private ChartBookStorage chartBookStorage;


    public StorageManager(TravelBuddyStorage travelBuddyStorage, UserPrefsStorage userPrefsStorage,
                          ChartBookStorage chartBookStorage) {
        super();
        this.travelBuddyStorage = travelBuddyStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.chartBookStorage = chartBookStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ TravelBuddy methods ==============================

    @Override
    public Path getTravelBuddyFilePath() {
        return travelBuddyStorage.getTravelBuddyFilePath();
    }

    @Override
    public Path getCountryChartFilePath() {
        return chartBookStorage.getCountryChartFilePath();
    }

    @Override
    public Path getRatingChartFilePath() {
        return chartBookStorage.getRatingChartFilePath();
    }

    @Override
    public Path getYearChartFilePath() {
        return chartBookStorage.getYearChartFilePath();
    }

    @Override
    public Optional<ReadOnlyTravelBuddy> readTravelBuddy() throws DataConversionException, IOException {
        return readTravelBuddy(travelBuddyStorage.getTravelBuddyFilePath());
    }

    @Override
    public Optional<ReadOnlyTravelBuddy> readTravelBuddy(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return travelBuddyStorage.readTravelBuddy(filePath);
    }

    @Override
    public void saveTravelBuddy(ReadOnlyTravelBuddy travelBuddy) throws IOException {
        saveTravelBuddy(travelBuddy, travelBuddyStorage.getTravelBuddyFilePath());
    }

    @Override
    public void saveTravelBuddy(ReadOnlyTravelBuddy travelBuddy, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        travelBuddyStorage.saveTravelBuddy(travelBuddy, filePath);
    }

    @Override
    public void saveCountryChart(ReadOnlyCountryChart countryChart) throws IOException {
        saveCountryChart(countryChart, chartBookStorage.getCountryChartFilePath());
    }

    @Override
    public void saveCountryChart(ReadOnlyCountryChart countryChart, Path filePath) throws IOException {
        logger.fine("Attempting to write to country data file: " + filePath);
        chartBookStorage.saveCountryChart(countryChart, filePath);
    }

    @Override
    public void saveRatingChart(ReadOnlyRatingChart ratingChart) throws IOException {
        saveRatingChart(ratingChart, chartBookStorage.getRatingChartFilePath());
    }

    @Override
    public void saveRatingChart(ReadOnlyRatingChart ratingChart, Path filePath) throws IOException {
        logger.fine("Attempting to write to rating data file: " + filePath);
        chartBookStorage.saveRatingChart(ratingChart, filePath);
    }

    @Override
    public void saveYearChart(ReadOnlyYearChart yearChart) throws IOException {
        saveYearChart(yearChart, chartBookStorage.getYearChartFilePath());
    }

    @Override
    public void saveYearChart(ReadOnlyYearChart yearChart, Path filePath) throws IOException {
        logger.fine("Attempting to write to year data file: " + filePath);
        chartBookStorage.saveYearChart(yearChart, filePath);
    }

    @Override
    public void backupTravelBuddy(ReadOnlyTravelBuddy travelBuddy) throws IOException {
        travelBuddyStorage.backupTravelBuddy(travelBuddy);
    }

    @Override
    public void backupCountryChart(ReadOnlyCountryChart countryChart) throws IOException {
        chartBookStorage.backupCountryChart(countryChart);
    }

    @Override
    public void backupRatingChart(ReadOnlyRatingChart ratingChart) throws IOException {
        chartBookStorage.backupRatingChart(ratingChart);
    }

    @Override
    public void backupYearChart(ReadOnlyYearChart yearChart) throws IOException {
        chartBookStorage.backupYearChart(yearChart);
    }

}
