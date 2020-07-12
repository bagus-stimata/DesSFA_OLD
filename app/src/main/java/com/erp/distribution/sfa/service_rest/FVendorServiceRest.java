package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FVendor;
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

public class FVendorServiceRest {
    protected static final String TAG = FVendorServiceRest.class.getSimpleName();
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

    public FVendorServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FVendor getFVendorById(int id) {
        FVendorServiceRest.FVendorCrudAsyncTask asyncTask = (FVendorServiceRest.FVendorCrudAsyncTask) new FVendorServiceRest.FVendorCrudAsyncTask(apiAuthenticationClient, id, true);
        FVendor fVendor = null;
        try {
            fVendor = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (fVendor==null) fVendor =new FVendor();
        return fVendor;
    }

    public List<FVendor> getAllFVendor() {
        FVendorServiceRest.FVendorAllAsyncTask asyncTask = (FVendorServiceRest.FVendorAllAsyncTask) new FVendorServiceRest.FVendorAllAsyncTask(apiAuthenticationClient);
        List<FVendor> listFVendor = new ArrayList<>();
        try {
            listFVendor = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFVendor;

    }
    public List<FVendor> getAllFVendorByDivision(int divisionBean) {
        FVendorServiceRest.FVendorAllAsyncTask asyncTask = (FVendorServiceRest.FVendorAllAsyncTask) new FVendorServiceRest.FVendorAllAsyncTask(apiAuthenticationClient, divisionBean);
        List<FVendor> listFVendor = new ArrayList<>();
        try {
            listFVendor = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFVendor;

    }


    public void createFVendor(FVendor fVendor) {
//        new FVendorCreateAsyncTask(apiAuthenticationClient, fVendor).execute();
        new FVendorServiceRest.FVendorCrudAsyncTask(apiAuthenticationClient, fVendor).execute();
    }
    public void updateFVendor(Integer id, FVendor fVendor) {
//        new FVendorUpdateAsyncTask(apiAuthenticationClient, id, fVendor).execute();
        new FVendorServiceRest.FVendorCrudAsyncTask(apiAuthenticationClient, id, fVendor).execute();
    }
    public void deleteFVendor(Integer id) {
//        new FVendorDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FVendorServiceRest.FVendorCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FVendorCrudAsyncTask extends AsyncTask<Void, Void, FVendor> {

        String operation = "";
        FVendor newFVendor = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FVendorCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FVendorCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FVendor newFVendor){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFVendor = newFVendor;
            operation = "ADD_NEW";
        }
        private FVendorCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FVendor updateFVendor){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFVendor = updateFVendor;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FVendorCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected FVendor doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FVendor> response = restTemplate.exchange(url, HttpMethod.POST, FVendor.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFVendor, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FVendor> response = restTemplate.postForEntity(url, httpEntity,  FVendor.class);
                ResponseEntity<FVendor> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFVendor";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFVendor, apiAuthenticationClient.getRequestHeaders()), FVendor.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFVendorInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFVendor, apiAuthenticationClient.getRequestHeaders()), FVendor.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFVendor/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FVendor.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFVendorById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FVendor.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FVendor();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FVendor();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FVendor();
            }
        }

        @Override
        protected void onPostExecute(FVendor result) {
            dismissProgressDialog();
//            if(result==null) result = new FVendor();
//            displayResponseFVendor(result);
        }
    }

    public class FVendorAllAsyncTask extends  AsyncTask<Void, Void, List<FVendor>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FVendorAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FVendorAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FVendorAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FVendor> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFVendor";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FVendor> list = new ArrayList<>();
            try {
                ResponseEntity<FVendor[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFVendor";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FVendor[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFVendorByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FVendor[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFVendor";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FVendor[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FVendor>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FVendor>();
            }
        }

        @Override
        protected void onPostExecute(List<FVendor> result) {
            dismissProgressDialog();
        }
    }


}
