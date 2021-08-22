package com.example.farm_monitoring.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CommunityRequest extends StringRequest {
    final static private String url = "http://easyfarm.dothome.co.kr/json/community_detail.php";
    private Map<String,String> map;

    public CommunityRequest(String num, Response.Listener<String> listener) {
        super(Method.POST, url, listener, null);

        map = new HashMap<>();
        map.put("num", num); //php파일로 보낼 파라미터
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}