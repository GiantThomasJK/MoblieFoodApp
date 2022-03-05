package com.example.orderfoodapp.API.TEST;

import com.example.orderfoodapp.API.ArrayCart;
import com.example.orderfoodapp.API.Categories;
import com.example.orderfoodapp.API.User;
import com.example.orderfoodapp.models.CartItem;
import com.example.orderfoodapp.models.Cartitempost;
import com.example.orderfoodapp.models.FavoriteModel;
import com.example.orderfoodapp.models.Forgot;
import com.example.orderfoodapp.models.HomeVerModel;
import com.example.orderfoodapp.models.NotificationModel;
import com.example.orderfoodapp.models.OrderItem;
import com.example.orderfoodapp.models.OrderModel;
import com.example.orderfoodapp.models.RecommendModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIController {

    String DOMAIN = "https://cocodak.herokuapp.com/";
    Gson gson =new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();

    APIController apiService = new Retrofit.Builder()
            .baseUrl(DOMAIN)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIController.class);



    //User

    @POST("users")
    Call<User> register(@Body User user);

    @PATCH("users/{id}")
    Call<User> changePassword(@Path("id") String id, @Body User user);

    @PATCH("users/{id}")
    Call<User> changeInfo(@Path("id") String id, @Body User user);


    @GET("users")
    Call<List<User>> getUser();

    @GET("users/{iduser}")
    Call<User> getUserCurrent(@Path("iduser") String idUser);

    @POST("login")
    Call<List<User>> Login(@Body User user);

    @POST("users/forgot")
    Call<Message> forgot(@Body Forgot forgot);

    //Categories

    @GET("categories")
    Call<List<Categories>> getCategories();

    @GET("products")
    Call<List<HomeVerModel>> getPopular();

    @GET("products")
    Call<List<RecommendModel>> getRecommend();

    @GET("products/{idproduct}")
    Call<HomeVerModel> getProduct(@Path("idproduct") String idproduct);

    //Cart

    @GET("cart/{idcart}/cartitem")
    Call<List<CartItem>> getmycart(@Path("idcart") String idcart);

    @POST("cart/{idcart}/cartitem")
    Call<List<CartItem>> addmycart(@Path("idcart") String idcart,@Body Cartitempost cartItem);


    @DELETE("cartitem/{idcartitem}")
    Call<Boolean> deletecartitem(@Path("idcartitem") String idcartitem);

    //Favorite

    @GET("users/{idUser}/favorite")
    Call<List<HomeVerModel>> getfavorite(@Path("idUser") String idUser);

    @POST("users/{idUser}/favorite")
    Call<List<String>> postfavorite(@Path("idUser") String idUser,@Body FavoriteModel favoriteModel);

    @DELETE("users/{idUser}/favorite/{idproduct}")
    Call<List<String>> deletefavorite(@Path("idUser") String idUser,@Path("idproduct") String idproduct);

    //Order

    @POST("order")
    Call<List<OrderModel>> postorder(@Body OrderModel orderModel);

    @POST("users/{idUser}/order")
    Call<OrderModel> createOrderForUser(@Path("idUser") String id, @Body OrderModel order);

    @GET("users/{idUser}/order")
    Call<List<NotificationModel>> getOrderUser(@Path("idUser") String id);

    @POST("order/{idOrder}/orderitem")
    Call<Void> addItemOrderToOrder(@Path("idOrder") String id, @Body OrderItem orderItem);

    @GET("order/{idOrder}/orderitem")
    Call<List<OrderItem>> getOrderitem (@Path("idOrder") String id);

//    @GET("order/{idOrder}/")
//    Call<>

    



}
