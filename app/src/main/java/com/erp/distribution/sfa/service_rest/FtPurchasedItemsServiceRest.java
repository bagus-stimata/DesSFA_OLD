package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FtPurchasedItems;
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

public class FtPurchasedItemsServiceRest {
    protected static final String TAG = FtPurchasedItemsServiceRest.class.getSimpleName();
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

    public FtPurchasedItemsServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FtPurchasedItems getFtPurchasedItemsById(Long id) {
        FtPurchasedItemsServiceRest.FtPurchasedItemsCrudAsyncTask asyncTask = (FtPurchasedItemsServiceRest.FtPurchasedItemsCrudAsyncTask) new FtPurchasedItemsServiceRest.FtPurchasedItemsCrudAsyncTask(apiAuthenticationClient, id, true);
        FtPurchasedItems ftPurchasedItems = null;
        try {
//            ftPurchasedItems = asyncTask.execute().get();
            ftPurchasedItems = asyncTask.execute().get(5, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
        if (ftPurchasedItems==null) ftPurchasedItems =new FtPurchasedItems();
        return ftPurchasedItems;
    }

    public List<FtPurchasedItems> getAllFtPurchasedItems() {
        FtPurchasedItemsServiceRest.FtPurchasedItemsAllAsyncTask asyncTask = (FtPurchasedItemsServiceRest.FtPurchasedItemsAllAsyncTask) new FtPurchasedItemsServiceRest.FtPurchasedItemsAllAsyncTask(apiAuthenticationClient);
        List<FtPurchasedItems> listFtPurchasedItems = new ArrayList<>();
        try {
            listFtPurchasedItems = asyncTask.execute().get(7, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
        return listFtPurchasedItems;

    }
    public List<FtPurchasedItems> getAllFtPurchasedItemsByParentId(Long parentId) {
        FtPurchasedItemsServiceRest.FtPurchasedItemsAllAsyncTask asyncTask = (FtPurchasedItemsServiceRest.FtPurchasedItemsAllAsyncTask) new FtPurchasedItemsServiceRest.FtPurchasedItemsAllAsyncTask(apiAuthenticationClient, parentId);
        List<FtPurchasedItems> listFtPurchasedItems = new ArrayList<>();
        try {
            listFtPurchasedItems = asyncTask.execute().get(7, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
        return listFtPurchasedItems;
    }
    public List<FtPurchasedItems> getAllFtPurchasedItemsByListParentId(List<Long> listParentId) {
        FtPurchasedItemsServiceRest.FtPurchasedItemsAllAsyncTask asyncTask = (FtPurchasedItemsServiceRest.FtPurchasedItemsAllAsyncTask) new FtPurchasedItemsServiceRest.FtPurchasedItemsAllAsyncTask(apiAuthenticationClient, listParentId);
        List<FtPurchasedItems> listFtPurchasedItems = new ArrayList<>();
        try {
            listFtPurchasedItems = asyncTask.execute().get(7, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
        return listFtPurchasedItems;
    }
    
    public void createFtPurchasedItems(FtPurchasedItems ftPurchasedItems) {
//        new FtPurchasedItemsCreateAsyncTask(apiAuthenticationClient, ftPurchasedItems).execute();
        new FtPurchasedItemsServiceRest.FtPurchasedItemsCrudAsyncTask(apiAuthenticationClient, ftPurchasedItems).execute();
    }
    public void updateFtPurchasedItems(Long id, FtPurchasedItems ftPurchasedItems) {
//        new FtPurchasedItemsUpdateAsyncTask(apiAuthenticationClient, id, ftPurchasedItems).execute();
        new FtPurchasedItemsServiceRest.FtPurchasedItemsCrudAsyncTask(apiAuthenticationClient, id, ftPurchasedItems).execute();
    }
    public void deleteFtPurchasedItems(Long id) {
//        new FtPurchasedItemsDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FtPurchasedItemsServiceRest.FtPurchasedItemsCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FtPurchasedItemsCrudAsyncTask extends AsyncTask<Void, Void, FtPurchasedItems> {

        String operation = "";
        FtPurchasedItems newFtPurchasedItems = null;
        long id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FtPurchasedItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FtPurchasedItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FtPurchasedItems newFtPurchasedItems){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtPurchasedItems = newFtPurchasedItems;
            operation = "ADD_NEW";
        }
        private FtPurchasedItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_update, FtPurchasedItems updateFtPurchasedItems){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtPurchasedItems = updateFtPurchasedItems;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FtPurchasedItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FtPurchasedItems doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FtPurchasedItems> response = restTemplate.exchange(url, HttpMethod.POST, FtPurchasedItems.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFtPurchasedItems, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FtPurchasedItems> response = restTemplate.postForEntity(url, httpEntity,  FtPurchasedItems.class);
                ResponseEntity<FtPurchasedItems> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFtPurchasedItems";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFtPurchasedItems, apiAuthenticationClient.getRequestHeaders()), FtPurchasedItems.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFtPurchasedItemsInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFtPurchasedItems, apiAuthenticationClient.getRequestHeaders()), FtPurchasedItems.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFtPurchasedItems/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPurchasedItems.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFtPurchasedItemsById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPurchasedItems.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FtPurchasedItems();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtPurchasedItems();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtPurchasedItems();
            }
        }

        @Override
        protected void onPostExecute(FtPurchasedItems result) {
            dismissProgressDialog();
//            if(result==null) result = new FtPurchasedItems();
//            displayResponseFtPurchasedItems(result);
        }
    }

    public class FtPurchasedItemsAllAsyncTask extends  AsyncTask<Void, Void, List<FtPurchasedItems>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        long id = 0;
        List<Long> listParentId = new ArrayList<>();

        private FtPurchasedItemsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FtPurchasedItemsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FtPurchasedItemsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Long> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FtPurchasedItems> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFtPurchasedItems";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FtPurchasedItems> list = new ArrayList<>();
            try {
                ResponseEntity<FtPurchasedItems[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFtPurchasedItems";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPurchasedItems[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFtPurchasedItemsByParentId/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPurchasedItems[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFtPurchasedItemsByListParentId";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FtPurchasedItems[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtPurchasedItems>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtPurchasedItems>();
            }
        }

        @Override
        protected void onPostExecute(List<FtPurchasedItems> result) {
            dismissProgressDialog();
        }
    }


}
