package org.techtown.finalproject.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import org.techtown.finalproject.dataAccessObject.DGangjwa;
import org.techtown.finalproject.dataAccessObject.DUser;
import org.techtown.finalproject.valueObject.VGangjwa;
import org.techtown.finalproject.valueObject.VUser;

@Database(entities = {VUser.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public abstract DUser dUser();
}
