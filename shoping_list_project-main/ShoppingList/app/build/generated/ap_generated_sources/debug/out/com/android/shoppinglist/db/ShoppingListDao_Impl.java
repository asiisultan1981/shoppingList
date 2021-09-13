package com.android.shoppinglist.db;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ShoppingListDao_Impl implements ShoppingListDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Category> __insertionAdapterOfCategory;

  private final EntityInsertionAdapter<Items> __insertionAdapterOfItems;

  private final EntityDeletionOrUpdateAdapter<Category> __deletionAdapterOfCategory;

  private final EntityDeletionOrUpdateAdapter<Items> __deletionAdapterOfItems;

  private final EntityDeletionOrUpdateAdapter<Category> __updateAdapterOfCategory;

  private final EntityDeletionOrUpdateAdapter<Items> __updateAdapterOfItems;

  public ShoppingListDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCategory = new EntityInsertionAdapter<Category>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Category` (`uid`,`categoryName`) VALUES (nullif(?, 0),?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Category value) {
        stmt.bindLong(1, value.uid);
        if (value.categoryName == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.categoryName);
        }
      }
    };
    this.__insertionAdapterOfItems = new EntityInsertionAdapter<Items>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Items` (`uid`,`itemName`,`categoryId`,`completed`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Items value) {
        stmt.bindLong(1, value.uid);
        if (value.itemName == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.itemName);
        }
        stmt.bindLong(3, value.categoryId);
        final int _tmp;
        _tmp = value.completed ? 1 : 0;
        stmt.bindLong(4, _tmp);
      }
    };
    this.__deletionAdapterOfCategory = new EntityDeletionOrUpdateAdapter<Category>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Category` WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Category value) {
        stmt.bindLong(1, value.uid);
      }
    };
    this.__deletionAdapterOfItems = new EntityDeletionOrUpdateAdapter<Items>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Items` WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Items value) {
        stmt.bindLong(1, value.uid);
      }
    };
    this.__updateAdapterOfCategory = new EntityDeletionOrUpdateAdapter<Category>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Category` SET `uid` = ?,`categoryName` = ? WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Category value) {
        stmt.bindLong(1, value.uid);
        if (value.categoryName == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.categoryName);
        }
        stmt.bindLong(3, value.uid);
      }
    };
    this.__updateAdapterOfItems = new EntityDeletionOrUpdateAdapter<Items>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Items` SET `uid` = ?,`itemName` = ?,`categoryId` = ?,`completed` = ? WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Items value) {
        stmt.bindLong(1, value.uid);
        if (value.itemName == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.itemName);
        }
        stmt.bindLong(3, value.categoryId);
        final int _tmp;
        _tmp = value.completed ? 1 : 0;
        stmt.bindLong(4, _tmp);
        stmt.bindLong(5, value.uid);
      }
    };
  }

  @Override
  public void insertCategory(final Category... categories) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCategory.insert(categories);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertItems(final Items items) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfItems.insert(items);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteCategory(final Category category) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfCategory.handle(category);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteItem(final Items items) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfItems.handle(items);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateCategory(final Category category) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfCategory.handle(category);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateItems(final Items items) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfItems.handle(items);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Category> getAllCategoriesList() {
    final String _sql = "Select * from Category";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfCategoryName = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryName");
      final List<Category> _result = new ArrayList<Category>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Category _item;
        _item = new Category();
        _item.uid = _cursor.getInt(_cursorIndexOfUid);
        _item.categoryName = _cursor.getString(_cursorIndexOfCategoryName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Items> getAllItemsList(final int catId) {
    final String _sql = "Select * from Items where categoryId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, catId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfItemName = CursorUtil.getColumnIndexOrThrow(_cursor, "itemName");
      final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
      final int _cursorIndexOfCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "completed");
      final List<Items> _result = new ArrayList<Items>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Items _item;
        _item = new Items();
        _item.uid = _cursor.getInt(_cursorIndexOfUid);
        _item.itemName = _cursor.getString(_cursorIndexOfItemName);
        _item.categoryId = _cursor.getInt(_cursorIndexOfCategoryId);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfCompleted);
        _item.completed = _tmp != 0;
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
