package br.unipar.trabpedidopizza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvPedido;

    private Spinner spSabores;

    private ListView lvSabores;

    private ArrayAdapter<String> listViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPedido = findViewById(R.id.tvPedido);
        spSabores = findViewById(R.id.spSabores);

               String[] vetorSabores = new String[]{"", "Calabresa", "4 Queijos", "Bacon", "Portugesa"};

        ArrayAdapter adapterSabores = new ArrayAdapter(this, android.R.layout.simple_list_item_1, vetorSabores);

        spSabores.setAdapter(adapterSabores);

       /* listViewAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        lvSabores.setAdapter(listViewAdapter);

        try {
            spSabores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedSabor = (String) parent.getItemAtPosition(position);
                    if (!selectedSabor.isEmpty()) {
                        listViewAdapter.add(selectedSabor);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        } catch (Exception ex){
            Log.e("Erro: ", ex.getMessage());
        }*/




        //usar o arrayadapter


        /*cbAluno = findViewById(R.id.cbAluno);
        cbProfessor = findViewById(R.id.cbProfessor);

        cbAluno.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validaCampos();
            }
        });*/

    }

    public void validaCampos(){
       /* if(cbAluno.isChecked()){
           Toast.makeText(this, "Selecionou o Aluno", Toast.LENGTH_SHORT).show();
        }

        if(cbProfessor.isChecked()){
            Toast.makeText(this, "Selecionou o Professor", Toast.LENGTH_SHORT).show();
        }*/
    }
    public void SelecionarOpcao(View view){
        boolean checkado = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.rbGrande:
                if(checkado){
                    if(tvPedido.getText().toString().contains("Média")){
                        tvPedido.setText(tvPedido.getText().toString().replace("Média", "Grande"));
                    }else if(tvPedido.getText().toString().contains("Pequena")){
                        tvPedido.setText(tvPedido.getText().toString().replace("Pequena", "Grande"));
                    } else if (!tvPedido.getText().toString().contains("Grande")) {
                        tvPedido.setText(tvPedido.getText().toString() + "Pizza Grande com os sabores:\n");
                    }

                }
                break;

            case R.id.rbMedia:
                if(checkado){
                    if(tvPedido.getText().toString().contains("Grande")){
                        tvPedido.setText(tvPedido.getText().toString().replace("Grande", "Média"));
                    }else if(tvPedido.getText().toString().contains("Pequena")){
                        tvPedido.setText(tvPedido.getText().toString().replace("Pequena", "Média"));
                    } else if (!tvPedido.getText().toString().contains("Média")) {
                        tvPedido.setText(tvPedido.getText().toString() + "Pizza Média com os sabores:\n");
                    }
                }
                break;
            case R.id.rbPequena:
                if(checkado){
                    if(tvPedido.getText().toString().contains("Grande")){
                        tvPedido.setText(tvPedido.getText().toString().replace("Grande", "Pequena"));
                    }else if(tvPedido.getText().toString().contains("Média")){
                        tvPedido.setText(tvPedido.getText().toString().replace("Média", "Pequena"));
                    } else if (!tvPedido.getText().toString().contains("Pequena")) {
                        tvPedido.setText(tvPedido.getText().toString() + "Pizza Pequena com os sabores:\n");
                    }
                }
                break;
        }




    }
}