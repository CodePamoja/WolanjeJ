package com.wolanjeAfrica.wolanjej.RealmDataBase;

import androidx.annotation.Nullable;

import io.realm.DynamicRealm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class DbMigrations implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        if (oldVersion == 0) {
            schema.get("user")
                    .addField("location", String.class);
            oldVersion++;
        }
        if (oldVersion == 1) {
            schema.get("user")
                    .addField("role", String.class);
            oldVersion++;
        }
        if(oldVersion == 3){
            schema.get("user")
                    .removeField("biologin");
            oldVersion++;
        }

    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof DbMigrations;
    }

    @Override
    public int hashCode() {
        return DbMigrations.class.hashCode();
    }

    public static RealmConfiguration getDefaultInstance() {
        return new RealmConfiguration.Builder()
                .schemaVersion(4)
                .migration(new DbMigrations())
                .build();
    }
}
