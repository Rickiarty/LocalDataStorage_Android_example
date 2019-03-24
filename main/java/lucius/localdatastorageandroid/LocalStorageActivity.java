package lucius.localdatastorageandroid;

/*
*
*  Written by Lucius Lin in 2015 A.D.
*
* */

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LocalStorageActivity extends Activity {

    private Button button1; // Save.
    private Button button2; // Read.
    private Button button3; // Delete.
    private Button button4; // Clear all.
    private EditText textBox1; // for the keys.
    private EditText textBox2; // for the values.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_storage);

        this.button1 = (Button)findViewById(R.id.button1);
        this.button2 = (Button)findViewById(R.id.button2);
        this.button3 = (Button)findViewById(R.id.button3);
        this.button4 = (Button)findViewById(R.id.button4);
        this.textBox1 = (EditText)findViewById(R.id.editText1);
        this.textBox2 = (EditText)findViewById(R.id.editText2);

        this.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save
                // First, get the 'SharedPreferences' object from Android system, and set its accessibility-mode as we want.
                SharedPreferences storageObj = getSharedPreferences("Pili", MODE_PRIVATE);
                // Call the method(s) '.putXX...X()' of the 'Editor' object which the method '.edit()' returned.
                storageObj.edit()
                        .putString(LocalStorageActivity.this.textBox1.getText().toString(), LocalStorageActivity.this.textBox2.getText().toString())
                        .commit(); // MUST commit after proceeding.
            }
        });

        this.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Read
                // First, get the 'SharedPreferences' object from Android system, and set its accessibility-mode as we want.
                SharedPreferences storageObj = getSharedPreferences("Pili", MODE_PRIVATE);

                // Get the key for the data-reading.
                String str = LocalStorageActivity.this.textBox1.getText().toString();

                // Read and display.
                if( !str.equals("") ){
                    // Return the default value '(null)' if there is no returned value from the method call.
                    LocalStorageActivity.this.textBox2.setText(storageObj.getString(str, "(null)"));
                }else{
                    LocalStorageActivity.this.textBox2.setText("(null)");
                }
            }
        });

        this.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Delete
                // First, get the 'SharedPreferences' object from Android system, and set its accessibility-mode as we want.
                SharedPreferences storageObj = getSharedPreferences("Pili", MODE_PRIVATE);
                // Get the key for the data-reading.
                String key = LocalStorageActivity.this.textBox1.getText().toString();
                // Remove the field.
                storageObj.edit()
                        .remove(key)
                        .commit(); // MUST commit after proceeding.
            }
        });

        this.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear all
                // First, get the 'SharedPreferences' object from Android system, and set its accessibility-mode as we want.
                SharedPreferences storageObj = getSharedPreferences("Pili", MODE_PRIVATE);
                // Clear all the data.
                storageObj.edit()
                        .clear()
                        .commit(); // MUST commit after proceeding.
            }
        });
    }

}
