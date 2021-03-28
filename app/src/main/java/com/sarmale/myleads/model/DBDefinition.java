package com.sarmale.myleads.model;

import android.provider.BaseColumns;

public final class DBDefinition {

    public static class LeadsEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_LASTNAME = "lastName";
        public static final String COLUMN_NAME_PHONE= "phone";
        public static final String COLUMN_NAME_EMAIL= "email";
    }

}
