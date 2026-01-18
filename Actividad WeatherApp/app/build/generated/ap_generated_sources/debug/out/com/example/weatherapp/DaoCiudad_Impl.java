package com.example.weatherapp;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DaoCiudad_Impl implements DaoCiudad {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Ciudad> __insertionAdapterOfCiudad;

  private final SharedSQLiteStatement __preparedStmtOfBorrarTodo;

  public DaoCiudad_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCiudad = new EntityInsertionAdapter<Ciudad>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `tabla_ciudad` (`id`,`nombre`,`temperatura`,`condicion`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Ciudad entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getNombre() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getNombre());
        }
        statement.bindDouble(3, entity.getTemperatura());
        if (entity.getCondicion() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCondicion());
        }
      }
    };
    this.__preparedStmtOfBorrarTodo = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM tabla_ciudad";
        return _query;
      }
    };
  }

  @Override
  public void insertar(final Ciudad ciudad) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCiudad.insert(ciudad);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void borrarTodo() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfBorrarTodo.acquire();
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfBorrarTodo.release(_stmt);
    }
  }

  @Override
  public LiveData<List<Ciudad>> obtenerCiudadesAlfabetizadas() {
    final String _sql = "SELECT * FROM tabla_ciudad ORDER BY nombre ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"tabla_ciudad"}, false, new Callable<List<Ciudad>>() {
      @Override
      @Nullable
      public List<Ciudad> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfTemperatura = CursorUtil.getColumnIndexOrThrow(_cursor, "temperatura");
          final int _cursorIndexOfCondicion = CursorUtil.getColumnIndexOrThrow(_cursor, "condicion");
          final List<Ciudad> _result = new ArrayList<Ciudad>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Ciudad _item;
            final String _tmpNombre;
            if (_cursor.isNull(_cursorIndexOfNombre)) {
              _tmpNombre = null;
            } else {
              _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            }
            final double _tmpTemperatura;
            _tmpTemperatura = _cursor.getDouble(_cursorIndexOfTemperatura);
            final String _tmpCondicion;
            if (_cursor.isNull(_cursorIndexOfCondicion)) {
              _tmpCondicion = null;
            } else {
              _tmpCondicion = _cursor.getString(_cursorIndexOfCondicion);
            }
            _item = new Ciudad(_tmpNombre,_tmpTemperatura,_tmpCondicion);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
