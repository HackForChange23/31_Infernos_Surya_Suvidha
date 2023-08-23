package com.example.ss31;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ss31.adapters.PostAdapter;
import com.example.ss31.modelClass.Post;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedFragment extends Fragment {

    CardView fasalKYC;
    Context context;
    public FeedFragment(Context context){
        this.context = context;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FeedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedFragment newInstance(String param1, String param2) {
        FeedFragment fragment = new FeedFragment();
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

        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        // Inflate the layout for this fragment
        RecyclerView recyclerView = view.findViewById(R.id.posts_recycler);
        ArrayList<Post> arrayListPost = new ArrayList<>();
        arrayListPost.add(new Post(1,1,1,1,1,"#AzadikaAmritMahotsav #Farmer #kisaan","https://firebasestorage.googleapis.com/v0/b/ss31-c21a4.appspot.com/o/Screenshot%201945-06-01%20at%204.07.35%20PM.png?alt=media&token=cf334985-9218-4736-a140-bdede37cc035",
                "Tejash Seth","15th August","03:52 hrs"));

        arrayListPost.add(new Post(1,1,1,1,1,"#AzadikaAmritMahotsav #soil #kisaan","https://firebasestorage.googleapis.com/v0/b/ss31-c21a4.appspot.com/o/Screenshot%201945-06-01%20at%204.09.22%20PM.png?alt=media&token=d23152a1-eb75-4578-9f42-9c68a99a932c",
                "Ayush Dubey","18th August","13:53 hrs"));

        arrayListPost.add(new Post(1,1,1,1,1,"#AzadikaAmritMahotsav #soil #kisaan","https://firebasestorage.googleapis.com/v0/b/ss31-c21a4.appspot.com/o/Screenshot%201945-06-01%20at%204.13.49%20PM.png?alt=media&token=de2a8c10-d293-420d-9f1d-faf4f34f2d83",
                "Soumen Paul","22nd August","09:05 hrs"));

        arrayListPost.add(new Post(1,1,1,1,1,"#AzadikaAmritMahotsav #soil #kisaan","https://firebasestorage.googleapis.com/v0/b/ss31-c21a4.appspot.com/o/Screenshot%201945-06-01%20at%204.14.20%20PM.png?alt=media&token=aa139d5b-8e0e-49b8-a7a6-e0161294dd25",
                "Kritish Shukla","23rd August","16:13 hrs"));

        arrayListPost.add(new Post(1,1,1,1,1,"#AzadikaAmritMahotsav #soil #kisaan","https://firebasestorage.googleapis.com/v0/b/ss31-c21a4.appspot.com/o/Screenshot%201945-06-01%20at%204.15.34%20PM.png?alt=media&token=52773d29-ffcc-4b8b-9bde-1a947ee81ac0",
                "Unnati Bhardwaj","20th August","12:01 hrs"));



        PostAdapter postAdapter = new PostAdapter(context,arrayListPost);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(postAdapter);
//        ImageView imageView = view.findViewById(R.id.imageView);
//        Glide.with(view)
//                .load("https://firebasestorage.googleapis.com/v0/b/mukt---drug-free-india.appspot.com/o/images%2Fpost1.jpg?alt=media&token=862e04fc-0d08-4047-ac4e-4d53cb09dad7,https://firebasestorage.googleapis.com/v0/b/mukt---drug-free-india.appspot.com/o/images%2Fpost1.jpg?alt=media&token=862e04fc-0d08-4047-ac4e-4d53cb09dad7 ")
//                .into(imageView);

//        Glide.with(this)
//                .asBitmap()
//                .load("https://firebasestorage.googleapis.com/v0/b/mukt---drug-free-india.appspot.com/o/images%2Fpost1.jpg?alt=media&token=862e04fc-0d08-4047-ac4e-4d53cb09dad7")
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(@NonNull Bitmap resource, Transition<? super Bitmap> transition) {
//                        imageView.setImageBitmap(resource);
////                        imageview2.setImageBitmap(resource);
//                    }
//                });
//        StorageReference = FirebaseStorage.getInstance().getReference().child("images/post1.jpg");
//
//        try {
//           final File localFile = File.createTempFile("post1","jpg");
//           StorageReference.getFile(localFile)
//           .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//               @Override
//               public void onSuccess(@NonNull FileDownloadTask.TaskSnapshot taskSnapshot) {
//                   Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
//                   (ImageView)view.findViewById(R.id.imageView).setImageBitmap(bitmap);
//               }
//           }).addOnFailureListener(new OnFailureListener() {
//               @Override
//               public void onFailure(@NonNull Exception e) {
//
//               }
//           });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        fasalKYC = view.findViewById(R.id.fasal_kyc);
        fasalKYC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(),Crop_Prediction.class);
                startActivity(i);
            }
        });
        return  view;
    }
}