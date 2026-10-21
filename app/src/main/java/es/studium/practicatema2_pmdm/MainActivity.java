package es.studium.practicatema2_pmdm;

import android.content.res.Configuration;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    Spinner spinner;
    String  contenidoNombre, contenidoApellidos, contenidoEdad, genero, edad, hijos, estado;
    EditText txtNombre, txtApellidos, txtEdad;

    boolean camposRellenos;
    TextView txtError;
    RadioButton rdHombre,rdMujer;
    RadioGroup rdGenero;
    Switch switch1;

    Button btnAceptar, btnLimpiar, btnEspanol, btnRumano, btnTailandes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        spinner = findViewById(R.id.spnEstado);
        
        String[] EstadoSP = {
                getString(R.string.seleccione),
                getString(R.string.Soltero),
                getString(R.string.Casado),
                getString(R.string.Separado),
                getString(R.string.Viudo),
                getString(R.string.Otro),

        };
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, EstadoSP);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        txtNombre = findViewById(R.id.edtNombre);
        txtApellidos = findViewById(R.id.edtApellidos);
        txtEdad = findViewById(R.id.edtEdad);
        txtError = findViewById(R.id.txtError);

        rdGenero = findViewById(R.id.rdGenero);
        rdHombre = findViewById(R.id.rdHombre);
        rdMujer = findViewById(R.id.rdMujer);

        spinner.setOnItemSelectedListener(this);
        switch1 = findViewById(R.id.swchHijos);
        btnAceptar = findViewById(R.id.button5);
        btnAceptar.setOnClickListener(this);
        btnLimpiar = findViewById(R.id.button4);
        btnLimpiar.setOnClickListener(this);

        btnEspanol = findViewById(R.id.btnEsp);
        btnEspanol.setOnClickListener(this);
        btnRumano = findViewById(R.id.btnAlm);
        btnRumano.setOnClickListener(this);
        btnTailandes = findViewById(R.id.btnChn);
        btnTailandes.setOnClickListener(this);

    }

    private void cambiarIdioma(String idioma) {
        Locale locale = new Locale(idioma);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        recreate();
    }
    @Override
    public void onClick(View view) {

        if (view == btnEspanol) {
            cambiarIdioma("es");
        } else if (view == btnRumano) {
            cambiarIdioma("de");
        } else if (view == btnTailandes) {
            cambiarIdioma("zh");

        }
        if (view == btnAceptar) {
            txtError.setText("");
            camposRellenos = true;
            contenidoNombre = txtNombre.getText().toString();
            contenidoApellidos = txtApellidos.getText().toString();
            contenidoEdad = txtEdad.getText().toString();
            if (contenidoNombre.isEmpty()) {
                txtError.append(getString(R.string.errorNombre) + "\n");
                camposRellenos = false;
            }
            if (contenidoApellidos.isEmpty()) {
                txtError.append(getString(R.string.errorApellidos) + "\n");
                camposRellenos = false;
            }
            if (contenidoEdad.isEmpty()) {
                txtError.append(getString(R.string.errorEdad) + "\n");
                camposRellenos = false;
            }
            if (rdGenero.getCheckedRadioButtonId() == -1) {
                txtError.append(getString(R.string.errorGenero) + "\n");
                camposRellenos = false;
            }
            if (spinner.getSelectedItemPosition() == 0) {
                txtError.append(getString(R.string.errorEC) + "\n");
                camposRellenos = false;
            }
            if (camposRellenos) {
                txtError.setText("");
                if (rdHombre.isChecked()) {
                    genero = getString(R.string.hombre);
                    if (spinner.getSelectedItem().toString().equals(getString(R.string.Soltero))){
                        estado = getString(R.string.Solter);
                    } else if (spinner.getSelectedItem().toString().equals(getString(R.string.Casado))){
                        estado = getString(R.string.Casad);
                    }
                    else if (spinner.getSelectedItem().toString().equals(getString(R.string.Separado))){
                        estado = getString(R.string.Separad);
                    }
                    else if (spinner.getSelectedItem().toString().equals(getString(R.string.Viudo))){
                        estado = getString(R.string.Viud);
                    }
                    else if (spinner.getSelectedItem().toString().equals(getString(R.string.Otro))){
                        estado = getString(R.string.Otro_);
                    }

                } else {
                    genero = getString(R.string.mujer);
                    if (spinner.getSelectedItem().toString().equals(getString(R.string.Soltero))){
                        estado = getString(R.string.Soltera);
                    } else if (spinner.getSelectedItem().toString().equals(getString(R.string.Casado))){
                        estado = getString(R.string.Casada);
                        }
                    else if (spinner.getSelectedItem().toString().equals(getString(R.string.Separado))){
                        estado = getString(R.string.Separada);
                        }
                    else if (spinner.getSelectedItem().toString().equals(getString(R.string.Viudo))){
                        estado = getString(R.string.Viuda);
                        }
                    else if (spinner.getSelectedItem().toString().equals(getString(R.string.Otro))){
                        estado = getString(R.string.Otra_);
                    }
                }
                if (switch1.isChecked()) {
                    hijos = getString(R.string.conHijos);
                } else {
                    hijos = getString(R.string.sinHijos);
                }
                if (Integer.parseInt(contenidoEdad) >= 18) {
                    edad = getString(R.string.mayor);
                } else {
                    edad = getString(R.string.menor);
                }
                Toast.makeText(this, contenidoApellidos + ", " + contenidoNombre + ", " + edad + ", " + genero + " " + estado + " " + getString(R.string.y) +" " + hijos, Toast.LENGTH_LONG).show();

            }
        }
        if (view == btnLimpiar){
            txtNombre.setText("");
            txtApellidos.setText("");
            txtEdad.setText("");
            rdGenero.clearCheck();
            spinner.setSelection(0);
            switch1.setChecked(false);
            txtError.setText("");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
