package com.example.jennifer.yhackstesting;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "SIGHH";
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //dummy array
        String[] ydb = {"0003-0215","0007-4641","0009-0306"};

        for (int j = 0; j < ydb.length; j++) {
            db.collection("drugs")
                    .whereEqualTo("PRODUCTNDC", ydb[j])
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("AHH", "" + document.getData()); //this is hashmap of drug info

                                    //HERE HERE MODIFY CARDS/APP INTERFACE
                                    //write card info
                                }
                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });
        }

        //DUMMY RULES
        Rule[] rules = {new Rule(1,"0002-0800","1","greater", "age", "65")};

        // DUMMY PATIENT
        Patient patient = new Patient("John Lee", "89", "500", "M",
                new String[]{"0002-0800", "0002-3229"}, new String[]{"deadness", "fatigue"}, new String[]{"hackathons"});

        // HERE HERE HERE - WARNING/FLAGS!!! warnings[0] is index of drug in ydb, warnings[1] is code
        int[][] warnings = new int[ydb.length * rules.length][2];
        Boolean beware = false;
        for (int i = 0; i < ydb.length; i++) {
            int k = i;
            for (Rule rule : rules) {
                beware = false;
                if (ydb[i].equals(rule.NCD)) {
                    if (rule.type == 1) {
                        if (rule.field.equals("age")) {
                            beware = evalType1(rule.predicate, patient.age,rule.value);
                        } else if (rule.field.equals("gfr")) {
                            beware = evalType1(rule.predicate, patient.gfr, rule.value);
                        } else {
                            beware = evalType1(rule.predicate, patient.gender, rule.value);
                        }
                    } else {
                        if (rule.field.equals("meds")) {
                            beware = evalType2(patient.meds, rule.value);
                        } else if (rule.field.equals("conditions")) {
                            beware = evalType2(patient.conditions, rule.value);
                        } else {
                            beware = evalType2(patient.allergies, rule.value);
                        }
                    }
                }

                // HERE HERE CAN JUST UPDATE WARNINGS HERE INSIDE IF STATEMENT
                if (beware) {
                    warnings[k] = new int[]{i, Integer.parseInt(rule.code)};
                    k++;
                }
            }
        }
    }


    public Boolean evalType2(String[] fieldVal, String value) {
        for (String val : fieldVal) {
            if (val.equals(value)) {
                return true;
            }
        }

        return false;
    }

    public Boolean evalType1(String predicate, String fieldVal, String value) {
        if (predicate.equals("eq")) {
            return fieldVal.equals(value);
        } else if (predicate.equals("lesser")) {
            return Integer.parseInt(fieldVal) < Integer.parseInt(value);
        } else {
            return Integer.parseInt(fieldVal) > Integer.parseInt(value);
        }
    }

    public String inPatientList(String field, String name) {
      return "sigh";
    }
}
