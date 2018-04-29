package br.com.andrewesteves.despesas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.andrewesteves.despesas.models.Despesa;
import br.com.andrewesteves.despesas.models.DespesaDados;

public class EditarActivity extends AppCompatActivity {

    EditText editarEditTextCodigo;
    EditText editarEditTextTitulo;
    EditText editarEditTextTotal;
    Button editarButtonConfirmar;
    Button editarButtonRemover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        editarEditTextCodigo = (EditText) findViewById(R.id.editarEditTextCodigo);
        editarEditTextTitulo = (EditText) findViewById(R.id.editarEditTextTitulo);
        editarEditTextTotal = (EditText) findViewById(R.id.editarEditTextTotal);
        editarButtonConfirmar =  (Button) findViewById(R.id.editarButtonConfirmar);
        editarButtonRemover = (Button) findViewById(R.id.editarButtonRemover);

        try {
            Despesa despesa = getIntent().getExtras().getParcelable("despesa");
            DespesaDados dd = new DespesaDados(EditarActivity.this);
            if (despesa == null ){
                Toast.makeText(getApplicationContext(),"nulo",Toast.LENGTH_LONG).show();
            }else {
                Despesa editarDespesa = dd.encontrarId(despesa.getCodigo());

                editarEditTextCodigo.setText(Integer.toString(editarDespesa.getCodigo()));
                editarEditTextTitulo.setText(editarDespesa.getTitulo());
                editarEditTextTotal.setText(editarDespesa.getTotal().toString());

                this.editarButtonRemover.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        confirmRemove(view);
                    }
                });

                this.editarButtonConfirmar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Despesa editarDespesa = new Despesa();
                        DespesaDados edd = new DespesaDados(EditarActivity.this);

                        editarDespesa.setCodigo(Integer.parseInt(editarEditTextCodigo.getText().toString()));
                        editarDespesa.setTitulo(editarEditTextTitulo.getText().toString());
                        editarDespesa.setTotal(Double.parseDouble(editarEditTextTotal.getText().toString()));
                        edd.atualizar(editarDespesa);

                        Toast.makeText(getApplicationContext(), "Despesa atualizada com sucesso.",Toast.LENGTH_LONG).show();
                    }
                });
            }
        }catch(Exception ex){
            Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    protected void confirmRemove(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage("Deseja remover?")
                .setTitle("Atenção");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                try {
                    Despesa removerDespesa = new Despesa();
                    DespesaDados edd = new DespesaDados(EditarActivity.this);

                    removerDespesa.setCodigo(Integer.parseInt(editarEditTextCodigo.getText().toString()));
                    removerDespesa.setTitulo(editarEditTextTitulo.getText().toString());
                    removerDespesa.setTotal(Double.parseDouble(editarEditTextTotal.getText().toString()));
                    edd.remover(removerDespesa);
                    Toast.makeText(getApplicationContext(), "Despesa removida com sucesso.",Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getApplicationContext(), ListarActivity.class);
                    startActivity(intent);
                }catch (Exception ex) {
                    Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("Cancelar", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
