package com.erp.distribution.sfa.master.Customer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.erp.distribution.R;
import com.erp.distribution.sfa.model.FCustomer;

import java.text.SimpleDateFormat;

public class CustomerAdapter extends ListAdapter<FCustomer, CustomerAdapter.FCustomerHolder>{
    private OnItemClickListener listener;
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
    final SparseBooleanArray selectedItems = new SparseBooleanArray();
    private int currentSelectedPos;

    public CustomerAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<FCustomer> DIFF_CALLBACK = new DiffUtil.ItemCallback<FCustomer>() {
        @Override
        public boolean areItemsTheSame(FCustomer oldItem, FCustomer newItem) {
            return oldItem.getId() == newItem.getId();
        }
        @Override
        public boolean areContentsTheSame(FCustomer oldItem, FCustomer newItem) {
            return oldItem.getCustno().equals(newItem.getCustno()) &&
                    oldItem.getCustname().equals(newItem.getCustname());
        }
    };
    public FCustomer getFCustomerAt(int position) {
        return getItem(position);
    }

    @NonNull
    @Override
    public FCustomerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_rv_item_template1, parent, false);
        return new FCustomerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FCustomerHolder holder, int position) {
        FCustomer currentNote = getItem(position);
        holder.bind(currentNote);

    }


    class FCustomerHolder extends RecyclerView.ViewHolder {
        TextView txtUser;
        TextView txtIcon;
        TextView txtSubject;
        TextView txtPreview;
        TextView txtDate;
        ImageView imgStar;

        public FCustomerHolder(View itemView) {
            super(itemView);
            txtUser = itemView.findViewById(R.id.txt_user);
            txtIcon = itemView.findViewById(R.id.txt_icon);
            txtSubject = itemView.findViewById(R.id.txt_subject);
            txtPreview = itemView.findViewById(R.id.txt_preview);
            txtDate = itemView.findViewById(R.id.txt_date);
            imgStar = itemView.findViewById(R.id.img_star);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem((position)));
                    }

                }
            });
        }

        void bind(FCustomer email) {
            int hash = email.getCustname().hashCode();

            txtIcon.setText(String.valueOf(email.getCustname().trim().charAt(0)));
            txtIcon.setBackground(oval(Color.rgb(hash, hash / 2, 0), txtIcon));
            txtUser.setText(email.getCustname());
            txtSubject.setText(email.getCustno());
            txtPreview.setText(email.getAddress1() + " " + email.getAddress2() + " " + email.getCity1());
            txtDate.setText(sdf.format(email.getModified()));

            txtUser.setTypeface(Typeface.DEFAULT, email.isUnread() ? Typeface.BOLD : Typeface.NORMAL);
            txtSubject.setTypeface(Typeface.DEFAULT, email.isUnread() ? Typeface.BOLD : Typeface.NORMAL);
            txtDate.setTypeface(Typeface.DEFAULT, email.isUnread() ? Typeface.BOLD : Typeface.NORMAL);

            imgStar.setImageResource(email.isStared()
                    ? R.drawable.ic_star_black_24dp
                    : R.drawable.ic_star_border_black_24dp);

            if (email.isSelected()) {
                txtIcon.setBackground(oval(Color.rgb(26, 115, 233), txtIcon));
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setShape(GradientDrawable.RECTANGLE);
                gradientDrawable.setCornerRadius(32f);
                gradientDrawable.setColor(Color.rgb(232, 240, 253));
                itemView.setBackground(gradientDrawable);
            } else {
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setShape(GradientDrawable.RECTANGLE);
                gradientDrawable.setCornerRadius(32f);
                gradientDrawable.setColor(Color.WHITE);
                itemView.setBackground(gradientDrawable);
            }

            // animation
            if (selectedItems.size() > 0) animate(txtIcon, email);
        }

        private void animate(final TextView view, final FCustomer email) {
            ObjectAnimator oa1 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f);
            final ObjectAnimator oa2 = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f);
            oa1.setInterpolator(new DecelerateInterpolator());
            oa2.setInterpolator(new AccelerateDecelerateInterpolator());
            oa1.setDuration(200);
            oa2.setDuration(200);

            oa1.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    if (email.isSelected())
                        view.setText("\u2713");
                    oa2.start();
                }
            });
            oa1.start();
        }


    }
    private static ShapeDrawable oval(@ColorInt int color, View view) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.setIntrinsicHeight(view.getHeight());
        shapeDrawable.setIntrinsicWidth(view.getWidth());
        shapeDrawable.getPaint().setColor(color);
        return shapeDrawable;
    }
    public interface OnItemClickListener {
        void onItemClick(FCustomer note);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}