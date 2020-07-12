package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FWarehouse;
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

public class FWarehouseServiceRest {
    protected static final String TAG = FWarehouseServiceRest.class.getSimpleName();
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

    public FWarehouseServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FWarehouse getFWarehouseById(int id) {
        FWarehouseServiceRest.FWarehouseCrudAsyncTask asyncTask = (FWarehouseServiceRest.FWarehouseCrudAsyncTask) new FWarehouseServiceRest.FWarehouseCrudAsyncTask(apiAuthenticationClient, id, true);
        FWarehouse fWarehouse = null;
        try {
            fWarehouse = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (fWarehouse==null) fWarehouse =new FWarehouse();
        return fWarehouse;
    }

    public List<FWarehouse> getAllFWarehouse() {
        FWarehouseServiceRest.FWarehouseAllAsyncTask asyncTask = (FWarehouseServiceRest.FWarehouseAllAsyncTask) new FWarehouseServiceRest.FWarehouseAllAsyncTask(apiAuthenticationClient);
        List<FWarehouse> listFWarehouse = new ArrayList<>();
        try {
            listFWarehouse = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFWarehouse;

    }
    public List<FWarehouse> getAllFWarehouseByDivision(int divisionBean) {
        FWarehouseServiceRest.FWarehouseAllAsyncTask asyncTask = (FWarehouseServiceRest.FWarehouseAllAsyncTask) new FWarehouseServiceRest.FWarehouseAllAsyncTask(apiAuthenticationClient, divisionBean);
        List<FWarehouse> listFWarehouse = new ArrayList<>();
        try {
            listFWarehouse = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFWarehouse;

    }


    public void createFWarehouse(FWarehouse fWarehouse) {
//        new FWarehouseCreateAsyncTask(apiAuthenticationClient, fWarehouse).execute();
        new FWarehouseServiceRest.FWarehouseCrudAsyncTask(apiAuthenticationClient, fWarehouse).execute();
    }
    public void updateFWarehouse(Integer id, FWarehouse fWarehouse) {
//        new FWarehouseUpdateAsyncTask(apiAuthenticationClient, id, fWarehouse).execute();
        new FWarehouseServiceRest.FWarehouseCrudAsyncTask(apiAuthenticationClient, id, fWarehouse).execute();
    }
    public void deleteFWarehouse(Integer id) {
//        new FWarehouseDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FWarehouseServiceRest.FWarehouseCrudAsyncTask(apiAuthenticationClient, id).execute();
    }

    public class FWarehouseCrudAsyncTask extends AsyncTask<Void, Void, FWarehouse> {

        String operation = "";
        FWarehouse newFWarehouse = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FWarehouseCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FWarehouseCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FWarehouse newFWarehouse){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFWarehouse = newFWarehouse;
            operation = "ADD_NEW";
        }
        private FWarehouseCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FWarehouse updateFWarehouse){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFWarehouse = updateFWarehouse;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FWarehouseCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected FWarehouse doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FWarehouse> response = restTemplate.exchange(url, HttpMethod.POST, FWarehouse.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFWarehouse, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FWarehouse> response = restTemplate.postForEntity(url, httpEntity,  FWarehouse.class);
                ResponseEntity<FWarehouse> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFWarehouse";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFWarehouse, apiAuthenticationClient.getRequestHeaders()), FWarehouse.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFWarehouseInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFWarehouse, apiAuthenticationClient.getRequestHeaders()), FWarehouse.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFWarehouse/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FWarehouse.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFWarehouseById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FWarehouse.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FWarehouse();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FWarehouse();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FWarehouse();
            }
        }

        @Override
        protected void onPostExecute(FWarehouse result) {
            dismissProgressDialog();
//            if(result==null) result = new FWarehouse();
//            displayResponseFWarehouse(result);
        }
    }

    public class FWarehouseAllAsyncTask extends  AsyncTask<Void, Void, List<FWarehouse>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FWarehouseAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FWarehouseAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FWarehouseAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FWarehouse> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFWarehouse";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FWarehouse> list = new ArrayList<>();
            try {
                ResponseEntity<FWarehouse[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFWarehouse";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FWarehouse[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFWarehouseByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FWarehouse[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFWarehouse";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FWarehouse[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FWarehouse>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FWarehouse>();
            }
        }

        @Override
        protected void onPostExecute(List<FWarehouse> result) {
            dismissProgressDialog();
        }
    }


}
