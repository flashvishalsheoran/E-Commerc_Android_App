package devops.vishal.ecommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import devops.vishal.ecommerce.R;
import devops.vishal.ecommerce.models.ProductModel;

public class ProductsAdapters extends RecyclerView.Adapter<ProductsAdapters.ViewHolder> {

    private Context mContext;
    private ArrayList<ProductModel> mCategoryList;
    private Integer [] images = {R.drawable.p5,R.drawable.p6,R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4};

    public ProductsAdapters(Context mContext, ArrayList<ProductModel> mCategoryList) {
        this.mContext = mContext;
        this.mCategoryList = mCategoryList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.popular_product_single_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductModel model = mCategoryList.get(position);

        int imgPos = images[position];
        holder.productName.setText(model.getProductName());
        holder.productCategory.setText(model.getProductCategory());
        holder.productPrice.setText(model.getProductPrice());

        Glide.with(mContext).load(imgPos).into(holder.productImage);

    }


    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

   public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView productName,productCategory,productPrice;
        private ImageView productImage;

       public ViewHolder(@NonNull View itemView) {
           super(itemView);

           productName = itemView.findViewById(R.id.productnameTv);
           productCategory = itemView.findViewById(R.id.productCategoryTv);
           productPrice = itemView.findViewById(R.id.productpriceTv);
           productImage = itemView.findViewById(R.id.productImageIv);

       }

   }

}


