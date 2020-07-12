package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FtStockTransferh;
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

public class FtStockTransferhServiceRest {
    protected static final String TAG = FtStockTransferhServiceRest.class.getSimpleName();
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

    public FtStockTransferhServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FtStockTransferh getFtStockTransferhById(Long id) {
        FtStockTransferhServiceRest.FtStockTransferhCrudAsyncTask asyncTask = (FtStockTransferhServiceRest.FtStockTransferhCrudAsyncTask) new FtStockTransferhServiceRest.FtStockTransferhCrudAsyncTask(apiAuthenticationClient, id, true);
        FtStockTransferh ftStockTransferh = null;
        try {
            ftStockTransferh = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (ftStockTransferh==null) ftStockTransferh =new FtStockTransferh();
        return ftStockTransferh;
    }

    public List<FtStockTransferh> getAllFtStockTransferh() {
        FtStockTransferhServiceRest.FtStockTransferhAllAsyncTask asyncTask = (FtStockTransferhServiceRest.FtStockTransferhAllAsyncTask) new FtStockTransferhServiceRest.FtStockTransferhAllAsyncTask(apiAuthenticationClient);
        List<FtStockTransferh> listFtStockTransferh = new ArrayList<>();
        try {
            listFtStockTransferh = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtStockTransferh;

    }
    public List<FtStockTransferh> getAllFtStockTransferhByDivision(Long divisionBean) {
        FtStockTransferhServiceRest.FtStockTransferhAllAsyncTask asyncTask = (FtStockTransferhServiceRest.FtStockTransferhAllAsyncTask) new FtStockTransferhServiceRest.FtStockTransferhAllAsyncTask(apiAuthenticationClient, divisionBean);
        List<FtStockTransferh> listFtStockTransferh = new ArrayList<>();
        try {
            listFtStockTransferh = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtStockTransferh;

    }


    public void createFtStockTransferh(FtStockTransferh ftStockTransferh) {
//        new FtStockTransferhCreateAsyncTask(apiAuthenticationClient, ftStockTransferh).execute();
        new FtStockTransferhServiceRest.FtStockTransferhCrudAsyncTask(apiAuthenticationClient, ftStockTransferh).execute();
    }
    public void updateFtStockTransferh(Long id, FtStockTransferh ftStockTransferh) {
//        new FtStockTransferhUpdateAsyncTask(apiAuthenticationClient, id, ftStockTransferh).execute();
        new FtStockTransferhServiceRest.FtStockTransferhCrudAsyncTask(apiAuthenticationClient, id, ftStockTransferh).execute();
    }
    public void deleteFtStockTransferh(Long id) {
//        new FtStockTransferhDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FtStockTransferhServiceRest.FtStockTransferhCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FtStockTransferhCrudAsyncTask extends AsyncTask<Void, Void, FtStockTransferh> {

        String operation = "";
        FtStockTransferh newFtStockTransferh = null;
        long id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FtStockTransferhCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FtStockTransferhCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FtStockTransferh newFtStockTransferh){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtStockTransferh = newFtStockTransferh;
            operation = "ADD_NEW";
        }
        private FtStockTransferhCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_update, FtStockTransferh updateFtStockTransferh){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtStockTransferh = updateFtStockTransferh;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FtStockTransferhCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected FtStockTransferh doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FtStockTransferh> response = restTemplate.exchange(url, HttpMethod.POST, FtStockTransferh.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFtStockTransferh, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FtStockTransferh> response = restTemplate.postForEntity(url, httpEntity,  FtStockTransferh.class);
                ResponseEntity<FtStockTransferh> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFtStockTransferh";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFtStockTransferh, apiAuthenticationClient.getRequestHeaders()), FtStockTransferh.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFtStockTransferhInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFtStockTransferh, apiAuthenticationClient.getRequestHeaders()), FtStockTransferh.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFtStockTransferh/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtStockTransferh.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFtStockTransferhById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtStockTransferh.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FtStockTransferh();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtStockTransferh();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtStockTransferh();
            }
        }

        @Override
        protected void onPostExecute(FtStockTransferh result) {
            dismissProgressDialog();
//            if(result==null) result = new FtStockTransferh();
//            displayResponseFtStockTransferh(result);
        }
    }

    public class FtStockTransferhAllAsyncTask extends  AsyncTask<Void, Void, List<FtStockTransferh>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        long id = 0;
        List<Long> listParentId = new ArrayList<>();

        private FtStockTransferhAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FtStockTransferhAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FtStockTransferhAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Long> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FtStockTransferh> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFtStockTransferh";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FtStockTransferh> list = new ArrayList<>();
            try {
                ResponseEntity<FtStockTransferh[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFtStockTransferh";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtStockTransferh[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFtStockTransferhByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtStockTransferh[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFtStockTransferh";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FtStockTransferh[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtStockTransferh>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtStockTransferh>();
            }
        }

        @Override
        protected void onPostExecute(List<FtStockTransferh> result) {
            dismissProgressDialog();
        }
    }


}
