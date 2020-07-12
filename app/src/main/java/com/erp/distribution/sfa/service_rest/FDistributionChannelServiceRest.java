package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FDistributionChannel;
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

public class FDistributionChannelServiceRest {
    protected static final String TAG = FDistributionChannelServiceRest.class.getSimpleName();
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

    public FDistributionChannelServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FDistributionChannel getFDistributionChannelById(int id) {
        FDistributionChannelServiceRest.FDistributionChannelCrudAsyncTask asyncTask = (FDistributionChannelServiceRest.FDistributionChannelCrudAsyncTask) new FDistributionChannelServiceRest.FDistributionChannelCrudAsyncTask(apiAuthenticationClient, id, true);
        FDistributionChannel fDistributionChannel = null;
        try {
            fDistributionChannel = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (fDistributionChannel==null) fDistributionChannel =new FDistributionChannel();
        return fDistributionChannel;
    }

    public List<FDistributionChannel> getAllFDistributionChannel() {
        FDistributionChannelServiceRest.FDistributionChannelAllAsyncTask asyncTask = (FDistributionChannelServiceRest.FDistributionChannelAllAsyncTask) new FDistributionChannelServiceRest.FDistributionChannelAllAsyncTask(apiAuthenticationClient);
        List<FDistributionChannel> listFDistributionChannel = new ArrayList<>();
        try {
            listFDistributionChannel = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFDistributionChannel;

    }
    public List<FDistributionChannel> getAllFDistributionChannelByDivision(int divisionBean) {
        FDistributionChannelServiceRest.FDistributionChannelAllAsyncTask asyncTask = (FDistributionChannelServiceRest.FDistributionChannelAllAsyncTask) new FDistributionChannelServiceRest.FDistributionChannelAllAsyncTask(apiAuthenticationClient, divisionBean);
        List<FDistributionChannel> listFDistributionChannel = new ArrayList<>();
        try {
            listFDistributionChannel = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFDistributionChannel;

    }

    public void createFDistributionChannel(FDistributionChannel fDistributionChannel) {
//        new FDistributionChannelCreateAsyncTask(apiAuthenticationClient, fDistributionChannel).execute();
        new FDistributionChannelServiceRest.FDistributionChannelCrudAsyncTask(apiAuthenticationClient, fDistributionChannel).execute();
    }
    public void updateFDistributionChannel(Integer id, FDistributionChannel fDistributionChannel) {
//        new FDistributionChannelUpdateAsyncTask(apiAuthenticationClient, id, fDistributionChannel).execute();
        new FDistributionChannelServiceRest.FDistributionChannelCrudAsyncTask(apiAuthenticationClient, id, fDistributionChannel).execute();
    }
    public void deleteFDistributionChannel(Integer id) {
//        new FDistributionChannelDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FDistributionChannelServiceRest.FDistributionChannelCrudAsyncTask(apiAuthenticationClient, id).execute();
    }





    public class FDistributionChannelCrudAsyncTask extends AsyncTask<Void, Void, FDistributionChannel> {

        String operation = "";
        FDistributionChannel newFDistributionChannel = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FDistributionChannelCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FDistributionChannelCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FDistributionChannel newFDistributionChannel){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFDistributionChannel = newFDistributionChannel;
            operation = "ADD_NEW";
        }
        private FDistributionChannelCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FDistributionChannel updateFDistributionChannel){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFDistributionChannel = updateFDistributionChannel;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FDistributionChannelCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected FDistributionChannel doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FDistributionChannel> response = restTemplate.exchange(url, HttpMethod.POST, FDistributionChannel.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFDistributionChannel, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FDistributionChannel> response = restTemplate.postForEntity(url, httpEntity,  FDistributionChannel.class);
                ResponseEntity<FDistributionChannel> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFDistributionChannel";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFDistributionChannel, apiAuthenticationClient.getRequestHeaders()), FDistributionChannel.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFDistributionChannelInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFDistributionChannel, apiAuthenticationClient.getRequestHeaders()), FDistributionChannel.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFDistributionChannel/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FDistributionChannel.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFDistributionChannelById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FDistributionChannel.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FDistributionChannel();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FDistributionChannel();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FDistributionChannel();
            }
        }

        @Override
        protected void onPostExecute(FDistributionChannel result) {
            dismissProgressDialog();
//            if(result==null) result = new FDistributionChannel();
//            displayResponseFDistributionChannel(result);
        }
    }

    public class FDistributionChannelAllAsyncTask extends  AsyncTask<Void, Void, List<FDistributionChannel>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FDistributionChannelAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FDistributionChannelAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FDistributionChannelAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FDistributionChannel> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFDistributionChannel";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FDistributionChannel> list = new ArrayList<>();
            try {
                ResponseEntity<FDistributionChannel[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFDistributionChannel";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FDistributionChannel[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFDistributionChannelByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FDistributionChannel[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFDistributionChannel";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FDistributionChannel[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FDistributionChannel>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FDistributionChannel>();
            }
        }

        @Override
        protected void onPostExecute(List<FDistributionChannel> result) {
            dismissProgressDialog();
        }
    }


}
