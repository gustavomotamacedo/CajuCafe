package com.gustavomacedo.pedefava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button btnCalcular;
    private EditText edtConta;
    private EditText edtCouvert;
    private EditText edtDivisao;
    private TextView txtTaxad;
    private TextView txtConta;
    private TextView txtDivisao;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnCalcular = findViewById(R.id.btnCalcular);
        edtConta = findViewById(R.id.edtConta);
        edtCouvert = findViewById(R.id.edtCouvert);
        edtDivisao = findViewById(R.id.edtDivisao);
        txtTaxad = findViewById(R.id.txtTaxad);
        txtConta = findViewById(R.id.txtConta);
        txtDivisao = findViewById(R.id.txtDivisao);

        btnCalcular.setOnClickListener(v -> {
            try {
                String strConta = edtConta.getText().toString();
                String strCouvert = edtCouvert.getText().toString();
                String strDivisao = edtDivisao.getText().toString();
                double conta = (strConta.isEmpty()) ? 0.0 : Double.parseDouble(strConta);
                double couvert = (strCouvert.isEmpty()) ? 0.0 : Double.parseDouble(strCouvert);
                int divisao = (strDivisao.isEmpty() || Integer.parseInt(strDivisao) < 1) ? 1 : Integer.parseInt(strDivisao);
                double taxa = conta * 0.1;
                double valorIndividual = 0.0;

                conta = conta + taxa + couvert;
                valorIndividual = conta / divisao;

                txtConta.setText("R$ "+ String.format(Locale.getDefault(), "%.2f", conta));
                txtTaxad.setText("R$ " + String.format(Locale.getDefault(), "%.2f", taxa));
                txtDivisao.setText("R$ " + String.format(Locale.getDefault(), "%.2f", valorIndividual) + " cada.");

            } catch (IllegalArgumentException e) {
                Toast.makeText(this, "DIGITE APENAS NÚMEROS", Toast.LENGTH_SHORT).show();
            }

        });
    }
}