package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model_acc_cb.AccCostCenter;
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

public class AccCostCenterServiceRest {
    protected static final String TAG = AccCostCenterServiceRest.class.getSimpleName();
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

    public AccCostCenterServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public AccCostCenter getAccCostCenterById(int id) {
        AccCostCenterServiceRest.AccCostCenterCrudAsyncTask asyncTask = (AccCostCenterServiceRest.AccCostCenterCrudAsyncTask) new AccCostCenterServiceRest.AccCostCenterCrudAsyncTask(apiAuthenticationClient, id, true);
        AccCostCenter accCostCenter = null;
        try {
            accCostCenter = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (accCostCenter==null) accCostCenter =new AccCostCenter();
        return accCostCenter;
    }
    public List<AccCostCenter> getAllAccCostCenter() {
        AccCostCenterServiceRest.AccCostCenterAllAsyncTask asyncTask = (AccCostCenterServiceRest.AccCostCenterAllAsyncTask) new AccCostCenterServiceRest.AccCostCenterAllAsyncTask(apiAuthenticationClient);
        List<AccCostCenter> listAccCostCenter = new ArrayList<>();
        try {
            listAccCostCenter = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listAccCostCenter;

    }
    public void createAccCostCenter(AccCostCenter fArea) {
//        new AccCostCenterCreateAsyncTask(apiAuthenticationClient, fArea).execute();
        new AccCostCenterServiceRest.AccCostCenterCrudAsyncTask(apiAuthenticationClient, fArea).execute();
    }
    public void updateAccCostCenter(Integer id, AccCostCenter fArea) {
//        new AccCostCenterUpdateAsyncTask(apiAuthenticationClient, id, fArea).execute();
        new AccCostCenterServiceRest.AccCostCenterCrudAsyncTask(apiAuthenticationClient, id, fArea).execute();
    }
    public void deleteAccCostCenter(Integer id) {
//        new AccCostCenterDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new AccCostCenterServiceRest.AccCostCenterCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class AccCostCenterCrudAsyncTask extends AsyncTask<Void, Void, AccCostCenter> {

        String operation = "";
        AccCostCenter newAccCostCenter = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private AccCostCenterCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private AccCostCenterCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  AccCostCenter newAccCostCenter){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newAccCostCenter = newAccCostCenter;
            operation = "ADD_NEW";
        }
        private AccCostCenterCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, AccCostCenter updateAccCostCenter){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newAccCostCenter = updateAccCostCenter;
            this.id = id_update;
            operation = "UPDATE";
        }
        private AccCostCenterCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected AccCostCenter doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<AccCostCenter> response = restTemplate.exchange(url, HttpMethod.POST, AccCostCenter.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newAccCostCenter, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<AccCostCenter> response = restTemplate.postForEntity(url, httpEntity,  AccCostCenter.class);
                ResponseEntity<AccCostCenter> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createAccCostCenter";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newAccCostCenter, apiAuthenticationClient.getRequestHeaders()), AccCostCenter.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateAccCostCenterInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newAccCostCenter, apiAuthenticationClient.getRequestHeaders()), AccCostCenter.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteAccCostCenter/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), AccCostCenter.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getAccCostCenterById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), AccCostCenter.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new AccCostCenter();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new AccCostCenter();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new AccCostCenter();
            }
        }

        @Override
        protected void onPostExecute(AccCostCenter result) {
            dismissProgressDialog();
//            if(result==null) result = new AccCostCenter();
//            displayResponseAccCostCenter(result);
        }
    }

    public class AccCostCenterAllAsyncTask extends  AsyncTask<Void, Void, List<AccCostCenter>>{
        private ApiAuthenticationClient apiAuthenticationClient;

        private AccCostCenterAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<AccCostCenter> doInBackground(Void... voids) {
            final String url = apiAuthenticationClient.getBaseUrl() + "getAllAccCostCenter";

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {
                // Make the network request
                Log.d(TAG, url);
                ResponseEntity<AccCostCenter[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), AccCostCenter[].class);
                List<AccCostCenter> list = Arrays.asList(response.getBody());
                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<AccCostCenter>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<AccCostCenter>();
            }
        }

        @Override
        protected void onPostExecute(List<AccCostCenter> result) {
            dismissProgressDialog();
//            if(result.size()==0) result = new AccCostCenter();
//            displayResponseAccCostCenter(result);
        }


    }


}
