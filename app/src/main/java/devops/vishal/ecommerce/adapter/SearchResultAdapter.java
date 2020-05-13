package devops.vishal.ecommerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import devops.vishal.ecommerce.R;
import devops.vishal.ecommerce.activity.ProductDescriptionActivity;
import devops.vishal.ecommerce.models.ProductModel;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {
    private RecyclerView recyclerView;
    private Context mContext;
    private List<ProductModel> mCategoryList;
    private ProductModel model;
  //  private Integer [] images = {R.drawable.p5,R.drawable.p6,R.drawable.phone1,R.drawable.phone2,R.drawable.phone3,R.drawable.phone4};

    public SearchResultAdapter(RecyclerView recyclerView, Context mContext, List<ProductModel> mCategoryList) {
        this.recyclerView = recyclerView;
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

        model = mCategoryList.get(position);
             //  int imgPos = images[position];
        holder.productName.setText(model.getProductName());
        holder.productCategory.setText(model.getProductCategory());
        holder.productPrice.setText(model.getProductPrice());

        Glide.with(mContext).load(model.getProductImage()).into(holder.productImage);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(mContext, ProductDescriptionActivity.class);
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

                mContext.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{

        private TextView productName,productCategory,productPrice;
        private ImageView productImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.productName);
            productCategory = itemView.findViewById(R.id.productDescription);
            productPrice = itemView.findViewById(R.id.productPrice);
            productImage = itemView.findViewById(R.id.productIv);

        }
    }
}
