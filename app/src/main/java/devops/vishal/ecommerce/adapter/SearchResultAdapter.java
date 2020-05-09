package devops.vishal.ecommerce.adapter;

import android.content.Context;
import android.content.Intent;
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
import devops.vishal.ecommerce.activity.ProductDescriptionActivity;
import devops.vishal.ecommerce.models.ProductModel;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<ProductModel> mCategoryList;
    private Integer [] images = {R.drawable.p5,R.drawable.p6,R.drawable.phone1,R.drawable.phone2,R.drawable.phone3,R.drawable.phone4};

    public SearchResultAdapter(Context mContext, ArrayList<ProductModel> mCategoryList) {
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

        ProductModel model = mCategoryList.get(position);

        int imgPos = images[position];
        holder.productName.setText(model.getProductName());
        holder.productCategory.setText(model.getProductCategory());
        holder.productPrice.setText(model.getProductPrice());

        Glide.with(mContext).load(imgPos).into(holder.productImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, ProductDescriptionActivity.class));
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
