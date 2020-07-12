package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FtPriceAltdItems;
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

public class FtPriceAltdItemsServiceRest {
    protected static final String TAG = FtPriceAltdItemsServiceRest.class.getSimpleName();
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

    public FtPriceAltdItemsServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FtPriceAltdItems getFtPriceAltdItemsById(Long id) {
        FtPriceAltdItemsServiceRest.FtPriceAltdItemsCrudAsyncTask asyncTask = (FtPriceAltdItemsServiceRest.FtPriceAltdItemsCrudAsyncTask) new FtPriceAltdItemsServiceRest.FtPriceAltdItemsCrudAsyncTask(apiAuthenticationClient, id, true);
        FtPriceAltdItems ftPriceAltdItems = null;
        try {
            ftPriceAltdItems = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (ftPriceAltdItems==null) ftPriceAltdItems =new FtPriceAltdItems();
        return ftPriceAltdItems;
    }

    public List<FtPriceAltdItems> getAllFtPriceAltdItems() {
        FtPriceAltdItemsServiceRest.FtPriceAltdItemsAllAsyncTask asyncTask = (FtPriceAltdItemsServiceRest.FtPriceAltdItemsAllAsyncTask) new FtPriceAltdItemsServiceRest.FtPriceAltdItemsAllAsyncTask(apiAuthenticationClient);
        List<FtPriceAltdItems> listFtPriceAltdItems = new ArrayList<>();
        try {
            listFtPriceAltdItems = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtPriceAltdItems;

    }
    public List<FtPriceAltdItems> getAllFtPriceAltdItemsByParentId(Long parentId) {
        FtPriceAltdItemsServiceRest.FtPriceAltdItemsAllAsyncTask asyncTask = (FtPriceAltdItemsServiceRest.FtPriceAltdItemsAllAsyncTask) new FtPriceAltdItemsServiceRest.FtPriceAltdItemsAllAsyncTask(apiAuthenticationClient, parentId);
        List<FtPriceAltdItems> listFtPriceAltdItems = new ArrayList<>();
        try {
            listFtPriceAltdItems = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtPriceAltdItems;
    }
    public List<FtPriceAltdItems> getAllFtPriceAltdItemsByListParentId(List<Long> listParentId) {
        FtPriceAltdItemsServiceRest.FtPriceAltdItemsAllAsyncTask asyncTask = (FtPriceAltdItemsServiceRest.FtPriceAltdItemsAllAsyncTask) new FtPriceAltdItemsServiceRest.FtPriceAltdItemsAllAsyncTask(apiAuthenticationClient, listParentId);
        List<FtPriceAltdItems> listFtPriceAltdItems = new ArrayList<>();
        try {
            listFtPriceAltdItems = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtPriceAltdItems;
    }
   
   
    public void createFtPriceAltdItems(FtPriceAltdItems ftPriceAltdItems) {
//        new FtPriceAltdItemsCreateAsyncTask(apiAuthenticationClient, ftPriceAltdItems).execute();
        new FtPriceAltdItemsServiceRest.FtPriceAltdItemsCrudAsyncTask(apiAuthenticationClient, ftPriceAltdItems).execute();
    }
    public void updateFtPriceAltdItems(Long id, FtPriceAltdItems ftPriceAltdItems) {
//        new FtPriceAltdItemsUpdateAsyncTask(apiAuthenticationClient, id, ftPriceAltdItems).execute();
        new FtPriceAltdItemsServiceRest.FtPriceAltdItemsCrudAsyncTask(apiAuthenticationClient, id, ftPriceAltdItems).execute();
    }
    public void deleteFtPriceAltdItems(Long id) {
//        new FtPriceAltdItemsDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FtPriceAltdItemsServiceRest.FtPriceAltdItemsCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FtPriceAltdItemsCrudAsyncTask extends AsyncTask<Void, Void, FtPriceAltdItems> {

        String operation = "";
        FtPriceAltdItems newFtPriceAltdItems = null;
        long id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FtPriceAltdItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FtPriceAltdItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FtPriceAltdItems newFtPriceAltdItems){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtPriceAltdItems = newFtPriceAltdItems;
            operation = "ADD_NEW";
        }
        private FtPriceAltdItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_update, FtPriceAltdItems updateFtPriceAltdItems){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtPriceAltdItems = updateFtPriceAltdItems;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FtPriceAltdItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected FtPriceAltdItems doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FtPriceAltdItems> response = restTemplate.exchange(url, HttpMethod.POST, FtPriceAltdItems.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFtPriceAltdItems, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FtPriceAltdItems> response = restTemplate.postForEntity(url, httpEntity,  FtPriceAltdItems.class);
                ResponseEntity<FtPriceAltdItems> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFtPriceAltdItems";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFtPriceAltdItems, apiAuthenticationClient.getRequestHeaders()), FtPriceAltdItems.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFtPriceAltdItemsInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFtPriceAltdItems, apiAuthenticationClient.getRequestHeaders()), FtPriceAltdItems.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFtPriceAltdItems/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPriceAltdItems.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFtPriceAltdItemsById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPriceAltdItems.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FtPriceAltdItems();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtPriceAltdItems();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtPriceAltdItems();
            }
        }

        @Override
        protected void onPostExecute(FtPriceAltdItems result) {
            dismissProgressDialog();
//            if(result==null) result = new FtPriceAltdItems();
//            displayResponseFtPriceAltdItems(result);
        }
    }

    public class FtPriceAltdItemsAllAsyncTask extends  AsyncTask<Void, Void, List<FtPriceAltdItems>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        long id = 0;
        List<Long> listParentId = new ArrayList<>();

        private FtPriceAltdItemsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FtPriceAltdItemsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FtPriceAltdItemsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Long> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FtPriceAltdItems> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFtPriceAltdItems";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FtPriceAltdItems> list = new ArrayList<>();
            try {
                ResponseEntity<FtPriceAltdItems[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFtPriceAltdItems";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPriceAltdItems[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFtPriceAltdItemsByParentId/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPriceAltdItems[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFtPriceAltdItemsByListParentId";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FtPriceAltdItems[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtPriceAltdItems>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtPriceAltdItems>();
            }
        }

        @Override
        protected void onPostExecute(List<FtPriceAltdItems> result) {
            dismissProgressDialog();
        }
    }


}
