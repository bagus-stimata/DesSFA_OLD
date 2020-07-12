package com.erp.distribution.sfa.master.customer_ref;

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


/**
 * Julho, 03 2019
 *
 * @author suporte@moonjava.com.br (Tiago Aguiar).
 */
//class FCustomerAdapter extends RecyclerView.Adapter<FCustomerAdapter.FCustomerViewHolder> implements Filterable {
  public class FCustomerRefAdapter extends ListAdapter<FCustomer, FCustomerRefAdapter.FCustomerViewHolder> {

//    private AdapterView.OnItemClickListener listener;

  SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
  private FCustomerAdapterListener listener;


  final SparseBooleanArray selectedItems = new SparseBooleanArray();
  private int currentSelectedPos;

  public FCustomerRefAdapter() {
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


  interface FCustomerAdapterListener {
    void onItemClick(int position);
    void onItemLongClick(int position);
  }

//  public FCustomerAdapter(List<FCustomer> emails) {
//    this.listFiltered = emails;
//
//
//  }

//  public void submitFreshRvList(List<FCustomer> emails){
//      listSource = new ArrayList<>(emails);
//      listFiltered = new ArrayList<>(emails);
//  }

  public FCustomer getFCustomerAt(int position) {
//        return notes.get(position);
    return getItem(position);
  }
  public void setListener(FCustomerAdapterListener listener) {
    this.listener = listener;
  }

  @NonNull
  @Override
  public FCustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(
            R.layout.adapter_rv_item_template1, parent, false);
    return new FCustomerViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull FCustomerViewHolder holder, final int position) {
//    FCustomer email = listFiltered.get(position);
    FCustomer email = getItem(position);
    holder.bind(email);

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (selectedItems.size() > 0 && listener != null)
          listener.onItemClick(position);
      }
    });

    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View view) {
        if (listener != null)
          listener.onItemLongClick(position);
        return true;
      }
    });

    if (currentSelectedPos == position) currentSelectedPos = -1;
  }

//  @Override
//  public int getItemCount() {
//    return listFiltered.size();
//  }
//  public FCustomer getCustomerAt(int position) {
//        return listFiltered.get(position);
//  }

  
//  void deleteFCustomers() {
//    List<FCustomer> emails = new ArrayList<>();
//    for (FCustomer email : this.listFiltered) {
//      if (email.isSelected())
//        emails.add(email);
//    }
//
//    this.listFiltered.removeAll(emails);
//    notifyDataSetChanged();
//    currentSelectedPos = -1;
//  }

//  void toggleSelection(int position) {
//    currentSelectedPos = position;
//    if (selectedItems.get(position)) {
//      selectedItems.delete(position);
//      listFiltered.get(position).setSelected(false);
//    } else {
//      selectedItems.put(position, true);
//      listFiltered.get(position).setSelected(true);
//    }
//    notifyItemChanged(position);
//  }

  /**
   * Fungsi mengisi Design Layout dengan Data
   */
  class FCustomerViewHolder extends RecyclerView.ViewHolder {

    TextView txtUser;
    TextView txtIcon;
    TextView txtSubject;
    TextView txtPreview;
    TextView txtDate;
    ImageView imgStar;

    FCustomerViewHolder(@NonNull View itemView) {
      super(itemView);
      txtUser = itemView.findViewById(R.id.txt_user);
      txtIcon = itemView.findViewById(R.id.txt_icon);
      txtSubject = itemView.findViewById(R.id.txt_subject);
      txtPreview = itemView.findViewById(R.id.txt_preview);
      txtDate = itemView.findViewById(R.id.txt_date);
      imgStar = itemView.findViewById(R.id.img_star);
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
      if (selectedItems.size() > 0)
        animate(txtIcon, email);
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


}
