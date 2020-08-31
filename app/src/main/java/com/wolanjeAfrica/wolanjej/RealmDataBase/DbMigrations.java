package com.wolanjeAfrica.wolanjej.RealmDataBase;

import io.realm.DynamicRealm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class DbMigrations implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        //no recent migrations




    }

    //no db migrations yet
    public static RealmConfiguration getDefaultInstance() {
        return new RealmConfiguration.Builder()
                .schemaVersion(1)
                .migration(new DbMigrations())
                .build();
    }
}
