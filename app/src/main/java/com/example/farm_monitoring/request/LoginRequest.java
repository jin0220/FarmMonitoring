package com.example.farm_monitoring.request;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    final static private String url = "http://easyfarm.dothome.co.kr/json/user_table.php";
    private Map<String,String> map;

    public LoginRequest(String id, String password, Response.Listener<String> listener) {
        super(Method.POST, url, listener, null);

        map = new HashMap<>();
        map.put("id",id);
        map.put("password", password);
        Log.d("확인", id + " " + password);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}
