package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FtJobh;
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

public class FtJobhServiceRest {
    protected static final String TAG = FtJobhServiceRest.class.getSimpleName();
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

    public FtJobhServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FtJobh getFtJobhById(int id) {
        FtJobhServiceRest.FtJobhCrudAsyncTask asyncTask = (FtJobhServiceRest.FtJobhCrudAsyncTask) new FtJobhServiceRest.FtJobhCrudAsyncTask(apiAuthenticationClient, id, true);
        FtJobh ftJobh = null;
        try {
            ftJobh = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (ftJobh==null) ftJobh =new FtJobh();
        return ftJobh;
    }
    
    public List<FtJobh> getAllFtJobh() {
        FtJobhServiceRest.FtJobhAllAsyncTask asyncTask = (FtJobhServiceRest.FtJobhAllAsyncTask) new FtJobhServiceRest.FtJobhAllAsyncTask(apiAuthenticationClient);
        List<FtJobh> listFtJobh = new ArrayList<>();
        try {
            listFtJobh = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFtJobh;

    }
    public List<FtJobh> getAllFtJobhByDivision(int divisionBean) {
        FtJobhServiceRest.FtJobhAllAsyncTask asyncTask = (FtJobhServiceRest.FtJobhAllAsyncTask) new FtJobhServiceRest.FtJobhAllAsyncTask(apiAuthenticationClient, divisionBean);
        List<FtJobh> listFtJobh = new ArrayList<>();
        try {
            listFtJobh = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtJobh;

    }
    
    
    public void createFtJobh(FtJobh ftJobh) {
//        new FtJobhCreateAsyncTask(apiAuthenticationClient, ftJobh).execute();
        new FtJobhServiceRest.FtJobhCrudAsyncTask(apiAuthenticationClient, ftJobh).execute();
    }
    public void updateFtJobh(Integer id, FtJobh ftJobh) {
//        new FtJobhUpdateAsyncTask(apiAuthenticationClient, id, ftJobh).execute();
        new FtJobhServiceRest.FtJobhCrudAsyncTask(apiAuthenticationClient, id, ftJobh).execute();
    }
    public void deleteFtJobh(Integer id) {
//        new FtJobhDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FtJobhServiceRest.FtJobhCrudAsyncTask(apiAuthenticationClient, id).execute();
    }

    public class FtJobhCrudAsyncTask extends AsyncTask<Void, Void, FtJobh> {

        String operation = "";
        FtJobh newFtJobh = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FtJobhCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FtJobhCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FtJobh newFtJobh){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtJobh = newFtJobh;
            operation = "ADD_NEW";
        }
        private FtJobhCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FtJobh updateFtJobh){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtJobh = updateFtJobh;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FtJobhCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        public FtJobh getFtJobhById(int id) {
//        FtJobhByIdAsyncTask asyncTask = (FtJobhByIdAsyncTask) new FtJobhByIdAsyncTask(apiAuthenticationClient, id);
            FtJobhServiceRest.FtJobhCrudAsyncTask asyncTask = (FtJobhServiceRest.FtJobhCrudAsyncTask) new FtJobhServiceRest.FtJobhCrudAsyncTask(apiAuthenticationClient, id, true);
            FtJobh ftJobh = null;
            try {
//            ftJobh = asyncTask.execute().get();
                ftJobh = asyncTask.execute().get(5, TimeUnit.SECONDS);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException | TimeoutException e) {
                e.printStackTrace();
            }
            if (ftJobh==null) ftJobh =new FtJobh();
            return ftJobh;
        }
        public List<FtJobh> getAllFtJobh() {
            FtJobhServiceRest.FtJobhAllAsyncTask asyncTask = (FtJobhServiceRest.FtJobhAllAsyncTask) new FtJobhServiceRest.FtJobhAllAsyncTask(apiAuthenticationClient);
            List<FtJobh> listFtJobh = new ArrayList<>();
            try {
//            ftJobh = asyncTask.execute().get();
                listFtJobh = asyncTask.execute().get(7, TimeUnit.SECONDS);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException | TimeoutException e) {
                e.printStackTrace();
            }
//        if (ftJobh==null) ftJobh =new FtJobh();
            return listFtJobh;

        }
        public void createFtJobh(FtJobh ftJobh) {
//        new FtJobhCreateAsyncTask(apiAuthenticationClient, ftJobh).execute();
            new FtJobhServiceRest.FtJobhCrudAsyncTask(apiAuthenticationClient, ftJobh).execute();
        }
        public void updateFtJobh(Integer id, FtJobh ftJobh) {
//        new FtJobhUpdateAsyncTask(apiAuthenticationClient, id, ftJobh).execute();
            new FtJobhServiceRest.FtJobhCrudAsyncTask(apiAuthenticationClient, id, ftJobh).execute();
        }
        public void deleteFtJobh(Integer id) {
//        new FtJobhDeleteAsyncTask(apiAuthenticationClient, id).execute();
            new FtJobhServiceRest.FtJobhCrudAsyncTask(apiAuthenticationClient, id).execute();
        }


        @Override
        protected FtJobh doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FtJobh> response = restTemplate.exchange(url, HttpMethod.POST, FtJobh.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFtJobh, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FtJobh> response = restTemplate.postForEntity(url, httpEntity,  FtJobh.class);
                ResponseEntity<FtJobh> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFtJobh";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFtJobh, apiAuthenticationClient.getRequestHeaders()), FtJobh.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFtJobhInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFtJobh, apiAuthenticationClient.getRequestHeaders()), FtJobh.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFtJobh/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtJobh.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFtJobhById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtJobh.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FtJobh();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtJobh();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtJobh();
            }
        }

        @Override
        protected void onPostExecute(FtJobh result) {
            dismissProgressDialog();
//            if(result==null) result = new FtJobh();
//            displayResponseFtJobh(result);
        }
    }

    public class FtJobhAllAsyncTask extends  AsyncTask<Void, Void, List<FtJobh>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FtJobhAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FtJobhAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FtJobhAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FtJobh> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFtJobh";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FtJobh> list = new ArrayList<>();
            try {
                ResponseEntity<FtJobh[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFtJobh";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtJobh[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFtJobhByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtJobh[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFtJobh";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FtJobh[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtJobh>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtJobh>();
            }
        }

        @Override
        protected void onPostExecute(List<FtJobh> result) {
            dismissProgressDialog();
        }
    }


}
