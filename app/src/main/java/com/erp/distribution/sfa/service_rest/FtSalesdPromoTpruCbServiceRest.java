package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FtSalesdPromoTpruCb;
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

public class FtSalesdPromoTpruCbServiceRest {
    protected static final String TAG = FtSalesdPromoTpruCbServiceRest.class.getSimpleName();
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

    public FtSalesdPromoTpruCbServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FtSalesdPromoTpruCb getFtSalesdPromoTpruCbById(Long id) {
        FtSalesdPromoTpruCbServiceRest.FtSalesdPromoTpruCbCrudAsyncTask asyncTask = (FtSalesdPromoTpruCbServiceRest.FtSalesdPromoTpruCbCrudAsyncTask) new FtSalesdPromoTpruCbServiceRest.FtSalesdPromoTpruCbCrudAsyncTask(apiAuthenticationClient, id, true);
        FtSalesdPromoTpruCb ftSalesdPromoTpruCb = null;
        try {
            ftSalesdPromoTpruCb = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (ftSalesdPromoTpruCb==null) ftSalesdPromoTpruCb =new FtSalesdPromoTpruCb();
        return ftSalesdPromoTpruCb;
    }

    public List<FtSalesdPromoTpruCb> getAllFtSalesdPromoTpruCb() {
        FtSalesdPromoTpruCbServiceRest.FtSalesdPromoTpruCbAllAsyncTask asyncTask = (FtSalesdPromoTpruCbServiceRest.FtSalesdPromoTpruCbAllAsyncTask) new FtSalesdPromoTpruCbServiceRest.FtSalesdPromoTpruCbAllAsyncTask(apiAuthenticationClient);
        List<FtSalesdPromoTpruCb> listFtSalesdPromoTpruCb = new ArrayList<>();
        try {
            listFtSalesdPromoTpruCb = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtSalesdPromoTpruCb;

    }
    public List<FtSalesdPromoTpruCb> getAllFtSalesdPromoTpruCbByParentId(Long parentId) {
        FtSalesdPromoTpruCbServiceRest.FtSalesdPromoTpruCbAllAsyncTask asyncTask = (FtSalesdPromoTpruCbServiceRest.FtSalesdPromoTpruCbAllAsyncTask) new FtSalesdPromoTpruCbServiceRest.FtSalesdPromoTpruCbAllAsyncTask(apiAuthenticationClient, parentId);
        List<FtSalesdPromoTpruCb> listFtSalesdPromoTpruCb = new ArrayList<>();
        try {
            listFtSalesdPromoTpruCb = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtSalesdPromoTpruCb;
    }
    public List<FtSalesdPromoTpruCb> getAllFtSalesdPromoTpruCbByListParentId(List<Long> listParentId) {
        FtSalesdPromoTpruCbServiceRest.FtSalesdPromoTpruCbAllAsyncTask asyncTask = (FtSalesdPromoTpruCbServiceRest.FtSalesdPromoTpruCbAllAsyncTask) new FtSalesdPromoTpruCbServiceRest.FtSalesdPromoTpruCbAllAsyncTask(apiAuthenticationClient, listParentId);
        List<FtSalesdPromoTpruCb> listFtSalesdPromoTpruCb = new ArrayList<>();
        try {
            listFtSalesdPromoTpruCb = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtSalesdPromoTpruCb;
    }


    public void createFtSalesdPromoTpruCb(FtSalesdPromoTpruCb ftSalesdPromoTpruCb) {
//        new FtSalesdPromoTpruCbCreateAsyncTask(apiAuthenticationClient, ftSalesdPromoTpruCb).execute();
        new FtSalesdPromoTpruCbServiceRest.FtSalesdPromoTpruCbCrudAsyncTask(apiAuthenticationClient, ftSalesdPromoTpruCb).execute();
    }
    public void updateFtSalesdPromoTpruCb(Long id, FtSalesdPromoTpruCb ftSalesdPromoTpruCb) {
//        new FtSalesdPromoTpruCbUpdateAsyncTask(apiAuthenticationClient, id, ftSalesdPromoTpruCb).execute();
        new FtSalesdPromoTpruCbServiceRest.FtSalesdPromoTpruCbCrudAsyncTask(apiAuthenticationClient, id, ftSalesdPromoTpruCb).execute();
    }
    public void deleteFtSalesdPromoTpruCb(Long id) {
//        new FtSalesdPromoTpruCbDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FtSalesdPromoTpruCbServiceRest.FtSalesdPromoTpruCbCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FtSalesdPromoTpruCbCrudAsyncTask extends AsyncTask<Void, Void, FtSalesdPromoTpruCb> {

        String operation = "";
        FtSalesdPromoTpruCb newFtSalesdPromoTpruCb = null;
        long id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FtSalesdPromoTpruCbCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FtSalesdPromoTpruCbCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FtSalesdPromoTpruCb newFtSalesdPromoTpruCb){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtSalesdPromoTpruCb = newFtSalesdPromoTpruCb;
            operation = "ADD_NEW";
        }
        private FtSalesdPromoTpruCbCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_update, FtSalesdPromoTpruCb updateFtSalesdPromoTpruCb){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtSalesdPromoTpruCb = updateFtSalesdPromoTpruCb;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FtSalesdPromoTpruCbCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected FtSalesdPromoTpruCb doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FtSalesdPromoTpruCb> response = restTemplate.exchange(url, HttpMethod.POST, FtSalesdPromoTpruCb.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFtSalesdPromoTpruCb, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FtSalesdPromoTpruCb> response = restTemplate.postForEntity(url, httpEntity,  FtSalesdPromoTpruCb.class);
                ResponseEntity<FtSalesdPromoTpruCb> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFtSalesdPromoTpruCb";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFtSalesdPromoTpruCb, apiAuthenticationClient.getRequestHeaders()), FtSalesdPromoTpruCb.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFtSalesdPromoTpruCbInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFtSalesdPromoTpruCb, apiAuthenticationClient.getRequestHeaders()), FtSalesdPromoTpruCb.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFtSalesdPromoTpruCb/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtSalesdPromoTpruCb.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFtSalesdPromoTpruCbById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtSalesdPromoTpruCb.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FtSalesdPromoTpruCb();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtSalesdPromoTpruCb();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtSalesdPromoTpruCb();
            }
        }

        @Override
        protected void onPostExecute(FtSalesdPromoTpruCb result) {
            dismissProgressDialog();
//            if(result==null) result = new FtSalesdPromoTpruCb();
//            displayResponseFtSalesdPromoTpruCb(result);
        }
    }

    public class FtSalesdPromoTpruCbAllAsyncTask extends  AsyncTask<Void, Void, List<FtSalesdPromoTpruCb>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        long id = 0;
        List<Long> listParentId = new ArrayList<>();

        private FtSalesdPromoTpruCbAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FtSalesdPromoTpruCbAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FtSalesdPromoTpruCbAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Long> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FtSalesdPromoTpruCb> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFtSalesdPromoTpruCb";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FtSalesdPromoTpruCb> list = new ArrayList<>();
            try {
                ResponseEntity<FtSalesdPromoTpruCb[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFtSalesdPromoTpruCb";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtSalesdPromoTpruCb[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFtSalesdPromoTpruCbByParentId/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtSalesdPromoTpruCb[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFtSalesdPromoTpruCbByListParentId";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FtSalesdPromoTpruCb[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtSalesdPromoTpruCb>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtSalesdPromoTpruCb>();
            }
        }

        @Override
        protected void onPostExecute(List<FtSalesdPromoTpruCb> result) {
            dismissProgressDialog();
        }
    }


}
