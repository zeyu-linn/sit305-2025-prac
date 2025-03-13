package com.example.converter;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Spinner categorySpinner, sourceUnitSpinner, targetUnitSpinner;
    private EditText inputValue;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        categorySpinner = findViewById(R.id.categorySpinner);
        sourceUnitSpinner = findViewById(R.id.sourceUnitSpinner);
        targetUnitSpinner = findViewById(R.id.targetUnitSpinner);
        inputValue = findViewById(R.id.inputValue);
        resultText = findViewById(R.id.resultText);
        Button convertButton = findViewById(R.id.convertButton);

        // Setup category spinner
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(
                this, R.array.categories, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        // Handle category selection
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateUnitSpinners();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Convert button click listener
        convertButton.setOnClickListener(v -> performConversion());
    }

    private void updateUnitSpinners() {
        int arrayId;
        switch (categorySpinner.getSelectedItem().toString()) {
            case "Length": arrayId = R.array.length_units; break;
            case "Weight": arrayId = R.array.weight_units; break;
            case "Temperature": arrayId = R.array.temperature_units; break;
            default: return;
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, arrayId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sourceUnitSpinner.setAdapter(adapter);
        targetUnitSpinner.setAdapter(adapter);
    }

    private void performConversion() {
        try {
            Log.d("Conversion", "Convert button clicked"); // 添加日志
            String inputStr = inputValue.getText().toString().trim();
            if (inputStr.isEmpty()) {
                resultText.setText("Input cannot be empty");
                return;
            }
            double input = Double.parseDouble(inputValue.getText().toString());
            String category = categorySpinner.getSelectedItem().toString();
            String sourceUnit = sourceUnitSpinner.getSelectedItem().toString();
            String targetUnit = targetUnitSpinner.getSelectedItem().toString();

            double result = UnitConverter.convert(input, sourceUnit, targetUnit, category);
            String resultString = String.format(Locale.US, "%.2f %s = %.2f %s", input, sourceUnit, result, targetUnit);

            resultText.setText(resultString);
        } catch (NumberFormatException e) {
            resultText.setText(getString(R.string.error_invalid_number));
        }
    }
}