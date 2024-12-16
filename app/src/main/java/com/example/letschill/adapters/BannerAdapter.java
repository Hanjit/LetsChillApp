package com.example.letschill.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.letschill.R;
import com.example.letschill.models.BannerData;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerHolder> {

    private List<BannerData> banner;
    private ViewPager2 moviesBannerVP;
    private Context context;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            moviesBannerVP.setCurrentItem(moviesBannerVP.getCurrentItem() + 1, true);
        }
    };

    public BannerAdapter(List<BannerData> banner, ViewPager2 moviesBannerVP) {
        this.banner = banner;
        this.moviesBannerVP = moviesBannerVP;
    }

    @NonNull
    @Override
    public BannerAdapter.BannerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.banner_view, parent, false);
        return new BannerAdapter.BannerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerAdapter.BannerHolder holder, int position) {
        holder.setImage(banner.get(position));
        if (position == banner.size() - 2) {
            moviesBannerVP.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return banner.size();
    }

    public class BannerHolder extends RecyclerView.ViewHolder {
        private ImageView bannerImageIV;
        private TextView bannerInfoTV, bannerDescriptionTV;
        public BannerHolder(@NonNull View itemView) {
            super(itemView);
            bannerImageIV = itemView.findViewById(R.id.bannerImageIV);
            bannerInfoTV = itemView.findViewById(R.id.bannerInfoTV);
            bannerDescriptionTV = itemView.findViewById(R.id.bannerDescriptionTV);
        }

        void setImage(BannerData bannerData) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.transform(new CenterCrop());
            Glide.with(context)
                    .load(bannerData.getImage())
                    .apply(requestOptions)
                    .into(bannerImageIV);
            bannerInfoTV.setText(bannerData.getYear() + " . " + bannerData.getAge() + " . " + bannerData.getTime());
            bannerDescriptionTV.setText(bannerData.getDescription());
        }

    }
}
