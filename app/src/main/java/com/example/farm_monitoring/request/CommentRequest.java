package com.example.farm_monitoring.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CommentRequest extends StringRequest {
    final static private String url = "http://easyfarm.dothome.co.kr/json/comment.php";
    private Map<String,String> map;

    public CommentRequest(String id, String num, String comment, Response.Listener<String> listener) {
        super(Method.POST, url, listener, null);

        map = new HashMap<>();
        map.put("id", id); //php파일로 보낼 파라미터
        map.put("num", num);
        map.put("comment", comment);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}
