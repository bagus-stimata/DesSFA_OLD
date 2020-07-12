package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model_acc_cb.CbMutasiKash;
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

public class CbMutasiKashServiceRest {
    protected static final String TAG = CbMutasiKashServiceRest.class.getSimpleName();
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

    public CbMutasiKashServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public CbMutasiKash getCbMutasiKashById(int id) {
        CbMutasiKashServiceRest.CbMutasiKashCrudAsyncTask asyncTask = (CbMutasiKashServiceRest.CbMutasiKashCrudAsyncTask) new CbMutasiKashServiceRest.CbMutasiKashCrudAsyncTask(apiAuthenticationClient, id, true);
        CbMutasiKash cbMutasiKash = null;
        try {
            cbMutasiKash = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (cbMutasiKash==null) cbMutasiKash =new CbMutasiKash();
        return cbMutasiKash;
    }
    public List<CbMutasiKash> getAllCbMutasiKash() {
        CbMutasiKashServiceRest.CbMutasiKashAllAsyncTask asyncTask = (CbMutasiKashServiceRest.CbMutasiKashAllAsyncTask) new CbMutasiKashServiceRest.CbMutasiKashAllAsyncTask(apiAuthenticationClient);
        List<CbMutasiKash> listCbMutasiKash = new ArrayList<>();
        try {
            listCbMutasiKash = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listCbMutasiKash;

    }
    public void createCbMutasiKash(CbMutasiKash fArea) {
//        new CbMutasiKashCreateAsyncTask(apiAuthenticationClient, fArea).execute();
        new CbMutasiKashServiceRest.CbMutasiKashCrudAsyncTask(apiAuthenticationClient, fArea).execute();
    }
    public void updateCbMutasiKash(Integer id, CbMutasiKash fArea) {
//        new CbMutasiKashUpdateAsyncTask(apiAuthenticationClient, id, fArea).execute();
        new CbMutasiKashServiceRest.CbMutasiKashCrudAsyncTask(apiAuthenticationClient, id, fArea).execute();
    }
    public void deleteCbMutasiKash(Integer id) {
//        new CbMutasiKashDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new CbMutasiKashServiceRest.CbMutasiKashCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class CbMutasiKashCrudAsyncTask extends AsyncTask<Void, Void, CbMutasiKash> {

        String operation = "";
        CbMutasiKash newCbMutasiKash = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private CbMutasiKashCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private CbMutasiKashCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  CbMutasiKash newCbMutasiKash){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newCbMutasiKash = newCbMutasiKash;
            operation = "ADD_NEW";
        }
        private CbMutasiKashCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, CbMutasiKash updateCbMutasiKash){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newCbMutasiKash = updateCbMutasiKash;
            this.id = id_update;
            operation = "UPDATE";
        }
        private CbMutasiKashCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected CbMutasiKash doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<CbMutasiKash> response = restTemplate.exchange(url, HttpMethod.POST, CbMutasiKash.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newCbMutasiKash, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<CbMutasiKash> response = restTemplate.postForEntity(url, httpEntity,  CbMutasiKash.class);
                ResponseEntity<CbMutasiKash> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createCbMutasiKash";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newCbMutasiKash, apiAuthenticationClient.getRequestHeaders()), CbMutasiKash.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateCbMutasiKashInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newCbMutasiKash, apiAuthenticationClient.getRequestHeaders()), CbMutasiKash.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteCbMutasiKash/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), CbMutasiKash.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getCbMutasiKashById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), CbMutasiKash.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new CbMutasiKash();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new CbMutasiKash();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new CbMutasiKash();
            }
        }

        @Override
        protected void onPostExecute(CbMutasiKash result) {
            dismissProgressDialog();
//            if(result==null) result = new CbMutasiKash();
//            displayResponseCbMutasiKash(result);
        }
    }

    public class CbMutasiKashAllAsyncTask extends  AsyncTask<Void, Void, List<CbMutasiKash>>{
        private ApiAuthenticationClient apiAuthenticationClient;

        private CbMutasiKashAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<CbMutasiKash> doInBackground(Void... voids) {
            final String url = apiAuthenticationClient.getBaseUrl() + "getAllCbMutasiKash";

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {
                // Make the network request
                Log.d(TAG, url);
                ResponseEntity<CbMutasiKash[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), CbMutasiKash[].class);
                List<CbMutasiKash> list = Arrays.asList(response.getBody());
                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<CbMutasiKash>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<CbMutasiKash>();
            }
        }

        @Override
        protected void onPostExecute(List<CbMutasiKash> result) {
            dismissProgressDialog();
//            if(result.size()==0) result = new CbMutasiKash();
//            displayResponseCbMutasiKash(result);
        }


    }


}
