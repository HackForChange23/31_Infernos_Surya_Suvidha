package com.example.ss31;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OpenFrame2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OpenFrame2 extends Fragment {

    private String verificationId;
    EditText otp;
    EditText name;


    private FirebaseAuth mAuth;

    // variable for our text input
    // field for phone and OTP.


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OpenFrame2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OpenFrame2.
     */
    // TODO: Rename and change types and number of parameters
    public static OpenFrame2 newInstance(String param1, String param2) {
        OpenFrame2 fragment = new OpenFrame2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_open_frame2, container, false);

        mAuth = FirebaseAuth.getInstance();
        name = (EditText) v.findViewById(R.id.nameedtxt);
        otp = (EditText) v.findViewById(R.id.otptxt);

        TextView nextbtn = (TextView) v.findViewById(R.id.Nextbtn);

        View mark1 = (View) v.findViewById(R.id.markframe1);
        View mark2 = (View) v.findViewById(R.id.markframe2);
        View mark3 = (View) v.findViewById(R.id.markframe3);
        View mark4 = (View) v.findViewById(R.id.markframe4);

        mark1.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.orangecircle));
        mark2.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.orangecircle));
        mark3.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.whitecircle));
        mark4.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.whitecircle));


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(nextbtn.getText().equals("Generate OTP"))
                {
                    if (name.getText().toString().isEmpty()) {
                        // when mobile number text field is empty
                        // displaying a toast message.
                        Toast.makeText(getContext(), "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
                    } else {
                        // if the text field is not empty we are calling our
                        // send OTP method for getting OTP from Firebase.
                        String phone = "+91" + name.getText().toString();
                        sendVerificationCode(phone);
                    }

                    nextbtn.setText("Next");
                }
                else if(nextbtn.getText().equals("Next"))
                {
                    Bundle b = new Bundle();
                    b.putString("phone",name.getText().toString());
                    b.putString("map","false");

                    //verifyCode(otp.getText().toString());
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment myFragment = new OpenFrame3();
                    myFragment.setArguments(b);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.openframe, myFragment).addToBackStack(null).commit();

                }



//                if (name.getText().toString() != null) {
//                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                    Fragment myFragment = new OpenFrame3();
//                    //myFragment.setArguments(bundle_addsubject);
//                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.openframe, myFragment).addToBackStack(null).commit();
//
//                } else {
//                    Toast.makeText(getContext(), "Enter your name to proceed", Toast.LENGTH_SHORT).show();
//                }


            }
        });

        return v;
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        // inside this method we are checking if
        // the code entered is correct or not.
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // if the code is correct and the task is successful
                            // we are sending our user to new activity.
                            Toast.makeText(getContext(),"code",Toast.LENGTH_SHORT);

                        } else {
                            // if the code is not correct then we are
                            // displaying an error message to the user.
                            Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    private void sendVerificationCode(String number) {
        // this method is used for getting
        // OTP on user phone number.
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number)            // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(getActivity())                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)           // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    // callback method is called on Phone auth provider.
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks

            // initializing our callbacks for on
            // verification callback method.
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        // below method is used when
        // OTP is sent from Firebase
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            // when we receive the OTP it
            // contains a unique id which
            // we are storing in our string
            // which we have already created.
            verificationId = s;
        }

        // this method is called when user
        // receive OTP from Firebase.
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            // below line is used for getting OTP code
            // which is sent in phone auth credentials.
            final String code = phoneAuthCredential.getSmsCode();

            // checking if the code
            // is null or not.
            if (code != null) {
                // if the code is not null then
                // we are setting that code to
                // our OTP edittext field.
                otp.setText(code);

                // after setting this code
                // to OTP edittext field we
                // are calling our verifycode method.
                verifyCode(code);
            }
        }

        // this method is called when firebase doesn't
        // sends our OTP code due to any error or issue.
        @Override
        public void onVerificationFailed(FirebaseException e) {
            // displaying error message with firebase exception.
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    // below method is use to verify code from Firebase.
    private void verifyCode(String code) {
        // below line is used for getting
        // credentials from our verification id and code.
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

        // after getting credential we are
        // calling sign in method.
        signInWithCredential(credential);
    }
}