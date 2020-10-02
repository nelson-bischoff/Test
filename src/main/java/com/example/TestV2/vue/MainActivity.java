package com.example.TestV2.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.TestV2.R;
import com.example.TestV2.controleur.Controle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        this.controle = Controle.getInstance();
    }
    //propriétés
    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHommme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Controle controle;

    //initialisation des lien avec les objets graphiques

    private void init(){

        txtPoids = (EditText) findViewById(R.id.txtPoids);
        txtTaille = (EditText) findViewById(R.id.txtTaille);
        txtAge = (EditText) findViewById(R.id.txtAge);
        rdHommme = (RadioButton) findViewById(R.id.rdHomme);
        lblIMG = (TextView) findViewById(R.id.lblIMG);
        imgSmiley = (ImageView) findViewById(R.id.imgSmile);
        ecouteCalcul();
    }

    private void ecouteCalcul(){
        ((Button) findViewById(R.id.btnCALC)).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
               // Toast.makeText(MainActivity.this, "test" , Toast.LENGTH_SHORT).show();

                Integer poids =0;
                Integer taille =0;
                Integer age =0;
                Integer sexe =0;
                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                }catch (Exception e){};
                if(rdHommme.isChecked()){
                    sexe =1;
                }

                if(poids ==0 || taille==0 || age==0){
                    Toast.makeText(MainActivity.this,"Saisie incorrecte",Toast.LENGTH_SHORT).show();
                }else{
                    afficheResult(poids,taille,age,sexe);
                }



            }
        });
    }

    /**
     * Affichage de L'img du msg et l'image
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */

    private void afficheResult(Integer poids,Integer taille, Integer age , Integer sexe){
        //creation du profils et recup des info
        this.controle.creerProfil(poids,taille,age,sexe);
        float img = this.controle.getImg();
        String message = this.controle.getMessage();
        //affichage
        if(message =="Normal"){
            imgSmiley.setImageResource(R.drawable.normal);
            lblIMG.setTextColor(Color.GREEN);
        }else{
            lblIMG.setTextColor(Color.RED);
            if(message == "Trop faible"){
                imgSmiley.setImageResource(R.drawable.maigre);
            }else{
                imgSmiley.setImageResource(R.drawable.graisse);
                lblIMG.setTextColor(Color.RED);
            }
        }
        lblIMG.setText(String.format("%.01f",img)+" : IMG "+message);
    }
}
