package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FtOpnamedItems;
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

public class FtOpnamedItemsServiceRest {
    protected static final String TAG = FtOpnamedItemsServiceRest.class.getSimpleName();
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

    public FtOpnamedItemsServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FtOpnamedItems getFtOpnamedItemsById(Long id) {
        FtOpnamedItemsServiceRest.FtOpnamedItemsCrudAsyncTask asyncTask = (FtOpnamedItemsServiceRest.FtOpnamedItemsCrudAsyncTask) new FtOpnamedItemsServiceRest.FtOpnamedItemsCrudAsyncTask(apiAuthenticationClient, id, true);
        FtOpnamedItems ftOpnamedItems = null;
        try {
            ftOpnamedItems = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (ftOpnamedItems==null) ftOpnamedItems =new FtOpnamedItems();
        return ftOpnamedItems;
    }

    public List<FtOpnamedItems> getAllFtOpnamedItems() {
        FtOpnamedItemsServiceRest.FtOpnamedItemsAllAsyncTask asyncTask = (FtOpnamedItemsServiceRest.FtOpnamedItemsAllAsyncTask) new FtOpnamedItemsServiceRest.FtOpnamedItemsAllAsyncTask(apiAuthenticationClient);
        List<FtOpnamedItems> listFtOpnamedItems = new ArrayList<>();
        try {
            listFtOpnamedItems = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtOpnamedItems;

    }
    public List<FtOpnamedItems> getAllFtOpnamedItemsByParentId(Long parentId) {
        FtOpnamedItemsServiceRest.FtOpnamedItemsAllAsyncTask asyncTask = (FtOpnamedItemsServiceRest.FtOpnamedItemsAllAsyncTask) new FtOpnamedItemsServiceRest.FtOpnamedItemsAllAsyncTask(apiAuthenticationClient, parentId);
        List<FtOpnamedItems> listFtOpnamedItems = new ArrayList<>();
        try {
            listFtOpnamedItems = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtOpnamedItems;
    }
    public List<FtOpnamedItems> getAllFtOpnamedItemsByListParentId(List<Long> listParentId) {
        FtOpnamedItemsServiceRest.FtOpnamedItemsAllAsyncTask asyncTask = (FtOpnamedItemsServiceRest.FtOpnamedItemsAllAsyncTask) new FtOpnamedItemsServiceRest.FtOpnamedItemsAllAsyncTask(apiAuthenticationClient, listParentId);
        List<FtOpnamedItems> listFtOpnamedItems = new ArrayList<>();
        try {
            listFtOpnamedItems = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtOpnamedItems;
    }
    
    public void createFtOpnamedItems(FtOpnamedItems ftOpnamedItems) {
//        new FtOpnamedItemsCreateAsyncTask(apiAuthenticationClient, ftOpnamedItems).execute();
        new FtOpnamedItemsServiceRest.FtOpnamedItemsCrudAsyncTask(apiAuthenticationClient, ftOpnamedItems).execute();
    }
    public void updateFtOpnamedItems(Long id, FtOpnamedItems ftOpnamedItems) {
//        new FtOpnamedItemsUpdateAsyncTask(apiAuthenticationClient, id, ftOpnamedItems).execute();
        new FtOpnamedItemsServiceRest.FtOpnamedItemsCrudAsyncTask(apiAuthenticationClient, id, ftOpnamedItems).execute();
    }
    public void deleteFtOpnamedItems(Long id) {
//        new FtOpnamedItemsDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FtOpnamedItemsServiceRest.FtOpnamedItemsCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FtOpnamedItemsCrudAsyncTask extends AsyncTask<Void, Void, FtOpnamedItems> {

        String operation = "";
        FtOpnamedItems newFtOpnamedItems = null;
        long id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FtOpnamedItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FtOpnamedItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FtOpnamedItems newFtOpnamedItems){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtOpnamedItems = newFtOpnamedItems;
            operation = "ADD_NEW";
        }
        private FtOpnamedItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_update, FtOpnamedItems updateFtOpnamedItems){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtOpnamedItems = updateFtOpnamedItems;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FtOpnamedItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FtOpnamedItems doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FtOpnamedItems> response = restTemplate.exchange(url, HttpMethod.POST, FtOpnamedItems.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFtOpnamedItems, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FtOpnamedItems> response = restTemplate.postForEntity(url, httpEntity,  FtOpnamedItems.class);
                ResponseEntity<FtOpnamedItems> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFtOpnamedItems";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFtOpnamedItems, apiAuthenticationClient.getRequestHeaders()), FtOpnamedItems.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFtOpnamedItemsInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFtOpnamedItems, apiAuthenticationClient.getRequestHeaders()), FtOpnamedItems.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFtOpnamedItems/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtOpnamedItems.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFtOpnamedItemsById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtOpnamedItems.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FtOpnamedItems();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtOpnamedItems();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtOpnamedItems();
            }
        }

        @Override
        protected void onPostExecute(FtOpnamedItems result) {
            dismissProgressDialog();
//            if(result==null) result = new FtOpnamedItems();
//            displayResponseFtOpnamedItems(result);
        }
    }

    public class FtOpnamedItemsAllAsyncTask extends  AsyncTask<Void, Void, List<FtOpnamedItems>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        long id = 0;
        List<Long> listParentId = new ArrayList<>();

        private FtOpnamedItemsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FtOpnamedItemsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FtOpnamedItemsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Long> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FtOpnamedItems> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFtOpnamedItems";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FtOpnamedItems> list = new ArrayList<>();
            try {
                ResponseEntity<FtOpnamedItems[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFtOpnamedItems";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtOpnamedItems[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFtOpnamedItemsByParentId/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtOpnamedItems[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFtOpnamedItemsByListParentId";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FtOpnamedItems[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtOpnamedItems>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtOpnamedItems>();
            }
        }

        @Override
        protected void onPostExecute(List<FtOpnamedItems> result) {
            dismissProgressDialog();
        }
    }


}
