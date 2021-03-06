package com.dbele.stiv.persistence;

import android.content.ContentValues;
import android.content.Context;
import com.dbele.stiv.model.Movie;
import com.dbele.stiv.handlers.NotificationHandler;
import com.dbele.stiv.handlers.PreferencesHandler;
import java.util.ArrayList;

public class MovieRepository {

    private Context context;
    private static MovieRepository movieRepositoryInstance;

    public MovieRepository(Context context) {
        this.context = context;
    }

    public static MovieRepository getInstance(Context context) {
        if (movieRepositoryInstance == null) {
            movieRepositoryInstance = new MovieRepository(context.getApplicationContext());
        }
        return movieRepositoryInstance;
    }

    public void loadDatabaseAndNotifyIfNeeded(ArrayList<Movie> movies) {
        context.getContentResolver().delete(MoviesContentProvider.CONTENT_URI, MovieDatabaseHelper.DELETE_NOT_WATCHED_NOT_ARCHIVED, null);
        for(Movie movie : movies) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(MovieDatabaseHelper.COLUMN_NAME, movie.getName());
            contentValues.put(MovieDatabaseHelper.COLUMN_DESCRIPTION, movie.getDescription());
            contentValues.put(MovieDatabaseHelper.COLUMN_DIRECTOR, movie.getDirector());
            contentValues.put(MovieDatabaseHelper.COLUMN_ACTORS, movie.getActors());
            contentValues.put(MovieDatabaseHelper.COLUMN_LENGTH, movie.getLength());
            contentValues.put(MovieDatabaseHelper.COLUMN_GENRE, movie.getGenre());
            contentValues.put(MovieDatabaseHelper.COLUMN_PICTURE_PATH, movie.getPicturePath());
            contentValues.put(MovieDatabaseHelper.COLUMN_WATCHED_DATE, movie.getWatchedDate());
            contentValues.put(MovieDatabaseHelper.COLUMN_CINEMANAME, movie.getCinemaName());
            contentValues.put(MovieDatabaseHelper.COLUMN_TICKET_PATH, movie.getTicketPath());
            contentValues.put(MovieDatabaseHelper.COLUMN_RANK, movie.getRank());
            contentValues.put(MovieDatabaseHelper.COLUMN_DEGREE, movie.getDegree());
            contentValues.put(MovieDatabaseHelper.COLUMN_ARCHIVED, movie.getArchived());
            contentValues.put(MovieDatabaseHelper.COLUMN_WATCHED, movie.getWatched());
            contentValues.put(MovieDatabaseHelper.COLUMN_IMPRESSIONS, movie.getImpressions());
            context.getContentResolver().insert(MoviesContentProvider.CONTENT_URI, contentValues);
        }
        handleDBLoadedStateAndNotifyIfNeeded();
    }

    private void handleDBLoadedStateAndNotifyIfNeeded() {
        if (!PreferencesHandler.checkIfDbLoaded(context)) {
            PreferencesHandler.setDbLoaded(context);
        } else {
            NotificationHandler.sendMoviesInsertedNotification(context);
        }
    }
}
