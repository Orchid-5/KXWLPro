package com.kxwl.kxpro.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.kxwl.kxpro.entity.ClassificationEntity;
import com.kxwl.kxpro.entity.Goods;
import com.kxwl.kxpro.entity.GoodsEntity;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SEELE on 2017/3/2.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DB_NAME="kxpro.db";
    private static final int DB_CURRENT_VERSION=1;
    private Map<String,Dao> daos=new HashMap<String, Dao>();
    private static DatabaseHelper instance;

    private DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_CURRENT_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(Context context){
        if(instance==null){
            instance=new DatabaseHelper(context);
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, GoodsEntity.class);
            TableUtils.createTable(connectionSource, ClassificationEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public synchronized Dao getDao(Class clazz) throws SQLException
    {
        Dao dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className))
        {
            dao = daos.get(className);
        }
        if (dao == null)
        {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close()
    {
        super.close();

        for (String key : daos.keySet())
        {
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
