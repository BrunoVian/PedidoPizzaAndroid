package br.unipar.trabpedidopizza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvPedido, tvValor;

    private Spinner spSabores;

    private ListView lvSabores;

    private RadioGroup rgGrupo;

    private Button btnRemoverSabor, btnLimpar, btnConcluir;

    private ArrayAdapter<String> listViewAdapter;

    private RadioButton rbPequena, rbMedia, rbGrande;
    private CheckBox cbRefri, cbBorda;

    private int itemSelecionado = -1;
    private double vlrTotal = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPedido = findViewById(R.id.tvPedido);
        tvValor = findViewById(R.id.tvValor);
        spSabores = findViewById(R.id.spSabores);
        lvSabores = findViewById(R.id.lvSabores);
        rbGrande = findViewById(R.id.rbGrande);
        rbPequena = findViewById(R.id.rbPequena);
        rbMedia = findViewById(R.id.rbMedia);
        btnRemoverSabor = findViewById(R.id.btnRemoverSabor);
        cbRefri = findViewById(R.id.cbRefri);
        cbBorda = findViewById(R.id.cbBorda);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnConcluir = findViewById(R.id.btnConcluir);
        rgGrupo = findViewById(R.id.rgGrupo);

        String[] vetorSabores = new String[]{"", "Calabresa", "Tomate Seco", "4 Queijos", "Bacon", "Portugesa", "Strogonoff"};

        ArrayAdapter adapterSabores = new ArrayAdapter(this, android.R.layout.simple_list_item_1, vetorSabores);

        spSabores.setAdapter(adapterSabores);

        listViewAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        lvSabores.setAdapter(listViewAdapter);

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpar();
            }
        });

        btnConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concluir();
            }
        });

        spSabores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int quantidadeItens = listViewAdapter.getCount();
                String saborSelecionado = (String) parent.getItemAtPosition(position);
                adcSabores(quantidadeItens, saborSelecionado);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lvSabores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemSelecionado = position;
            }
        });

        btnRemoverSabor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemSelecionado != -1) {
                    if (itemSelecionado < listViewAdapter.getCount()) {
                        String saborRemovido = listViewAdapter.getItem(itemSelecionado);
                        listViewAdapter.remove(saborRemovido);
                        removerSaborDoPedido(saborRemovido);
                    }
                    itemSelecionado = -1;
                }
            }
        });
        cbBorda.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                calculaTotal();
            }
        });

        cbRefri.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                calculaTotal();
            }
        });

    }

    public void adcSabores(int quantidadeItens, String SaborSelecionado) {

        if (!SaborSelecionado.isEmpty() || !SaborSelecionado.equals("")) {
            if (!rbGrande.isChecked() && !rbMedia.isChecked() && !rbPequena.isChecked()) {
                Toast.makeText(MainActivity.this, "Selecione o tamanho da Pizza! :)", Toast.LENGTH_SHORT).show();
            } else if (rbGrande.isChecked()) {
                if (quantidadeItens < 4) {
                    listViewAdapter.add(SaborSelecionado);
                    spSabores.setSelection(0);
                    tvPedido.setText(tvPedido.getText().toString() + "- " + SaborSelecionado + "\n");
                } else {
                    Toast.makeText(MainActivity.this, "A lista de sabores está cheia! :)", Toast.LENGTH_SHORT).show();
                }

            } else if (rbMedia.isChecked()) {
                if (quantidadeItens < 2) {
                    listViewAdapter.add(SaborSelecionado);
                    spSabores.setSelection(0);
                    tvPedido.setText(tvPedido.getText().toString() + "- " + SaborSelecionado + "\n");
                } else {
                    Toast.makeText(MainActivity.this, "A lista de sabores está cheia! :)", Toast.LENGTH_SHORT).show();
                }

            } else if (rbPequena.isChecked()) {
                if (quantidadeItens < 1) {
                    listViewAdapter.add(SaborSelecionado);
                    spSabores.setSelection(0);
                    tvPedido.setText(tvPedido.getText().toString() + "- " + SaborSelecionado + "\n");
                } else {
                    Toast.makeText(MainActivity.this, "A lista de sabores está cheia! :)", Toast.LENGTH_SHORT).show();
                }
            }
        }



    }

    public void SelecionarOpcao(View view) {
        boolean checkado = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rbGrande:
                if (checkado) {
                    if (tvPedido.getText().toString().contains("Média")) {
                        tvPedido.setText(tvPedido.getText().toString().replace("Média", "Grande"));
                        calculaTotal();
                    } else if (tvPedido.getText().toString().contains("Pequena")) {
                        tvPedido.setText(tvPedido.getText().toString().replace("Pequena", "Grande"));
                        calculaTotal();
                    } else if (!tvPedido.getText().toString().contains("Grande")) {
                        calculaTotal();
                    }

                }
                break;

            case R.id.rbMedia:
                if (checkado) {
                    if (tvPedido.getText().toString().contains("Grande")) {
                        tvPedido.setText(tvPedido.getText().toString().replace("Grande", "Média"));
                        calculaTotal();
                    } else if (tvPedido.getText().toString().contains("Pequena")) {
                        tvPedido.setText(tvPedido.getText().toString().replace("Pequena", "Média"));
                        calculaTotal();
                    } else if (!tvPedido.getText().toString().contains("Média")) {
                        tvPedido.setText(tvPedido.getText().toString() + "Pizza Média com os sabores:\n");
                        calculaTotal();
                    }
                }
                break;

            case R.id.rbPequena:
                if (checkado) {
                    if (tvPedido.getText().toString().contains("Grande")) {
                        tvPedido.setText(tvPedido.getText().toString().replace("Grande", "Pequena"));
                        calculaTotal();
                    } else if (tvPedido.getText().toString().contains("Média")) {
                        tvPedido.setText(tvPedido.getText().toString().replace("Média", "Pequena"));
                        calculaTotal();
                    } else if (!tvPedido.getText().toString().contains("Pequena")) {
                        tvPedido.setText(tvPedido.getText().toString() + "Pizza Pequena com os sabores:\n");
                        calculaTotal();
                    }
                }
                break;
        }


    }

    public void calculaTotal(){

        if(tvPedido.getText().toString().contains("Grande")){
            vlrTotal = 40;
        }
        if(tvPedido.getText().toString().contains("Média")){
            vlrTotal = 30;
        }
        if(tvPedido.getText().toString().contains("Pequena")){
            vlrTotal = 20;
        }
        if(cbRefri.isChecked()){
            vlrTotal = vlrTotal + 5;
        }
        if(cbBorda.isChecked()){
            vlrTotal = vlrTotal + 10;
        }

        tvValor.setText("Valor Total: R$ " + vlrTotal);
        vlrTotal = 0;
    }

    private void removerSaborDoPedido(String sabor) {
        String pedidoAtual = tvPedido.getText().toString();
        String saborRemovido = "- " + sabor;
        String novoPedido = pedidoAtual.replace(saborRemovido, "");

        novoPedido = novoPedido.trim();
        novoPedido = novoPedido + "\n";

        tvPedido.setText(novoPedido);
    }

    private void limpar(){
        tvPedido.setText("Seu Pedido:\n");
        tvValor.setText("Valor Total:");
        cbBorda.setChecked(false);
        cbRefri.setChecked(false);
        rbMedia.setChecked(false);
        rbPequena.setChecked(false);
        rbGrande.setChecked(false);
        listViewAdapter.clear();
    }

    private void concluir(){
        if(!rbGrande.isChecked() && !rbPequena.isChecked() && !rbMedia.isChecked() ){
            Toast.makeText(this, "Informe ao Menos o Tamanho da Pizza!!!", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Pedido Concluido!", Toast.LENGTH_SHORT).show();
            limpar();
        }

    }

}