package com.example.farm_monitoring.request;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SignUpRequest extends StringRequest {
    final static private String url = "http://easyfarm.dothome.co.kr/json/register.php";
    private Map<String,String> map;

    public SignUpRequest(String id, String name, String password, String email, String phone, String farm_id,
                         String category1,String category2,String category3, Response.Listener<String> listener) {
        super(Method.POST, url, listener, null);

        map = new HashMap<>();
        map.put("id",id); //php파일로 보낼 파라미터
        map.put("name", name);
        map.put("password", password);
        map.put("email",email);
        map.put("phone", phone);
        map.put("password", password);
        map.put("farm_id",farm_id);
        map.put("category1", category1);
        map.put("category2", category2);
        map.put("category3", category3);
        Log.d("확인", id + name + password + email + phone + farm_id + category1 + category2 + category3);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}
