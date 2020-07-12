package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FtPriceh;
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

public class FtPricehServiceRest {
    protected static final String TAG = FtPricehServiceRest.class.getSimpleName();
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

    public FtPricehServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FtPriceh getFtPricehById(Long id) {
        FtPricehServiceRest.FtPricehCrudAsyncTask asyncTask = (FtPricehServiceRest.FtPricehCrudAsyncTask) new FtPricehServiceRest.FtPricehCrudAsyncTask(apiAuthenticationClient, id, true);
        FtPriceh ftPriceh = null;
        try {
            ftPriceh = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (ftPriceh==null) ftPriceh =new FtPriceh();
        return ftPriceh;
    }
    
    public List<FtPriceh> getAllFtPriceh() {
        FtPricehServiceRest.FtPricehAllAsyncTask asyncTask = (FtPricehServiceRest.FtPricehAllAsyncTask) new FtPricehServiceRest.FtPricehAllAsyncTask(apiAuthenticationClient);
        List<FtPriceh> listFtPriceh = new ArrayList<>();
        try {
            listFtPriceh = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFtPriceh;

    }
    public List<FtPriceh> getAllFtPricehByDivision(Long divisionBean) {
        FtPricehServiceRest.FtPricehAllAsyncTask asyncTask = (FtPricehServiceRest.FtPricehAllAsyncTask) new FtPricehServiceRest.FtPricehAllAsyncTask(apiAuthenticationClient, divisionBean);
        List<FtPriceh> listFtPriceh = new ArrayList<>();
        try {
            listFtPriceh = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFtPriceh;

    }
    
    
    public void createFtPriceh(FtPriceh ftPriceh) {
//        new FtPricehCreateAsyncTask(apiAuthenticationClient, ftPriceh).execute();
        new FtPricehServiceRest.FtPricehCrudAsyncTask(apiAuthenticationClient, ftPriceh).execute();
    }
    public void updateFtPriceh(Long id, FtPriceh ftPriceh) {
//        new FtPricehUpdateAsyncTask(apiAuthenticationClient, id, ftPriceh).execute();
        new FtPricehServiceRest.FtPricehCrudAsyncTask(apiAuthenticationClient, id, ftPriceh).execute();
    }
    public void deleteFtPriceh(Long id) {
//        new FtPricehDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FtPricehServiceRest.FtPricehCrudAsyncTask(apiAuthenticationClient, id).execute();
    }





    public class FtPricehCrudAsyncTask extends AsyncTask<Void, Void, FtPriceh> {

        String operation = "";
        FtPriceh newFtPriceh = null;
        long id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FtPricehCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FtPricehCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FtPriceh newFtPriceh){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtPriceh = newFtPriceh;
            operation = "ADD_NEW";
        }
        private FtPricehCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_update, FtPriceh updateFtPriceh){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtPriceh = updateFtPriceh;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FtPricehCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected FtPriceh doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FtPriceh> response = restTemplate.exchange(url, HttpMethod.POST, FtPriceh.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFtPriceh, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FtPriceh> response = restTemplate.postForEntity(url, httpEntity,  FtPriceh.class);
                ResponseEntity<FtPriceh> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFtPriceh";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFtPriceh, apiAuthenticationClient.getRequestHeaders()), FtPriceh.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFtPricehInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFtPriceh, apiAuthenticationClient.getRequestHeaders()), FtPriceh.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFtPriceh/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPriceh.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFtPricehById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPriceh.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FtPriceh();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtPriceh();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtPriceh();
            }
        }

        @Override
        protected void onPostExecute(FtPriceh result) {
            dismissProgressDialog();
//            if(result==null) result = new FtPriceh();
//            displayResponseFtPriceh(result);
        }
    }

    public class FtPricehAllAsyncTask extends  AsyncTask<Void, Void, List<FtPriceh>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        long id = 0;
        List<Long> listParentId = new ArrayList<>();

        private FtPricehAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FtPricehAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FtPricehAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Long> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FtPriceh> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFtPriceh";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FtPriceh> list = new ArrayList<>();
            try {
                ResponseEntity<FtPriceh[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFtPriceh";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPriceh[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFtPricehByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPriceh[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFtPriceh";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FtPriceh[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtPriceh>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtPriceh>();
            }
        }

        @Override
        protected void onPostExecute(List<FtPriceh> result) {
            dismissProgressDialog();
        }
    }


}
