package br.com.andrewesteves.despesas.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by Andrew Esteves on 16/03/2018.
 */

public class DespesaDados extends SQLiteOpenHelper {
    private static final String BANCO_NOME = "despesas.db";
    private static final int BANCO_VERSAO = 1;

    private static final String DESPESA_TABELA_ID = "codigo";
    private static final String DESPESA_TABELA_NOME = "despesas";
    private static final String DESPESA_TABELA_TITULO = "titulo";
    private static final String DESPESA_TABELA_TOTAL = "total";

    public DespesaDados(Context context) {
        super(context, BANCO_NOME, null, BANCO_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + DESPESA_TABELA_NOME + "(" +
                DESPESA_TABELA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DESPESA_TABELA_TITULO + " TEXT, " +
                DESPESA_TABELA_TOTAL + " FLOAT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DESPESA_TABELA_NOME);
        onCreate(db);
    }

    public ArrayList<Despesa> listar() {
        ArrayList<Despesa> retorno = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + DESPESA_TABELA_NOME + " ORDER BY " + DESPESA_TABELA_ID + " DESC", null);
        if(res.moveToFirst()) {
            do {
                Despesa despesa = new Despesa();
                despesa.setCodigo(res.getInt(res.getColumnIndex(DespesaDados.DESPESA_TABELA_ID)));
                despesa.setTitulo(res.getString(res.getColumnIndex(DespesaDados.DESPESA_TABELA_TITULO)));
                despesa.setTotal(res.getDouble(res.getColumnIndex(DespesaDados.DESPESA_TABELA_TOTAL)));
                retorno.add(despesa);
            }while(res.moveToNext());
        }
        return retorno;
    }

    public Despesa encontrarId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = ?", DESPESA_TABELA_NOME, DESPESA_TABELA_ID), new String[] {Integer.toString(id)});
        Despesa retorno = new Despesa();
        if(res.moveToFirst()) {
            do {
                retorno.setCodigo(res.getInt(res.getColumnIndex(DespesaDados.DESPESA_TABELA_ID)));
                retorno.setTitulo(res.getString(res.getColumnIndex(DespesaDados.DESPESA_TABELA_TITULO)));
                retorno.setTotal(res.getDouble(res.getColumnIndex(DespesaDados.DESPESA_TABELA_TOTAL)));
            }while(res.moveToNext());
        }
        return retorno;
    }

    public void inserir(Despesa despesa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DESPESA_TABELA_TITULO, despesa.getTitulo());
        contentValues.put(DESPESA_TABELA_TOTAL, despesa.getTotal());
        db.insert(DESPESA_TABELA_NOME, null, contentValues);
    }

    public void atualizar(Despesa despesa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DESPESA_TABELA_ID, despesa.getCodigo());
        contentValues.put(DESPESA_TABELA_TITULO, despesa.getTitulo());
        contentValues.put(DESPESA_TABELA_TOTAL, despesa.getTotal());
        db.update(DESPESA_TABELA_NOME, contentValues, DESPESA_TABELA_ID + " = ? ", new String[] { Integer.toString(despesa.getCodigo()) });
    }

    public Integer remover(Despesa despesa) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DESPESA_TABELA_NOME, DESPESA_TABELA_ID + " = ? ", new String[] {Integer.toString(despesa.getCodigo())});
    }

    public Integer removerTudo() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DESPESA_TABELA_NOME, DESPESA_TABELA_ID + " > ? ", new String[]{Integer.toString(0)});
    }
}
