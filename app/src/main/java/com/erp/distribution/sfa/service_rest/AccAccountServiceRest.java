package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model_acc_cb.AccAccount;
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

public class AccAccountServiceRest {
    protected static final String TAG = AccAccountServiceRest.class.getSimpleName();
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

    public AccAccountServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public AccAccount getAccAccountById(int id) {
        AccAccountServiceRest.AccAccountCrudAsyncTask asyncTask = (AccAccountServiceRest.AccAccountCrudAsyncTask) new AccAccountServiceRest.AccAccountCrudAsyncTask(apiAuthenticationClient, id, true);
        AccAccount accAccount = null;
        try {
            accAccount = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (accAccount==null) accAccount =new AccAccount();
        return accAccount;
    }
    public List<AccAccount> getAllAccAccount() {
        AccAccountServiceRest.AccAccountAllAsyncTask asyncTask = (AccAccountServiceRest.AccAccountAllAsyncTask) new AccAccountServiceRest.AccAccountAllAsyncTask(apiAuthenticationClient);
        List<AccAccount> listAccAccount = new ArrayList<>();
        try {
            listAccAccount = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
        }
        return listAccAccount;

    }
    public void createAccAccount(AccAccount fArea) {
//        new AccAccountCreateAsyncTask(apiAuthenticationClient, fArea).execute();
        new AccAccountServiceRest.AccAccountCrudAsyncTask(apiAuthenticationClient, fArea).execute();
    }
    public void updateAccAccount(Integer id, AccAccount fArea) {
//        new AccAccountUpdateAsyncTask(apiAuthenticationClient, id, fArea).execute();
        new AccAccountServiceRest.AccAccountCrudAsyncTask(apiAuthenticationClient, id, fArea).execute();
    }
    public void deleteAccAccount(Integer id) {
//        new AccAccountDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new AccAccountServiceRest.AccAccountCrudAsyncTask(apiAuthenticationClient, id).execute();
    }

    public class AccAccountCrudAsyncTask extends AsyncTask<Void, Void, AccAccount> {

        String operation = "";
        AccAccount newAccAccount = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private AccAccountCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private AccAccountCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  AccAccount newAccAccount){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newAccAccount = newAccAccount;
            operation = "ADD_NEW";
        }
        private AccAccountCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, AccAccount updateAccAccount){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newAccAccount = updateAccAccount;
            this.id = id_update;
            operation = "UPDATE";
        }
        private AccAccountCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected AccAccount doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<AccAccount> response = restTemplate.exchange(url, HttpMethod.POST, AccAccount.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newAccAccount, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<AccAccount> response = restTemplate.postForEntity(url, httpEntity,  AccAccount.class);
                ResponseEntity<AccAccount> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createAccAccount";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newAccAccount, apiAuthenticationClient.getRequestHeaders()), AccAccount.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateAccAccountInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newAccAccount, apiAuthenticationClient.getRequestHeaders()), AccAccount.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteAccAccount/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), AccAccount.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getAccAccountById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), AccAccount.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new AccAccount();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new AccAccount();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new AccAccount();
            }
        }

        @Override
        protected void onPostExecute(AccAccount result) {
            dismissProgressDialog();
//            if(result==null) result = new AccAccount();
//            displayResponseAccAccount(result);
        }
    }

    public class AccAccountAllAsyncTask extends  AsyncTask<Void, Void, List<AccAccount>>{
        private ApiAuthenticationClient apiAuthenticationClient;

        private AccAccountAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<AccAccount> doInBackground(Void... voids) {
            final String url = apiAuthenticationClient.getBaseUrl() + "getAllAccAccount";

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {
                // Make the network request
                Log.d(TAG, url);
                ResponseEntity<AccAccount[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), AccAccount[].class);
                List<AccAccount> list = Arrays.asList(response.getBody());
                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<AccAccount>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<AccAccount>();
            }
        }

        @Override
        protected void onPostExecute(List<AccAccount> result) {
            dismissProgressDialog();
//            if(result.size()==0) result = new AccAccount();
//            displayResponseAccAccount(result);
        }


    }


}
