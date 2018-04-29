package br.com.andrewesteves.despesas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mainButtonCadastrar;
    Button mainButtonListar;
    Button mainButtonCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mainButtonCadastrar = (Button) findViewById(R.id.mainButtonCadastrar);
        this.mainButtonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CadastrarActivity.class);
                startActivity(intent);
            }
        });

        this.mainButtonListar = (Button) findViewById(R.id.mainButtonListar);
        this.mainButtonListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ListarActivity.class);
                startActivity(intent);
            }
        });

        this.mainButtonCamera = (Button) findViewById(R.id.mainButtonCamera);
        this.mainButtonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CameraActivity.class);
                startActivity(intent);
            }
        });
    }
}
