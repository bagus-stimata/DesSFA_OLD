package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FtStockTransferdItems;
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

public class FtStockTransferdItemsServiceRest {
    protected static final String TAG = FtStockTransferdItemsServiceRest.class.getSimpleName();
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

    public FtStockTransferdItemsServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FtStockTransferdItems getFtStockTransferdItemsById(Long id) {
        FtStockTransferdItemsServiceRest.FtStockTransferdItemsCrudAsyncTask asyncTask = (FtStockTransferdItemsServiceRest.FtStockTransferdItemsCrudAsyncTask) new FtStockTransferdItemsServiceRest.FtStockTransferdItemsCrudAsyncTask(apiAuthenticationClient, id, true);
        FtStockTransferdItems ftStockTransferdItems = null;
        try {
            ftStockTransferdItems = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (ftStockTransferdItems==null) ftStockTransferdItems =new FtStockTransferdItems();
        return ftStockTransferdItems;
    }

    public List<FtStockTransferdItems> getAllFtStockTransferdItems() {
        FtStockTransferdItemsServiceRest.FtStockTransferdItemsAllAsyncTask asyncTask = (FtStockTransferdItemsServiceRest.FtStockTransferdItemsAllAsyncTask) new FtStockTransferdItemsServiceRest.FtStockTransferdItemsAllAsyncTask(apiAuthenticationClient);
        List<FtStockTransferdItems> listFtStockTransferdItems = new ArrayList<>();
        try {
            listFtStockTransferdItems = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtStockTransferdItems;

    }
    public List<FtStockTransferdItems> getAllFtStockTransferdItemsByParentId(Long parentId) {
        FtStockTransferdItemsServiceRest.FtStockTransferdItemsAllAsyncTask asyncTask = (FtStockTransferdItemsServiceRest.FtStockTransferdItemsAllAsyncTask) new FtStockTransferdItemsServiceRest.FtStockTransferdItemsAllAsyncTask(apiAuthenticationClient, parentId);
        List<FtStockTransferdItems> listFtStockTransferdItems = new ArrayList<>();
        try {
            listFtStockTransferdItems = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtStockTransferdItems;
    }
    public List<FtStockTransferdItems> getAllFtStockTransferdItemsByListParentId(List<Long> listParentId) {
        FtStockTransferdItemsServiceRest.FtStockTransferdItemsAllAsyncTask asyncTask = (FtStockTransferdItemsServiceRest.FtStockTransferdItemsAllAsyncTask) new FtStockTransferdItemsServiceRest.FtStockTransferdItemsAllAsyncTask(apiAuthenticationClient, listParentId);
        List<FtStockTransferdItems> listFtStockTransferdItems = new ArrayList<>();
        try {
            listFtStockTransferdItems = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtStockTransferdItems;
    }


    public void createFtStockTransferdItems(FtStockTransferdItems ftStockTransferdItems) {
//        new FtStockTransferdItemsCreateAsyncTask(apiAuthenticationClient, ftStockTransferdItems).execute();
        new FtStockTransferdItemsServiceRest.FtStockTransferdItemsCrudAsyncTask(apiAuthenticationClient, ftStockTransferdItems).execute();
    }
    public void updateFtStockTransferdItems(Long id, FtStockTransferdItems ftStockTransferdItems) {
//        new FtStockTransferdItemsUpdateAsyncTask(apiAuthenticationClient, id, ftStockTransferdItems).execute();
        new FtStockTransferdItemsServiceRest.FtStockTransferdItemsCrudAsyncTask(apiAuthenticationClient, id, ftStockTransferdItems).execute();
    }
    public void deleteFtStockTransferdItems(Long id) {
//        new FtStockTransferdItemsDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FtStockTransferdItemsServiceRest.FtStockTransferdItemsCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FtStockTransferdItemsCrudAsyncTask extends AsyncTask<Void, Void, FtStockTransferdItems> {

        String operation = "";
        FtStockTransferdItems newFtStockTransferdItems = null;
        long id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FtStockTransferdItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FtStockTransferdItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FtStockTransferdItems newFtStockTransferdItems){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtStockTransferdItems = newFtStockTransferdItems;
            operation = "ADD_NEW";
        }
        private FtStockTransferdItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_update, FtStockTransferdItems updateFtStockTransferdItems){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtStockTransferdItems = updateFtStockTransferdItems;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FtStockTransferdItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected FtStockTransferdItems doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FtStockTransferdItems> response = restTemplate.exchange(url, HttpMethod.POST, FtStockTransferdItems.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFtStockTransferdItems, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FtStockTransferdItems> response = restTemplate.postForEntity(url, httpEntity,  FtStockTransferdItems.class);
                ResponseEntity<FtStockTransferdItems> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFtStockTransferdItems";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFtStockTransferdItems, apiAuthenticationClient.getRequestHeaders()), FtStockTransferdItems.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFtStockTransferdItemsInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFtStockTransferdItems, apiAuthenticationClient.getRequestHeaders()), FtStockTransferdItems.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFtStockTransferdItems/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtStockTransferdItems.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFtStockTransferdItemsById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtStockTransferdItems.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FtStockTransferdItems();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtStockTransferdItems();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtStockTransferdItems();
            }
        }

        @Override
        protected void onPostExecute(FtStockTransferdItems result) {
            dismissProgressDialog();
//            if(result==null) result = new FtStockTransferdItems();
//            displayResponseFtStockTransferdItems(result);
        }
    }

    public class FtStockTransferdItemsAllAsyncTask extends  AsyncTask<Void, Void, List<FtStockTransferdItems>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        long id = 0;
        List<Long> listParentId = new ArrayList<>();

        private FtStockTransferdItemsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FtStockTransferdItemsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FtStockTransferdItemsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Long> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FtStockTransferdItems> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFtStockTransferdItems";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FtStockTransferdItems> list = new ArrayList<>();
            try {
                ResponseEntity<FtStockTransferdItems[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFtStockTransferdItems";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtStockTransferdItems[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFtStockTransferdItemsByParentId/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtStockTransferdItems[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFtStockTransferdItemsByListParentId";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FtStockTransferdItems[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtStockTransferdItems>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtStockTransferdItems>();
            }
        }

        @Override
        protected void onPostExecute(List<FtStockTransferdItems> result) {
            dismissProgressDialog();
        }
    }


}
