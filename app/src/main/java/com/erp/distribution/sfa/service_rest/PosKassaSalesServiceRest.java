package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.PosKassaSales;
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

public class PosKassaSalesServiceRest {
    protected static final String TAG = PosKassaSalesServiceRest.class.getSimpleName();
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

    public PosKassaSalesServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public PosKassaSales getPosKassaSalesById(int id) {
        PosKassaSalesServiceRest.PosKassaSalesCrudAsyncTask asyncTask = (PosKassaSalesServiceRest.PosKassaSalesCrudAsyncTask) new PosKassaSalesServiceRest.PosKassaSalesCrudAsyncTask(apiAuthenticationClient, id, true);
        PosKassaSales fPosKassaSales = null;
        try {
//            fPosKassaSales = asyncTask.execute().get();
            fPosKassaSales = asyncTask.execute().get(5, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
        if (fPosKassaSales==null) fPosKassaSales =new PosKassaSales();
        return fPosKassaSales;
    }
    public List<PosKassaSales> getAllPosKassaSales() {
        PosKassaSalesServiceRest.PosKassaSalesAllAsyncTask asyncTask = (PosKassaSalesServiceRest.PosKassaSalesAllAsyncTask) new PosKassaSalesServiceRest.PosKassaSalesAllAsyncTask(apiAuthenticationClient);
        List<PosKassaSales> listPosKassaSales = new ArrayList<>();
        try {
//            fPosKassaSales = asyncTask.execute().get();
            listPosKassaSales = asyncTask.execute().get(5, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
//        if (fPosKassaSales==null) fPosKassaSales =new PosKassaSales();
        return listPosKassaSales;

    }
    public void createPosKassaSales(PosKassaSales fPosKassaSales) {
//        new PosKassaSalesCreateAsyncTask(apiAuthenticationClient, fPosKassaSales).execute();
        new PosKassaSalesServiceRest.PosKassaSalesCrudAsyncTask(apiAuthenticationClient, fPosKassaSales).execute();
    }
    public void updatePosKassaSales(Integer id, PosKassaSales fPosKassaSales) {
//        new PosKassaSalesUpdateAsyncTask(apiAuthenticationClient, id, fPosKassaSales).execute();
        new PosKassaSalesServiceRest.PosKassaSalesCrudAsyncTask(apiAuthenticationClient, id, fPosKassaSales).execute();
    }
    public void deletePosKassaSales(Integer id) {
//        new PosKassaSalesDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new PosKassaSalesServiceRest.PosKassaSalesCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class PosKassaSalesCrudAsyncTask extends AsyncTask<Void, Void, PosKassaSales> {

        String operation = "";
        PosKassaSales newPosKassaSales = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private PosKassaSalesCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private PosKassaSalesCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  PosKassaSales newPosKassaSales){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newPosKassaSales = newPosKassaSales;
            operation = "ADD_NEW";
        }
        private PosKassaSalesCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, PosKassaSales updatePosKassaSales){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newPosKassaSales = updatePosKassaSales;
            this.id = id_update;
            operation = "UPDATE";
        }
        private PosKassaSalesCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected PosKassaSales doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<PosKassaSales> response = restTemplate.exchange(url, HttpMethod.POST, PosKassaSales.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newPosKassaSales, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<PosKassaSales> response = restTemplate.postForEntity(url, httpEntity,  PosKassaSales.class);
                ResponseEntity<PosKassaSales> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createPosKassaSales";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newPosKassaSales, apiAuthenticationClient.getRequestHeaders()), PosKassaSales.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updatePosKassaSalesInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newPosKassaSales, apiAuthenticationClient.getRequestHeaders()), PosKassaSales.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deletePosKassaSales/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), PosKassaSales.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getPosKassaSalesById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), PosKassaSales.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new PosKassaSales();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new PosKassaSales();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new PosKassaSales();
            }
        }

        @Override
        protected void onPostExecute(PosKassaSales result) {
            dismissProgressDialog();
//            if(result==null) result = new PosKassaSales();
//            displayResponsePosKassaSales(result);
        }
    }

    public class PosKassaSalesAllAsyncTask extends  AsyncTask<Void, Void, List<PosKassaSales>>{
        private ApiAuthenticationClient apiAuthenticationClient;

        private PosKassaSalesAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<PosKassaSales> doInBackground(Void... voids) {
            final String url = apiAuthenticationClient.getBaseUrl() + "getAllPosKassaSales";

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {
                // Make the network request
                Log.d(TAG, url);
                ResponseEntity<PosKassaSales[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), PosKassaSales[].class);
                List<PosKassaSales> list = Arrays.asList(response.getBody());
                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<PosKassaSales>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<PosKassaSales>();
            }
        }

        @Override
        protected void onPostExecute(List<PosKassaSales> result) {
            dismissProgressDialog();
//            if(result.size()==0) result = new PosKassaSales();
//            displayResponsePosKassaSales(result);
        }


    }


}
