package com.noveogroup.googoltoone.database;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class ContentDescriptor {
    private static final String AUTHORITY = "com.noveogroup.googoltoone.database.GTOContentProvider";
    private static final Uri CONTENT_BASE_URI = new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT).authority(AUTHORITY).build();

    private ContentDescriptor() {
        throw new UnsupportedOperationException();
    }

    // TODO: finishing declaration

    // Description for each tables
    public static class Players {
        public static final String TABLE_NAME = "players";

        public static final Uri TABLE_URI = CONTENT_BASE_URI.buildUpon().appendPath(TABLE_NAME).build();
        public static final int ALL_URI_CODE = 0;
        public static final int URI_CODE = 1;

        public static class Cols {
            public static final String ID = BaseColumns._ID;
            public static final String NAME = "name";
        }
    }

    public static class Match {
        public static final String TABLE_NAME = "match";

        public static final Uri TABLE_URI = CONTENT_BASE_URI.buildUpon().appendPath(TABLE_NAME).build();
        public static final int ALL_URI_CODE = 0;
        public static final int URI_CODE = 1;

        public static class Cols {
            public static final String ID = BaseColumns._ID;
            public static final String ID_PLAYER1 = "id_player1";
            public static final String ID_PLAYER2 = "id_player2";
            public static final String PLAYER1_SCORE = "player1_score";
            public static final String PLAYER2_SCORE = "player2_score";
            public static final String TIME_FINISH = "time_finish";
        }
    }
}
