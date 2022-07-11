package com.example.whatmain;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {

    //binding
    private FragmentFragment1Binding binding;
    String prev_id;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
//    public static Fragment1 newInstance(String param1, String param2)  {
//        Fragment1 fragment = new Fragment1();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_busan_festival_step1);

//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v=inflater.inflate(R.layout.fragment_fragment_1,container,false);

        EditText edit_id=(EditText) v.findViewById(R.id.editUsername);
        EditText edit_pw=(EditText) v.findViewById(R.id.editPW);
        Button updateBtn=(Button) v.findViewById(R.id.upDateBtn);

        //기존 id, pw hint로 띄워주기
        Bundle bundle=this.getArguments();
        System.out.println("Fragment1 왔어!!!!!여기!!");
//        if (bundle!=null){
//            prev_id=bundle.getString("id");
//            //String prev_pw=bundle.getString("pw");
//            //System.out.println("Fragment1 여기!!!!! bundle 부분"+prev_id + " "+prev_pw);
//
//            edit_id.setHint(prev_id);
//            //edit_pw.setHint(prev_pw);
//        }
        prev_id=bundle.getString("id");
        //String prev_pw=bundle.getString("pw");
        //System.out.println("Fragment1 여기!!!!! bundle 부분"+prev_id + " "+prev_pw);

        edit_id.setHint(prev_id);
        //edit_pw.setHint(prev_pw);

        //new_id, new_pw로 업데이트
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_id=edit_id.getText().toString();
                String new_pw=edit_pw.getText().toString();
                updateUser(new_id,new_pw);
                Toast.makeText(getContext(), "수정되었습니다", Toast.LENGTH_SHORT).show();
            }
        });

        return inflater.inflate(R.layout.fragment_fragment_1, container, false);
    }
    public void updateUser(String username, String password){
        //DB에 userId, userPw 업데이트

    }
}