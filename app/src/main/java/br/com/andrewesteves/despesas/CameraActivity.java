package br.com.andrewesteves.despesas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class CameraActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1;

    ImageView cameraImageViewFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        cameraImageViewFoto = (ImageView) this.findViewById(R.id.cameraImageViewFoto);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            Bitmap foto = (Bitmap) data.getExtras().get("data");
            cameraImageViewFoto.setImageBitmap(foto);
            Toast.makeText(getApplicationContext(), "Foto tirada com sucesso.",Toast.LENGTH_LONG).show();
        }
    }
}
