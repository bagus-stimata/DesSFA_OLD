package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FCompany;
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

public class FCompanyServiceRest {
    protected static final String TAG = FCompanyServiceRest.class.getSimpleName();
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

    public FCompanyServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FCompany getFCompanyById(int id) {
        FCompanyServiceRest.FCompanyCrudAsyncTask asyncTask = (FCompanyServiceRest.FCompanyCrudAsyncTask) new FCompanyServiceRest.FCompanyCrudAsyncTask(apiAuthenticationClient, id, true);
        FCompany fCompany = null;
        try {
            fCompany = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (fCompany==null) fCompany =new FCompany();
        return fCompany;
    }
    public List<FCompany> getAllFCompany() {
        FCompanyServiceRest.FCompanyAllAsyncTask asyncTask = (FCompanyServiceRest.FCompanyAllAsyncTask) new FCompanyServiceRest.FCompanyAllAsyncTask(apiAuthenticationClient);
        List<FCompany> listFCompany = new ArrayList<>();
        try {
            listFCompany = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFCompany;

    }

    public void createFCompany(FCompany fCompany) {
//        new FCompanyCreateAsyncTask(apiAuthenticationClient, fCompany).execute();
        new FCompanyServiceRest.FCompanyCrudAsyncTask(apiAuthenticationClient, fCompany).execute();
    }
    public void updateFCompany(Integer id, FCompany fCompany) {
//        new FCompanyUpdateAsyncTask(apiAuthenticationClient, id, fCompany).execute();
        new FCompanyServiceRest.FCompanyCrudAsyncTask(apiAuthenticationClient, id, fCompany).execute();
    }
    public void deleteFCompany(Integer id) {
//        new FCompanyDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FCompanyServiceRest.FCompanyCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FCompanyCrudAsyncTask extends AsyncTask<Void, Void, FCompany> {

        String operation = "";
        FCompany newFCompany = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FCompanyCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FCompanyCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FCompany newFCompany){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFCompany = newFCompany;
            operation = "ADD_NEW";
        }
        private FCompanyCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FCompany updateFCompany){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFCompany = updateFCompany;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FCompanyCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FCompany doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FCompany> response = restTemplate.exchange(url, HttpMethod.POST, FCompany.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFCompany, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FCompany> response = restTemplate.postForEntity(url, httpEntity,  FCompany.class);
                ResponseEntity<FCompany> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFCompany";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFCompany, apiAuthenticationClient.getRequestHeaders()), FCompany.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFCompanyInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFCompany, apiAuthenticationClient.getRequestHeaders()), FCompany.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFCompany/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FCompany.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFCompanyById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FCompany.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FCompany();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FCompany();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FCompany();
            }
        }

        @Override
        protected void onPostExecute(FCompany result) {
            dismissProgressDialog();
//            if(result==null) result = new FCompany();
//            displayResponseFCompany(result);
        }
    }

    public class FCompanyAllAsyncTask extends  AsyncTask<Void, Void, List<FCompany>>{
        private ApiAuthenticationClient apiAuthenticationClient;

        private FCompanyAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FCompany> doInBackground(Void... voids) {
            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFCompany";

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {
                // Make the network request
                Log.d(TAG, url);
                ResponseEntity<FCompany[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FCompany[].class);
                List<FCompany> list = Arrays.asList(response.getBody());
                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FCompany>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FCompany>();
            }
        }

        @Override
        protected void onPostExecute(List<FCompany> result) {
            dismissProgressDialog();
//            if(result.size()==0) result = new FCompany();
//            displayResponseFCompany(result);
        }


    }


}
