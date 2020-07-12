package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model_acc_cb.AccBalance;
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

public class AccBalanceServiceRest {
    protected static final String TAG = AccBalanceServiceRest.class.getSimpleName();
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

    public AccBalanceServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public AccBalance getAccBalanceById(int id) {
        AccBalanceServiceRest.AccBalanceCrudAsyncTask asyncTask = (AccBalanceServiceRest.AccBalanceCrudAsyncTask) new AccBalanceServiceRest.AccBalanceCrudAsyncTask(apiAuthenticationClient, id, true);
        AccBalance accBalance = null;
        try {
            accBalance = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (accBalance==null) accBalance =new AccBalance();
        return accBalance;
    }
    public List<AccBalance> getAllAccBalance() {
        AccBalanceServiceRest.AccBalanceAllAsyncTask asyncTask = (AccBalanceServiceRest.AccBalanceAllAsyncTask) new AccBalanceServiceRest.AccBalanceAllAsyncTask(apiAuthenticationClient);
        List<AccBalance> listAccBalance = new ArrayList<>();
        try {
            listAccBalance = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listAccBalance;

    }
    public void createAccBalance(AccBalance fArea) {
//        new AccBalanceCreateAsyncTask(apiAuthenticationClient, fArea).execute();
        new AccBalanceServiceRest.AccBalanceCrudAsyncTask(apiAuthenticationClient, fArea).execute();
    }
    public void updateAccBalance(Integer id, AccBalance fArea) {
//        new AccBalanceUpdateAsyncTask(apiAuthenticationClient, id, fArea).execute();
        new AccBalanceServiceRest.AccBalanceCrudAsyncTask(apiAuthenticationClient, id, fArea).execute();
    }
    public void deleteAccBalance(Integer id) {
//        new AccBalanceDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new AccBalanceServiceRest.AccBalanceCrudAsyncTask(apiAuthenticationClient, id).execute();
    }



    public class AccBalanceCrudAsyncTask extends AsyncTask<Void, Void, AccBalance> {

        String operation = "";
        AccBalance newAccBalance = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private AccBalanceCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private AccBalanceCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  AccBalance newAccBalance){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newAccBalance = newAccBalance;
            operation = "ADD_NEW";
        }
        private AccBalanceCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, AccBalance updateAccBalance){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newAccBalance = updateAccBalance;
            this.id = id_update;
            operation = "UPDATE";
        }
        private AccBalanceCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected AccBalance doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<AccBalance> response = restTemplate.exchange(url, HttpMethod.POST, AccBalance.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newAccBalance, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<AccBalance> response = restTemplate.postForEntity(url, httpEntity,  AccBalance.class);
                ResponseEntity<AccBalance> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createAccBalance";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newAccBalance, apiAuthenticationClient.getRequestHeaders()), AccBalance.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateAccBalanceInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newAccBalance, apiAuthenticationClient.getRequestHeaders()), AccBalance.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteAccBalance/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), AccBalance.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getAccBalanceById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), AccBalance.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new AccBalance();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new AccBalance();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new AccBalance();
            }
        }

        @Override
        protected void onPostExecute(AccBalance result) {
            dismissProgressDialog();
//            if(result==null) result = new AccBalance();
//            displayResponseAccBalance(result);
        }
    }

    public class AccBalanceAllAsyncTask extends  AsyncTask<Void, Void, List<AccBalance>>{
        private ApiAuthenticationClient apiAuthenticationClient;

        private AccBalanceAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<AccBalance> doInBackground(Void... voids) {
            final String url = apiAuthenticationClient.getBaseUrl() + "getAllAccBalance";

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {
                // Make the network request
                Log.d(TAG, url);
                ResponseEntity<AccBalance[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), AccBalance[].class);
                List<AccBalance> list = Arrays.asList(response.getBody());
                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<AccBalance>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<AccBalance>();
            }
        }

        @Override
        protected void onPostExecute(List<AccBalance> result) {
            dismissProgressDialog();
//            if(result.size()==0) result = new AccBalance();
//            displayResponseAccBalance(result);
        }


    }


}
