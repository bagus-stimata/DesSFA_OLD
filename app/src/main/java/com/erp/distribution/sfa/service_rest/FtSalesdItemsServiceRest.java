package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FtSalesdItems;
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

public class FtSalesdItemsServiceRest {
    protected static final String TAG = FtSalesdItemsServiceRest.class.getSimpleName();
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

    public FtSalesdItemsServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FtSalesdItems getFtSalesdItemsById(Long id) {
        FtSalesdItemsServiceRest.FtSalesdItemsCrudAsyncTask asyncTask = (FtSalesdItemsServiceRest.FtSalesdItemsCrudAsyncTask) new FtSalesdItemsServiceRest.FtSalesdItemsCrudAsyncTask(apiAuthenticationClient, id, true);
        FtSalesdItems ftSalesdItems = null;
        try {
            ftSalesdItems = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (ftSalesdItems==null) ftSalesdItems =new FtSalesdItems();
        return ftSalesdItems;
    }

    public List<FtSalesdItems> getAllFtSalesdItems() {
        FtSalesdItemsServiceRest.FtSalesdItemsAllAsyncTask asyncTask = (FtSalesdItemsServiceRest.FtSalesdItemsAllAsyncTask) new FtSalesdItemsServiceRest.FtSalesdItemsAllAsyncTask(apiAuthenticationClient);
        List<FtSalesdItems> listFtSalesdItems = new ArrayList<>();
        try {
            listFtSalesdItems = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtSalesdItems;

    }
    public List<FtSalesdItems> getAllFtSalesdItemsByParentId(Long parentId) {
        FtSalesdItemsServiceRest.FtSalesdItemsAllAsyncTask asyncTask = (FtSalesdItemsServiceRest.FtSalesdItemsAllAsyncTask) new FtSalesdItemsServiceRest.FtSalesdItemsAllAsyncTask(apiAuthenticationClient, parentId);
        List<FtSalesdItems> listFtSalesdItems = new ArrayList<>();
        try {
            listFtSalesdItems = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtSalesdItems;
    }
    public List<FtSalesdItems> getAllFtSalesdItemsByListParentId(List<Long> listParentId) {
        FtSalesdItemsServiceRest.FtSalesdItemsAllAsyncTask asyncTask = (FtSalesdItemsServiceRest.FtSalesdItemsAllAsyncTask) new FtSalesdItemsServiceRest.FtSalesdItemsAllAsyncTask(apiAuthenticationClient, listParentId);
        List<FtSalesdItems> listFtSalesdItems = new ArrayList<>();
        try {
            listFtSalesdItems = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtSalesdItems;
    }
   
   
    public void createFtSalesdItems(FtSalesdItems ftSalesdItems) {
//        new FtSalesdItemsCreateAsyncTask(apiAuthenticationClient, ftSalesdItems).execute();
        new FtSalesdItemsServiceRest.FtSalesdItemsCrudAsyncTask(apiAuthenticationClient, ftSalesdItems).execute();
    }
    public void updateFtSalesdItems(Long id, FtSalesdItems ftSalesdItems) {
//        new FtSalesdItemsUpdateAsyncTask(apiAuthenticationClient, id, ftSalesdItems).execute();
        new FtSalesdItemsServiceRest.FtSalesdItemsCrudAsyncTask(apiAuthenticationClient, id, ftSalesdItems).execute();
    }
    public void deleteFtSalesdItems(Long id) {
//        new FtSalesdItemsDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FtSalesdItemsServiceRest.FtSalesdItemsCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FtSalesdItemsCrudAsyncTask extends AsyncTask<Void, Void, FtSalesdItems> {

        String operation = "";
        FtSalesdItems newFtSalesdItems = null;
        long id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FtSalesdItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FtSalesdItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FtSalesdItems newFtSalesdItems){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtSalesdItems = newFtSalesdItems;
            operation = "ADD_NEW";
        }
        private FtSalesdItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_update, FtSalesdItems updateFtSalesdItems){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtSalesdItems = updateFtSalesdItems;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FtSalesdItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected FtSalesdItems doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FtSalesdItems> response = restTemplate.exchange(url, HttpMethod.POST, FtSalesdItems.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFtSalesdItems, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FtSalesdItems> response = restTemplate.postForEntity(url, httpEntity,  FtSalesdItems.class);
                ResponseEntity<FtSalesdItems> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFtSalesdItems";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFtSalesdItems, apiAuthenticationClient.getRequestHeaders()), FtSalesdItems.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFtSalesdItemsInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFtSalesdItems, apiAuthenticationClient.getRequestHeaders()), FtSalesdItems.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFtSalesdItems/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtSalesdItems.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFtSalesdItemsById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtSalesdItems.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FtSalesdItems();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtSalesdItems();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtSalesdItems();
            }
        }

        @Override
        protected void onPostExecute(FtSalesdItems result) {
            dismissProgressDialog();
//            if(result==null) result = new FtSalesdItems();
//            displayResponseFtSalesdItems(result);
        }
    }

    public class FtSalesdItemsAllAsyncTask extends  AsyncTask<Void, Void, List<FtSalesdItems>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        long id = 0;
        List<Long> listParentId = new ArrayList<>();

        private FtSalesdItemsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FtSalesdItemsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FtSalesdItemsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Long> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FtSalesdItems> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFtSalesdItems";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FtSalesdItems> list = new ArrayList<>();
            try {
                ResponseEntity<FtSalesdItems[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFtSalesdItems";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtSalesdItems[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFtSalesdItemsByParentId/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtSalesdItems[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFtSalesdItemsByListParentId";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FtSalesdItems[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtSalesdItems>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtSalesdItems>();
            }
        }

        @Override
        protected void onPostExecute(List<FtSalesdItems> result) {
            dismissProgressDialog();
        }
    }


}
