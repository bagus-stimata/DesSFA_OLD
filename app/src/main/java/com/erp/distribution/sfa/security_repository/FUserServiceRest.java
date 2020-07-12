package com.erp.distribution.sfa.security_repository;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.security_config.ApiAuthenticationClient;
import com.erp.distribution.sfa.security_model.FUser;

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

public class FUserServiceRest {
    protected static final String TAG = FUserServiceRest.class.getSimpleName();
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

    public FUserServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FUser getFUserById(int id) {
        FUserServiceRest.FUserCrudAsyncTask asyncTask = (FUserServiceRest.FUserCrudAsyncTask) new FUserServiceRest.FUserCrudAsyncTask(apiAuthenticationClient, id, true);
        FUser fUser = null;
        try {
//            fUser = asyncTask.execute().get();
            fUser = asyncTask.execute().get(5, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
        if (fUser==null) fUser =new FUser();
        return fUser;
    }

    public FUser getFUserByUsername(String username) {
        FUserWithUsernameAsyncTask asyncTask = (FUserWithUsernameAsyncTask) new FUserWithUsernameAsyncTask(apiAuthenticationClient, username);
        FUser fUser = null;
        try {
            fUser = asyncTask.execute().get(5, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }

//        if (fUser==null) fUser =new FUser();
        return fUser;
    }
    public FUser getFUserByEmail(String email) {
        FUserServiceRest.FUserWithEmailAsyncTask asyncTask = (FUserServiceRest.FUserWithEmailAsyncTask) new FUserServiceRest.FUserWithEmailAsyncTask(apiAuthenticationClient, email);
        FUser fUser = null;
        try {
            fUser = asyncTask.execute().get(5, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
        if (fUser==null) fUser =new FUser();
        return fUser;
    }


    public List<FUser> getAllFUser() {
        FUserServiceRest.FUserAllAsyncTask asyncTask = (FUserServiceRest.FUserAllAsyncTask) new FUserServiceRest.FUserAllAsyncTask(apiAuthenticationClient);
        List<FUser> listFUser = new ArrayList<>();
        try {
//            fUser = asyncTask.execute().get();
            listFUser = asyncTask.execute().get(5, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
//        if (fUser==null) fUser =new FUser();
        return listFUser;

    }

    public void createFUser(FUser fUser) {
//        new FUserCreateAsyncTask(apiAuthenticationClient, fUser).execute();
        new FUserServiceRest.FUserCrudAsyncTask(apiAuthenticationClient, fUser).execute();
    }
    public void updateFUser(Integer id, FUser fUser) {
//        new FUserUpdateAsyncTask(apiAuthenticationClient, id, fUser).execute();
        new FUserServiceRest.FUserCrudAsyncTask(apiAuthenticationClient, id, fUser).execute();
    }
    public void deleteFUser(Integer id) {
//        new FUserDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FUserServiceRest.FUserCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FUserCrudAsyncTask extends AsyncTask<Void, Void, FUser> {

        String operation = "";
        FUser newFUser = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FUserCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FUserCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FUser newFUser){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFUser = newFUser;
            operation = "ADD_NEW";
        }
        private FUserCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FUser updateFUser){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFUser = updateFUser;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FUserCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FUser doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FUser> response = restTemplate.exchange(url, HttpMethod.POST, FUser.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFUser, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FUser> response = restTemplate.postForEntity(url, httpEntity,  FUser.class);
                ResponseEntity<FUser> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFUser";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFUser, apiAuthenticationClient.getRequestHeaders()), FUser.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFUserInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFUser, apiAuthenticationClient.getRequestHeaders()), FUser.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFUser/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FUser.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFUserById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FUser.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FUser();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FUser();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FUser();
            }
        }

        @Override
        protected void onPostExecute(FUser result) {
            dismissProgressDialog();
//            if(result==null) result = new FUser();
//            displayResponseFUser(result);
        }
    }

    public class FUserAllAsyncTask extends  AsyncTask<Void, Void, List<FUser>>{
        private ApiAuthenticationClient apiAuthenticationClient;

        private FUserAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FUser> doInBackground(Void... voids) {
            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFUser";

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {
                // Make the network request
                Log.d(TAG, url);
                ResponseEntity<FUser[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FUser[].class);
                List<FUser> list = Arrays.asList(response.getBody());
                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FUser>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FUser>();
            }
        }

        @Override
        protected void onPostExecute(List<FUser> result) {
            dismissProgressDialog();
//            if(result.size()==0) result = new FUser();
//            displayResponseFUser(result);
        }


    }


    public class FUserWithUsernameAsyncTask extends AsyncTask<Void, Void, FUser> {

        FUser newFUser = null;
        String username = "";
        private ApiAuthenticationClient apiAuthenticationClient;

        private FUserWithUsernameAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  String username) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.username = username;
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FUser doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

                ResponseEntity<FUser> response = null;
                try {
                    url += "getFUserByUsername/" + username;
                    response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FUser.class);

                }catch (Exception ex){
                }
                return response!=null? response.getBody(): new FUser();
            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FUser();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FUser();
            }
        }

        @Override
        protected void onPostExecute(FUser result) {
            dismissProgressDialog();
        }
    }

    public class FUserWithEmailAsyncTask extends AsyncTask<Void, Void, FUser> {

        FUser newFUser = null;
        String email = "";
        private ApiAuthenticationClient apiAuthenticationClient;

        private FUserWithEmailAsyncTask(ApiAuthenticationClient apiAuthenticationClient, String email) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.email = email;
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FUser doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

                ResponseEntity<FUser> response = null;
                try {
                    url += "getFUserByEmail/" + email;
                    response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FUser.class);

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FUser();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FUser();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FUser();
            }
        }

        @Override
        protected void onPostExecute(FUser result) {
            dismissProgressDialog();
        }
    }

}
