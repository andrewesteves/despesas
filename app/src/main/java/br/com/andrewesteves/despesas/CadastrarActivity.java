package br.com.andrewesteves.despesas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.andrewesteves.despesas.models.Despesa;
import br.com.andrewesteves.despesas.models.DespesaDados;

public class CadastrarActivity extends AppCompatActivity {

    EditText cadastrarEditTextTitulo;
    EditText cadastrarEditTextTotal;
    Button cadastrarButtonConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        this.cadastrarEditTextTitulo  = (EditText) findViewById(R.id.cadastrarEditTextTitulo);
        this.cadastrarEditTextTotal   = (EditText) findViewById(R.id.cadastrarEditTextTotal);
        this.cadastrarButtonConfirmar = (Button) findViewById(R.id.cadastrarButtonConfirmar);

        this.cadastrarButtonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Despesa despesa = new Despesa(cadastrarEditTextTitulo.getText().toString(), Double.parseDouble(cadastrarEditTextTotal.getText().toString()));
                despesa.setCodigo(0);

                DespesaDados novaDespesa = new DespesaDados(view.getContext());
                novaDespesa.inserir(despesa);

                Intent intent = new Intent(view.getContext(), ListarActivity.class);
                intent.putExtra("despesa", despesa);
                startActivity(intent);
                Toast.makeText(CadastrarActivity.this, "Cadastrado: "+despesa.getTitulo()+" - "+despesa.getTotal()+" com sucesso!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
