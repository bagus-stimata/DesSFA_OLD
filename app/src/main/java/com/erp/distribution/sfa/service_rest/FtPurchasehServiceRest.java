package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FtPurchaseh;
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

public class FtPurchasehServiceRest {
    protected static final String TAG = FtPurchasehServiceRest.class.getSimpleName();
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

    public FtPurchasehServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FtPurchaseh getFtPurchasehById(int id) {
        FtPurchasehServiceRest.FtPurchasehCrudAsyncTask asyncTask = (FtPurchasehServiceRest.FtPurchasehCrudAsyncTask) new FtPurchasehServiceRest.FtPurchasehCrudAsyncTask(apiAuthenticationClient, id, true);
        FtPurchaseh ftPurchaseh = null;
        try {
            ftPurchaseh = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (ftPurchaseh==null) ftPurchaseh =new FtPurchaseh();
        return ftPurchaseh;
    }

    public List<FtPurchaseh> getAllFtPurchaseh() {
        FtPurchasehServiceRest.FtPurchasehAllAsyncTask asyncTask = (FtPurchasehServiceRest.FtPurchasehAllAsyncTask) new FtPurchasehServiceRest.FtPurchasehAllAsyncTask(apiAuthenticationClient);
        List<FtPurchaseh> listFtPurchaseh = new ArrayList<>();
        try {
            listFtPurchaseh = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtPurchaseh;

    }
    public List<FtPurchaseh> getAllFtPurchasehByDivision(int divisionBean) {
        FtPurchasehServiceRest.FtPurchasehAllAsyncTask asyncTask = (FtPurchasehServiceRest.FtPurchasehAllAsyncTask) new FtPurchasehServiceRest.FtPurchasehAllAsyncTask(apiAuthenticationClient, divisionBean);
        List<FtPurchaseh> listFtPurchaseh = new ArrayList<>();
        try {
            listFtPurchaseh = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtPurchaseh;

    }


    public void createFtPurchaseh(FtPurchaseh ftPurchaseh) {
//        new FtPurchasehCreateAsyncTask(apiAuthenticationClient, ftPurchaseh).execute();
        new FtPurchasehServiceRest.FtPurchasehCrudAsyncTask(apiAuthenticationClient, ftPurchaseh).execute();
    }
    public void updateFtPurchaseh(Integer id, FtPurchaseh ftPurchaseh) {
//        new FtPurchasehUpdateAsyncTask(apiAuthenticationClient, id, ftPurchaseh).execute();
        new FtPurchasehServiceRest.FtPurchasehCrudAsyncTask(apiAuthenticationClient, id, ftPurchaseh).execute();
    }
    public void deleteFtPurchaseh(Integer id) {
//        new FtPurchasehDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FtPurchasehServiceRest.FtPurchasehCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FtPurchasehCrudAsyncTask extends AsyncTask<Void, Void, FtPurchaseh> {

        String operation = "";
        FtPurchaseh newFtPurchaseh = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FtPurchasehCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FtPurchasehCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FtPurchaseh newFtPurchaseh){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtPurchaseh = newFtPurchaseh;
            operation = "ADD_NEW";
        }
        private FtPurchasehCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FtPurchaseh updateFtPurchaseh){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtPurchaseh = updateFtPurchaseh;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FtPurchasehCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected FtPurchaseh doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FtPurchaseh> response = restTemplate.exchange(url, HttpMethod.POST, FtPurchaseh.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFtPurchaseh, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FtPurchaseh> response = restTemplate.postForEntity(url, httpEntity,  FtPurchaseh.class);
                ResponseEntity<FtPurchaseh> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFtPurchaseh";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFtPurchaseh, apiAuthenticationClient.getRequestHeaders()), FtPurchaseh.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFtPurchasehInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFtPurchaseh, apiAuthenticationClient.getRequestHeaders()), FtPurchaseh.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFtPurchaseh/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPurchaseh.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFtPurchasehById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPurchaseh.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FtPurchaseh();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtPurchaseh();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtPurchaseh();
            }
        }

        @Override
        protected void onPostExecute(FtPurchaseh result) {
            dismissProgressDialog();
//            if(result==null) result = new FtPurchaseh();
//            displayResponseFtPurchaseh(result);
        }
    }

    public class FtPurchasehAllAsyncTask extends  AsyncTask<Void, Void, List<FtPurchaseh>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FtPurchasehAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FtPurchasehAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FtPurchasehAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FtPurchaseh> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFtPurchaseh";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FtPurchaseh> list = new ArrayList<>();
            try {
                ResponseEntity<FtPurchaseh[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFtPurchaseh";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPurchaseh[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFtPurchasehByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPurchaseh[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFtPurchaseh";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FtPurchaseh[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtPurchaseh>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtPurchaseh>();
            }
        }

        @Override
        protected void onPostExecute(List<FtPurchaseh> result) {
            dismissProgressDialog();
        }
    }


}
