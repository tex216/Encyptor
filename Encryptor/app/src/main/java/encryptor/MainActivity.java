package encryptor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private EditText textValue;
    private EditText aKey;
    private EditText bKey;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textValue = (EditText) findViewById(R.id.plaintextID);
        aKey = (EditText) findViewById(R.id.alphaKeyID);
        bKey = (EditText) findViewById(R.id.betaKeyID);
        textResult = (TextView) findViewById(R.id.ciphertextID);
    }


    private String encipher(String plaintext, int a, int b){
            String ciphertext = "";
            char[] arr = plaintext.toCharArray();
            for(int i = 0; i < arr.length; i++){
                if(Character.isLetter(arr[i])){
                    if (Character.isUpperCase(arr[i])) {
                        ciphertext += (char) ((((a * (arr[i] - 'A')) + b) % 26) + 'A');
                    }
                    else{
                        ciphertext += (char) ((((a * (arr[i] - 'a')) + b) % 26) + 'a');
                    }
                }
                else{
                    ciphertext += arr[i];
                }
            }
            return ciphertext;
    }

    public void handleClick(View view){
        switch (view.getId()){
            case R.id.encipherButtonID:
                String result;
                String value = textValue.getText().toString() ;
                int alpha = Integer.parseInt(aKey.getText().toString()) ;
                int beta = Integer.parseInt(bKey.getText().toString());
                int n = 0;
                for (int i = 0; i < value.length(); i++) {
                    if (Character.isLetter(value.charAt(i))) {
                        n++;
                    }
                }
                String aString = Integer.toString(alpha);
                String[] brr = {"1", "3", "5", "7", "9", "11", "15", "17", "19", "21", "23", "25"};
                if (value.isEmpty()) {
                    textValue.setError("Invalid Plaintext");
                }
                if (n == 0) {
                    textValue.setError("Invalid Plaintext");
                }
                if (!Arrays.asList(brr).contains(aString)) {
                    aKey.setError("Invalid Alpha Key");
                }
                if (beta < 1 || beta >= 26) {
                    bKey.setError("Invalid Beta Key");
                }
                else {
                    result = encipher(value, alpha, beta);
                    textResult.setText(result);
                    break;
                }
        }
    }
}
