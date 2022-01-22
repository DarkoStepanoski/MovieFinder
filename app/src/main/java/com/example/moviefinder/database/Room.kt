package com.example.moviefinder.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {
    @Query("SELECT * FROM databasemovie")
    fun getMovies(): LiveData<List<DatabaseMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg movies: DatabaseMovie)

    @Query("DELETE FROM databasemovie")
    fun clear()
}

/**
 * A database that stores movie information.
 */
@Database(entities = [DatabaseMovie::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}

private lateinit var INSTANCE: MoviesDatabase

/**
 * Helper function to get the database.
 */
fun getDatabase(context: Context): MoviesDatabase {
    synchronized(MoviesDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
            MoviesDatabase::class.java,
            "movies").build()
        }
    }
    return INSTANCE
}