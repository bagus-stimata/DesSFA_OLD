package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FtApPaymentd;
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

public class FtApPaymentdServiceRest {
    protected static final String TAG = FtApPaymentdServiceRest.class.getSimpleName();
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

    public FtApPaymentdServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FtApPaymentd getFtApPaymentdById(Long id) {
        FtApPaymentdServiceRest.FtApPaymentdCrudAsyncTask asyncTask = (FtApPaymentdServiceRest.FtApPaymentdCrudAsyncTask) new FtApPaymentdServiceRest.FtApPaymentdCrudAsyncTask(apiAuthenticationClient, id, true);
        FtApPaymentd ftApPaymentd = null;
        try {
            ftApPaymentd = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (ftApPaymentd==null) ftApPaymentd =new FtApPaymentd();
        return ftApPaymentd;
    }

    public List<FtApPaymentd> getAllFtApPaymentd() {
        FtApPaymentdServiceRest.FtApPaymentdAllAsyncTask asyncTask = (FtApPaymentdServiceRest.FtApPaymentdAllAsyncTask) new FtApPaymentdServiceRest.FtApPaymentdAllAsyncTask(apiAuthenticationClient);
        List<FtApPaymentd> listFtApPaymentd = new ArrayList<>();
        try {
            listFtApPaymentd = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFtApPaymentd;

    }
    public List<FtApPaymentd> getAllFtApPaymentdByParentId(Long parentId) {
        FtApPaymentdServiceRest.FtApPaymentdAllAsyncTask asyncTask = (FtApPaymentdServiceRest.FtApPaymentdAllAsyncTask) new FtApPaymentdServiceRest.FtApPaymentdAllAsyncTask(apiAuthenticationClient, parentId);
        List<FtApPaymentd> listFtApPaymentd = new ArrayList<>();
        try {
            listFtApPaymentd = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFtApPaymentd;
    }
    public List<FtApPaymentd> getAllFtApPaymentdByListParentId(List<Long> listParentId) {
        FtApPaymentdServiceRest.FtApPaymentdAllAsyncTask asyncTask = (FtApPaymentdServiceRest.FtApPaymentdAllAsyncTask) new FtApPaymentdServiceRest.FtApPaymentdAllAsyncTask(apiAuthenticationClient, listParentId);
        List<FtApPaymentd> listFtApPaymentd = new ArrayList<>();
        try {
            listFtApPaymentd = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFtApPaymentd;
    }
   
   
    public void createFtApPaymentd(FtApPaymentd ftApPaymentd) {
//        new FtApPaymentdCreateAsyncTask(apiAuthenticationClient, ftApPaymentd).execute();
        new FtApPaymentdServiceRest.FtApPaymentdCrudAsyncTask(apiAuthenticationClient, ftApPaymentd).execute();
    }
    public void updateFtApPaymentd(Integer id, FtApPaymentd ftApPaymentd) {
//        new FtApPaymentdUpdateAsyncTask(apiAuthenticationClient, id, ftApPaymentd).execute();
        new FtApPaymentdServiceRest.FtApPaymentdCrudAsyncTask(apiAuthenticationClient, id, ftApPaymentd).execute();
    }
    public void deleteFtApPaymentd(Integer id) {
//        new FtApPaymentdDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FtApPaymentdServiceRest.FtApPaymentdCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FtApPaymentdCrudAsyncTask extends AsyncTask<Void, Void, FtApPaymentd> {

        String operation = "";
        FtApPaymentd newFtApPaymentd = null;
        long id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FtApPaymentdCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  long id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FtApPaymentdCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FtApPaymentd newFtApPaymentd){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtApPaymentd = newFtApPaymentd;
            operation = "ADD_NEW";
        }
        private FtApPaymentdCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  long id_update, FtApPaymentd updateFtApPaymentd){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtApPaymentd = updateFtApPaymentd;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FtApPaymentdCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  long id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected FtApPaymentd doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FtApPaymentd> response = restTemplate.exchange(url, HttpMethod.POST, FtApPaymentd.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFtApPaymentd, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FtApPaymentd> response = restTemplate.postForEntity(url, httpEntity,  FtApPaymentd.class);
                ResponseEntity<FtApPaymentd> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFtApPaymentd";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFtApPaymentd, apiAuthenticationClient.getRequestHeaders()), FtApPaymentd.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFtApPaymentdInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFtApPaymentd, apiAuthenticationClient.getRequestHeaders()), FtApPaymentd.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFtApPaymentd/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtApPaymentd.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFtApPaymentdById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtApPaymentd.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FtApPaymentd();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtApPaymentd();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtApPaymentd();
            }
        }

        @Override
        protected void onPostExecute(FtApPaymentd result) {
            dismissProgressDialog();
//            if(result==null) result = new FtApPaymentd();
//            displayResponseFtApPaymentd(result);
        }
    }

    public class FtApPaymentdAllAsyncTask extends  AsyncTask<Void, Void, List<FtApPaymentd>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        long id = 0;
        List<Long> listParentId = new ArrayList<>();

        private FtApPaymentdAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FtApPaymentdAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FtApPaymentdAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Long> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FtApPaymentd> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFtApPaymentd";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FtApPaymentd> list = new ArrayList<>();
            try {
                ResponseEntity<FtApPaymentd[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFtApPaymentd";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtApPaymentd[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFtApPaymentdByParentId/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtApPaymentd[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFtApPaymentdByListParentId";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FtApPaymentd[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtApPaymentd>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtApPaymentd>();
            }
        }

        @Override
        protected void onPostExecute(List<FtApPaymentd> result) {
            dismissProgressDialog();
        }
    }


}
