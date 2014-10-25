package br.com.fiap.pizzaria;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity {
	
	private ImageView imgPizza;
	private RadioGroup radioSabor;
	private CheckBox chkBorda;
	private Button btnCalcular;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		imgPizza = (ImageView) findViewById(R.id.imgPizza);
		
		radioSabor = (RadioGroup) findViewById(R.id.radioGroup);
		radioSabor.setOnCheckedChangeListener(chkGroupListener);
		
		chkBorda = (CheckBox) findViewById(R.id.chkBorda);
		
		btnCalcular = (Button) findViewById(R.id.btnCalcular);
		btnCalcular.setOnClickListener(calcularListener);
		
	}
	
	public OnCheckedChangeListener chkGroupListener = new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup rg, int checkedId) {
			
			if (rg == radioSabor) {
				if (checkedId == R.id.btnQueijo) {
					imgPizza.setImageResource(R.drawable.queijo);
				} else if (checkedId == R.id.btnPortuguesa) {
					imgPizza.setImageResource(R.drawable.portuguesa);
				} else if (checkedId == R.id.btnCalabresa) {
					imgPizza.setImageResource(R.drawable.calabresa);
				}
			}
			
			Log.i("PIZZARIA", "sabor selecionado");
		}
	};
	
	public OnClickListener calcularListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			calcularPreco();
		}
	};
	
	private void calcularPreco() {
		int idSabor = radioSabor.getCheckedRadioButtonId();
		float preco = 0.0f;
		
		if (idSabor == R.id.btnQueijo) {
			preco = 10.0f;
		} else if (idSabor == R.id.btnPortuguesa) {
			preco = 15.0f;
		} else if (idSabor == R.id.btnCalabresa) {
			preco = 18.0f;
		}
		
		if (chkBorda.isChecked()) {
			preco += 3.0f;
		}
		
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("Preço da pizza");
		builder.setMessage(String.valueOf(preco));
		
		builder.show();
	}
}
