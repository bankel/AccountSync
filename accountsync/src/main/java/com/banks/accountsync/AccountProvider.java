package com.banks.accountsync;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.Nullable;

public class AccountProvider extends ContentProvider {
    public static final String AUTHORITY ="project.test.com.myapplication.account.provide";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;
    public static final String TABLE_NAME = "data";
    public static final Uri CONTENT_URI = Uri.parse(CONTENT_URI_BASE + "/" +TABLE_NAME);

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[]selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        return 0;
    }
}
