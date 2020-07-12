package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FtOpnameh;
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

public class FtOpnamehServiceRest {
    protected static final String TAG = FtOpnamehServiceRest.class.getSimpleName();
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

    public FtOpnamehServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FtOpnameh getFtOpnamehById(Long id) {
        FtOpnamehServiceRest.FtOpnamehCrudAsyncTask asyncTask = (FtOpnamehServiceRest.FtOpnamehCrudAsyncTask) new FtOpnamehServiceRest.FtOpnamehCrudAsyncTask(apiAuthenticationClient, id, true);
        FtOpnameh ftOpnameh = null;
        try {
            ftOpnameh = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (ftOpnameh==null) ftOpnameh =new FtOpnameh();
        return ftOpnameh;
    }
    
    public List<FtOpnameh> getAllFtOpnameh() {
        FtOpnamehServiceRest.FtOpnamehAllAsyncTask asyncTask = (FtOpnamehServiceRest.FtOpnamehAllAsyncTask) new FtOpnamehServiceRest.FtOpnamehAllAsyncTask(apiAuthenticationClient);
        List<FtOpnameh> listFtOpnameh = new ArrayList<>();
        try {
            listFtOpnameh = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtOpnameh;

    }
    public List<FtOpnameh> getAllFtOpnamehByDivision(Long divisionBean) {
        FtOpnamehServiceRest.FtOpnamehAllAsyncTask asyncTask = (FtOpnamehServiceRest.FtOpnamehAllAsyncTask) new FtOpnamehServiceRest.FtOpnamehAllAsyncTask(apiAuthenticationClient, divisionBean);
        List<FtOpnameh> listFtOpnameh = new ArrayList<>();
        try {
            listFtOpnameh = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtOpnameh;

    }
    

    public void createFtOpnameh(FtOpnameh ftOpnameh) {
//        new FtOpnamehCreateAsyncTask(apiAuthenticationClient, ftOpnameh).execute();
        new FtOpnamehServiceRest.FtOpnamehCrudAsyncTask(apiAuthenticationClient, ftOpnameh).execute();
    }
    public void updateFtOpnameh(Long id, FtOpnameh ftOpnameh) {
//        new FtOpnamehUpdateAsyncTask(apiAuthenticationClient, id, ftOpnameh).execute();
        new FtOpnamehServiceRest.FtOpnamehCrudAsyncTask(apiAuthenticationClient, id, ftOpnameh).execute();
    }
    public void deleteFtOpnameh(Long id) {
//        new FtOpnamehDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FtOpnamehServiceRest.FtOpnamehCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FtOpnamehCrudAsyncTask extends AsyncTask<Void, Void, FtOpnameh> {

        String operation = "";
        FtOpnameh newFtOpnameh = null;
        long id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FtOpnamehCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FtOpnamehCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FtOpnameh newFtOpnameh){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtOpnameh = newFtOpnameh;
            operation = "ADD_NEW";
        }
        private FtOpnamehCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_update, FtOpnameh updateFtOpnameh){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtOpnameh = updateFtOpnameh;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FtOpnamehCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected FtOpnameh doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FtOpnameh> response = restTemplate.exchange(url, HttpMethod.POST, FtOpnameh.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFtOpnameh, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FtOpnameh> response = restTemplate.postForEntity(url, httpEntity,  FtOpnameh.class);
                ResponseEntity<FtOpnameh> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFtOpnameh";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFtOpnameh, apiAuthenticationClient.getRequestHeaders()), FtOpnameh.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFtOpnamehInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFtOpnameh, apiAuthenticationClient.getRequestHeaders()), FtOpnameh.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFtOpnameh/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtOpnameh.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFtOpnamehById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtOpnameh.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FtOpnameh();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtOpnameh();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtOpnameh();
            }
        }

        @Override
        protected void onPostExecute(FtOpnameh result) {
            dismissProgressDialog();
//            if(result==null) result = new FtOpnameh();
//            displayResponseFtOpnameh(result);
        }
    }

    public class FtOpnamehAllAsyncTask extends  AsyncTask<Void, Void, List<FtOpnameh>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        long id = 0;
        List<Long> listParentId = new ArrayList<>();

        private FtOpnamehAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FtOpnamehAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FtOpnamehAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Long> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FtOpnameh> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFtOpnameh";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FtOpnameh> list = new ArrayList<>();
            try {
                ResponseEntity<FtOpnameh[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFtOpnameh";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtOpnameh[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFtOpnamehByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtOpnameh[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFtOpnameh";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FtOpnameh[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtOpnameh>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtOpnameh>();
            }
        }

        @Override
        protected void onPostExecute(List<FtOpnameh> result) {
            dismissProgressDialog();
        }
    }


}
