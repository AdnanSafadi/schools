package com.tradinos.network;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.security.auth.login.LoginException;

public class
TradinosRequest<T> extends Request<JSONObject> {

    private SuccessCallback<T> successCallback;
    FailedCallback failedCallback;
    private Map<String, String> parameters;
    private Map<String, String> headers;
    private Context context;
    private TradinosParser<T> parser;
    private boolean authenticationRequired = false;

    String url = "";

    public TradinosRequest(Context context, String url, RequestMethod method, final TradinosParser<T> parser, SuccessCallback<T> successCallback, final FailedCallback failedCallback) {

        super(method == RequestMethod.Get ? com.android.volley.Request.Method.GET : com.android.volley.Request.Method.POST, url, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof AuthFailureError) {
                    failedCallback.OnFailed(Code.AuthenticationError, "Authentication Error !");
                } else if (error instanceof ServerError) {
                    failedCallback.OnFailed(Code.ServerError, "Server Error !");
                } else if (error instanceof NetworkError) {
                    failedCallback.OnFailed(Code.NetworkError, "Network Error !");
                } else if (error instanceof ParseError) {
                    failedCallback.OnFailed(Code.ParsingError, "Parsing Error !");
                } else if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    failedCallback.OnFailed(Code.TimeOutError, "Timeout Error !");
                } else {
                    failedCallback.OnFailed(Code.ServerError, error.getMessage());
                }


            }
        });
        this.url = url;

        this.context = context;
        parameters = new HashMap<>();
        headers = new HashMap<>();
        this.successCallback = successCallback;
        this.failedCallback = failedCallback;
        this.parser = parser;
        if (this.getMethod() == Method.POST)
            setRetryPolicy(new VolleyRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        if (volleyError.networkResponse != null && volleyError.networkResponse.data != null) {
            try {
                String response = new String(volleyError.networkResponse.data);
                Log.e("Error Response", response);
                JSONObject jsonObject = new JSONObject(response);
                boolean status = jsonObject.getBoolean("status");
                if (status) {
                } else {
                    String message = jsonObject.getString("error");
                    volleyError = new VolleyError(message);
                }
            } catch (JSONException e) {
                Log.e("parsing error -false-", e.getMessage());
                volleyError = new ParseError();
            }
        }
        return volleyError;
    }


    public void turnOnAuthentication(String username, String password) {
        try {
            String authenticationValue = "Basic " +
                    String.valueOf(Base64.encodeToString((username + ":" + password).getBytes(), Base64.NO_WRAP));
            getHeaders().put("Authorization", authenticationValue);
            authenticationRequired = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void turnOffAuthentication() {
        authenticationRequired = false;
    }

    public void call() {
        InternetManager.getInstance(getContext()).addToRequestQueue(this, url);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {

            String data = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            Log.e("Data: sos ", data);
            JSONObject json = new JSONObject(data);
            return Response.success(
                    json,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (JSONException e) {
            Log.e("ee1", response.data.toString());
            Log.e("ee1", e.getMessage());
            Log.e("ee", e.getMessage());
            return Response.error(new ParseError(e));
        } catch (UnsupportedEncodingException e) {
            Log.e("exc", e.getMessage());
            return Response.error(new ParseError(e));
        }
    }

    public void addParameter(String key, String value) {

        parameters.put(key, value);
        if (getMethod() == Method.GET) {
            if (parameters.size() == 0)
                url += "?";
            else
                url += "&";

            url += key + "=" + value;

        }

    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }


    @Override
    protected void deliverResponse(JSONObject response) {
        try {
            Log.e("Data: ", response.toString());
            boolean status = response.getBoolean("status");
            if (status) {
                String data = response.optString("data", "");
                if (!data.equals("")) {
                    T result = (T) parser.Parse(data);
                    successCallback.OnSuccess(result);
                } else {
                    String message = response.getString("status");
                    successCallback.OnSuccess((T) message);
                }
            } else {
                String message = response.getString("message");
                failedCallback.OnFailed(Code.ServerError, message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            failedCallback.OnFailed(Code.ParsingError, "Parsing Error");
        }
    }

    public boolean isAuthenticationRequired() {
        return authenticationRequired;
    }

    public void setAuthenticationRequired(boolean authenticationRequired) {
        this.authenticationRequired = authenticationRequired;
    }
}
