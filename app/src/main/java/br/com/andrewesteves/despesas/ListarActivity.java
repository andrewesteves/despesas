package br.com.andrewesteves.despesas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import br.com.andrewesteves.despesas.models.Despesa;
import br.com.andrewesteves.despesas.models.DespesaDados;

public class ListarActivity extends AppCompatActivity {

    private ArrayList<Despesa> despesas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        this.listar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //this.listar();
    }

    private void listar() {
        DespesaDados dd = new DespesaDados(ListarActivity.this);

        for(Despesa d : dd.listar()) {
            despesas.add(d);
        }

        ArrayAdapter adapter = new ArrayAdapter<Despesa>(this, R.layout.listview_row, despesas);
        ListView listarListViewDespesas = (ListView) findViewById(R.id.listarListViewDespesas);
        listarListViewDespesas.setAdapter(adapter);

        listarListViewDespesas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    Despesa d = despesas.get(i);
                    Toast.makeText(getApplicationContext(), d.getTitulo(),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(view.getContext(), EditarActivity.class);
                    intent.putExtra("despesa", d);
                    startActivity(intent);
                }catch(Exception ex){
                    Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
