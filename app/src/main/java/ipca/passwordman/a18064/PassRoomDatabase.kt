package ipca.passwordman.a18064

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [Password::class], version = 1, exportSchema = false)
public abstract class PassRoomDatabase : RoomDatabase() {

    abstract fun passDao(): PassDao

    companion object {
        @Volatile
        private var INSTANCE: PassRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): PassRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PassRoomDatabase::class.java,
                    "word_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(PasswordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class PasswordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.passDao())
                    }
                }
            }
        }

        fun populateDatabase(passDao: PassDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            passDao.deleteAll()

            var pass = Password("Hello")
            passDao.insert(pass)
            pass = Password("World!")
            passDao.insert(pass)
        }
    }
}