package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FtJobdItems;
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

public class FtJobdItemsServiceRest {
    protected static final String TAG = FtJobdItemsServiceRest.class.getSimpleName();
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

    public FtJobdItemsServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FtJobdItems getFtJobdItemsById(int id) {
        FtJobdItemsServiceRest.FtJobdItemsCrudAsyncTask asyncTask = (FtJobdItemsServiceRest.FtJobdItemsCrudAsyncTask) new FtJobdItemsServiceRest.FtJobdItemsCrudAsyncTask(apiAuthenticationClient, id, true);
        FtJobdItems ftJobdItems = null;
        try {
            ftJobdItems = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (ftJobdItems==null) ftJobdItems =new FtJobdItems();
        return ftJobdItems;
    }
    public List<FtJobdItems> getAllFtJobdItems() {
        FtJobdItemsServiceRest.FtJobdItemsAllAsyncTask asyncTask = (FtJobdItemsServiceRest.FtJobdItemsAllAsyncTask) new FtJobdItemsServiceRest.FtJobdItemsAllAsyncTask(apiAuthenticationClient);
        List<FtJobdItems> listFtJobdItems = new ArrayList<>();
        try {
            listFtJobdItems = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFtJobdItems;

    }
    public void createFtJobdItems(FtJobdItems ftJobdItems) {
//        new FtJobdItemsCreateAsyncTask(apiAuthenticationClient, ftJobdItems).execute();
        new FtJobdItemsServiceRest.FtJobdItemsCrudAsyncTask(apiAuthenticationClient, ftJobdItems).execute();
    }
    public void updateFtJobdItems(Integer id, FtJobdItems ftJobdItems) {
//        new FtJobdItemsUpdateAsyncTask(apiAuthenticationClient, id, ftJobdItems).execute();
        new FtJobdItemsServiceRest.FtJobdItemsCrudAsyncTask(apiAuthenticationClient, id, ftJobdItems).execute();
    }
    public void deleteFtJobdItems(Integer id) {
//        new FtJobdItemsDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FtJobdItemsServiceRest.FtJobdItemsCrudAsyncTask(apiAuthenticationClient, id).execute();
    }

    public class FtJobdItemsCrudAsyncTask extends AsyncTask<Void, Void, FtJobdItems> {

        String operation = "";
        FtJobdItems newFtJobdItems = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FtJobdItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FtJobdItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FtJobdItems newFtJobdItems){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtJobdItems = newFtJobdItems;
            operation = "ADD_NEW";
        }
        private FtJobdItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FtJobdItems updateFtJobdItems){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtJobdItems = updateFtJobdItems;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FtJobdItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        public FtJobdItems getFtJobdItemsById(int id) {
//        FtJobdItemsByIdAsyncTask asyncTask = (FtJobdItemsByIdAsyncTask) new FtJobdItemsByIdAsyncTask(apiAuthenticationClient, id);
            FtJobdItemsServiceRest.FtJobdItemsCrudAsyncTask asyncTask = (FtJobdItemsServiceRest.FtJobdItemsCrudAsyncTask) new FtJobdItemsServiceRest.FtJobdItemsCrudAsyncTask(apiAuthenticationClient, id, true);
            FtJobdItems ftJobdItems = null;
            try {
//            ftJobdItems = asyncTask.execute().get();
                ftJobdItems = asyncTask.execute().get(5, TimeUnit.SECONDS);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException | TimeoutException e) {
                e.printStackTrace();
            }
            if (ftJobdItems==null) ftJobdItems =new FtJobdItems();
            return ftJobdItems;
        }
        public List<FtJobdItems> getAllFtJobdItems() {
            FtJobdItemsServiceRest.FtJobdItemsAllAsyncTask asyncTask = (FtJobdItemsServiceRest.FtJobdItemsAllAsyncTask) new FtJobdItemsServiceRest.FtJobdItemsAllAsyncTask(apiAuthenticationClient);
            List<FtJobdItems> listFtJobdItems = new ArrayList<>();
            try {
//            ftJobdItems = asyncTask.execute().get();
                listFtJobdItems = asyncTask.execute().get(7, TimeUnit.SECONDS);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException | TimeoutException e) {
                e.printStackTrace();
            }
//        if (ftJobdItems==null) ftJobdItems =new FtJobdItems();
            return listFtJobdItems;

        }
        public void createFtJobdItems(FtJobdItems ftJobdItems) {
//        new FtJobdItemsCreateAsyncTask(apiAuthenticationClient, ftJobdItems).execute();
            new FtJobdItemsServiceRest.FtJobdItemsCrudAsyncTask(apiAuthenticationClient, ftJobdItems).execute();
        }
        public void updateFtJobdItems(Integer id, FtJobdItems ftJobdItems) {
//        new FtJobdItemsUpdateAsyncTask(apiAuthenticationClient, id, ftJobdItems).execute();
            new FtJobdItemsServiceRest.FtJobdItemsCrudAsyncTask(apiAuthenticationClient, id, ftJobdItems).execute();
        }
        public void deleteFtJobdItems(Integer id) {
//        new FtJobdItemsDeleteAsyncTask(apiAuthenticationClient, id).execute();
            new FtJobdItemsServiceRest.FtJobdItemsCrudAsyncTask(apiAuthenticationClient, id).execute();
        }


        @Override
        protected FtJobdItems doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FtJobdItems> response = restTemplate.exchange(url, HttpMethod.POST, FtJobdItems.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFtJobdItems, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FtJobdItems> response = restTemplate.postForEntity(url, httpEntity,  FtJobdItems.class);
                ResponseEntity<FtJobdItems> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFtJobdItems";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFtJobdItems, apiAuthenticationClient.getRequestHeaders()), FtJobdItems.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFtJobdItemsInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFtJobdItems, apiAuthenticationClient.getRequestHeaders()), FtJobdItems.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFtJobdItems/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtJobdItems.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFtJobdItemsById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtJobdItems.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FtJobdItems();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtJobdItems();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtJobdItems();
            }
        }

        @Override
        protected void onPostExecute(FtJobdItems result) {
            dismissProgressDialog();
//            if(result==null) result = new FtJobdItems();
//            displayResponseFtJobdItems(result);
        }
    }

    public class FtJobdItemsAllAsyncTask extends  AsyncTask<Void, Void, List<FtJobdItems>>{
        private ApiAuthenticationClient apiAuthenticationClient;

        private FtJobdItemsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FtJobdItems> doInBackground(Void... voids) {
            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFtJobdItems";

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {
                // Make the network request
                Log.d(TAG, url);
                ResponseEntity<FtJobdItems[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtJobdItems[].class);
                List<FtJobdItems> list = Arrays.asList(response.getBody());
                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtJobdItems>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtJobdItems>();
            }
        }

        @Override
        protected void onPostExecute(List<FtJobdItems> result) {
            dismissProgressDialog();
//            if(result.size()==0) result = new FtJobdItems();
//            displayResponseFtJobdItems(result);
        }


    }


}
