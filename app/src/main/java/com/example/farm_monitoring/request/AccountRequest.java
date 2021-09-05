package com.example.farm_monitoring.request;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AccountRequest extends StringRequest {
    final static private String url = "http://easyfarm.dothome.co.kr/json/account.php";
    private Map<String,String> map;

    public AccountRequest(String id, String name, String password, String email, String phone, String farm_id,
                          Response.Listener<String> listener) {
        super(Method.POST, url, listener, null);

        map = new HashMap<>();
        map.put("id",id); //php파일로 보낼 파라미터
        map.put("name", name);
        map.put("password", password);
        map.put("email",email);
        map.put("phone", phone);
        map.put("password", password);
        map.put("farm_id",farm_id);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}
