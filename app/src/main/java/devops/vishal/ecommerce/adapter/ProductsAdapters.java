package devops.vishal.ecommerce.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import devops.vishal.ecommerce.R;
import devops.vishal.ecommerce.activity.ProductDescriptionActivity;
import devops.vishal.ecommerce.models.ProductModel;

public class ProductsAdapters extends RecyclerView.Adapter<ProductsAdapters.ViewHolder> {
    private Activity activity;
    private RecyclerView recyclerView;
    private Context mContext;
    private List<ProductModel> mCategoryList;

    private int posit;
    //private Integer [] images = {R.drawable.p5,R.drawable.p6,R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4};

    public ProductsAdapters(Activity activity, RecyclerView recyclerView, Context mContext, List<ProductModel> mCategoryList) {
        this.activity = activity;
        this.recyclerView =recyclerView;
        this.mContext = mContext;
        this.mCategoryList = mCategoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_product_screen, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ProductModel model = mCategoryList.get(position);

     //   int imgPos = images[position];

        Glide.with(mContext).load(model.getProductImage()).into(holder.productImage);
        holder.productName.setText(model.getProductName());
        holder.productCategory.setText(model.getProductCategory());
        holder.productPrice.setText(model.getProductPrice());
        holder.productStock.setText("In Stock : "+model.getProductAvailable());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(activity, ProductDescriptionActivity.class);
                intent.putExtra("productType",model.getProductType());
                intent.putExtra("productName",model.getProductName());
                intent.putExtra("productPrice",model.getProductPrice());
                intent.putExtra("productImage",model.getProductImage());
                intent.putExtra("productFeatures1",model.getProductFeatures1());
                intent.putExtra("productFeatures2",model.getProductFeatures2());
                intent.putExtra("productFeatures3",model.getProductFeatures3());
                intent.putExtra("productFeatures4",model.getProductFeatures4());
                intent.putExtra("productDiscountPrice",model.getProductDiscountPrice());
                intent.putExtra("productDescription",model.getProductDescription());
                intent.putExtra("productCategory",model.getProductCategory());
                intent.putExtra("productBrand",model.getProductBrand());
                intent.putExtra("productDiscountPercent",model.getProductDiscountPercent());
                intent.putExtra("productAvailable",model.getProductAvailable());
                intent.putExtra("productTotalPrice",model.getProductTotalPrice());
                intent.putExtra("productId",model.getProductId());

                activity.startActivity(intent);
            }


        });

    }


    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

   public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView productName,productCategory,productPrice,productStock;
        private ImageView productImage;

       public ViewHolder(@NonNull View itemView) {
           super(itemView);

           productName = itemView.findViewById(R.id.productName);
           productCategory = itemView.findViewById(R.id.productDescription);
           productPrice = itemView.findViewById(R.id.productPrice);
           productImage = itemView.findViewById(R.id.productIv);
           productStock = itemView.findViewById(R.id.stockTv);



       }

   }

}


