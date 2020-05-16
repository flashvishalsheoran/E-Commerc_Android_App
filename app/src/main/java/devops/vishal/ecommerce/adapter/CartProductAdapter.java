package devops.vishal.ecommerce.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import devops.vishal.ecommerce.R;
import devops.vishal.ecommerce.activity.BuyProductActivity;
import devops.vishal.ecommerce.models.ProductModel;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.ViewHolder> {
    private Activity activity;
    private Context mContext;
    private RecyclerView recyclerView;
    private List<ProductModel> productModelList;
    private ProductModel model;

    public CartProductAdapter(Activity activity, RecyclerView recyclerView, Context mContext, List<ProductModel> productModelList) {
        this.activity = activity;
        this.mContext = mContext;
        this.recyclerView = recyclerView;
        this.productModelList = productModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_product_screen, parent, false);
        return new CartProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.favButton.setVisibility(View.GONE);
        model = productModelList.get(position);

        holder.productName.setText(model.getProductName());
        holder.productCategory.setText(model.getProductCategory());
        holder.productPrice.setText(" ₹ "+model.getProductPrice());
        holder.productStock.setText("In Stock : "+model.getProductAvailable());

        Glide.with(mContext).load(model.getProductImage()).into(holder.productImage);

        holder.buyProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(activity, BuyProductActivity.class);

                intent.putExtra("productPrice",model.getProductPrice());
                intent.putExtra("productId",model.getProductId());

                activity.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView productName,productCategory,productPrice,productStock,buyProductButton;
        private ImageView productImage,favButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productCategory = itemView.findViewById(R.id.productDescription);
            productPrice = itemView.findViewById(R.id.productPrice);
            productImage = itemView.findViewById(R.id.productIv);
            productStock = itemView.findViewById(R.id.stockTv);
            buyProductButton = itemView.findViewById(R.id.buyNowTV);
            favButton = itemView.findViewById(R.id.favoriteProductButton);



        }
    }
}
