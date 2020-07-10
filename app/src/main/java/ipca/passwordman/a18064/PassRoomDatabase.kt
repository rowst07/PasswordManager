package ipca.passwordman.a18064

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Password::class), version = 1, exportSchema = false)
public abstract class PassRoomDatabase : RoomDatabase() {

    abstract fun passDao(): PassDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PassRoomDatabase? = null

        fun getDatabase(context: Context): PassRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PassRoomDatabase::class.java,
                    "password_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}