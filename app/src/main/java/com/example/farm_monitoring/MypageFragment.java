package com.example.farm_monitoring;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.farm_monitoring.request.LoginRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MypageFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    TextView id, email, userPlant, userPlant_Dday;
    long D_day = 0;

    public MypageFragment() {
        // Required empty public constructor
    }

    public static MypageFragment newInstance(String param1, String param2) {
        MypageFragment fragment = new MypageFragment();
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
        View view = inflater.inflate(R.layout.fragment_mypage, container, false);

        id = view.findViewById(R.id.id);
        email = view.findViewById(R.id.email);
        userPlant = view.findViewById(R.id.userPlant);
        userPlant_Dday = view.findViewById(R.id.userPlant_Dday);

        String userId = PreferenceManager.getString(getContext(),"id");

        id.setText(userId);
        plantDate(userId);
        login();

        //공지사항
        view.findViewById(R.id.notice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NoticeActivity.class);
                startActivity(intent);
            }
        });

        //faq
        view.findViewById(R.id.faq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //qna
        view.findViewById(R.id.qna).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //개인 정보 수정
        view.findViewById(R.id.account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AccountActivity.class);
                startActivity(intent);
            }
        });

        //로그아웃
        view.findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.removeKey(getContext(),"id");
                PreferenceManager.removeKey(getContext(),"pw");
                PreferenceManager.removeKey(getContext(),"loginCheck");

                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        //회원탈퇴
//        view.findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog dialog = new AlertDialog.Builder(); //프래그먼트에서 다이얼로그를 사용하려면 커스텀 다이얼로그해야 사용가능
//            }
//        });

        return view;
    }

    public void plantDate(String id) {
        String url = "http://easyfarm.dothome.co.kr/json/user_plant_table.php?id=" + id;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String plant3 = jsonObject.getString("plant3");
                        String start = jsonObject.getString("start").split(" ")[0];
                        String end = jsonObject.getString("end").split(" ")[0];

                        Date currentTime = Calendar.getInstance().getTime();
                        long current = currentTime.getTime();

                        if(end.equals("null")){
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date startTime = sdf.parse(start);
                            long timestamp = startTime.getTime();

                            D_day = (current - timestamp) / (24*60*60*1000);
                            userPlant.setText(plant3);
                            userPlant_Dday.setText(D_day+"");
                        }
                    }
                } catch (JSONException | ParseException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "데이터를 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(jsonArrayRequest);
    }

    public void login() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success) {
                        String userEmail = jsonObject.getString("email");
                        email.setText(userEmail);

                    } else {

                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        LoginRequest loginRequest = new LoginRequest(PreferenceManager.getString(getContext(),"id"),
                PreferenceManager.getString(getContext(),"pw"), responseListener);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(loginRequest);
    }
}