package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FParamDiskonItemVendor;
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

public class FParamDiskonItemVendorServiceRest {
    protected static final String TAG = FParamDiskonItemVendorServiceRest.class.getSimpleName();
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

    public FParamDiskonItemVendorServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FParamDiskonItemVendor getFParamDiskonItemVendorById(int id) {
        FParamDiskonItemVendorServiceRest.FParamDiskonItemVendorCrudAsyncTask asyncTask = (FParamDiskonItemVendorServiceRest.FParamDiskonItemVendorCrudAsyncTask) new FParamDiskonItemVendorServiceRest.FParamDiskonItemVendorCrudAsyncTask(apiAuthenticationClient, id, true);
        FParamDiskonItemVendor fParamDiskonItemVendor = null;
        try {
            fParamDiskonItemVendor = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (fParamDiskonItemVendor==null) fParamDiskonItemVendor =new FParamDiskonItemVendor();
        return fParamDiskonItemVendor;
    }

    public List<FParamDiskonItemVendor> getAllFParamDiskonItemVendor() {
        FParamDiskonItemVendorServiceRest.FParamDiskonItemVendorAllAsyncTask asyncTask = (FParamDiskonItemVendorServiceRest.FParamDiskonItemVendorAllAsyncTask) new FParamDiskonItemVendorServiceRest.FParamDiskonItemVendorAllAsyncTask(apiAuthenticationClient);
        List<FParamDiskonItemVendor> listFParamDiskonItemVendor = new ArrayList<>();
        try {
            listFParamDiskonItemVendor = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFParamDiskonItemVendor;

    }
    public List<FParamDiskonItemVendor> getAllFParamDiskonItemVendorByDivision(int divisionBean) {
        FParamDiskonItemVendorServiceRest.FParamDiskonItemVendorAllAsyncTask asyncTask = (FParamDiskonItemVendorServiceRest.FParamDiskonItemVendorAllAsyncTask) new FParamDiskonItemVendorServiceRest.FParamDiskonItemVendorAllAsyncTask(apiAuthenticationClient, divisionBean);
        List<FParamDiskonItemVendor> listFParamDiskonItemVendor = new ArrayList<>();
        try {
            listFParamDiskonItemVendor = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFParamDiskonItemVendor;

    }


    public void createFParamDiskonItemVendor(FParamDiskonItemVendor fParamDiskonItemVendor) {
//        new FParamDiskonItemVendorCreateAsyncTask(apiAuthenticationClient, fParamDiskonItemVendor).execute();
        new FParamDiskonItemVendorServiceRest.FParamDiskonItemVendorCrudAsyncTask(apiAuthenticationClient, fParamDiskonItemVendor).execute();
    }
    public void updateFParamDiskonItemVendor(Integer id, FParamDiskonItemVendor fParamDiskonItemVendor) {
//        new FParamDiskonItemVendorUpdateAsyncTask(apiAuthenticationClient, id, fParamDiskonItemVendor).execute();
        new FParamDiskonItemVendorServiceRest.FParamDiskonItemVendorCrudAsyncTask(apiAuthenticationClient, id, fParamDiskonItemVendor).execute();
    }
    public void deleteFParamDiskonItemVendor(Integer id) {
//        new FParamDiskonItemVendorDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FParamDiskonItemVendorServiceRest.FParamDiskonItemVendorCrudAsyncTask(apiAuthenticationClient, id).execute();
    }





    public class FParamDiskonItemVendorCrudAsyncTask extends AsyncTask<Void, Void, FParamDiskonItemVendor> {

        String operation = "";
        FParamDiskonItemVendor newFParamDiskonItemVendor = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FParamDiskonItemVendorCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FParamDiskonItemVendorCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FParamDiskonItemVendor newFParamDiskonItemVendor){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFParamDiskonItemVendor = newFParamDiskonItemVendor;
            operation = "ADD_NEW";
        }
        private FParamDiskonItemVendorCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FParamDiskonItemVendor updateFParamDiskonItemVendor){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFParamDiskonItemVendor = updateFParamDiskonItemVendor;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FParamDiskonItemVendorCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FParamDiskonItemVendor doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FParamDiskonItemVendor> response = restTemplate.exchange(url, HttpMethod.POST, FParamDiskonItemVendor.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFParamDiskonItemVendor, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FParamDiskonItemVendor> response = restTemplate.postForEntity(url, httpEntity,  FParamDiskonItemVendor.class);
                ResponseEntity<FParamDiskonItemVendor> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFParamDiskonItemVendor";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFParamDiskonItemVendor, apiAuthenticationClient.getRequestHeaders()), FParamDiskonItemVendor.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFParamDiskonItemVendorInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFParamDiskonItemVendor, apiAuthenticationClient.getRequestHeaders()), FParamDiskonItemVendor.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFParamDiskonItemVendor/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FParamDiskonItemVendor.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFParamDiskonItemVendorById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FParamDiskonItemVendor.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FParamDiskonItemVendor();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FParamDiskonItemVendor();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FParamDiskonItemVendor();
            }
        }

        @Override
        protected void onPostExecute(FParamDiskonItemVendor result) {
            dismissProgressDialog();
//            if(result==null) result = new FParamDiskonItemVendor();
//            displayResponseFParamDiskonItemVendor(result);
        }
    }

    public class FParamDiskonItemVendorAllAsyncTask extends  AsyncTask<Void, Void, List<FParamDiskonItemVendor>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FParamDiskonItemVendorAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FParamDiskonItemVendorAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FParamDiskonItemVendorAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FParamDiskonItemVendor> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFParamDiskonItemVendor";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FParamDiskonItemVendor> list = new ArrayList<>();
            try {
                ResponseEntity<FParamDiskonItemVendor[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFParamDiskonItemVendor";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FParamDiskonItemVendor[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFParamDiskonItemVendorByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FParamDiskonItemVendor[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFParamDiskonItemVendor";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FParamDiskonItemVendor[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FParamDiskonItemVendor>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FParamDiskonItemVendor>();
            }
        }

        @Override
        protected void onPostExecute(List<FParamDiskonItemVendor> result) {
            dismissProgressDialog();
        }
    }


}
