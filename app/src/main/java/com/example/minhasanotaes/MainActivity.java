package com.example.minhasanotaes;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.minhasanotaes.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AnotacaoPreferencias preferencias;
    private EditText editAnotacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        editAnotacao = findViewById(R.id.editAnotacao);

        preferencias = new AnotacaoPreferencias(getApplicationContext());

        //FAB
        binding.fab.setOnClickListener(view -> {

            String textoRecuperado = editAnotacao.getText().toString();
            //Validar se foi digitado algo
            if (textoRecuperado.equals("")){
                Snackbar.make(view, "Preencha a anotação!", Snackbar.LENGTH_LONG).show();
            }else{
                preferencias.salvarAnotacao(textoRecuperado);
                Snackbar.make(view, "Anotação salva com sucesso!", Snackbar.LENGTH_LONG).show();
            }
        });

        //Recuperar anotação
        String anotacao = preferencias.recuperarAnotacao();
        if (!anotacao.equals("")){
            editAnotacao.setText(anotacao);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        preferencias.salvarAnotacao(editAnotacao.getText().toString());
    }

}