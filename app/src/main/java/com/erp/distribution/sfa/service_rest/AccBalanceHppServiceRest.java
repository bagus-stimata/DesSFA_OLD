package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model_acc_cb.AccBalanceHpp;
import com.erp.distribution.sfa.security_config.ApiAuthenticationClient;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AccBalanceHppServiceRest {
    protected static final String TAG = AccBalanceHppServiceRest.class.getSimpleName();
    private ApiAuthenticationClient apiAuthenticationClient;

    private Context context;
    private ProgressDialog progressDialog;

    public void showProgressDialog(CharSequence message) {
        if (context !=null) {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(context);
                progressDialog.setIndeterminate(true);
            }
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }
    public void showLoadingProgressDialog() {
        this.showProgressDialog("Loading. Please wait...");
    }
    public void dismissProgressDialog() {
        if (progressDialog != null ) {
            progressDialog.dismiss();
        }
    }

    public AccBalanceHppServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public AccBalanceHpp getAccBalanceHppById(int id) {
        AccBalanceHppServiceRest.AccBalanceHppCrudAsyncTask asyncTask = (AccBalanceHppServiceRest.AccBalanceHppCrudAsyncTask) new AccBalanceHppServiceRest.AccBalanceHppCrudAsyncTask(apiAuthenticationClient, id, true);
        AccBalanceHpp accBalanceHpp = null;
        try {
            accBalanceHpp = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (accBalanceHpp==null) accBalanceHpp =new AccBalanceHpp();
        return accBalanceHpp;
    }
    public List<AccBalanceHpp> getAllAccBalanceHpp() {
        AccBalanceHppServiceRest.AccBalanceHppAllAsyncTask asyncTask = (AccBalanceHppServiceRest.AccBalanceHppAllAsyncTask) new AccBalanceHppServiceRest.AccBalanceHppAllAsyncTask(apiAuthenticationClient);
        List<AccBalanceHpp> listAccBalanceHpp = new ArrayList<>();
        try {
            listAccBalanceHpp = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listAccBalanceHpp;

    }
    public void createAccBalanceHpp(AccBalanceHpp fArea) {
//        new AccBalanceHppCreateAsyncTask(apiAuthenticationClient, fArea).execute();
        new AccBalanceHppServiceRest.AccBalanceHppCrudAsyncTask(apiAuthenticationClient, fArea).execute();
    }
    public void updateAccBalanceHpp(Integer id, AccBalanceHpp fArea) {
//        new AccBalanceHppUpdateAsyncTask(apiAuthenticationClient, id, fArea).execute();
        new AccBalanceHppServiceRest.AccBalanceHppCrudAsyncTask(apiAuthenticationClient, id, fArea).execute();
    }
    public void deleteAccBalanceHpp(Integer id) {
//        new AccBalanceHppDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new AccBalanceHppServiceRest.AccBalanceHppCrudAsyncTask(apiAuthenticationClient, id).execute();
    }



    public class AccBalanceHppCrudAsyncTask extends AsyncTask<Void, Void, AccBalanceHpp> {

        String operation = "";
        AccBalanceHpp newAccBalanceHpp = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private AccBalanceHppCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private AccBalanceHppCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  AccBalanceHpp newAccBalanceHpp){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newAccBalanceHpp = newAccBalanceHpp;
            operation = "ADD_NEW";
        }
        private AccBalanceHppCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, AccBalanceHpp updateAccBalanceHpp){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newAccBalanceHpp = updateAccBalanceHpp;
            this.id = id_update;
            operation = "UPDATE";
        }
        private AccBalanceHppCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected AccBalanceHpp doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<AccBalanceHpp> response = restTemplate.exchange(url, HttpMethod.POST, AccBalanceHpp.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newAccBalanceHpp, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<AccBalanceHpp> response = restTemplate.postForEntity(url, httpEntity,  AccBalanceHpp.class);
                ResponseEntity<AccBalanceHpp> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createAccBalanceHpp";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newAccBalanceHpp, apiAuthenticationClient.getRequestHeaders()), AccBalanceHpp.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateAccBalanceHppInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newAccBalanceHpp, apiAuthenticationClient.getRequestHeaders()), AccBalanceHpp.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteAccBalanceHpp/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), AccBalanceHpp.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getAccBalanceHppById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), AccBalanceHpp.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new AccBalanceHpp();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new AccBalanceHpp();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new AccBalanceHpp();
            }
        }

        @Override
        protected void onPostExecute(AccBalanceHpp result) {
            dismissProgressDialog();
//            if(result==null) result = new AccBalanceHpp();
//            displayResponseAccBalanceHpp(result);
        }
    }

    public class AccBalanceHppAllAsyncTask extends  AsyncTask<Void, Void, List<AccBalanceHpp>>{
        private ApiAuthenticationClient apiAuthenticationClient;

        private AccBalanceHppAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<AccBalanceHpp> doInBackground(Void... voids) {
            final String url = apiAuthenticationClient.getBaseUrl() + "getAllAccBalanceHpp";

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {
                // Make the network request
                Log.d(TAG, url);
                ResponseEntity<AccBalanceHpp[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), AccBalanceHpp[].class);
                List<AccBalanceHpp> list = Arrays.asList(response.getBody());
                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<AccBalanceHpp>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<AccBalanceHpp>();
            }
        }

        @Override
        protected void onPostExecute(List<AccBalanceHpp> result) {
            dismissProgressDialog();
//            if(result.size()==0) result = new AccBalanceHpp();
//            displayResponseAccBalanceHpp(result);
        }


    }


}
