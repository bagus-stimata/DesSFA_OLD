package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FtSalesdPromoTprb;
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

public class FtSalesdPromoTprbServiceRest {
    protected static final String TAG = FtSalesdPromoTprbServiceRest.class.getSimpleName();
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

    public FtSalesdPromoTprbServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FtSalesdPromoTprb getFtSalesdPromoTprbById(Long id) {
        FtSalesdPromoTprbServiceRest.FtSalesdPromoTprbCrudAsyncTask asyncTask = (FtSalesdPromoTprbServiceRest.FtSalesdPromoTprbCrudAsyncTask) new FtSalesdPromoTprbServiceRest.FtSalesdPromoTprbCrudAsyncTask(apiAuthenticationClient, id, true);
        FtSalesdPromoTprb ftSalesdPromoTprb = null;
        try {
            ftSalesdPromoTprb = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (ftSalesdPromoTprb==null) ftSalesdPromoTprb =new FtSalesdPromoTprb();
        return ftSalesdPromoTprb;
    }

    public List<FtSalesdPromoTprb> getAllFtSalesdPromoTprb() {
        FtSalesdPromoTprbServiceRest.FtSalesdPromoTprbAllAsyncTask asyncTask = (FtSalesdPromoTprbServiceRest.FtSalesdPromoTprbAllAsyncTask) new FtSalesdPromoTprbServiceRest.FtSalesdPromoTprbAllAsyncTask(apiAuthenticationClient);
        List<FtSalesdPromoTprb> listFtSalesdPromoTprb = new ArrayList<>();
        try {
            listFtSalesdPromoTprb = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtSalesdPromoTprb;

    }
    public List<FtSalesdPromoTprb> getAllFtSalesdPromoTprbByParentId(Long parentId) {
        FtSalesdPromoTprbServiceRest.FtSalesdPromoTprbAllAsyncTask asyncTask = (FtSalesdPromoTprbServiceRest.FtSalesdPromoTprbAllAsyncTask) new FtSalesdPromoTprbServiceRest.FtSalesdPromoTprbAllAsyncTask(apiAuthenticationClient, parentId);
        List<FtSalesdPromoTprb> listFtSalesdPromoTprb = new ArrayList<>();
        try {
            listFtSalesdPromoTprb = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtSalesdPromoTprb;
    }
    public List<FtSalesdPromoTprb> getAllFtSalesdPromoTprbByListParentId(List<Long> listParentId) {
        FtSalesdPromoTprbServiceRest.FtSalesdPromoTprbAllAsyncTask asyncTask = (FtSalesdPromoTprbServiceRest.FtSalesdPromoTprbAllAsyncTask) new FtSalesdPromoTprbServiceRest.FtSalesdPromoTprbAllAsyncTask(apiAuthenticationClient, listParentId);
        List<FtSalesdPromoTprb> listFtSalesdPromoTprb = new ArrayList<>();
        try {
            listFtSalesdPromoTprb = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtSalesdPromoTprb;
    }


    public void createFtSalesdPromoTprb(FtSalesdPromoTprb ftSalesdPromoTprb) {
//        new FtSalesdPromoTprbCreateAsyncTask(apiAuthenticationClient, ftSalesdPromoTprb).execute();
        new FtSalesdPromoTprbServiceRest.FtSalesdPromoTprbCrudAsyncTask(apiAuthenticationClient, ftSalesdPromoTprb).execute();
    }
    public void updateFtSalesdPromoTprb(Long id, FtSalesdPromoTprb ftSalesdPromoTprb) {
//        new FtSalesdPromoTprbUpdateAsyncTask(apiAuthenticationClient, id, ftSalesdPromoTprb).execute();
        new FtSalesdPromoTprbServiceRest.FtSalesdPromoTprbCrudAsyncTask(apiAuthenticationClient, id, ftSalesdPromoTprb).execute();
    }
    public void deleteFtSalesdPromoTprb(Long id) {
//        new FtSalesdPromoTprbDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FtSalesdPromoTprbServiceRest.FtSalesdPromoTprbCrudAsyncTask(apiAuthenticationClient, id).execute();
    }





    public class FtSalesdPromoTprbCrudAsyncTask extends AsyncTask<Void, Void, FtSalesdPromoTprb> {

        String operation = "";
        FtSalesdPromoTprb newFtSalesdPromoTprb = null;
        long id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FtSalesdPromoTprbCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FtSalesdPromoTprbCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FtSalesdPromoTprb newFtSalesdPromoTprb){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtSalesdPromoTprb = newFtSalesdPromoTprb;
            operation = "ADD_NEW";
        }
        private FtSalesdPromoTprbCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_update, FtSalesdPromoTprb updateFtSalesdPromoTprb){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtSalesdPromoTprb = updateFtSalesdPromoTprb;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FtSalesdPromoTprbCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected FtSalesdPromoTprb doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FtSalesdPromoTprb> response = restTemplate.exchange(url, HttpMethod.POST, FtSalesdPromoTprb.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFtSalesdPromoTprb, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FtSalesdPromoTprb> response = restTemplate.postForEntity(url, httpEntity,  FtSalesdPromoTprb.class);
                ResponseEntity<FtSalesdPromoTprb> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFtSalesdPromoTprb";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFtSalesdPromoTprb, apiAuthenticationClient.getRequestHeaders()), FtSalesdPromoTprb.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFtSalesdPromoTprbInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFtSalesdPromoTprb, apiAuthenticationClient.getRequestHeaders()), FtSalesdPromoTprb.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFtSalesdPromoTprb/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtSalesdPromoTprb.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFtSalesdPromoTprbById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtSalesdPromoTprb.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FtSalesdPromoTprb();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtSalesdPromoTprb();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtSalesdPromoTprb();
            }
        }

        @Override
        protected void onPostExecute(FtSalesdPromoTprb result) {
            dismissProgressDialog();
//            if(result==null) result = new FtSalesdPromoTprb();
//            displayResponseFtSalesdPromoTprb(result);
        }
    }

    public class FtSalesdPromoTprbAllAsyncTask extends  AsyncTask<Void, Void, List<FtSalesdPromoTprb>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        long id = 0;
        List<Long> listParentId = new ArrayList<>();

        private FtSalesdPromoTprbAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FtSalesdPromoTprbAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FtSalesdPromoTprbAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Long> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FtSalesdPromoTprb> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFtSalesdPromoTprb";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FtSalesdPromoTprb> list = new ArrayList<>();
            try {
                ResponseEntity<FtSalesdPromoTprb[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFtSalesdPromoTprb";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtSalesdPromoTprb[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFtSalesdPromoTprbByParentId/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtSalesdPromoTprb[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFtSalesdPromoTprbByListParentId";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FtSalesdPromoTprb[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtSalesdPromoTprb>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtSalesdPromoTprb>();
            }
        }

        @Override
        protected void onPostExecute(List<FtSalesdPromoTprb> result) {
            dismissProgressDialog();
        }
    }


}
