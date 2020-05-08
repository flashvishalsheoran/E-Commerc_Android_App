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

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<String> mCategoryList;
    private Integer [] images = {R.drawable.electronics_category,R.drawable.fashion_category,R.drawable.shoe_category,R.drawable.kids_category,R.drawable.house_category};

    public CategoriesAdapter(Context mContext, ArrayList<String> mCategoryList) {
        this.mContext = mContext;
        this.mCategoryList = mCategoryList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.category_single_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int imgPos = images[position];

        holder.categoryTitle.setText(mCategoryList.get(position));
        Glide.with(mContext).load(imgPos).into(holder.categoryImage);

    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView categoryTitle;
        private ImageView categoryImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.categoryNameTv);
            categoryImage = itemView.findViewById(R.id.categoryImageIv);
        }
    }
}
