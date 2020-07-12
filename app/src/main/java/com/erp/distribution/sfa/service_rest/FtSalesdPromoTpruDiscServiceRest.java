package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FtSalesdPromoTpruDisc;
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

public class FtSalesdPromoTpruDiscServiceRest {
    protected static final String TAG = FtSalesdPromoTpruDiscServiceRest.class.getSimpleName();
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

    public FtSalesdPromoTpruDiscServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FtSalesdPromoTpruDisc getFtSalesdPromoTpruDiscById(Long id) {
        FtSalesdPromoTpruDiscServiceRest.FtSalesdPromoTpruDiscCrudAsyncTask asyncTask = (FtSalesdPromoTpruDiscServiceRest.FtSalesdPromoTpruDiscCrudAsyncTask) new FtSalesdPromoTpruDiscServiceRest.FtSalesdPromoTpruDiscCrudAsyncTask(apiAuthenticationClient, id, true);
        FtSalesdPromoTpruDisc ftSalesdPromoTpruDisc = null;
        try {
            ftSalesdPromoTpruDisc = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (ftSalesdPromoTpruDisc==null) ftSalesdPromoTpruDisc =new FtSalesdPromoTpruDisc();
        return ftSalesdPromoTpruDisc;
    }

    public List<FtSalesdPromoTpruDisc> getAllFtSalesdPromoTpruDisc() {
        FtSalesdPromoTpruDiscServiceRest.FtSalesdPromoTpruDiscAllAsyncTask asyncTask = (FtSalesdPromoTpruDiscServiceRest.FtSalesdPromoTpruDiscAllAsyncTask) new FtSalesdPromoTpruDiscServiceRest.FtSalesdPromoTpruDiscAllAsyncTask(apiAuthenticationClient);
        List<FtSalesdPromoTpruDisc> listFtSalesdPromoTpruDisc = new ArrayList<>();
        try {
            listFtSalesdPromoTpruDisc = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtSalesdPromoTpruDisc;

    }
    public List<FtSalesdPromoTpruDisc> getAllFtSalesdPromoTpruDiscByParentId(Long parentId) {
        FtSalesdPromoTpruDiscServiceRest.FtSalesdPromoTpruDiscAllAsyncTask asyncTask = (FtSalesdPromoTpruDiscServiceRest.FtSalesdPromoTpruDiscAllAsyncTask) new FtSalesdPromoTpruDiscServiceRest.FtSalesdPromoTpruDiscAllAsyncTask(apiAuthenticationClient, parentId);
        List<FtSalesdPromoTpruDisc> listFtSalesdPromoTpruDisc = new ArrayList<>();
        try {
            listFtSalesdPromoTpruDisc = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtSalesdPromoTpruDisc;
    }
    public List<FtSalesdPromoTpruDisc> getAllFtSalesdPromoTpruDiscByListParentId(List<Long> listParentId) {
        FtSalesdPromoTpruDiscServiceRest.FtSalesdPromoTpruDiscAllAsyncTask asyncTask = (FtSalesdPromoTpruDiscServiceRest.FtSalesdPromoTpruDiscAllAsyncTask) new FtSalesdPromoTpruDiscServiceRest.FtSalesdPromoTpruDiscAllAsyncTask(apiAuthenticationClient, listParentId);
        List<FtSalesdPromoTpruDisc> listFtSalesdPromoTpruDisc = new ArrayList<>();
        try {
            listFtSalesdPromoTpruDisc = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtSalesdPromoTpruDisc;
    }
   
   
    public void createFtSalesdPromoTpruDisc(FtSalesdPromoTpruDisc ftSalesdPromoTpruDisc) {
//        new FtSalesdPromoTpruDiscCreateAsyncTask(apiAuthenticationClient, ftSalesdPromoTpruDisc).execute();
        new FtSalesdPromoTpruDiscServiceRest.FtSalesdPromoTpruDiscCrudAsyncTask(apiAuthenticationClient, ftSalesdPromoTpruDisc).execute();
    }
    public void updateFtSalesdPromoTpruDisc(Long id, FtSalesdPromoTpruDisc ftSalesdPromoTpruDisc) {
//        new FtSalesdPromoTpruDiscUpdateAsyncTask(apiAuthenticationClient, id, ftSalesdPromoTpruDisc).execute();
        new FtSalesdPromoTpruDiscServiceRest.FtSalesdPromoTpruDiscCrudAsyncTask(apiAuthenticationClient, id, ftSalesdPromoTpruDisc).execute();
    }
    public void deleteFtSalesdPromoTpruDisc(Long id) {
//        new FtSalesdPromoTpruDiscDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FtSalesdPromoTpruDiscServiceRest.FtSalesdPromoTpruDiscCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FtSalesdPromoTpruDiscCrudAsyncTask extends AsyncTask<Void, Void, FtSalesdPromoTpruDisc> {

        String operation = "";
        FtSalesdPromoTpruDisc newFtSalesdPromoTpruDisc = null;
        long id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FtSalesdPromoTpruDiscCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FtSalesdPromoTpruDiscCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FtSalesdPromoTpruDisc newFtSalesdPromoTpruDisc){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtSalesdPromoTpruDisc = newFtSalesdPromoTpruDisc;
            operation = "ADD_NEW";
        }
        private FtSalesdPromoTpruDiscCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_update, FtSalesdPromoTpruDisc updateFtSalesdPromoTpruDisc){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtSalesdPromoTpruDisc = updateFtSalesdPromoTpruDisc;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FtSalesdPromoTpruDiscCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected FtSalesdPromoTpruDisc doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FtSalesdPromoTpruDisc> response = restTemplate.exchange(url, HttpMethod.POST, FtSalesdPromoTpruDisc.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFtSalesdPromoTpruDisc, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FtSalesdPromoTpruDisc> response = restTemplate.postForEntity(url, httpEntity,  FtSalesdPromoTpruDisc.class);
                ResponseEntity<FtSalesdPromoTpruDisc> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFtSalesdPromoTpruDisc";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFtSalesdPromoTpruDisc, apiAuthenticationClient.getRequestHeaders()), FtSalesdPromoTpruDisc.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFtSalesdPromoTpruDiscInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFtSalesdPromoTpruDisc, apiAuthenticationClient.getRequestHeaders()), FtSalesdPromoTpruDisc.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFtSalesdPromoTpruDisc/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtSalesdPromoTpruDisc.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFtSalesdPromoTpruDiscById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtSalesdPromoTpruDisc.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FtSalesdPromoTpruDisc();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtSalesdPromoTpruDisc();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtSalesdPromoTpruDisc();
            }
        }

        @Override
        protected void onPostExecute(FtSalesdPromoTpruDisc result) {
            dismissProgressDialog();
//            if(result==null) result = new FtSalesdPromoTpruDisc();
//            displayResponseFtSalesdPromoTpruDisc(result);
        }
    }

    public class FtSalesdPromoTpruDiscAllAsyncTask extends  AsyncTask<Void, Void, List<FtSalesdPromoTpruDisc>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        long id = 0;
        List<Long> listParentId = new ArrayList<>();

        private FtSalesdPromoTpruDiscAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FtSalesdPromoTpruDiscAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FtSalesdPromoTpruDiscAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Long> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FtSalesdPromoTpruDisc> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFtSalesdPromoTpruDisc";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FtSalesdPromoTpruDisc> list = new ArrayList<>();
            try {
                ResponseEntity<FtSalesdPromoTpruDisc[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFtSalesdPromoTpruDisc";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtSalesdPromoTpruDisc[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFtSalesdPromoTpruDiscByParentId/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtSalesdPromoTpruDisc[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFtSalesdPromoTpruDiscByListParentId";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FtSalesdPromoTpruDisc[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtSalesdPromoTpruDisc>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtSalesdPromoTpruDisc>();
            }
        }

        @Override
        protected void onPostExecute(List<FtSalesdPromoTpruDisc> result) {
            dismissProgressDialog();
        }
    }


}
