package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FtPricedItems;
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

public class FtPricedItemsServiceRest {
    protected static final String TAG = FtPricedItemsServiceRest.class.getSimpleName();
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

    public FtPricedItemsServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FtPricedItems getFtPricedItemsById(Long id) {
        FtPricedItemsServiceRest.FtPricedItemsCrudAsyncTask asyncTask = (FtPricedItemsServiceRest.FtPricedItemsCrudAsyncTask) new FtPricedItemsServiceRest.FtPricedItemsCrudAsyncTask(apiAuthenticationClient, id, true);
        FtPricedItems ftPricedItems = null;
        try {
            ftPricedItems = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (ftPricedItems==null) ftPricedItems =new FtPricedItems();
        return ftPricedItems;
    }

    public List<FtPricedItems> getAllFtPricedItems() {
        FtPricedItemsServiceRest.FtPricedItemsAllAsyncTask asyncTask = (FtPricedItemsServiceRest.FtPricedItemsAllAsyncTask) new FtPricedItemsServiceRest.FtPricedItemsAllAsyncTask(apiAuthenticationClient);
        List<FtPricedItems> listFtPricedItems = new ArrayList<>();
        try {
            listFtPricedItems = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtPricedItems;

    }
    public List<FtPricedItems> getAllFtPricedItemsByParentId(Long parentId) {
        FtPricedItemsServiceRest.FtPricedItemsAllAsyncTask asyncTask = (FtPricedItemsServiceRest.FtPricedItemsAllAsyncTask) new FtPricedItemsServiceRest.FtPricedItemsAllAsyncTask(apiAuthenticationClient, parentId);
        List<FtPricedItems> listFtPricedItems = new ArrayList<>();
        try {
            listFtPricedItems = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtPricedItems;
    }
    public List<FtPricedItems> getAllFtPricedItemsByListParentId(List<Long> listParentId) {
        FtPricedItemsServiceRest.FtPricedItemsAllAsyncTask asyncTask = (FtPricedItemsServiceRest.FtPricedItemsAllAsyncTask) new FtPricedItemsServiceRest.FtPricedItemsAllAsyncTask(apiAuthenticationClient, listParentId);
        List<FtPricedItems> listFtPricedItems = new ArrayList<>();
        try {
            listFtPricedItems = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtPricedItems;
    }
   
    public void createFtPricedItems(FtPricedItems ftPricedItems) {
//        new FtPricedItemsCreateAsyncTask(apiAuthenticationClient, ftPricedItems).execute();
        new FtPricedItemsServiceRest.FtPricedItemsCrudAsyncTask(apiAuthenticationClient, ftPricedItems).execute();
    }
    public void updateFtPricedItems(Long id, FtPricedItems ftPricedItems) {
//        new FtPricedItemsUpdateAsyncTask(apiAuthenticationClient, id, ftPricedItems).execute();
        new FtPricedItemsServiceRest.FtPricedItemsCrudAsyncTask(apiAuthenticationClient, id, ftPricedItems).execute();
    }
    public void deleteFtPricedItems(Long id) {
//        new FtPricedItemsDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FtPricedItemsServiceRest.FtPricedItemsCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FtPricedItemsCrudAsyncTask extends AsyncTask<Void, Void, FtPricedItems> {

        String operation = "";
        FtPricedItems newFtPricedItems = null;
        long id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FtPricedItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FtPricedItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FtPricedItems newFtPricedItems){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtPricedItems = newFtPricedItems;
            operation = "ADD_NEW";
        }
        private FtPricedItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_update, FtPricedItems updateFtPricedItems){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtPricedItems = updateFtPricedItems;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FtPricedItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FtPricedItems doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FtPricedItems> response = restTemplate.exchange(url, HttpMethod.POST, FtPricedItems.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFtPricedItems, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FtPricedItems> response = restTemplate.postForEntity(url, httpEntity,  FtPricedItems.class);
                ResponseEntity<FtPricedItems> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFtPricedItems";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFtPricedItems, apiAuthenticationClient.getRequestHeaders()), FtPricedItems.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFtPricedItemsInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFtPricedItems, apiAuthenticationClient.getRequestHeaders()), FtPricedItems.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFtPricedItems/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPricedItems.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFtPricedItemsById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPricedItems.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FtPricedItems();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtPricedItems();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtPricedItems();
            }
        }

        @Override
        protected void onPostExecute(FtPricedItems result) {
            dismissProgressDialog();
//            if(result==null) result = new FtPricedItems();
//            displayResponseFtPricedItems(result);
        }
    }

    public class FtPricedItemsAllAsyncTask extends  AsyncTask<Void, Void, List<FtPricedItems>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        long id = 0;
        List<Long> listParentId = new ArrayList<>();

        private FtPricedItemsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FtPricedItemsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FtPricedItemsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Long> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FtPricedItems> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFtPricedItems";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FtPricedItems> list = new ArrayList<>();
            try {
                ResponseEntity<FtPricedItems[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFtPricedItems";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPricedItems[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFtPricedItemsByParentId/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPricedItems[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFtPricedItemsByListParentId";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FtPricedItems[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtPricedItems>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtPricedItems>();
            }
        }

        @Override
        protected void onPostExecute(List<FtPricedItems> result) {
            dismissProgressDialog();
        }
    }


}
