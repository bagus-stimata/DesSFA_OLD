package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FtSaleshHistorySJ;
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

public class FtSaleshHistorySJServiceRest {
    protected static final String TAG = FtSaleshHistorySJServiceRest.class.getSimpleName();
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

    public FtSaleshHistorySJServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FtSaleshHistorySJ getFtSaleshHistorySJById(Long id) {
        FtSaleshHistorySJServiceRest.FtSaleshHistorySJCrudAsyncTask asyncTask = (FtSaleshHistorySJServiceRest.FtSaleshHistorySJCrudAsyncTask) new FtSaleshHistorySJServiceRest.FtSaleshHistorySJCrudAsyncTask(apiAuthenticationClient, id, true);
        FtSaleshHistorySJ ftSaleshHistorySJ = null;
        try {
            ftSaleshHistorySJ = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (ftSaleshHistorySJ==null) ftSaleshHistorySJ =new FtSaleshHistorySJ();
        return ftSaleshHistorySJ;
    }

    public List<FtSaleshHistorySJ> getAllFtSaleshHistorySJ() {
        FtSaleshHistorySJServiceRest.FtSaleshHistorySJAllAsyncTask asyncTask = (FtSaleshHistorySJServiceRest.FtSaleshHistorySJAllAsyncTask) new FtSaleshHistorySJServiceRest.FtSaleshHistorySJAllAsyncTask(apiAuthenticationClient);
        List<FtSaleshHistorySJ> listFtSaleshHistorySJ = new ArrayList<>();
        try {
            listFtSaleshHistorySJ = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtSaleshHistorySJ;

    }
    public List<FtSaleshHistorySJ> getAllFtSaleshHistorySJByParentId(Long parentId) {
        FtSaleshHistorySJServiceRest.FtSaleshHistorySJAllAsyncTask asyncTask = (FtSaleshHistorySJServiceRest.FtSaleshHistorySJAllAsyncTask) new FtSaleshHistorySJServiceRest.FtSaleshHistorySJAllAsyncTask(apiAuthenticationClient, parentId);
        List<FtSaleshHistorySJ> listFtSaleshHistorySJ = new ArrayList<>();
        try {
            listFtSaleshHistorySJ = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtSaleshHistorySJ;
    }
    public List<FtSaleshHistorySJ> getAllFtSaleshHistorySJByListParentId(List<Long> listParentId) {
        FtSaleshHistorySJServiceRest.FtSaleshHistorySJAllAsyncTask asyncTask = (FtSaleshHistorySJServiceRest.FtSaleshHistorySJAllAsyncTask) new FtSaleshHistorySJServiceRest.FtSaleshHistorySJAllAsyncTask(apiAuthenticationClient, listParentId);
        List<FtSaleshHistorySJ> listFtSaleshHistorySJ = new ArrayList<>();
        try {
            listFtSaleshHistorySJ = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtSaleshHistorySJ;
    }
    
    public void createFtSaleshHistorySJ(FtSaleshHistorySJ ftSaleshHistorySJ) {
//        new FtSaleshHistorySJCreateAsyncTask(apiAuthenticationClient, ftSaleshHistorySJ).execute();
        new FtSaleshHistorySJServiceRest.FtSaleshHistorySJCrudAsyncTask(apiAuthenticationClient, ftSaleshHistorySJ).execute();
    }
    public void updateFtSaleshHistorySJ(Long id, FtSaleshHistorySJ ftSaleshHistorySJ) {
//        new FtSaleshHistorySJUpdateAsyncTask(apiAuthenticationClient, id, ftSaleshHistorySJ).execute();
        new FtSaleshHistorySJServiceRest.FtSaleshHistorySJCrudAsyncTask(apiAuthenticationClient, id, ftSaleshHistorySJ).execute();
    }
    public void deleteFtSaleshHistorySJ(Long id) {
//        new FtSaleshHistorySJDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FtSaleshHistorySJServiceRest.FtSaleshHistorySJCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FtSaleshHistorySJCrudAsyncTask extends AsyncTask<Void, Void, FtSaleshHistorySJ> {

        String operation = "";
        FtSaleshHistorySJ newFtSaleshHistorySJ = null;
        long id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FtSaleshHistorySJCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FtSaleshHistorySJCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FtSaleshHistorySJ newFtSaleshHistorySJ){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtSaleshHistorySJ = newFtSaleshHistorySJ;
            operation = "ADD_NEW";
        }
        private FtSaleshHistorySJCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_update, FtSaleshHistorySJ updateFtSaleshHistorySJ){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtSaleshHistorySJ = updateFtSaleshHistorySJ;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FtSaleshHistorySJCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected FtSaleshHistorySJ doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FtSaleshHistorySJ> response = restTemplate.exchange(url, HttpMethod.POST, FtSaleshHistorySJ.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFtSaleshHistorySJ, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FtSaleshHistorySJ> response = restTemplate.postForEntity(url, httpEntity,  FtSaleshHistorySJ.class);
                ResponseEntity<FtSaleshHistorySJ> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFtSaleshHistorySJ";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFtSaleshHistorySJ, apiAuthenticationClient.getRequestHeaders()), FtSaleshHistorySJ.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFtSaleshHistorySJInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFtSaleshHistorySJ, apiAuthenticationClient.getRequestHeaders()), FtSaleshHistorySJ.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFtSaleshHistorySJ/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtSaleshHistorySJ.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFtSaleshHistorySJById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtSaleshHistorySJ.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FtSaleshHistorySJ();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtSaleshHistorySJ();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtSaleshHistorySJ();
            }
        }

        @Override
        protected void onPostExecute(FtSaleshHistorySJ result) {
            dismissProgressDialog();
//            if(result==null) result = new FtSaleshHistorySJ();
//            displayResponseFtSaleshHistorySJ(result);
        }
    }

    public class FtSaleshHistorySJAllAsyncTask extends  AsyncTask<Void, Void, List<FtSaleshHistorySJ>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        long id = 0;
        List<Long> listParentId = new ArrayList<>();

        private FtSaleshHistorySJAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FtSaleshHistorySJAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FtSaleshHistorySJAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Long> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FtSaleshHistorySJ> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFtSaleshHistorySJ";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FtSaleshHistorySJ> list = new ArrayList<>();
            try {
                ResponseEntity<FtSaleshHistorySJ[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFtSaleshHistorySJ";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtSaleshHistorySJ[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFtSaleshHistorySJByParentId/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtSaleshHistorySJ[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFtSaleshHistorySJByListParentId";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FtSaleshHistorySJ[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtSaleshHistorySJ>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtSaleshHistorySJ>();
            }
        }

        @Override
        protected void onPostExecute(List<FtSaleshHistorySJ> result) {
            dismissProgressDialog();
        }
    }


}
