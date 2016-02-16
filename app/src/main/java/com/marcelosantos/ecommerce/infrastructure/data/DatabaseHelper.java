package com.marcelosantos.ecommerce.infrastructure.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.marcelosantos.ecommerce.domain.model.CreditCard;
import com.marcelosantos.ecommerce.domain.model.CreditCardType;
import com.marcelosantos.ecommerce.domain.model.Person;
import com.marcelosantos.ecommerce.domain.model.Transaction;

import java.sql.SQLException;

/**
 * Created by Marcelo on 13/02/2016.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, "ecommerce.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {

        try {

            this.createTables();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {

            this.createTables();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTables() throws SQLException {

        TableUtils.createTableIfNotExists(connectionSource, CreditCard.class);
        TableUtils.createTableIfNotExists(connectionSource, CreditCardType.class);
        TableUtils.createTableIfNotExists(connectionSource, Person.class);
        TableUtils.createTableIfNotExists(connectionSource, Transaction.class);
    }

    @Override
    public void close() {
        super.close();
    }
}
