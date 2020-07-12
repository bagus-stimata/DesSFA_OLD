package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FParamDiskonItem;
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

public class FParamDiskonItemServiceRest {
    protected static final String TAG = FParamDiskonItemServiceRest.class.getSimpleName();
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

    public FParamDiskonItemServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FParamDiskonItem getFParamDiskonItemById(int id) {
        FParamDiskonItemServiceRest.FParamDiskonItemCrudAsyncTask asyncTask = (FParamDiskonItemServiceRest.FParamDiskonItemCrudAsyncTask) new FParamDiskonItemServiceRest.FParamDiskonItemCrudAsyncTask(apiAuthenticationClient, id, true);
        FParamDiskonItem fParamDiskonItem = null;
        try {
            fParamDiskonItem = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (fParamDiskonItem==null) fParamDiskonItem =new FParamDiskonItem();
        return fParamDiskonItem;
    }

    public List<FParamDiskonItem> getAllFParamDiskonItem() {
        FParamDiskonItemServiceRest.FParamDiskonItemAllAsyncTask asyncTask = (FParamDiskonItemServiceRest.FParamDiskonItemAllAsyncTask) new FParamDiskonItemServiceRest.FParamDiskonItemAllAsyncTask(apiAuthenticationClient);
        List<FParamDiskonItem> listFParamDiskonItem = new ArrayList<>();
        try {
            listFParamDiskonItem = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFParamDiskonItem;

    }
    public List<FParamDiskonItem> getAllFParamDiskonItemByDivision(int divisionBean) {
        FParamDiskonItemServiceRest.FParamDiskonItemAllAsyncTask asyncTask = (FParamDiskonItemServiceRest.FParamDiskonItemAllAsyncTask) new FParamDiskonItemServiceRest.FParamDiskonItemAllAsyncTask(apiAuthenticationClient, divisionBean);
        List<FParamDiskonItem> listFParamDiskonItem = new ArrayList<>();
        try {
            listFParamDiskonItem = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFParamDiskonItem;

    }


    public void createFParamDiskonItem(FParamDiskonItem fParamDiskonItem) {
//        new FParamDiskonItemCreateAsyncTask(apiAuthenticationClient, fParamDiskonItem).execute();
        new FParamDiskonItemServiceRest.FParamDiskonItemCrudAsyncTask(apiAuthenticationClient, fParamDiskonItem).execute();
    }
    public void updateFParamDiskonItem(Integer id, FParamDiskonItem fParamDiskonItem) {
//        new FParamDiskonItemUpdateAsyncTask(apiAuthenticationClient, id, fParamDiskonItem).execute();
        new FParamDiskonItemServiceRest.FParamDiskonItemCrudAsyncTask(apiAuthenticationClient, id, fParamDiskonItem).execute();
    }
    public void deleteFParamDiskonItem(Integer id) {
//        new FParamDiskonItemDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FParamDiskonItemServiceRest.FParamDiskonItemCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FParamDiskonItemCrudAsyncTask extends AsyncTask<Void, Void, FParamDiskonItem> {

        String operation = "";
        FParamDiskonItem newFParamDiskonItem = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FParamDiskonItemCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FParamDiskonItemCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FParamDiskonItem newFParamDiskonItem){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFParamDiskonItem = newFParamDiskonItem;
            operation = "ADD_NEW";
        }
        private FParamDiskonItemCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FParamDiskonItem updateFParamDiskonItem){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFParamDiskonItem = updateFParamDiskonItem;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FParamDiskonItemCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FParamDiskonItem doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FParamDiskonItem> response = restTemplate.exchange(url, HttpMethod.POST, FParamDiskonItem.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFParamDiskonItem, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FParamDiskonItem> response = restTemplate.postForEntity(url, httpEntity,  FParamDiskonItem.class);
                ResponseEntity<FParamDiskonItem> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFParamDiskonItem";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFParamDiskonItem, apiAuthenticationClient.getRequestHeaders()), FParamDiskonItem.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFParamDiskonItemInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFParamDiskonItem, apiAuthenticationClient.getRequestHeaders()), FParamDiskonItem.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFParamDiskonItem/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FParamDiskonItem.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFParamDiskonItemById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FParamDiskonItem.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FParamDiskonItem();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FParamDiskonItem();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FParamDiskonItem();
            }
        }

        @Override
        protected void onPostExecute(FParamDiskonItem result) {
            dismissProgressDialog();
//            if(result==null) result = new FParamDiskonItem();
//            displayResponseFParamDiskonItem(result);
        }
    }

    public class FParamDiskonItemAllAsyncTask extends  AsyncTask<Void, Void, List<FParamDiskonItem>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FParamDiskonItemAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FParamDiskonItemAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FParamDiskonItemAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FParamDiskonItem> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFParamDiskonItem";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FParamDiskonItem> list = new ArrayList<>();
            try {
                ResponseEntity<FParamDiskonItem[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFParamDiskonItem";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FParamDiskonItem[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFParamDiskonItemByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FParamDiskonItem[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFParamDiskonItem";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FParamDiskonItem[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FParamDiskonItem>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FParamDiskonItem>();
            }
        }

        @Override
        protected void onPostExecute(List<FParamDiskonItem> result) {
            dismissProgressDialog();
        }
    }


}
